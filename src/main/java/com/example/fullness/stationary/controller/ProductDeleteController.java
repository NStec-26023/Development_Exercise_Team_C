package com.example.fullness.stationary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductStock;

import jakarta.servlet.http.HttpSession;
import com.example.fullness.stationary.service.ProductDeleteService;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/admin/product")
public class ProductDeleteController {

    @Autowired
    ProductDeleteService productDeleteService;

    // BURL007:商品削除（確認）画面表示
    // URL:/admin/product/delete/{proId}
    @GetMapping("/delete/{id}")
    public String showDeleteConfirm(@PathVariable("id") Integer id, Model model) {
        // Serviceから削除確認用のデータを取得
        ProductStock productStock = productDeleteService.getProductForDelete(id);
        model.addAttribute("product", productStock);

        // 削除確認画面のHTMLを表示する（例: admin/product/delete.html）
        return "admin/product/delete_confirm";
    }
    // // BURL007:商品削除（確認）画面表示
    // // URL:/admin/product/delete/{proId}
    // @GetMapping("/delete/{proId}")
    // public String enter(@PathVariable("proId") Integer proId, Model model,
    // RedirectAttributes redirectAttributes) {
    // try {
    // ProductStock product = productDeleteService.getProductForDelete(proId);

    // if (product == null) {
    // redirectAttributes.addFlashAttribute("errorMessage", "指定された商品は存在しません");
    // return "redirect:/admin/product"; // BP006: 商品検索画面へ
    // }

    // model.addAttribute("product", product);
    // model.addAttribute("proId", proId);
    // model.addAttribute("title", "商品削除（確認）");
    // return "admin/product/delete_confirm";

    // } catch (Exception e) {
    // e.printStackTrace();
    // redirectAttributes.addFlashAttribute("exceptionMessage", "データの取得に失敗しました");
    // return "redirect:/admin/error"; // BP000: エラー画面へ
    // }
    // }

    // BURL007:[完了]ボタン押下時の削除処理実行
    // URL:/admin/product/{proId}
    // @PostMapping("/{proId}")
    // public String result(@PathVariable("proId") int proId, RedirectAttributes
    // redirectAttributes, HttpSession session) {
    // try {
    // Product product = productDeleteService.getProductForDelete(proId);
    // if (product == null) {
    // redirectAttributes.addFlashAttribute("errorMessage", "指定された商品は存在しません");
    // return "redirect:/admin/product"; // BP006: 商品検索画面へ
    // }

    // // 処理仕様：delete_flg を 1 に更新
    // boolean success = productDeleteService.deleteProduct(proId);
    // if (!success) {
    // session.setAttribute("errorMessage", "データの取得に失敗しました");
    // return "redirect:/admin/product/delete/complete";
    // }

    // session.setAttribute("deletedProductName", product.getName());
    // session.setAttribute("isDeleteVerified", true);
    // return "redirect:/admin/product/delete/complete"; // BURL008

    // } catch (Exception e) {
    // session.setAttribute("errorMessage", "データの取得に失敗しました");
    // return "redirect:/admin/product/delete/complete";
    // }
    // }

    // BURL008: 商品削除（完了）画面表示
    // URL: /admin/product/delete/complete
    // @GetMapping("/complete")
    // public String complete(HttpSession session, Model model, RedirectAttributes
    // redirectAttributes) {
    // // セッションからデータを取り出す
    // Object isDeleteVerified = session.getAttribute("isDeleteVerified");
    // String deletedProductName = (String)
    // session.getAttribute("deletedProductName");
    // String errorMessage = (String) session.getAttribute("errorMessage");

    // // セッションクリア
    // session.removeAttribute("isDeleteVerified");
    // session.removeAttribute("deletedProductName");
    // session.removeAttribute("errorMessage");

    // // データ取得エラー発生時
    // if (errorMessage != null && !errorMessage.isEmpty()) {
    // // redirect先の[BURL000]で[BMSG010]を表示させるため、FlashAttributeに格納する
    // redirectAttributes.addFlashAttribute("exceptionMessage", "データの取得に失敗しました");
    // return "redirect:/admin/error";
    // }

    // // 直接アクセス禁止
    // if (isDeleteVerified == null || !(Boolean) isDeleteVerified) {
    // return "redirect:/admin/error";
    // }

    // // 【仕様書No.10, 14 正常系】
    // model.addAttribute("title", "商品削除（完了）");
    // model.addAttribute("completeMsg", "商品名 " + deletedProductName + " を削除しました。");

    // return "product/delete/complete";
}
