/* departmentテーブルにデータを挿入 */
INSERT INTO department (dept_id, name) VALUES (nextval('seq_deptno'), '総務部');
INSERT INTO department (dept_id, name) VALUES (nextval('seq_deptno'), '営業部');
INSERT INTO department (dept_id, name) VALUES (nextval('seq_deptno'), '開発部');


/* employeeテーブルにデータを挿入 */
INSERT INTO employee (emp_id, name, kana, dept_id) VALUES (nextval('seq_empno'), 'フルネス太郎', 'フルネスタロウ', 101);
INSERT INTO employee (emp_id, name, kana, dept_id) VALUES (nextval('seq_empno'), '佐藤美咲', 'サトウミサキ', 102);
INSERT INTO employee (emp_id, name, kana, dept_id) VALUES (nextval('seq_empno'), '鈴木健太', 'スズキケンタ', 103);
INSERT INTO employee (emp_id, name, kana, dept_id) VALUES (nextval('seq_empno'), '高橋優太', 'タカハシユウタ', 101);


/* employee_accountテーブルにデータを挿入 */
INSERT INTO employee_account (acc_id, name, password, emp_id) VALUES (nextval('seq_accno'), 'apple1', '$2a$12$LyuFaL5qBtmBQV2XLJIyPOToKoTjZGTbUqhHu2Y.2Usjx3Yix.rnO', 1002);
INSERT INTO employee_account (acc_id, name, password, emp_id) VALUES (nextval('seq_accno'), 'dog1234', '$2a$12$JKzJsvhJFrDlxaB8mSY.EeaTThHGrF0uwZuNoNr6EhMrvgoyXit3e', 1003);


/* product_categoryテーブルにデータを挿入 */
INSERT INTO
    product_category (cat_id, name)
VALUES (nextval ('seq_catno'), '文房具');

INSERT INTO
    product_category (cat_id, name)
VALUES (nextval ('seq_catno'), 'PC雑貨');

INSERT INTO
    product_category (cat_id, name)
VALUES (
        nextval ('seq_catno'),
        'ノート・紙雑貨'
    );

/* productテーブルにデータを挿入 */
INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        '鉛筆(黒)',
        '100',
        'black_pen.jpg',
        '101',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        '油性ボールペン(黒)',
        '100',
        'black_pen_o.jpg',
        '101',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        '水性ボールペン2(黒)',
        '220',
        'black_pen_w.jpg',
        '101',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        '水性ボールペン(青)',
        '120',
        'blue_pen_w.jpeg',
        '101',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        'USB有線式キーボード',
        '1400',
        'keybord2.jpg',
        '102',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        '無線式キーボード',
        '1900',
        'keybord.jpg',
        '102',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        '有線ゲーミングマウス',
        '3800',
        'mouse_a.jpg',
        '102',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        '有線光学式マウス',
        '500',
        'mouse_b.jpg',
        '102',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        'ワイヤレスマウス',
        '900',
        'mouse_c.jpg',
        '102',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        'ワイヤレストラックボール',
        '1300',
        'mouse_d.jpeg',
        '102',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        '水性ボールペン(赤)',
        '120',
        'red_pen_w.jpg',
        '101',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        '水性ボールペン(黒)',
        '120',
        'black_pen_w.jpg',
        '101',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        'マーカー(緑)',
        '150',
        'green_maker.jpg',
        '101',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        'マーカー(黄)',
        '150',
        'yellow_maker.jpg',
        '101',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        'マーカー(青)',
        '150',
        'blue_maker.jpg',
        '101',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        '筆ペン(黒)',
        '150',
        'black_fudepen.jpg',
        '101',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        '筆ペン(赤)',
        '150',
        'red_fudepen.jpg',
        '101',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        'ノート(青)',
        '160',
        'blue_note.jpg',
        '101',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        'ノート(緑)',
        '160',
        'green_note.jpg',
        '101',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        'ノート(橙)',
        '160',
        'orange_note.jpg',
        '101',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        'ノート(紫)',
        '160',
        'purple_note.jpg',
        '101',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        'ノート(白)',
        '160',
        'white_note.jpg',
        '101',
        '0'
    );

/* product_stockテーブルにデータを挿入 */
INSERT INTO
    product_stock (sto_id, quantity, pro_id)
VALUES (
        nextval ('seq_stono'),
        '0',
        '001'
    );

INSERT INTO
    product_stock (sto_id, quantity, pro_id)
VALUES (
        nextval ('seq_stono'),
        '15',
        '002'
    );

INSERT INTO
    product_stock (sto_id, quantity, pro_id)
VALUES (
        nextval ('seq_stono'),
        '3',
        '003'
    );

INSERT INTO
    product_stock (sto_id, quantity, pro_id)
VALUES (
        nextval ('seq_stono'),
        '20',
        '004'
    );

INSERT INTO
    product_stock (sto_id, quantity, pro_id)
VALUES (
        nextval ('seq_stono'),
        '50',
        '005'
    );

INSERT INTO
    product_stock (sto_id, quantity, pro_id)
VALUES (
        nextval ('seq_stono'),
        '0',
        '006'
    );

INSERT INTO
    product_stock (sto_id, quantity, pro_id)
VALUES (
        nextval ('seq_stono'),
        '8',
        '007'
    );

INSERT INTO
    product_stock (sto_id, quantity, pro_id)
VALUES (
        nextval ('seq_stono'),
        '120',
        '008'
    );

INSERT INTO
    product_stock (sto_id, quantity, pro_id)
VALUES (
        nextval ('seq_stono'),
        '1',
        '009'
    );

INSERT INTO
    product_stock (sto_id, quantity, pro_id)
VALUES (
        nextval ('seq_stono'),
        '24',
        '010'
    );

INSERT INTO
    product_stock (sto_id, quantity, pro_id)
VALUES (
        nextval ('seq_stono'),
        '45',
        '011'
    );

INSERT INTO
    product_stock (sto_id, quantity, pro_id)
VALUES (
        nextval ('seq_stono'),
        '10',
        '012'
    );

/* order_statusテーブルにデータを挿入 */
INSERT INTO
    order_status (sta_id, name)
VALUES (nextval ('seq_stano'), '完了');

INSERT INTO
    order_status (sta_id, name)
VALUES (nextval ('seq_stano'), '配送中');

INSERT INTO
    order_status (sta_id, name)
VALUES (
        nextval ('seq_stano'),
        'キャンセル'
    );

INSERT INTO
    order_status (sta_id, name)
VALUES (nextval ('seq_stano'), '注文済み');

/* customerテーブルにデータを挿入 */
INSERT INTO
    customer (
        cus_id,
        name,
        address1,
        address2,
        phone_number,
        mail_address,
        username,
        password,
        register_date
    )
VALUES (
        nextval ('seq_cusno'),
        '山田太郎',
        '東京都渋谷区',
        '1-11-11',
        '090-1234-5678',
        'taro@example.com',
        'taro123',
        'Pass_yamada1',
        '2024-04-12'
    );

INSERT INTO
    customer (
        cus_id,
        name,
        address1,
        address2,
        phone_number,
        mail_address,
        username,
        password,
        register_date
    )
VALUES (
        nextval ('seq_cusno'),
        '田中健二',
        '東京都新宿区',
        'テストビル101',
        '080-1234-5678',
        't.kenji@example.com',
        'testuser(テスト顧客)',
        'Pass_tanaka2',
        '2024-05-08'
    );

/* payment_methodテーブルにデータを挿入 */
INSERT INTO
    payment_method (pay_id, name)
VALUES (
        nextval ('seq_payno'),
        'クレジットカード'
    );

INSERT INTO
    payment_method (pay_id, name)
VALUES (nextval ('seq_payno'), '口座振替');

/* ordersテーブルにデータを挿入 */
INSERT INTO
    orders (
        ord_id,
        order_date,
        amount_total,
        cus_id,
        sta_id,
        pay_id
    )
VALUES (
        nextval ('seq_ordno'),
        '2024-04-09 06:30:00',
        '320',
        '10001',
        '101',
        '001'
    );

INSERT INTO
    orders (
        ord_id,
        order_date,
        amount_total,
        cus_id,
        sta_id,
        pay_id
    )
VALUES (
        nextval ('seq_ordno'),
        '2024-05-12 15:30:00',
        '100',
        '10002',
        '104',
        '002'
    );

/* orders_detailテーブルにデータを挿入 */
INSERT INTO
    orders_detail (
        det_id,
        ord_id,
        pro_id,
        counts
    )
VALUES (
        nextval ('seq_detno'),
        '2',
        '001',
        '1'
    );

INSERT INTO
    orders_detail (
        det_id,
        ord_id,
        pro_id,
        counts
    )
VALUES (
        nextval ('seq_detno'),
        '1',
        '005',
        '2'
    );
/* product_categoryテーブルにデータを挿入 */
INSERT INTO
    product_category (cat_id, name)
VALUES (nextval ('seq_catno'), '文房具');

INSERT INTO
    product_category (cat_id, name)
VALUES (nextval ('seq_catno'), 'PC雑貨');

INSERT INTO
    product_category (cat_id, name)
VALUES (
        nextval ('seq_catno'),
        'ノート・紙雑貨'
    );

/* productテーブルにデータを挿入 */
INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        '鉛筆(黒)',
        '100',
        'black_pen.jpg',
        '101',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        '油性ボールペン(黒)',
        '100',
        'black_pen_o.jpg',
        '101',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        '水性ボールペン2(黒)',
        '220',
        'black_pen_w.jpg',
        '101',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        '水性ボールペン(青)',
        '120',
        'blue_pen_w.jpeg',
        '101',
        '1'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        'USB有線式キーボード',
        '1400',
        'keybord2.jpg',
        '102',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        '無線式キーボード',
        '1900',
        'keybord.jpg',
        '102',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        '有線ゲーミングマウス',
        '3800',
        'mouse_a.jpg',
        '102',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        '有線光学式マウス',
        '500',
        'mouse_b.jpg',
        '102',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        'ワイヤレスマウス',
        '900',
        'mouse_c.jpg',
        '102',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        'ワイヤレストラックボール',
        '1300',
        'mouse_d.jpeg',
        '102',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        '水性ボールペン(赤)',
        '120',
        'red_pen_w.jpg',
        '101',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        '水性ボールペン(黒)',
        '120',
        'black_pen_w.jpg',
        '101',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        'マーカー(緑)',
        '150',
        'green_maker.jpg',
        '101',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        'マーカー(黄)',
        '150',
        'yellow_maker.jpg',
        '101',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        'マーカー(青)',
        '150',
        'blue_maker.jpg',
        '101',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        '筆ペン(黒)',
        '150',
        'black_fudepen.jpg',
        '101',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        '筆ペン(赤)',
        '150',
        'red_fudepen.jpg',
        '101',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        'ノート(青)',
        '160',
        'blue_note.jpg',
        '101',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        'ノート(緑)',
        '160',
        'green_note.jpg',
        '101',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        'ノート(橙)',
        '160',
        'orange_note.jpg',
        '101',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        'ノート(紫)',
        '160',
        'purple_note.jpg',
        '101',
        '0'
    );

INSERT INTO
    product (
        pro_id,
        name,
        price,
        image_url,
        cat_id,
        delete_flg
    )
VALUES (
        nextval ('seq_prono'),
        'ノート(白)',
        '160',
        'white_note.jpg',
        '101',
        '0'
    );

/* product_stockテーブルにデータを挿入 */
INSERT INTO
    product_stock (sto_id, quantity, pro_id)
VALUES (
        nextval ('seq_stono'),
        '0',
        '001'
    );

INSERT INTO
    product_stock (sto_id, quantity, pro_id)
VALUES (
        nextval ('seq_stono'),
        '15',
        '002'
    );

INSERT INTO
    product_stock (sto_id, quantity, pro_id)
VALUES (
        nextval ('seq_stono'),
        '3',
        '003'
    );

INSERT INTO
    product_stock (sto_id, quantity, pro_id)
VALUES (
        nextval ('seq_stono'),
        '20',
        '004'
    );

INSERT INTO
    product_stock (sto_id, quantity, pro_id)
VALUES (
        nextval ('seq_stono'),
        '50',
        '005'
    );

INSERT INTO
    product_stock (sto_id, quantity, pro_id)
VALUES (
        nextval ('seq_stono'),
        '0',
        '006'
    );

INSERT INTO
    product_stock (sto_id, quantity, pro_id)
VALUES (
        nextval ('seq_stono'),
        '8',
        '007'
    );

INSERT INTO
    product_stock (sto_id, quantity, pro_id)
VALUES (
        nextval ('seq_stono'),
        '120',
        '008'
    );

INSERT INTO
    product_stock (sto_id, quantity, pro_id)
VALUES (
        nextval ('seq_stono'),
        '1',
        '009'
    );

INSERT INTO
    product_stock (sto_id, quantity, pro_id)
VALUES (
        nextval ('seq_stono'),
        '24',
        '010'
    );

INSERT INTO
    product_stock (sto_id, quantity, pro_id)
VALUES (
        nextval ('seq_stono'),
        '45',
        '011'
    );

INSERT INTO
    product_stock (sto_id, quantity, pro_id)
VALUES (
        nextval ('seq_stono'),
        '10',
        '012'
    );