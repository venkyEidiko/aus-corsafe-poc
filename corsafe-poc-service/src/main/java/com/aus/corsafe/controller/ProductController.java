package com.aus.corsafe.controller;

import com.aus.corsafe.dto.ProductResponse;
import com.aus.corsafe.entity.ResponseModel;
import com.aus.corsafe.response.CommonResponse;
import com.aus.corsafe.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ProductController {



    @Autowired
    private ProductService productService;


    @GetMapping("/getAllproducts")
    public ResponseEntity<ResponseModel<Object>> getAllProducts()
    {
        List<ProductResponse> allProducts = productService.getAllProducts();
        return new CommonResponse<>().prepareSuccessResponseObject(allProducts, HttpStatus.OK);
    }
}
