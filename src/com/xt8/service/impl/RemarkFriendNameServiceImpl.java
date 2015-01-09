package com.xt8.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;






import com.xt8.model.Province;
import com.xt8.model.RemarkFriendName;
import com.xt8.model.User;
import com.xt8.model.UserSetting;
import com.xt8.service.ProvinceService;
import com.xt8.service.RemarkFriendNameService;

@Transactional
public class RemarkFriendNameServiceImpl extends BasicServiceImpl implements RemarkFriendNameService {

	@Override
	public RemarkFriendName findById(Serializable id) {
		return (RemarkFriendName) super.findById(RemarkFriendName.class, id);
	}

	@Override
	public RemarkFriendName insertRemarkFriendName(RemarkFriendName remarkName) {
		// TODO Auto-generated method stub
		Serializable id = super.save(remarkName);
		return findById(id);
	}

	@Override
	public RemarkFriendName findByUser(User me, User friend) {
		// TODO Auto-generated method stub
		String hql = "from RemarkFriendName bean where bean.user= ? and bean.friend= ?";
		Object[] params = {me,friend};
		
		List<RemarkFriendName> list = super.executeQuery(hql, params);
		
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
	public void updateRemarkFriendName(RemarkFriendName remarkName) {
		// TODO Auto-generated method stub
		super.update(remarkName);
	}

	@Override
	public void deleteRemarkFriendName(RemarkFriendName remarkName) {
		// TODO Auto-generated method stub
		super.delete(remarkName);
		
	}
}
