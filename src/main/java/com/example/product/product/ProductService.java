package com.example.product.product;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;



@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductRepository repo;

	public List<Product> listAll() {
		return repo.findAll();
	}

	public void save(Product product) {
		repo.save(product);
	}

	public Product get(long id) {
		return repo.findById(id).get();
	}

	public void delete(long id) {
		repo.deleteById(id);
	}

	public void saveProductToDB(MultipartFile file, String name, String brand, String madein,float price) {
		Product product = new Product();
		System.out.println("inside service class");
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		if (filename.contains("..")) {
			System.out.println("not a valid file");
		}
		try {
			product.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * product.setDescription(description);
		 * 
		 * product.setName(name); product.setPrice(price);
		 */
		product.setBrand(brand);
		product.setMadein(madein);
		product.setName(name);
		product.setPrice(price);
		
		System.out.println("production service...");
		repo.save(product);

	}
}
