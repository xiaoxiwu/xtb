package com.xt8.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.xt8.model.Mood;
import com.xt8.model.MoodImage;
import com.xt8.service.MoodImageService;

public class MoodImageServiceImpl extends BasicServiceImpl implements
		MoodImageService {

	@Override
	public List<Serializable> save(List<MoodImage> entities) {
		List<Serializable> list = new ArrayList<Serializable>();
		for (MoodImage entity : entities) {
			Serializable id = super.save(entity);
			list.add(id);
		}
		return list;
	}

	@Override
	public List<MoodImage> findByMood(Mood entity) {
		String hql = "from MoodImage bean where bean.mood=?";
		Object[] params = { entity };
		List<MoodImage> list = super.executeQuery(hql, params);
		return list;
	}

	@Override
	public MoodImage saveAndReturn(MoodImage moodImage) {
		return (MoodImage) super.saveAndReturn(MoodImage.class, moodImage);
	}

}
