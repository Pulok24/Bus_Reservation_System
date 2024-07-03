package org.jsp.merchantbootapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.merchantbootapp.dao.MerchantDao;
import org.jsp.merchantbootapp.dao.ProductDao;
import org.jsp.merchantbootapp.dto.Merchant;
import org.jsp.merchantbootapp.dto.Product;
import org.jsp.merchantbootapp.dto.ResponseStructure;
import org.jsp.merchantbootapp.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private MerchantDao merchantDao;

	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product, int merchant_id) {
		Optional<Merchant> recMerchant = merchantDao.findById(merchant_id);
		if (recMerchant.isPresent()) {
			Merchant merchant = recMerchant.get();
			product.setMerchant(merchant);
			merchant.getProducts().add(product);
			ResponseStructure<Product> structure = new ResponseStructure<>();
			structure.setData(productDao.saveProduct(product));
			merchantDao.saveMerchant(merchant);
			structure.setMessage("Project added");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(structure);
		}
		throw new ProductNotFoundException("Cannot save Product as Id is Invalid");
	}

	public ResponseEntity<ResponseStructure<Product>> updateProduct(Product product) {
		Optional<Product> recProduct = productDao.findById(product.getId());
		ResponseStructure<Product> structure = new ResponseStructure<>();
		if (recProduct.isPresent()) {
			Product dbproduct = recProduct.get();
			dbproduct.setCategory(product.getCategory());
			dbproduct.setCost(product.getCost());
			dbproduct.setDescription(product.getDescription());
			dbproduct.setImg_url(product.getImg_url());
			dbproduct.setName(product.getName());
			structure.setData(productDao.saveProduct(dbproduct));
			structure.setMessage("Product Updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(structure);
		}
		throw new ProductNotFoundException("Cannot Update Product as Id is Invalid");
	}

	public ResponseEntity<ResponseStructure<Product>> findById(int id) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Optional<Product> recProduct = productDao.findById(id);
		if (recProduct.isPresent()) {
			structure.setData(recProduct.get());
			structure.setMessage("Product Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new ProductNotFoundException("Invalid Product Id");
	}
	
	public ResponseEntity<ResponseStructure<List<Product>>> findByCategory(String category) {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		List<Product> products = productDao.findByCategory(category);
		if (products.isEmpty())
			throw new ProductNotFoundException("No Product Found with entered category");
		structure.setData(products);
		structure.setMessage("List of Products with entered category");
		structure.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(structure);
	}

}
