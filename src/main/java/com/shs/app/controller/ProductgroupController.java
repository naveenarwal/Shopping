package com.shs.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shs.app.entity.ProductGroup;
import com.shs.app.exceptions.AlreadyFoundDataException;
import com.shs.app.exceptions.DataNotFoundException;
import com.shs.app.service.IProductGroupService;

@RestController
@RequestMapping("/group")
public class ProductgroupController {

	@Autowired
	private IProductGroupService groupService;
	
	@PostMapping("/")
	public ResponseEntity<ProductGroup> cerateNewGroup(@RequestBody ProductGroup productGroup){
		ResponseEntity<ProductGroup> rs = null;
		try {
			rs = new ResponseEntity<ProductGroup>(groupService.createGroup(productGroup),HttpStatus.CREATED);
		} catch (AlreadyFoundDataException e) {
			throw new AlreadyFoundDataException("Group with productGroup Name => "+productGroup.getGroupName() +"  Already Exist !!");
			
		}
		return rs;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<ProductGroup>> getAllGroups(){
		ResponseEntity<List<ProductGroup>> rs = null;
		try {
			rs =new ResponseEntity<List<ProductGroup>>(this.groupService.getAllProductGroup(),HttpStatus.OK);
		} catch (DataNotFoundException e) {
			throw new DataNotFoundException("No Group are there !!");
	
		}
		return rs;
	}
	
	@GetMapping("/getGroup")
	public ResponseEntity<ProductGroup> getAGroupByName(@RequestParam("groupName") String groupName){
		ResponseEntity<ProductGroup> rs = null;
		try {
			rs =new ResponseEntity<ProductGroup>(this.groupService.getProductGroupByName(groupName),HttpStatus.OK);
		} catch (DataNotFoundException e) {
			throw new DataNotFoundException("Group with productGroup Name => "+groupName +"  Not Exist !!");
			
		}
		return rs;
	}

	@PutMapping("/update")
	public ResponseEntity<ProductGroup> updateGroup(@RequestBody ProductGroup productGroup){
		ResponseEntity<ProductGroup> rs = null;
		try {
			rs = new ResponseEntity<ProductGroup>(groupService.updateGroupAddGroupVarient(productGroup),HttpStatus.OK);
		} catch (DataNotFoundException e) {
			throw new DataNotFoundException("Group with productGroup Name => "+productGroup.getGroupName() +"  Not Exist !!");
				
		}
		return rs;
	}

}
