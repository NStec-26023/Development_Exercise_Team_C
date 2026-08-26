package com.example.fullness.stationary.util;

import java.util.List;
import java.util.ArrayList;

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;

public class TestUtil {

    public static final ProductCategory cat101;
    public static final ProductCategory cat102;
    public static final ProductCategory cat103;

    public static final Product pro101;
    public static final Product pro102;
    public static final Product pro103;
    public static final Product pro104;
    public static final Product pro105;
    public static final Product pro106;
    public static final Product pro107;
    public static final Product pro108;
    public static final Product pro109;
    public static final Product pro110;
    public static final Product pro111;
    public static final Product pro112;
    public static final Product pro113;
    public static final Product pro114;
    public static final Product pro115;
    public static final Product pro116;
    public static final Product pro117;
    public static final Product pro118;
    public static final Product pro119;
    public static final Product pro120;
    public static final Product pro121;
    public static final Product pro122;

    static {

        cat101 = new ProductCategory();
        cat101.setCatId(101);
        cat101.setName("文房具");

        cat102 = new ProductCategory();
        cat102.setCatId(102);
        cat102.setName("PC雑貨");

        cat103 = new ProductCategory();
        cat103.setCatId(103);
        cat103.setName("ノート・紙雑貨");

        pro101 = new Product();
        pro101.setProId(1);
        pro101.setName("鉛筆(黒)");
        pro101.setPrice(100);
        pro101.setImageUrl("black_pen.jpg");
        pro101.setCatId(101);
        pro101.setDeleteFlg(0);

        pro102 = new Product();
        pro102.setProId(2);
        pro102.setName("油性ボールペン(黒)");
        pro102.setPrice(100);
        pro102.setImageUrl("black_pen_o.jpg");
        pro102.setCatId(101);
        pro102.setDeleteFlg(0);

        pro103 = new Product();
        pro103.setProId(3);
        pro103.setName("水性ボールペン2(黒)");
        pro103.setPrice(220);
        pro103.setImageUrl("black_pen_w.jpg");
        pro103.setCatId(101);
        pro103.setDeleteFlg(0);

        pro104 = new Product();
        pro104.setProId(4);
        pro104.setName("水性ボールペン(青)");
        pro104.setPrice(120);
        pro104.setImageUrl("blue_pen_w.jpeg");
        pro104.setCatId(101);
        pro104.setDeleteFlg(0);

        pro105 = new Product();
        pro105.setProId(5);
        pro105.setName("USB有線式キーボード");
        pro105.setPrice(1400);
        pro105.setImageUrl("keybord2.jpg");
        pro105.setCatId(102);
        pro105.setDeleteFlg(0);

        pro106 = new Product();
        pro106.setProId(6);
        pro106.setName("無線式キーボード");
        pro106.setPrice(1900);
        pro106.setImageUrl("keybord.jpg");
        pro106.setCatId(102);
        pro106.setDeleteFlg(0);

        pro107 = new Product();
        pro107.setProId(7);
        pro107.setName("有線ゲーミングマウス");
        pro107.setPrice(3800);
        pro107.setImageUrl("mouse_a.jpg");
        pro107.setCatId(102);
        pro107.setDeleteFlg(0);

        pro108 = new Product();
        pro108.setProId(8);
        pro108.setName("有線光学式マウス");
        pro108.setPrice(500);
        pro108.setImageUrl("mouse_b.jpg");
        pro108.setCatId(102);
        pro108.setDeleteFlg(0);

        pro109 = new Product();
        pro109.setProId(9);
        pro109.setName("ワイヤレスマウス");
        pro109.setPrice(900);
        pro109.setImageUrl("mouse_c.jpg");
        pro109.setCatId(102);
        pro109.setDeleteFlg(0);

        pro110 = new Product();
        pro110.setProId(10);
        pro110.setName("ワイヤレストラックボール");
        pro110.setPrice(1300);
        pro110.setImageUrl("mouse_d.jpeg");
        pro110.setCatId(102);
        pro110.setDeleteFlg(0);

        pro111 = new Product();
        pro111.setProId(11);
        pro111.setName("水性ボールペン(赤)");
        pro111.setPrice(120);
        pro111.setImageUrl("red_pen_w.jpg");
        pro111.setCatId(101);
        pro111.setDeleteFlg(0);

        pro112 = new Product();
        pro112.setProId(12);
        pro112.setName("水性ボールペン(黒)");
        pro112.setPrice(120);
        pro112.setImageUrl("black_pen_w.jpg");
        pro112.setCatId(101);
        pro112.setDeleteFlg(0);

        pro113 = new Product();
        pro113.setProId(13);
        pro113.setName("マーカー(緑)");
        pro113.setPrice(150);
        pro113.setImageUrl("green_maker.jpg");
        pro113.setCatId(101);
        pro113.setDeleteFlg(0);

        pro114 = new Product();
        pro114.setProId(14);
        pro114.setName("マーカー(黄)");
        pro114.setPrice(150);
        pro114.setImageUrl("yellow_maker.jpg");
        pro114.setCatId(101);
        pro114.setDeleteFlg(0);

        pro115 = new Product();
        pro115.setProId(15);
        pro115.setName("マーカー(青)");
        pro115.setPrice(150);
        pro115.setImageUrl("blue_maker.jpg");
        pro115.setCatId(101);
        pro115.setDeleteFlg(0);

        pro116 = new Product();
        pro116.setProId(16);
        pro116.setName("筆ペン(黒)");
        pro116.setPrice(150);
        pro116.setImageUrl("black_fudepen.jpg");
        pro116.setCatId(101);
        pro116.setDeleteFlg(0);

        pro117 = new Product();
        pro117.setProId(17);
        pro117.setName("筆ペン(赤)");
        pro117.setPrice(150);
        pro117.setImageUrl("red_fudepen.jpg");
        pro117.setCatId(101);
        pro117.setDeleteFlg(0);

        pro118 = new Product();
        pro118.setProId(18);
        pro118.setName("ノート(青)");
        pro118.setPrice(160);
        pro118.setImageUrl("blue_note.jpg");
        pro118.setCatId(101);
        pro118.setDeleteFlg(0);

        pro119 = new Product();
        pro119.setProId(19);
        pro119.setName("ノート(緑)");
        pro119.setPrice(160);
        pro119.setImageUrl("green_note.jpg");
        pro119.setCatId(101);
        pro119.setDeleteFlg(0);

        pro120 = new Product();
        pro120.setProId(20);
        pro120.setName("ノート(橙)");
        pro120.setPrice(160);
        pro120.setImageUrl("orange_note.jpg");
        pro120.setCatId(101);
        pro120.setDeleteFlg(0);

        pro121 = new Product();
        pro121.setProId(21);
        pro121.setName("ノート(紫)");
        pro121.setPrice(160);
        pro121.setImageUrl("purple_note.jpg");
        pro121.setCatId(101);
        pro121.setDeleteFlg(0);

        pro122 = new Product();
        pro122.setProId(22);
        pro122.setName("ノート(白)");
        pro122.setPrice(160);
        pro122.setImageUrl("white_note.jpg");
        pro122.setCatId(101);
        pro122.setDeleteFlg(0);
    }

    public static List<ProductCategory> getExpectedCategories() {
        List<ProductCategory> categories = new ArrayList<ProductCategory>();
        categories.add(cat101);
        categories.add(cat102);
        categories.add(cat103);
        return categories;
    }

    public static List<Product> getExpectedAllProducts() {
        List<Product> products = new ArrayList<Product>();
        products.add(pro101);
        products.add(pro102);
        products.add(pro103);
        products.add(pro104);
        products.add(pro105);
        products.add(pro106);
        products.add(pro107);
        products.add(pro108);
        products.add(pro109);
        products.add(pro110);
        products.add(pro111);
        products.add(pro112);
        products.add(pro113);
        products.add(pro114);
        products.add(pro115);
        products.add(pro116);
        products.add(pro117);
        products.add(pro118);
        products.add(pro119);
        products.add(pro120);
        products.add(pro121);
        products.add(pro122);
        return products;
    }

    public static List<Product> getExpectedProductsByCatId102() {
        List<Product> products = new ArrayList<Product>();
        products.add(pro105);
        products.add(pro106);
        products.add(pro107);
        products.add(pro108);
        products.add(pro109);
        products.add(pro110);
        return products;
    }
}
