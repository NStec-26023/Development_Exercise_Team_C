package com.example.fullness.stationary.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
// import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.fullness.stationary.controller.form.ProductRegisterForm;
import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.service.ProductRegisterService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/product")
@SessionAttributes(types = ProductRegisterForm.class)
public class ProductRegisterController {

    @Autowired
    ProductRegisterService productRegisterService;

    // // 画像ファイルをサーバー内に保存するためのフォルダ指定
    // private final String UPLOAD_DIR =
    // "src/main/java/com/example/fullness/stationary/resources/static/images/";

    // // 許可する画像形式（Wordファイルなどはここに含まれないためエラーになります）
    // private final List<String> ALLOWED_TYPES = Arrays.asList("image/jpeg",
    // "image/png", "image/jpg");

    // @Autowired
    // public ProductRegisterController(ProductRegisterService
    // productRegisterService) {
    // this.productRegisterService = productRegisterService;
    // }

    @ModelAttribute("form")
    public ProductRegisterForm setUpForm() {
        return new ProductRegisterForm();
    }

    /**
     * 1. 入力画面表示（GET）
     */
    @GetMapping("/add")
    public String showAddForm(Model model, @ModelAttribute("form") ProductRegisterForm form) {
        // もしリダイレクト元からエラー情報（BindingResult）が届いていたら、Modelに入れ直す
        if (model.containsAttribute("org.springframework.validation.BindingResult.form")) {
            // これにより、画面にエラーメッセージが出るようになります
        }

        List<ProductCategory> categories = productRegisterService.getAllCategories();
        model.addAttribute("categories", categories);
        return "admin/product/add_form";
    }

    /**
     * 2. 確認処理（POST）➔ バリデーション＆画像保存 ➔ 成功したらリダイレクト
     */
    @PostMapping("/add/input")
    public String validateForConfirm(@Valid @ModelAttribute("form") ProductRegisterForm form,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes,
            HttpSession httpSession) { // ⭕ 引数にこれを1つ追加します！

        // ① 入力項目の入力チェック
        if (result.hasErrors()) {
            // ⭕ エラー内容と入力データをリダイレクト先（/add）に引き継ぐ設定
            model.addAttribute("fields",
                    result);
            model.addAttribute("form", form);
            return "admin/product/add_form";
        }

        httpSession.setAttribute("form", form);

        // ② 画像ファイルのチェックと保存処理
        // MultipartFile file = form.getImage();
        // if (file != null && !file.isEmpty()) {
        // String contentType = file.getContentType();
        // if (contentType == null || !ALLOWED_TYPES.contains(contentType)) {
        // result.rejectValue("image", "error.image",
        // "画像ファイル（JPEG/PNG/GIF）のみアップロード可能です。");
        // redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.form",
        // result);
        // redirectAttributes.addFlashAttribute("form", form);
        // return "redirect:/admin/product/add";
        // }

        // try {
        // byte[] bytes = file.getBytes();
        // String fileName = file.getOriginalFilename();
        // Path path = Paths.get(UPLOAD_DIR + fileName);
        // Files.write(path, bytes);
        // form.setImageUrl("/images/" + fileName);

        // } catch (IOException e) {
        // result.rejectValue("image", "error.image", "ファイルの保存に失敗しました。");
        // redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.form",
        // result);
        // redirectAttributes.addFlashAttribute("form", form);
        // return "redirect:/admin/product/add";
        // }
        // } else {
        // // 画像ファイルが必須の場合のエラー
        // result.rejectValue("image", "error.image", "画像ファイルを選択してください。");
        // redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.form",
        // result);
        // redirectAttributes.addFlashAttribute("form", form);
        // return "redirect:/admin/product/add";
        // }

        // 全てのチェック成功：確認画面用の「GET用URL」へリダイレクト（PRGパターン）
        return "redirect:/admin/product/add/confirm";
    }

    /**
     * 3. 確認画面表示（GET）
     */
    @GetMapping("/add/confirm")
    // ⭕ 引数に Model model を追加します
    public String showConfirmForm(@ModelAttribute("form") ProductRegisterForm form, Model model) {
        if (form.getName() == null) {
            return "redirect:/admin/product/add";
        }

        // ⭕ ER図のルール（cat_id ➔ catId）に基づいてカテゴリ名を探します
        String selectedCategoryName = "未選択";
        List<ProductCategory> categories = productRegisterService.getAllCategories();
        for (ProductCategory cat : categories) {
            // cat.getCatId() と form.getCatId() を比較
            if (cat.getCatId() != null && cat.getCatId().equals(form.getCatId())) {
                selectedCategoryName = cat.getName(); // 一致するカテゴリ名を発見！
                break;
            }
        }

        // ⭕ 見つけたカテゴリ名を「categoryName」という名前で直接画面に送る
        model.addAttribute("categoryName", selectedCategoryName);

        return "admin/product/add_confirm";
    }

    /**
     * 4. 登録実行処理（POST）➔ 成功したらリダイレクト
     */
    @PostMapping("/add/confirm/input")
    public String executeComplete(
            @ModelAttribute("form") ProductRegisterForm form,
            @RequestParam("action") String action) { // ⭕ HTMLの name="action" を受け取ります！

        if (form.getName() == null) {
            return "redirect:/admin/product/add";
        }

        // ⭕ 「戻る」ボタンが押された場合：入力画面（GET: /add）にリダイレクト
        if ("back".equals(action)) {
            return "redirect:/admin/product/add";
        }

        // ⭕ 「完了」ボタンが押された場合：保存処理をして完了画面（GET: /complete）へ
        // Serviceを呼び出して、商品情報と在庫数の連続インサートを実行
        // productRegisterService.saveProduct(form, form.getImageUrl());
        productRegisterService.saveProduct(form);

        // 登録成功：完了画面用の「GET用URL」へリダイレクト
        return "redirect:/admin/product/complete";
    }

    /**
     * 5. 完了画面表示（GET）
     */
    @GetMapping("/complete")
    public String showCompleteForm(@ModelAttribute("form") ProductRegisterForm form,
            SessionStatus sessionStatus, Model model) {
        if (form.getName() == null) {
            return "redirect:/admin/product/add";
        }

        model.addAttribute("completeMsg", form.getName());

        // 完了画面を表示する直前でセッションをクリア（二重登録防止）
        sessionStatus.setComplete();

        return "admin/product/add_complete";

    }
}