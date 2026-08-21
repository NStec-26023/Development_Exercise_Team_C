/* departmentテーブル */
CREATE TABLE department (
    /* 部署ID：主キー */
    dept_id integer,
    /* 部署名：100文字以内で保存 */
    name VARCHAR(100),
    primary key (dept_id)
);

CREATE sequence seq_deptno start
with
    101 increment by 1 maxvalue 999;

/* employeeテーブル */
CREATE TABLE employee (
    /* 社員ID：主キー */
    emp_id integer,
    /* 社員名：100文字以内で保存 */
    name VARCHAR(100),
    /* 社員名カナ：100文字以内で保存 */
    kana VARCHAR(100),
    /* 部署ID*/
    dept_id integer,
    primary key (emp_id),
    FOREIGN key (dept_id) references department (dept_id)
);

CREATE sequence seq_empno start
with
    1001 increment by 1 maxvalue 9999;

/* employee_accountテーブル */
CREATE TABLE employee_account (
    /* アカウントID：主キー */
    acc_id integer,
    /* アカウント名：20文字以内で保存 */
    name VARCHAR(20),
    /* パスワード：225文字以内で保存 */
    password VARCHAR(225),
    /* 社員ID */
    emp_id integer,
    primary key (acc_id),
    FOREIGN key (emp_id) references employee (emp_id)
);

CREATE sequence seq_accno start
with
    001 increment by 1 maxvalue 999;

/* product_categoryテーブル */
CREATE TABLE product_category (
    /* 商品カテゴリID：主キー */
    cat_id integer,
    /* 商品カテゴリ名：30文字以内で保存 */
    name VARCHAR(30),
    primary key (cat_id)
);

CREATE sequence seq_catno start
with
    101 increment by 1 maxvalue 999;

/* productテーブル */
CREATE TABLE product (
    /* 商品ID：主キー */
    pro_id integer,
    /* 商品名：100文字以内で保存 */
    name VARCHAR(100),
    /* 価格 */
    price integer,
    /* 画像URL：200文字以内で保存 */
    image_url VARCHAR(200),
    /* 商品カテゴリID */
    cat_id integer,
    /* 削除フラグ */
    delete_flg integer,
    primary key (pro_id),
    FOREIGN key (cat_id) references product_category (cat_id)
);

CREATE sequence seq_prono start
with
    001 increment by 1 maxvalue 999;

/* product_stockテーブル */
CREATE TABLE product_stock (
    /* 商品在庫ID：主キー */
    sto_id integer,
    /* 商品在庫数 */
    quantity integer,
    /* 商品ID */
    pro_id integer,
    primary key (sto_id),
    FOREIGN key (pro_id) references product (pro_id)
);

CREATE sequence seq_stono start
with
    1001 increment by 1 maxvalue 9999;

/* order_statusテーブル */
CREATE TABLE order_status (
    /* 注文ステータスID：主キー */
    sta_id integer,
    /* 注文ステータス名：100文字以内で保存 */
    name VARCHAR(100),
    primary key (sta_id)
);

CREATE sequence seq_stano start
with
    101 increment by 1 maxvalue 999;

/* customerテーブル */
CREATE TABLE customer (
    /* 顧客ID：主キー */
    cus_id integer,
    /* 顧客名：20文字以内で保存 */
    name VARCHAR(20),
    /* 住所1：100文字以内で保存 */
    address1 VARCHAR(100),
    /* 住所2：100文字以内で保存 */
    address2 VARCHAR(100),
    /* 電話番号：20文字以内で保存 */
    phone_number VARCHAR(20),
    /* メールアドレス：200文字以内で保存 */
    mail_address VARCHAR(200),
    /* アカウント名：30文字以内で保存 */
    username VARCHAR(30),
    /* パスワード：225文字以内で保存 */
    password VARCHAR(225),
    /* 登録日 */
    register_date TIMESTAMP,
    primary key (cus_id)
);

CREATE sequence seq_cusno start
with
    10001 increment by 1 maxvalue 99999;

/* payment_methodテーブル */
CREATE TABLE payment_method (
    /* 支払い方法ID：主キー */
    pay_id integer,
    /* 支払い方法名：100文字以内で保存 */
    name VARCHAR(100),
    primary key (pay_id)
);

CREATE sequence seq_payno start
with
    001 increment by 1 maxvalue 999;

/* ordersテーブル */
CREATE TABLE orders (
    /* 注文ID：主キー */
    ord_id integer,
    /* 注文日 */
    order_date TIMESTAMP,
    /* 合計金額 */
    amount_total integer,
    /* 顧客ID */
    cus_id integer,
    /* 注文ステータスID */
    sta_id integer,
    /* 支払い方法ID */
    pay_id integer,
    primary key (ord_id),
    FOREIGN key (cus_id) references customer (cus_id),
    FOREIGN key (sta_id) references order_status (sta_id),
    FOREIGN key (pay_id) references payment_method (pay_id)
);

CREATE sequence seq_ordno start
with
    1 increment by 1 maxvalue 999;

/* orders_detailテーブル */
CREATE TABLE orders_detail (
    /* 注文明細ID：主キー */
    det_id integer,
    /* 注文ID */
    ord_id integer,
    /* 商品ID */
    pro_id integer,
    /* 購入時単価 */
    unit_price integer,
    /* 購入数 */
    counts integer,
    primary key (det_id),
    FOREIGN key (ord_id) references orders (ord_id),
    FOREIGN key (pro_id) references product (pro_id)
);

CREATE sequence seq_detno start
with
    101 increment by 1 maxvalue 999;