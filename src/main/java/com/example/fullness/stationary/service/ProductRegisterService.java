package com.example.fullness.stationary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.fullness.stationary.repository.ProductRegisterRepository;

@Service
@Transactional
public class ProductRegisterService {

    @Autowired
    private ProductRegisterRepository Product;

}