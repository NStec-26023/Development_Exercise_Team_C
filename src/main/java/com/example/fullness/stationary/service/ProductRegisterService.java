package com.example.fullness.stationary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.fullness.stationary.controller.form.ProductRegisterForm;
import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.entity.ProductStock;
import com.example.fullness.stationary.repository.ProductRegisterRepository;

@Service
@Transactional
public class ProductRegisterService {

    @Autowired
    // private ProductRegisterRepository productRegisterRepository;

    private final ProductRegisterRepository productRegisterRepository;

    // SpringがRepositoryを自動でこのクラスにセット
    @Autowired
    public ProductRegisterService(ProductRegisterRepository productRegisterRepository) {
        this.productRegisterRepository = productRegisterRepository;
    }

    /**
     * プルダウン用のカテゴリ一覧を取得する
     */
    public List<ProductCategory> getAllCategories() {
        return productRegisterRepository.findAllCategories();
    }

    /**
     * 商品情報と在庫数をそれぞれのテーブルに連続で登録する
     */
    public void saveProduct(ProductRegisterForm form, String imageUrl) {
        // --- 1段階目：商品情報のEntity（Product）組み立てと保存 ---
        Product product = new Product();
        product.setCatId(form.getCatId()); // カテゴリIDをセット
        product.setName(form.getName()); // 商品名をセット
        product.setPrice(form.getPrice()); // 価格をセット
        product.setImageUrl(imageUrl); // コントローラー側で処理した画像のURL・パスをセット

        // 商品テーブル（product）にインサートを実行
        productRegisterRepository.insertProduct(product);

        // --- 2段階目：自動生成されたIDを取得して在庫数を保存 ---
        Integer generatedProductId = product.getProId();
        Integer stockCount = form.getQuantity(); // Formから在庫数を取り出す

        // ProductStockオブジェクトを新しく作る（組み立てる）
        ProductStock productStock = new ProductStock();
        productStock.setProId(generatedProductId); // 自動生成された商品IDをセット
        productStock.setQuantity(stockCount); // 在庫数をセット

        // 取得した商品IDを紐付けて、在庫数テーブル（product_stock）にインサートを実行
        productRegisterRepository.insertProductStock(productStock);
    }

}