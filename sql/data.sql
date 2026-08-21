
/* product_categoryテーブルにデータを挿入 */
INSERT INTO product_category (cat_id, name) VALUES (nextval('seq_catno'), '文房具');
INSERT INTO product_category (cat_id, name) VALUES (nextval('seq_catno'), 'PC雑貨');
INSERT INTO product_category (cat_id, name) VALUES (nextval('seq_catno'), 'ノート・紙雑貨');


/* productテーブルにデータを挿入 */
INSERT INTO product (pro_id, name, price, image_url, cat_id, delete_flg) VALUES (nextval('seq_prono'), '鉛筆(黒)', '100', 'black_pen.jpg', '101', '0');
INSERT INTO product (pro_id, name, price, image_url, cat_id, delete_flg) VALUES (nextval('seq_prono'), '油性ボールペン(黒)', '100', 'black_pen_o.jpg', '101', '0');
INSERT INTO product (pro_id, name, price, image_url, cat_id, delete_flg) VALUES (nextval('seq_prono'), '水性ボールペン2(黒)', '220', 'black_pen_w.jpg', '101', '0');
INSERT INTO product (pro_id, name, price, image_url, cat_id, delete_flg) VALUES (nextval('seq_prono'), '水性ボールペン(青)', '120', 'blue_pen_w.jpeg', '101', '1');
INSERT INTO product (pro_id, name, price, image_url, cat_id, delete_flg) VALUES (nextval('seq_prono'), 'USB有線式キーボード', '1400', 'keybord2.jpg', '102', '0');
INSERT INTO product (pro_id, name, price, image_url, cat_id, delete_flg) VALUES (nextval('seq_prono'), '無線式キーボード', '1900', 'keybord.jpg', '102', '0');
INSERT INTO product (pro_id, name, price, image_url, cat_id, delete_flg) VALUES (nextval('seq_prono'), '有線ゲーミングマウス', '3800', 'mouse_a.jpg', '102', '0');
INSERT INTO product (pro_id, name, price, image_url, cat_id, delete_flg) VALUES (nextval('seq_prono'), '有線光学式マウス', '500', 'mouse_b.jpg', '102', '0');
INSERT INTO product (pro_id, name, price, image_url, cat_id, delete_flg) VALUES (nextval('seq_prono'), 'ワイヤレスマウス', '900', 'mouse_c.jpg', '102', '0');
INSERT INTO product (pro_id, name, price, image_url, cat_id, delete_flg) VALUES (nextval('seq_prono'), 'ワイヤレストラックボール', '1300', 'mouse_d.jpeg', '102', '0');
INSERT INTO product (pro_id, name, price, image_url, cat_id, delete_flg) VALUES (nextval('seq_prono'), '水性ボールペン(赤)', '120', 'red_pen_w.jpg', '101', '0');
INSERT INTO product (pro_id, name, price, image_url, cat_id, delete_flg) VALUES (nextval('seq_prono'), '水性ボールペン(黒)', '120', 'black_pen_w.jpg', '101', '0');
INSERT INTO product (pro_id, name, price, image_url, cat_id, delete_flg) VALUES (nextval('seq_prono'), 'マーカー(緑)', '150', 'green_maker.jpg', '101', '0');
INSERT INTO product (pro_id, name, price, image_url, cat_id, delete_flg) VALUES (nextval('seq_prono'), 'マーカー(黄)', '150', 'yellow_maker.jpg', '101', '0');
INSERT INTO product (pro_id, name, price, image_url, cat_id, delete_flg) VALUES (nextval('seq_prono'), 'マーカー(青)', '150', 'blue_maker.jpg', '101', '0');
INSERT INTO product (pro_id, name, price, image_url, cat_id, delete_flg) VALUES (nextval('seq_prono'), '筆ペン(黒)', '150', 'black_fudepen.jpg', '101', '0');
INSERT INTO product (pro_id, name, price, image_url, cat_id, delete_flg) VALUES (nextval('seq_prono'), '筆ペン(赤)', '150', 'red_fudepen.jpg', '101', '0');
INSERT INTO product (pro_id, name, price, image_url, cat_id, delete_flg) VALUES (nextval('seq_prono'), 'ノート(青)', '160', 'blue_note.jpg', '101', '0');
INSERT INTO product (pro_id, name, price, image_url, cat_id, delete_flg) VALUES (nextval('seq_prono'), 'ノート(緑)', '160', 'green_note.jpg', '101', '0');
INSERT INTO product (pro_id, name, price, image_url, cat_id, delete_flg) VALUES (nextval('seq_prono'), 'ノート(橙)', '160', 'orange_note.jpg', '101', '0');
INSERT INTO product (pro_id, name, price, image_url, cat_id, delete_flg) VALUES (nextval('seq_prono'), 'ノート(紫)', '160', 'purple_note.jpg', '101', '0');
INSERT INTO product (pro_id, name, price, image_url, cat_id, delete_flg) VALUES (nextval('seq_prono'), 'ノート(白)', '160', 'white_note.jpg', '101', '0');


/* product_stockテーブルにデータを挿入 */
INSERT INTO product_stock (sto_id, quantity, pro_id) VALUES (nextval('seq_stono'), '0', '001');
INSERT INTO product_stock (sto_id, quantity, pro_id) VALUES (nextval('seq_stono'), '15', '002');
INSERT INTO product_stock (sto_id, quantity, pro_id) VALUES (nextval('seq_stono'), '3', '003');
INSERT INTO product_stock (sto_id, quantity, pro_id) VALUES (nextval('seq_stono'), '20', '004');
INSERT INTO product_stock (sto_id, quantity, pro_id) VALUES (nextval('seq_stono'), '50', '005');
INSERT INTO product_stock (sto_id, quantity, pro_id) VALUES (nextval('seq_stono'), '0', '006');
INSERT INTO product_stock (sto_id, quantity, pro_id) VALUES (nextval('seq_stono'), '8', '007');
INSERT INTO product_stock (sto_id, quantity, pro_id) VALUES (nextval('seq_stono'), '120', '008');
INSERT INTO product_stock (sto_id, quantity, pro_id) VALUES (nextval('seq_stono'), '1', '009');
INSERT INTO product_stock (sto_id, quantity, pro_id) VALUES (nextval('seq_stono'), '24', '010');
INSERT INTO product_stock (sto_id, quantity, pro_id) VALUES (nextval('seq_stono'), '45', '011');
INSERT INTO product_stock (sto_id, quantity, pro_id) VALUES (nextval('seq_stono'), '10', '012');