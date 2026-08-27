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
        Product productStock = productDeleteService.getProductForDelete(id);
        model.addAttribute("product", productStock);
        // 削除確認画面のHTMLを表示
        return "admin/product/delete_confirm";
    }

    // BURL007:[完了]ボタン押下時の削除処理実行
    // URL:/admin/product/delete/{id}
    @PostMapping("/delete/{id}")
    public String deleteExecute(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes,
            HttpSession session) {
        try {
            // 削除対象の商品情報を取得（商品名表示用）
            Product product = productDeleteService.getProductForDelete(id);
            if (product == null) {
                redirectAttributes.addFlashAttribute("errorMessage", "指定された商品は存在しません");
                return "redirect:/admin/product";
            }
            // 削除処理の実行（delete_flg を 1 に更新）
            boolean success = productDeleteService.deleteProduct(id);
            if (!success) {
                session.setAttribute("errorMessage", "データの取得に失敗しました");
                return "redirect:/admin/product/delete/complete";
            }
            // 完了画面へ渡すためのデータをセッションに保存
            session.setAttribute("deletedProductName", product.getName());
            session.setAttribute("isDeleteVerified", true);
            return "redirect:/admin/product/delete/complete"; // BURL008へリダイレクト
        } catch (Exception e) {
            session.setAttribute("errorMessage", "データの取得に失敗しました");
            return "redirect:/admin/product/delete/complete";
        }
    }

    // BURL008: 商品削除（完了）画面表示
    // URL: /admin/product/delete/complete
    @GetMapping("/delete/complete")
    public String showDeleteComplete(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        // セッションからデータを取り出す
        Object isDeleteVerified = session.getAttribute("isDeleteVerified");
        String deletedProductName = (String) session.getAttribute("deletedProductName");
        String errorMessage = (String) session.getAttribute("errorMessage");

        // セッションクリア
        session.removeAttribute("isDeleteVerified");
        session.removeAttribute("deletedProductName");
        session.removeAttribute("errorMessage");

        // データ取得エラー発生時
        if (errorMessage != null && !errorMessage.isEmpty()) {
            redirectAttributes.addFlashAttribute("exceptionMessage", "データの取得に失敗しました");
            return "redirect:/admin/error";
        }

        // 直接アクセス禁止
        if (isDeleteVerified == null || !(Boolean) isDeleteVerified) {
            return "redirect:/admin/error";
        }
        model.addAttribute("productName", deletedProductName);
        return "admin/product/delete_complete";
    }
}