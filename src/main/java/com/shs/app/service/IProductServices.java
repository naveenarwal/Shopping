package com.shs.app.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.shs.app.entity.Image;
import com.shs.app.entity.Product;

public interface IProductServices {

	Product saveProduct(Product p);
	Product updateProduct(Product p);
	List<Product> getAllProduct();
	Product getproductbyId(Long id);
	
	List<Image> getAllImages(Long productId);
	String deleteProductById(Long productId);
	String uploadImage(MultipartFile file,Long pId);
	byte[] downloadImage(String fileName);
	
}
