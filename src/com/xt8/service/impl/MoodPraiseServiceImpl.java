package com.xt8.service.impl;

import java.util.List;

import com.xt8.model.Mood;
import com.xt8.model.MoodPraise;
import com.xt8.service.MoodPraiseService;

public class MoodPraiseServiceImpl extends BasicServiceImpl implements
		MoodPraiseService {

	@Override
	public MoodPraise findById(Integer id) {
		return (MoodPraise) super.findById(MoodPraise.class, id);
	}

	@Override
	public MoodPraise insert(MoodPraise moodPraise) {
		return (MoodPraise) super.saveAndReturn(MoodPraise.class, moodPraise);
	}

	@Override
	public List<MoodPraise> findByMood(Mood mood) {
		String hql = "from MoodPraise bean where bean.mood=?";
		Object[] params = { mood };
		return super.executeQuery(hql, params);
	}

	@Override
	public int countMoodPraiseUnderMood(Mood mood) {
		String hql = "select count(*) from MoodPraise bean where bean.mood=?";
		Object[] params = { mood };
		return (int) super.count(hql, params);
	}

}
