package com.xt8.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xt8.model.Goods;
import com.xt8.model.GoodsImage;
import com.xt8.model.Image;
import com.xt8.service.GoodsImageService;

@Transactional
public class GoodsImageServiceImpl extends BasicServiceImpl implements
		GoodsImageService {

	@Override
	public GoodsImage insert(GoodsImage goodsImage) {
		return (GoodsImage) super.saveAndReturn(GoodsImage.class, goodsImage);
	}

	@Override
	public GoodsImage findById(Serializable id) {
		// TODO Auto-generated method stub
		return (GoodsImage) super.findById(GoodsImage.class, id);
	}

	@Override
	public List<GoodsImage> findByGoods(Goods goods) {
		// TODO Auto-generated method stub
		String hql = "from GoodsImage bean where bean.goods = ?";
		Object[] params = { goods };
		List<GoodsImage> list = super.executeQuery(hql, params);

		if (null == list || list.isEmpty()) {
			return null;
		} else {
			return list;
		}
	}

	@Override
	public Image findImageByGoods(Goods goods) {// 暂时先假设只有一张图片
		// TODO Auto-generated method stub
		GoodsImage goodsImage = findByGoods(goods).get(0);
		return goodsImage.getImage();
	}

}
