package com.xt8.service.impl;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

import com.xt8.model.File;
import com.xt8.service.FileService;

@Transactional
public class FileServiceImpl extends BasicServiceImpl implements FileService {

	@Override
	public File findById(Serializable id) {
		return (File) findById(File.class, id);
	}

}
