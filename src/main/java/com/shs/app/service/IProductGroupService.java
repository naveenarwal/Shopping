package com.shs.app.service;

import java.util.List;

import com.shs.app.entity.GroupVarient;
import com.shs.app.entity.ProductGroup;


public interface IProductGroupService {

	ProductGroup createGroup(ProductGroup pg );
	ProductGroup getProductGroupByName(String groupName );
	ProductGroup updateGroupAddGroupVarient(ProductGroup pg);
	
	List<ProductGroup> getAllProductGroup();
	
	
	List<GroupVarient> getAllGroupVariantByGroupName(String groupName);
	ProductGroup removeGroupVarient(GroupVarient gv);
	
	
	
	
}
