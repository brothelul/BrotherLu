package com.swjtu.aroundyou.persistence.dao.massage.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.swjtu.aroundyou.persistence.dao.base.entity.Pagination;
import com.swjtu.aroundyou.persistence.dao.base.impl.GenericHibernateDaoImpl;
import com.swjtu.aroundyou.persistence.dao.massage.KeyWordDao;
import com.swjtu.aroundyou.persistence.entity.message.KeyWord;

@Repository(value=KeyWordDao.NAME)
public class KeyWordDaoImpl extends GenericHibernateDaoImpl<KeyWord, Integer> implements
		KeyWordDao {


	@Override
	public List<String> getHostKeys() {
		String sql = "select key_word ";
		sql = sql + "from search_key ";
		sql = sql + "group by key_word ";
		sql = sql + "order by count(key_word) desc ";
		sql = sql + "limit 0,5";
		SQLQuery query = getSession().createSQLQuery(sql);

		return query.list();
	}
	
	@Override
	public Pagination<KeyWord> getAllKeyWords(Integer page, Integer pageSize) {
		String hql = "from KeyWord";
		return findPagination(hql, page, pageSize, null);
	}

}
