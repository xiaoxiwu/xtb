package com.xt8.service;

import java.io.Serializable;

import com.xt8.model.Image;

public interface ImageService extends BasicService {

	public Image findById(Serializable id);

	public Image insert(Image image);
	
}
