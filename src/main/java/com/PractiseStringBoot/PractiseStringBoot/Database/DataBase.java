package com.PractiseStringBoot.PractiseStringBoot.Database;

import com.PractiseStringBoot.PractiseStringBoot.Model.Product;
import com.PractiseStringBoot.PractiseStringBoot.Repositories.ProductRepositories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataBase {
    private final static Logger logger = LoggerFactory.getLogger(DataBase.class);
    @Bean
    CommandLineRunner initDatabaseCommandLineRunner(ProductRepositories productRepositories) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Product productA = new Product("Samsung", 2002, 43000, "");
                Product productB = new Product("Iphne", 2005, 20444, "");
                Product productC = new Product("Redmi", 2007, 23644, "");

                logger.info("Insert data : " + productRepositories.save(productA));
                logger.info("Insert data : " + productRepositories.save(productB));
                logger.info("Insert data : " + productRepositories.save(productC));
            }
        };
    }
}
