package com.swjtu.aroundyou.persistence.dao.user.impl;

import org.springframework.stereotype.Repository;

import com.swjtu.aroundyou.persistence.dao.base.impl.GenericHibernateDaoImpl;
import com.swjtu.aroundyou.persistence.dao.user.UserInfoDao;
import com.swjtu.aroundyou.persistence.dto.user.UserInfoDTO;

@Repository(value=UserInfoDao.NAME)
public class UserInfoDaoImpl extends GenericHibernateDaoImpl<UserInfoDTO> implements UserInfoDao {

}
