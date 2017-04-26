package com.swjtu.aroundyou.biz.service.message;

import java.util.List;

import com.swjtu.aroundyou.persistence.dao.base.entity.Pagination;
import com.swjtu.aroundyou.persistence.entity.message.HostKey;
import com.swjtu.aroundyou.persistence.entity.message.KeyWord;

public interface KeyWordService {

	String NAME = "keyWordService";
	
	/**
	 * 
	 * @auther brotherlu
	 * @date  2017年4月12日下午11:08:41
	 * @param void
	 * <p>描述：保存用户搜索的key</p>
	 */
	void saveKeyWord(KeyWord keyWord);
	
	/**
	 * 
	 * @auther brotherlu
	 * @date  2017年4月12日下午11:14:45
	 * @param HostKey
	 * <p>描述：得到热搜</p>
	 */
	List<String> getHostKey();

	Pagination<KeyWord> getAllKeyWords(Integer page, Integer pageSize);
}
