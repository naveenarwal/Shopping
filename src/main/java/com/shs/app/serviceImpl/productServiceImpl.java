package com.shs.app.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shs.app.entity.Image;
import com.shs.app.entity.Product;
import com.shs.app.entity.Stock;
import com.shs.app.exceptions.AlreadyFoundDataException;
import com.shs.app.exceptions.DataNotFoundException;
import com.shs.app.imageutils.ImageUtils;
import com.shs.app.repo.IImageRepository;
import com.shs.app.repo.IProductRepository;
import com.shs.app.repo.IStockRepository;
import com.shs.app.service.IProductServices;

@Service
public class productServiceImpl implements IProductServices {

	@Autowired
	private IProductRepository prepo;
	@Autowired
	private IImageRepository irepo;

	@Autowired
	private IStockRepository stockrepo;
	
	@Override
	public Product saveProduct(Product p) {
		Product product = prepo.findByProductName(p.getProductName());
		Stock presentstock = stockrepo.findByProduct(product);
		if (product != null&&presentstock!=null) {
              Long quantity = presentstock.getQuantity();
              presentstock.setQuantity(quantity++);
              stockrepo.save(presentstock);
			throw new AlreadyFoundDataException("Product Already Exist!!");
		}
		else if(product==null) {
			  Stock stock=new Stock();
              stock.setProduct(product);
              Long quantity = presentstock.getQuantity();
              stock.setQuantity(quantity++);
              stockrepo.save(stock);
				
			return prepo.save(p);
		}
		return null;
	}

	@Override
	public String deleteProductById(Long productId) {
		if (prepo.findById(productId) != null) {
			prepo.deleteById(productId);
			return "Product with Product Id =>" + productId + " Deleted Succesfully";
		}
		throw new DataNotFoundException("Product not Exist!!");
	}

	@Override
	public Product updateProduct(Product p) {
		Product product = prepo.findByProductName(p.getProductName());
		Stock presentstock = stockrepo.findByProduct(product);
		
		if (product == null) {

			throw new DataNotFoundException("Product Not Exist with product Name  => " + p.getProductName());
		}
	      Long quantity = presentstock.getQuantity();
          presentstock.setQuantity(quantity++);
          stockrepo.save(presentstock);
	
		return prepo.save(p);
	}

	@Override
	public List<Product> getAllProduct() {
		// TODO Auto-generated method stub
		return prepo.findAll();
	}

	@Override
	public Product getproductbyId(Long id) {

		return prepo.findById(id).get();
	}

//	@Transactional(readOnly = true)
	@Override
	public List<Image> getAllImages(Long productId) {
		List<Image> images = irepo.findByProduct(productId);
		List<Image> all =new ArrayList<>();
		for (Image image : images) {
			    image.setData(ImageUtils.decompressImage(image.getData()));
		all.add(image);
		}
		return all;
	}
	
	@Override
	 public String uploadImage(MultipartFile file,Long pId) {
	        Image imageData=null;
			try {
				Image build = Image.builder()
		        .name(file.getOriginalFilename())
		        .type(file.getContentType())
		        .data(ImageUtils.compressImage(file.getBytes())).build();
				build.setProduct(prepo.findById(pId).get());
				imageData = irepo.save(build);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new DataNotFoundException("Product Image Cannot be add to unknown product ");
			}
	        if (imageData != null) {
	            return "file uploaded successfully : " + file.getOriginalFilename();
	        }
	        return null;
	    }

	@Override
	 public byte[] downloadImage(String fileName) {
	        Optional<Image> dbImageData = irepo.findById((long) 1);
	        byte[] images = ImageUtils.decompressImage(dbImageData.get().getData());
	        return images;
	    }

	}

