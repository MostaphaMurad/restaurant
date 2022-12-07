package com.restapi.Services;

import com.restapi.Models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface ProductServices {
    Page<Product> getProducts(Integer page);
    Product saveProduct(Product product);
    void deleteProduct(Integer id);
    Product findProduct(Integer id);
    Product updateProduct(Product product);
}
