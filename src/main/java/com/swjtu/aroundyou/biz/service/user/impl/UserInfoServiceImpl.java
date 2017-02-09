package com.swjtu.aroundyou.biz.service.user.impl;

import org.springframework.stereotype.Service;

import com.swjtu.aroundyou.biz.entity.user.UserInfo;
import com.swjtu.aroundyou.biz.service.user.UserInfoService;
import com.swjtu.aroundyou.persistence.dao.base.impl.GenericHibernateDaoImpl;

@Service(value=UserInfoService.NAME)
public class UserInfoServiceImpl extends GenericHibernateDaoImpl<UserInfo> implements
		UserInfoService {

}
