package com.PractiseStringBoot.PractiseStringBoot.Repositories;

import com.PractiseStringBoot.PractiseStringBoot.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ProductRepositories extends JpaRepository<Product, Long> {
    public List<Product> findByProductName(String productName);

}
