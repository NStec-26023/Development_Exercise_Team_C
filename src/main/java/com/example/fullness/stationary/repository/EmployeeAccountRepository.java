package com.example.fullness.stationary.repository;

import org.apache.ibatis.annotations.Mapper;
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

}
