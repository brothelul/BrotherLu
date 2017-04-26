package com.swjtu.aroundyou.biz.service.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swjtu.aroundyou.biz.service.manager.ManagerService;
import com.swjtu.aroundyou.persistence.dao.manager.ManagerInfoDao;
import com.swjtu.aroundyou.persistence.dao.manager.ManagerLoginDao;
import com.swjtu.aroundyou.persistence.dao.manager.ManagerLoginLogDao;
import com.swjtu.aroundyou.persistence.entity.manager.ManagerInfo;
import com.swjtu.aroundyou.persistence.entity.manager.ManagerLogin;
import com.swjtu.aroundyou.persistence.entity.manager.ManagerLoginLog;

@Service(value = ManagerService.NAME)
public class ManagerServiceImpl implements ManagerService {
	
	@Autowired
	private ManagerInfoDao managerInfoDao;
	@Autowired
	private ManagerLoginDao managerLoginDao;
	@Autowired
	private ManagerLoginLogDao managerLoginLogDao;
	
	@Override
	public ManagerLogin getManagerLogin(String managerName, String password) {
		ManagerLogin managerLogin = managerLoginDao.getManagerLogin(managerName, password);
		return managerLogin;
	}

	@Override
	public ManagerInfo getManagerInfo(Integer managerNo) {
		ManagerInfo managerInfo = managerInfoDao.getById(managerNo);
		return managerInfo;
	}

	@Override
	public void saveManagerLoginLog(ManagerLoginLog managerLoginLog) {
		managerLoginLogDao.save(managerLoginLog);
	}

	@Override
	public ManagerLoginLog getManagerLoginLog(Integer managerNo) {
		ManagerLoginLog managerLoginLog = managerLoginLogDao.getManagerLoginLog(managerNo);
		return managerLoginLog;
	}
	
}
