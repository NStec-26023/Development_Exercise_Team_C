package com.example.fullness.stationary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.fullness.stationary.entity.Customer;
import com.example.fullness.stationary.repository.CustomerRepository;

@Service
public class CustomerDetailsService implements UserDetailsService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CustomerDetails loadUserByUsername(String mailAddress) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByMailAddress(mailAddress);
        if (customer == null) {
            throw new UsernameNotFoundException("ユーザーが見つかりません");
        }
        return new CustomerDetails(customer);

    }
}
