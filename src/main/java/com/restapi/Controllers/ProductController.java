package com.restapi.Controllers;

import com.restapi.Models.Product;
import com.restapi.ServicesImp.ProductServicesImp;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.Locale;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
@RequestMapping("/rest/v1/api")
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
public class ProductController {
    private final ProductServicesImp productServicesImp;
    @GetMapping("/test")
    public String test(@RequestHeader("api")String api){
        System.out.println(api);
        return "working";
    }
    @GetMapping("/products/all")
    public ResponseEntity<Page<Product>>getProducts(@RequestHeader("Authorization")String auth,@RequestParam(defaultValue = "0") Integer page, HttpServletRequest request){
        Locale currentLocale=request.getLocale();
        System.out.println("auth in controller " +auth);
        String countryCode=currentLocale.getCountry();
        String countryName=currentLocale.getDisplayCountry();
        String langCode=currentLocale.getLanguage();
        String langName=currentLocale.getDisplayLanguage();
       /* System.out.println(countryCode+" "+countryName);
        System.out.println(langCode+" "+langName);*/
       /* String []languages=Locale.getISOLanguages();
        for(String lang:languages){
            Locale locale=new Locale(lang);
            System.out.println("Language "+locale.getDisplayLanguage());
        }*/
        return new ResponseEntity<>(productServicesImp.getProducts(page),OK);
    }
    @PostMapping("/product/add")
    public ResponseEntity<Product>addProduct(@RequestBody Product product){
        return  new ResponseEntity<>(productServicesImp.saveProduct(product),CREATED);
    }
    @GetMapping("/product-details/{id}")
    public ResponseEntity<Product>viewProductDetails(@PathVariable("id")Integer id){
        return new ResponseEntity<>(productServicesImp.findProduct(id),OK);
    }
}
