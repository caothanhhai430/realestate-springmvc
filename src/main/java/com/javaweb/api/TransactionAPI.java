package com.javaweb.api;

import com.javaweb.dto.TransactionDTO;
import com.javaweb.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api-server/transaction")
public class TransactionAPI {
	
	@Autowired
	ITransactionService service;
	
	@GetMapping("/list")
	public List<TransactionDTO> list(@ModelAttribute TransactionDTO transactionRequest){
		Pageable pageable = null;
		if(transactionRequest.getPage()!=null && transactionRequest.getSize()!=null)
			pageable = PageRequest.of(transactionRequest.getPage().intValue(),transactionRequest.getSize().intValue());

		List<TransactionDTO> results = service.findAll(transactionRequest,pageable);
		return results;
		
	}

	@GetMapping("/count")
	public long count(@ModelAttribute TransactionDTO transactionRequest){
		return service.count(transactionRequest);
	}


	@GetMapping
	public TransactionDTO findById(@RequestParam Long id){
		TransactionDTO result = service.findById(id);
		return result;

	}
	
	@RequestMapping(value = "",method = RequestMethod.POST,consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public TransactionDTO newTransaction(@RequestBody TransactionDTO transaction) {
		transaction.setCreatedBy("admin");
		transaction.setModifiedBy("admin");
		transaction.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		transaction.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		
		Long id = service.save(transaction);
		
		TransactionDTO result = service.findById(id);
		return result;
	}
//
//	@RequestMapping(value = "",method = RequestMethod.PUT,consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
//	protected TransactionDTO updateTransaction(@RequestBody TransactionDTO transaction) {
//
//		transaction.setCreatedBy("admin");
//		transaction.setModifiedBy("admin");
//		transaction.setCreatedDate(new Timestamp(System.currentTimeMillis()));
//		transaction.setModifiedDate(new Timestamp(System.currentTimeMillis()));
//
//		service.update(transaction);
//		TransactionDTO resp = service.findById(transaction.getId());
//		return resp;
//	}

//
//	@RequestMapping(value = "",method = RequestMethod.DELETE,consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
//	protected boolean deleteTransaction(@RequestBody Map<String, Object> json) {
//			List<Integer> tempIds = (List<Integer>) json.get("ids");
//		List<Long> ids=  tempIds.stream().map(e -> (Long)e.longValue()).collect(Collectors.toList());
//		return service.delete(ids);
//	}

}
