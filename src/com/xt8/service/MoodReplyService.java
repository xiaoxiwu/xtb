package com.xt8.service;

import java.util.List;

import com.xt8.model.Mood;
import com.xt8.model.MoodReply;

public interface MoodReplyService extends BasicService {

	public MoodReply findById(Integer id);

	public MoodReply insert(MoodReply moodReply);

	public List<MoodReply> findByMood(Mood mood);

	public List<MoodReply> findByMoodByPage(Mood mood, int pageIndex,
			int pageSize);

}
