package com.xt8.service;

import java.util.List;

import com.xt8.model.Mood;
import com.xt8.model.MoodPraise;

public interface MoodPraiseService extends BasicService {

	public MoodPraise findById(Integer id);

	public MoodPraise insert(MoodPraise moodPraise);

	public List<MoodPraise> findByMood(Mood mood);

	public int countMoodPraiseUnderMood(Mood mood);
}
