package com.my.test.api.interfaces;

import java.util.List;

import com.my.test.api.model.Product;

public interface ProductService {
	
  List<Product>lisProduct(Product pro)throws Exception;
}
