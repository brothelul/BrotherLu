package com.swjtu.aroundyou.persistence.dao.user;

import com.swjtu.aroundyou.persistence.dao.base.GenericHibernateDao;
import com.swjtu.aroundyou.persistence.dto.user.UserInfoDTO;

public interface UserInfoDao extends GenericHibernateDao<UserInfoDTO> {

	String NAME = "userInfoDao";
}
