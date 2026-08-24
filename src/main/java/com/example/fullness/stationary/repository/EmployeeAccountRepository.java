package com.example.fullness.stationary.repository;

<<<<<<< HEAD
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.fullness.stationary.controller.form.EmployeeRegisterForm;
import com.example.fullness.stationary.entity.Employee;
=======
import org.apache.ibatis.annotations.Mapper;
>>>>>>> 8791f08308d0b2f5168c6abfec57218a8fede49e
import com.example.fullness.stationary.entity.EmployeeAccount;

/**
 * 社員アカウントおよび関連する社員情報のデータベース操作を行うMyBatisマッパーインターフェースです。
 * 
 * @author 陳以勒
 */
@Mapper
public interface EmployeeAccountRepository {
    /**
     * アカウント名をキーにして社員アカウント情報を取得します。
     * Spring Securityのログイン機能などで使用されます。
     * 
     * @param name アカウント名
     * @return 該当する社員アカウント情報
     */
    EmployeeAccount findByName(String name);

<<<<<<< HEAD
    /**
     * アカウントが未登録の社員一覧を抽出します。
     * 
     * @return 未登録の社員情報のリスト
     */
    List<Employee> selectUnregisteredEmployees();

    /**
     * 新しい社員アカウント情報をデータベースに登録します。
     * 
     * @param employeeAccount 登録する社員アカウント情報
     */
    void insertAccount(EmployeeRegisterForm employeeRegisterForm);// 8/19
}
=======
}
>>>>>>> 8791f08308d0b2f5168c6abfec57218a8fede49e
