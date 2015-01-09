package com.xt8.service.impl;

import static com.xt8.util.Constants.MAX_RECORDS;

import java.util.List;

import com.xt8.model.Mood;
import com.xt8.model.MoodReply;
import com.xt8.service.MoodReplyService;

public class MoodReplyServiceImpl extends BasicServiceImpl implements
		MoodReplyService {

	@Override
	public MoodReply findById(Integer id) {
		return (MoodReply) super.findById(MoodReply.class, id);
	}

	@Override
	public MoodReply insert(MoodReply moodReply) {
		return (MoodReply) super.saveAndReturn(MoodReply.class, moodReply);
	}

	@Override
	public List<MoodReply> findByMood(Mood mood) {
		return findByMoodByPage(mood, 1, MAX_RECORDS);
	}

	@Override
	public List<MoodReply> findByMoodByPage(Mood mood, int pageIndex,
			int pageSize) {
		String hql = "from MoodReply bean where bean.mood=?";
		Object[] params = { mood };
		return super.executeQueryByPage(hql, params, pageIndex, pageSize);
	}

}
