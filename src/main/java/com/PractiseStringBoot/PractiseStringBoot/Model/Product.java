package com.PractiseStringBoot.PractiseStringBoot.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "Name of Product", unique = true, nullable = false, length = 300)
    private String productName;
    private int productYear;
    private double price;
    private String url;

    public Product(String productName, int productYear, double price, String url) {
        this.productName = productName;
        this.productYear = productYear;
        this.price = price;
        this.url = url;
    }

    @Transient
    private int age;
    public int getAge() {
        return Calendar.getInstance().get(Calendar.YEAR)-this.productYear;
    }

}
