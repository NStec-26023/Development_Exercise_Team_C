package com.example.fullness.stationary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.fullness.stationary.entity.Product;
import jakarta.servlet.http.HttpSession;
import com.example.fullness.stationary.service.ProductDeleteService;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/admin/product/delete")
public class ProductDeleteController {

    @Autowired
    ProductDeleteService productDeleteService;

    // BURL007:商品削除（確認）画面表示
    // URL:/admin/product/delete/{proId}
    @GetMapping("/{proId}")
    public String enter(@PathVariable("proId") int proId, Model model, RedirectAttributes redirectAttributes) {
        try {
            Product product = productDeleteService.getProductForDelete(proId);

            if (product == null) {
                redirectAttributes.addFlashAttribute("errorMessage", "指定された商品は存在しません");
                return "redirect:/admin/product"; // BURL006（商品一覧・検索）へ
            }

            model.addAttribute("product", product);
            model.addAttribute("proId", proId);
            model.addAttribute("title", "商品削除（確認）");
            return "admin/product/delete/001";

        } catch (Exception e) {
            model.addAttribute("errorMessage", "商品情報の取得に失敗しました");
            return "/admin/error"; // BURL000 エラー画面へ
        }
    }

    // BURL007:[完了]ボタン押下時の削除処理実行
    // URL:/admin/product/delete/{proId}
    @PostMapping("/{proId}")
    public String result(@PathVariable("proId") int proId, RedirectAttributes redirectAttributes, HttpSession session) {
        try {
            Product product = productDeleteService.getProductForDelete(proId);
            if (product == null) {
                redirectAttributes.addFlashAttribute("errorMessage", "指定された商品は存在しません");
                return "redirect:/admin/product/search";
            }

            // 処理仕様：delete_flg を 1 に更新
            boolean success = productDeleteService.deleteProduct(proId);
            if (!success) {
                session.setAttribute("errorMessage", "データの取得に失敗しました");
                return "redirect:/admin/product/delete/complete";
            }

            session.setAttribute("deletedProductName", product.getName());
            session.setAttribute("isDeleteVerified", true);
            return "redirect:/admin/product/delete/complete"; // BURL008

        } catch (Exception e) {
            session.setAttribute("errorMessage", "データの取得に失敗しました");
            return "redirect:/admin/product/delete/complete";
        }
    }

    // BURL008: 商品削除（完了）画面表示
    // URL: /admin/product/delete/complete
    @GetMapping("/complete")
    public String complete(HttpSession session, Model model) {
        // セッションからデータを取り出す
        Object isDeleteVerified = session.getAttribute("isDeleteVerified");
        String deletedProductName = (String) session.getAttribute("deletedProductName");
        String errorMessage = (String) session.getAttribute("errorMessage");

        // 一度読み取ったら、次回の表示のためにセッションをクリア（使い捨て化）
        session.removeAttribute("isDeleteVerified");
        session.removeAttribute("deletedProductName");
        session.removeAttribute("errorMessage");

        // 例外処理：エラー引き継ぎ時
        if (errorMessage != null && !errorMessage.isEmpty()) {
            model.addAttribute("exceptionMessage", "データの取得に失敗しました");
        }
        if (isDeleteVerified == null || !(Boolean) isDeleteVerified) {
            return "redirect:/admin?error=invalid_access"; // BURL001 管理トップへ戻す
        }

        model.addAttribute("title", "商品削除（完了）");
        model.addAttribute("completeMsg", "商品名 " + deletedProductName + " を削除しました。");

        // お手本の "pagination/list" と同じフォルダ配置ルールに統一
        return "product/delete_complete";
    }
}