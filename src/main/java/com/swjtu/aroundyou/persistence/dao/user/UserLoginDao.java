package com.swjtu.aroundyou.persistence.dao.user;

import com.swjtu.aroundyou.persistence.dao.base.GenericHibernateDao;
import com.swjtu.aroundyou.persistence.dto.user.UserLoginDTO;

public interface UserLoginDao extends GenericHibernateDao<UserLoginDTO>{

	public String NAME = "UserLoginDao";
}
