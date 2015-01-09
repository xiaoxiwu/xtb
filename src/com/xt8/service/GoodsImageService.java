package com.xt8.service;

import java.io.Serializable;
import java.util.List;

import com.xt8.model.Goods;
import com.xt8.model.GoodsImage;
import com.xt8.model.Image;

public interface GoodsImageService extends BasicService {

	public GoodsImage insert(GoodsImage goodsImage);
	
	public GoodsImage findById(Serializable id);
	
	public List<GoodsImage> findByGoods(Goods goods);
	
	public Image findImageByGoods(Goods goods);

}
