package com.xt8.service.impl;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

import com.xt8.model.Image;
import com.xt8.service.ImageService;

@Transactional
public class ImageServiceImpl extends BasicServiceImpl implements ImageService {

	@Override
	public com.xt8.model.Image findById(Serializable id) {
		return (com.xt8.model.Image) findById(Image.class, id);
	}

	@Override
	public Image insert(Image image) {
		return (Image) super.saveAndReturn(Image.class, image);
	}
	

}
