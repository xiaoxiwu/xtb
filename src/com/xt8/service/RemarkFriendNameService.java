package com.xt8.service;

import java.io.Serializable;

import com.xt8.model.RemarkFriendName;
import com.xt8.model.User;

public interface RemarkFriendNameService extends BasicService {
	
	public RemarkFriendName insertRemarkFriendName(RemarkFriendName remarkName);
	
	public RemarkFriendName findById(Serializable id);
	
	public RemarkFriendName findByUser(User me,User friend);
	
	public void updateRemarkFriendName(RemarkFriendName remarkName);
	
	public void deleteRemarkFriendName(RemarkFriendName remarkName);

}
