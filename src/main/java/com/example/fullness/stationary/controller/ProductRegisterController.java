package com.example.fullness.stationary.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.example.fullness.stationary.controller.form.ProductRegisterForm;
import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.service.ProductRegisterService;

@Controller
@RequestMapping("/admin/product")
@SessionAttributes
public class ProductRegisterController {

    private final ProductRegisterService productRegisterService;

    // 画像ファイルをサーバー内に保存するためのフォルダ指定
    private final String UPLOAD_DIR = "src/main/java/com/example/fullness/stationary/resources/static/images/";

    // 許可する画像形式（Wordファイルなどはここに含まれないためエラーになります）
    private final List<String> ALLOWED_TYPES = Arrays.asList("image/jpeg", "image/png", "image/gif");

    @Autowired
    public ProductRegisterController(ProductRegisterService productRegisterService) {
        this.productRegisterService = productRegisterService;
    }

    @ModelAttribute("productRegisterForm")
    public ProductRegisterForm setUpForm() {
        return new ProductRegisterForm();
    }

    /**
    * 1. 入力画面表示（GET）
    */
    @GetMapping("/add")
    public String showAddForm(Model model, @ModelAttribute("productRegisterForm") ProductRegisterForm form) { // プルダウン用のカテゴリ一覧をDBから取得してModelに格納
        List<ProductCategory> categories = productRegisterService.getAllCategories();
        model.addAttribute("categories", categories); // ★実際の入力画面HTMLの配置パス（templates/以降）に書き換えてください
        return "admin/product/add"
    }

    /**
    * 2. 確認処理（POST）➔ バリデーション＆画像保存 ➔ 成功したらリダイレクト
    */
    @PostMapping("/confirm/validate")
    public String validateForConfirm(@Validated @ModelAttribute("productRegisterForm") ProductRegisterForm form, BindingResult result, Model model) { // ① 入力項目の入力チェック
        if (result.hasErrors()) {
        model.addAttribute("categories", productRegisterService.getAllCategories());
        return "admin/product/add"
    }

    // ② 画像ファイルのチェックと保存処理
    MultipartFile file = form.getImage();
    if (file != null && !file.isEmpty()) {
    String contentType = file.getContentType(); // MIMEタイプチェック（Wordファイルなどはここでエラーになります）
    if (contentType == null || !ALLOWED_TYPES.contains(contentType)) { 
        result.rejectValue("image", "error.image", "画像ファイル（JPEG/PNG/GIF）のみアップロード可能です。");
        model.addAttribute("categories", productRegisterService.getAllCategories());
        return "admin/product/add"
    }

try {
// ファイルを指定のフォルダ（static/images/）に保存
byte[] bytes = file.getBytes();
String fileName = file.getOriginalFilename(); Path path = Paths.get(UPLOAD_DIR + fileName); Files.write(path, bytes); // DBの image_url カラムに入れるためのURLパスをFormに記憶させる
form.setImageUrl("/images/" + fileName);

} catch (IOException e) {
result.rejectValue("image", "error.image", "ファイルの保存に失敗しました。");
model.addAttribute("categories", productRegisterService.getAllCategories());
return "admin/product/add"
}
} else {
// 画像ファイルが必須の場合のエラー（任意ならこの else ブロックは削除してください）
result.rejectValue("image", "error.image", "画像ファイルを選択してください。");
model.addAttribute("categories", productRegisterService.getAllCategories());
return "admin/product/add"
}

// 全てのチェック成功：確認画面用の「GET用URL」へリダイレクト（PRGパターン）
return "redirect:/admin/product/confirm"
}

/**
* 3. 確認画面表示（GET）
*/
@GetMapping("/confirm")
public String showConfirmForm(@ModelAttribute("productRegisterForm") ProductRegisterForm form) { if (form.getProductName() == null) { return "redirect:/admin/product/add"
}
// ★実際の確認画面HTMLの配置パス（templates/以降）に書き換えてください
return "admin/product/confirm"
}

/**
* 4. 登録実行処理（POST）➔ 成功したらリダイレクト
*/
@PostMapping("/complete/execute")
public String executeComplete(@ModelAttribute("productRegisterForm") ProductRegisterForm form) { if (form.getProductName() == null) { return "redirect:/admin/product/add"
}

// Serviceを呼び出して、商品情報と在庫数の連続インサートを実行
productRegisterService.saveProduct(form, form.getImageUrl());

// 登録成功：完了画面用の「GET用URL」へリダイレクト（PRGパターン）
return "redirect:/admin/product/complete"
}

/**
* 5. 完了画面表示（GET）
*/
@GetMapping("/complete")
public String showCompleteForm(@ModelAttribute("productRegisterForm") ProductRegisterForm form, SessionStatus sessionStatus, Model model) { if (form.getProductName() == null) { return "redirect:/admin/product/add"
}

model.addAttribute("completeMsg", "商品名「" + form.getProductName() + "」を登録しました。");

// 完了画面を表示する直前でセッションをクリア（二重登録防止）
sessionStatus.setComplete();

// ★実際の完了画面HTMLの配置パス（templates/以降）に書き換えてください
return "admin/product/complete"

}
}