package com.shs.app.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shs.app.entity.Image;
import com.shs.app.entity.Product;
import com.shs.app.exceptions.AlreadyFoundDataException;
import com.shs.app.exceptions.DataNotFoundException;
import com.shs.app.repo.IImageRepository;
import com.shs.app.service.IProductServices;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private IProductServices productServices;
	@Autowired
	private IImageRepository iservices;

	@PostMapping("/")
	public ResponseEntity<Product> saveProduct(@RequestBody Product p) {
		ResponseEntity<Product> rs = null;
		try {
			rs = new ResponseEntity<Product>(productServices.saveProduct(p), HttpStatus.CREATED);
		} catch (AlreadyFoundDataException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new AlreadyFoundDataException("Product Already Present !!");
		}
		return rs;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		ResponseEntity<Product> rs = null;
		try {
			rs = new ResponseEntity<Product>(productServices.getproductbyId(id), HttpStatus.OK);
		} catch (AlreadyFoundDataException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new AlreadyFoundDataException("Product Not  Present !!");
		}
		return rs;
	}

	@GetMapping
	public ResponseEntity<List<Product>> getAllProduct() {
		ResponseEntity<List<Product>> rs = null;
		try {
			rs = new ResponseEntity<List<Product>>(productServices.getAllProduct(), HttpStatus.OK);
		} catch (AlreadyFoundDataException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new AlreadyFoundDataException("Product Data Not  Present !!");
		}
		return rs;
	}

	@PutMapping
	public ResponseEntity<Product> updateProduct(@RequestBody Product p) {
		ResponseEntity<Product> rs = null;
		try {
			rs = new ResponseEntity<Product>(productServices.updateProduct(p), HttpStatus.OK);
		} 
		catch (DataNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DataNotFoundException("Product Not  Found !!");
		}
		
		return rs;
	}
	
	@PostMapping("/addImage/{pid}")
	public ResponseEntity<String> updateProductaddimage(@RequestParam("image") MultipartFile p,@PathVariable Long pid) throws IOException {
		ResponseEntity<String> rs = null;
		try {
			rs = new ResponseEntity<String>(productServices.uploadImage(p,pid), HttpStatus.CREATED);
		} 
		catch (DataNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DataNotFoundException("Product Not  Found !!");
		}
		
		return rs;
	}
	
	@GetMapping("/getimages/{pid}")
	public ResponseEntity<List<Image>> getimage(@PathVariable Long pid){
		  List<Image> findById = productServices.getAllImages(pid);
		return ResponseEntity.ok()
				.contentType(MediaType.valueOf("image/png"))
				.body(findById);
	}
	
	@GetMapping("/image")
	public ResponseEntity<byte[]> getimageByName(@PathParam("name") String name){
		   byte[] image = productServices.downloadImage(name);
		return ResponseEntity.ok()
				.contentType(MediaType.valueOf("image/png")).body(image);
//				.contentType(MediaType.valueOf(findById.get()));
	}
	
	
}
