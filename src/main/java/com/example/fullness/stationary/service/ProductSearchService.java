package com.example.fullness.stationary.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.repository.ProductSearchRepository;



@Service
@Transactional
public class ProductSearchService {

    @Autowired
    private ProductSearchRepository productSearchRepository;

    public List<ProductCategory>findAllProductCategory(){
        return productSearchRepository.selectAllProductCategory();
    }

    public List<Product> findByCategory(Integer catId){
        if (catId == null) {
            return productSearchRepository.selectAll();
        }
        return productSearchRepository.selectByCatId(catId);
    }
        
  
    // public List<Product> findByCategoryAndPage(Integer catId, int page) {
    //     return null; 
    // }
    //  public int getTotalPages(Integer catId) {
    //     return 0; 
    // }
    
}

  