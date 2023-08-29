package com.PractiseStringBoot.PractiseStringBoot.Controller;

import com.PractiseStringBoot.PractiseStringBoot.Model.Product;
import com.PractiseStringBoot.PractiseStringBoot.Model.ResponseObject;
import com.PractiseStringBoot.PractiseStringBoot.Repositories.ProductRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/Products")
public class ProductController {
    @Autowired
    private ProductRepositories productRepositories;
    @GetMapping
    public List<Product> getListProducts() {
        return productRepositories.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getProductById(@PathVariable Long id) {
        Optional<Product> foundProduct = productRepositories.findById(id);
        return foundProduct.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Found product successfully", foundProduct)) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("Failed", "Cannot found this product with id : " + id, ""));
    }

    @PostMapping("/insert")
    public ResponseEntity<ResponseObject> createProduct(@RequestBody Product newProduct) {
        List<Product> listProducts = productRepositories.findByProductName(newProduct.getProductName().trim());
        if(listProducts.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("Failed", "Cannot create product", ""));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Create successfully", productRepositories.save(newProduct)));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateProduct(@PathVariable Long id, @RequestBody Product newProduct) {
        Product updateProduct = productRepositories.findById(id).map(product -> {
            product.setProductName(newProduct.getProductName());
            product.setProductYear(newProduct.getProductYear());
            product.setPrice(newProduct.getPrice());
            product.setUrl(newProduct.getUrl());
            return productRepositories.save(product);
        }).orElseGet(() -> {
            newProduct.setId(id);
            return productRepositories.save(newProduct);
        });
           return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Update successfully", updateProduct));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) {
        Optional<Product> deleteProduct = productRepositories.findById(id);
        boolean exists = productRepositories.existsById(id);
        if(exists) {
            productRepositories.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Delete product successfully", deleteProduct));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("Failed", "Cannot found this product", ""));
    }
}
