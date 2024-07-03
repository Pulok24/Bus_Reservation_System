package org.jsp.merchantbootapp.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.boot.MappingNotFoundException;
import org.jsp.merchantbootapp.dao.MerchantDao;
import org.jsp.merchantbootapp.dto.Merchant;
import org.jsp.merchantbootapp.dto.ResponseStructure;
import org.jsp.merchantbootapp.exception.MerchantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class MerchantService {
	@Autowired
	private MerchantDao merchantDao;
	
	public ResponseEntity<ResponseStructure<Merchant>> saveMerchant(@RequestBody Merchant merchant) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		structure.setMessage("Merchant Saved");
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setData(merchantDao.saveMerchant(merchant));
		return ResponseEntity.status(HttpStatus.CREATED).body(structure);
	}
	public ResponseEntity<ResponseStructure<Merchant>> findById(int id) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		Optional<Merchant> recMerchant = merchantDao.findById(id);
		if (recMerchant.isPresent()) {
			structure.setMessage("Merchant Found");
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setData(recMerchant.get());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new MerchantNotFoundException("Invalid Merchant id");
	}

	public ResponseEntity<ResponseStructure<Merchant>> updateMerchant(@RequestBody Merchant merchant) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		Optional<Merchant> recMerchant = merchantDao.findById(merchant.getId());
		if (recMerchant.isPresent()) {
			Merchant dbMerchant = recMerchant.get();
			dbMerchant.setEmail(merchant.getEmail());
			dbMerchant.setGst_number(merchant.getGst_number());
			dbMerchant.setName(merchant.getName());
			dbMerchant.setPassword(merchant.getPassword());
			dbMerchant.setPhone(merchant.getPhone());
			merchantDao.saveMerchant(dbMerchant);
			structure.setMessage("Merchant updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			structure.setData(dbMerchant);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(structure);
		}
		structure.setMessage("You have entered invalid Merchant Id");
		structure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
		structure.setData(null);
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(structure);
	}

	public ResponseEntity<ResponseStructure<List<Merchant>>> findAllMerchants() {
		ResponseStructure<List<Merchant>> structure = new ResponseStructure<>();
		structure.setMessage("These are all Merchants");
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setData(merchantDao.findAll());
		return ResponseEntity.status(HttpStatus.OK).body(structure);
	}

	public ResponseStructure<Merchant> deleteMerchant(int id) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		Optional<Merchant> recMerchant = merchantDao.findById(id);
		if (recMerchant.isPresent()) {
			structure.setMessage("Merchant Deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setData(recMerchant.get());
			merchantDao.delete(id);
			return structure;
		}
		structure.setMessage("Merchant not deleted as Invalid id");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		structure.setData(null);
		return structure;
	}

	public ResponseStructure<List<Merchant>> findByName( String name) {
		ResponseStructure<List<Merchant>> structure = new ResponseStructure<>();
		structure.setMessage("These are the Merchants which are matches with name");
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setData(merchantDao.findByName(name));
		return structure;
	}

	public ResponseStructure<Merchant> findByPhone( long phone) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		Optional<Merchant> recMerchant = merchantDao.findByPhone(phone);
		if (recMerchant.isPresent()) {
			structure.setMessage("Merchant Found");
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setData(recMerchant.get());	
		return structure;
		}
		structure.setMessage("Merchant Not Found");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		structure.setData(null);
		return structure;
	}

	public Merchant verifyMerchant( long phone, String password) {
		Optional<Merchant> recMerchant = merchantDao.findByPhoneAndPassword(phone, password);
		if (recMerchant.isPresent())
			return recMerchant.get();
		return null;
	}

	public Merchant verifyMerchant(String email,String password) {
		Optional<Merchant> recMerchant = merchantDao.findByEmailAndPassword(email, password);
		if (recMerchant.isPresent())
			return recMerchant.get();
		return null;
	}

	public Merchant verifyMerchant( int id,String password) {
		Optional<Merchant> recMerchant = merchantDao.verifyByIdAndPassword(id, password);
		if (recMerchant.isPresent())
			return recMerchant.get();
		return null;
	}
//
//	@GetMapping("/find-all-name")
//	public List<String> findAllName() {
//		return merchantDao.findAllName();
//	}
//
//	@PostMapping("/findIdByName")
//	public Optional<Integer> findIdByName(@RequestParam(name = "name") String name) {
//		return merchantDao.findIdByName(name);
//	}
}
