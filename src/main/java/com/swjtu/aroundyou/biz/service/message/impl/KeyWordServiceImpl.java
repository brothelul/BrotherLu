package com.swjtu.aroundyou.biz.service.message.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swjtu.aroundyou.biz.service.message.KeyWordService;
import com.swjtu.aroundyou.persistence.dao.base.entity.Pagination;
import com.swjtu.aroundyou.persistence.dao.massage.KeyWordDao;
import com.swjtu.aroundyou.persistence.entity.message.HostKey;
import com.swjtu.aroundyou.persistence.entity.message.KeyWord;

@Service(value=KeyWordService.NAME)
public class KeyWordServiceImpl implements KeyWordService {

	@Autowired
	private KeyWordDao keyWordDao;
	
	@Override
	public void saveKeyWord(KeyWord keyWord) {
		keyWordDao.save(keyWord);
	}

	@Override
	public List<String> getHostKey() {
		List<String> hostKeys = keyWordDao.getHostKeys();
		return hostKeys;
	}

	@Override
	public Pagination<KeyWord> getAllKeyWords(Integer page, Integer pageSize) {
		return keyWordDao.getAllKeyWords(page, pageSize);
	}

}
