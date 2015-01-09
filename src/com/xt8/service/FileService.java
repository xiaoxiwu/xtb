package com.xt8.service;

import java.io.Serializable;

public interface FileService extends BasicService {

	public com.xt8.model.File findById(Serializable id);

}
