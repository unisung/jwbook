package jwbook3.service;

import java.util.List;

import jwbook3.model.Product;

public interface ProductService {
	 List<Product> findAll();
	 Product find(String id);
}
