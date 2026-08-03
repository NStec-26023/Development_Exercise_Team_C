/* departmentテーブル */
CREATE TABLE department (
    /* 部署ID：主キー */
    id int ,
    /* 部署名：100文字以内で保存 */
    name VARCHAR(100),
    primary key (id)
);

CREATE sequence seq_department_id
    start with 1
    increment by 1
    maxvalue 999;

    