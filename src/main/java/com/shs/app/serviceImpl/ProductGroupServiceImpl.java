package com.shs.app.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shs.app.entity.GroupVarient;
import com.shs.app.entity.ProductGroup;
import com.shs.app.exceptions.AlreadyFoundDataException;
import com.shs.app.exceptions.DataNotFoundException;
import com.shs.app.repo.IProductGroupRepository;
import com.shs.app.service.IProductGroupService;

import lombok.Data;
 
@Service
public class ProductGroupServiceImpl implements IProductGroupService {

	@Autowired
	private IProductGroupRepository groupRepository;
	
	@Override
	public ProductGroup createGroup(ProductGroup pg) {
		ProductGroup group = groupRepository.findByGroupName(pg.getGroupName());
		if(group!=null) {
			throw new AlreadyFoundDataException("Group with productGroup Name => "+pg.getGroupName() +"  Already Exist !!");
		}
		return this.groupRepository.save(pg);
	}

	@Override
	public ProductGroup getProductGroupByName(String groupName) {
		ProductGroup group = groupRepository.findByGroupName(groupName);
		if(group==null) {
			throw new DataNotFoundException("Group with productGroup Name => "+groupName +"  Not Exist !!");
		}
		return group;
		
		
	}

	@Override
	public ProductGroup updateGroupAddGroupVarient(ProductGroup pg) {
		ProductGroup group = groupRepository.findByGroupName(pg.getGroupName());
		if(group!=null) {
		group.getVariants().addAll(pg.getVariants());
			return this.groupRepository.save(group);
		}
		throw new DataNotFoundException("Group with productGroup Name => "+pg.getGroupName() +"  Not Exist !!");
	
	}

	@Override
	public List<ProductGroup> getAllProductGroup() {
	List<ProductGroup> allGroups = groupRepository.findAll();
	if(allGroups!=null) {
		return allGroups;
	}
	throw new DataNotFoundException("No Group are there !!");
	}

	@Override
	public List<GroupVarient> getAllGroupVariantByGroupName(String groupName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductGroup removeGroupVarient(GroupVarient gv) {
		// TODO Auto-generated method stub
		return null;
	}

}
