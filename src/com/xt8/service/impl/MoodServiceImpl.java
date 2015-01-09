package com.xt8.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.xt8.model.Mood;
import com.xt8.model.MoodPraise;
import com.xt8.model.MoodReply;
import com.xt8.model.User;
import com.xt8.service.MoodPraiseService;
import com.xt8.service.MoodReplyService;
import com.xt8.service.MoodService;

@Transactional
public class MoodServiceImpl extends BasicServiceImpl implements MoodService {

	@Resource
	private MoodReplyService moodReplyService;

	@Resource
	private MoodPraiseService moodPraiseService;

	@Override
	public Mood findById(Serializable id) {
		return (Mood) super.findById(Mood.class, id);
	}

	@Override
	public Mood insert(Mood mood) {
		return (Mood) super.saveAndReturn(Mood.class, mood);
	}

	@Override
	public List<Mood> findByUserWithPage(User user, Integer pageIndex,
			Integer pageSize) {
		String countHql = "select count(*) from Mood bean where bean.user=?";
		String hql = "from Mood bean where bean.user=? order by bean.time desc";
		Object[] params = { user };
		int index = super.countPages(countHql, params, pageSize);// 取得页面数目
		index = (pageIndex > index) ? index : pageIndex;
		List<Mood> list = super
				.executeQueryByPage(hql, params, index, pageSize);
		return list;
	}

	@Override
	public List<Mood> findByUsersWithPage(List<User> users, Integer pageIndex,
			Integer pageSize, String sortProperty) {
		Session session = super.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(Mood.class);
		criteria.addOrder(Order.desc("time"));// 设置排序规则
		criteria.add(Restrictions.in("user", users));
		List list = criteria.setFirstResult((pageIndex - 1) * pageSize)
				.setMaxResults(pageSize).list();
		session.close();
		return list;
	}

	@Override
	public void delete(Mood mood) {
		List<MoodReply> mrlist = moodReplyService.findByMood(mood);
		List<MoodPraise> mpList = moodPraiseService.findByMood(mood);
		super.deleteByBatch(mrlist);
		super.deleteByBatch(mpList);
		super.delete(mood);
	}
}
