package com.restapi.ServicesImp;

import com.restapi.Models.Product;
import com.restapi.Repository.ProductRepository;
import com.restapi.RestaurantException.CustomException;
import com.restapi.Services.ProductServices;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public class ProductServicesImp implements ProductServices {
    private final ProductRepository productRepository;
    @Override
    public Page<Product> getProducts(Integer page) {
       try{
           System.out.println("page number "+page);
           return productRepository.findAll(PageRequest.of(page,6));
       }catch (CustomException e){
           throw new CustomException("Cant Retrieve Products");
       }
    }
    @Transactional
    @Override
    public Product saveProduct(Product product) {
        try{
            return productRepository.save(product);
        }catch (CustomException e){
            throw new CustomException("Failed To Add New Product");
        }
    }
    @Override
    public void deleteProduct(Integer id) {

    }
    @Override
    public Product findProduct(Integer id) {
        return productRepository.findById(id).get();
    }
    @Override
    public Product updateProduct(Product product) {
        return null;
    }
}
