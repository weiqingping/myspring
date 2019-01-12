package com.my.test.out.impl;

import java.util.ArrayList;
import java.util.List;

import com.my.test.api.interfaces.ProductService;
import com.my.test.api.model.Product;

public class ProductServiceImpl implements ProductService {

	@Override
	public List<Product> lisProduct(Product pro) throws Exception {
		System.out.println("paramter:"+pro.toString());
		List<Product>products=new ArrayList<Product>(3);
		products.add(new Product(1, "product1"));
		products.add(new Product(2, "product2"));
		products.add(new Product(3, "product3"));

		return products;
	}

}
