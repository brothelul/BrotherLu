package com.swjtu.aroundyou.persistence.dao.massage;

import java.util.List;

import com.swjtu.aroundyou.persistence.dao.base.GenericHibernateDao;
import com.swjtu.aroundyou.persistence.dao.base.entity.Pagination;
import com.swjtu.aroundyou.persistence.entity.message.KeyWord;

public interface KeyWordDao extends GenericHibernateDao<KeyWord, Integer> {

	String NAME = "keyWordDao";
	
	List<String> getHostKeys();

	Pagination<KeyWord> getAllKeyWords(Integer page, Integer pageSize);
}
