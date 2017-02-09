package com.swjtu.aroundyou.persistence.dao.user.impl;

import org.springframework.stereotype.Repository;

import com.swjtu.aroundyou.persistence.dao.base.impl.GenericHibernateDaoImpl;
import com.swjtu.aroundyou.persistence.dao.user.UserLoginDao;
import com.swjtu.aroundyou.persistence.dto.user.UserLoginDTO;

@Repository(value=UserLoginDao.NAME)
public class UserLoginDaoImpl extends GenericHibernateDaoImpl<UserLoginDTO> implements UserLoginDao{

}
