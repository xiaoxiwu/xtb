package com.xt8.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;






import com.xt8.model.BlackList;
import com.xt8.model.Province;
import com.xt8.model.User;
import com.xt8.model.UserSetting;
import com.xt8.service.BlackListService;
import com.xt8.service.ProvinceService;

@Transactional
public class BlackListServiceImpl extends BasicServiceImpl implements BlackListService {


	@Override
	public BlackList findById(Serializable id) {
		return (BlackList) super.findById(BlackList.class, id);
	}

	@Override
	public BlackList insertBlackList(BlackList blackList) {
		// TODO Auto-generated method stub
		Serializable id = super.save(blackList);
		
		return findById(id);
	}

	@Override
	public BlackList findByTwoUser(User user, User blackUser) {
		// TODO Auto-generated method stub

		String hql = "from BlackList bean where bean.user=? and bean.blackUser=?";
		Object[] params = {user,blackUser};
		
		List<BlackList> list = super.executeQuery(hql, params);
		
		if(null == list || list.isEmpty())
		{
			return null;
		}
		else
		{
			return list.get(0);
		}
	}

	@Override
	public void removeUserFromBlackList(BlackList blackList) {
		// TODO Auto-generated method stub
		super.delete(blackList);
	}
}
