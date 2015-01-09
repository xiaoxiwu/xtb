package com.xt8.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;






import com.xt8.model.Province;
import com.xt8.model.User;
import com.xt8.model.UserCommAddr;
import com.xt8.service.ProvinceService;
import com.xt8.service.UserCommAddrService;

@Transactional
public class UserCommAddrServiceImpl extends BasicServiceImpl implements UserCommAddrService {

	
	@Override
	public UserCommAddr insertUserCommAddr(UserCommAddr userCommAddr) {
		// TODO Auto-generated method stub
		Serializable id = super.save(userCommAddr);
		return findById(id);
	}

	
	@Override
	public UserCommAddr findById(Serializable id) {
		return (UserCommAddr) super.findById(UserCommAddr.class, id);
	}


	@Override
	public List<UserCommAddr> findByUser(User user) {
		// TODO Auto-generated method stub

		String hql = "from UserCommAddr bean where bean.user = ?";
		Object[] params = { user };
		
		List<UserCommAddr> list = super.executeQuery(hql, params);
		
		if (null == list || list.isEmpty()) {
			return null;
		} else {
			return list;
		}
	}


	@Override
	public void deleteUserCommAddr(UserCommAddr userCommAddr) {
		// TODO Auto-generated method stub
		
		super.delete(userCommAddr);
	}
}
