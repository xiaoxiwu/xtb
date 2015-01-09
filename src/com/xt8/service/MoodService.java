package com.xt8.service;

import java.io.Serializable;
import java.util.List;

import com.xt8.model.Mood;
import com.xt8.model.User;

public interface MoodService extends BasicService {
	public Mood findById(Serializable id);

	public Mood insert(Mood mood);

	public void delete(Mood mood);

	public List<Mood> findByUserWithPage(User user, Integer pageIndex,
			Integer pageSize);

	/**
	 * 通过用户列表查询，并排序
	 * 
	 * @param users
	 *            　用户集合
	 * @param pageIndex
	 *            　当前业
	 * @param pageSize
	 *            　页大小
	 * @param sortProperty
	 *            　排序属性
	 * @return 一页动态
	 */
	public List<Mood> findByUsersWithPage(List<User> users, Integer pageIndex,
			Integer pageSize, String sortProperty);

}
