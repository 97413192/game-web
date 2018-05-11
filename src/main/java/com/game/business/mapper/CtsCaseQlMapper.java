package com.game.business.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.game.business.model.Admin;
import com.game.business.model.CtsCaseQl;

@Repository
public interface CtsCaseQlMapper{
	
	int addCtsCaseQl(CtsCaseQl cases);
	
	List<CtsCaseQl> getCtsCaseQl(Map<String,Object> map);
	Admin findByName(String username);
	public void save(Admin admin);
}