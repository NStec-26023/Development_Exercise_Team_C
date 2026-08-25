package com.example.fullness.stationary.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductStock;

@Mapper
public interface ProductDeleteRepository {

    List<Product> selectAll();

    List<Product> selectAllWithCategory();

    Product selectById(int proId);

    int insert(Product product);

    int updateById(Product product);

    int deleteById(int proId);

    ProductStock selectByProIdWithProduct(Integer proId);

    ProductStock getProductForDelete(Integer proId);

}
