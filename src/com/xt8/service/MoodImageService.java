package com.xt8.service;

import java.io.Serializable;
import java.util.List;

import com.xt8.model.Mood;
import com.xt8.model.MoodImage;

public interface MoodImageService extends BasicService {

	public List<Serializable> save(List<MoodImage> entities);
	
	public MoodImage saveAndReturn(MoodImage moodImage);

	public List<MoodImage> findByMood(Mood entity);

}
