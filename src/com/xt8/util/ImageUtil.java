package com.xt8.util;

import com.xt8.model.Image;

public class ImageUtil {

	public static Image setImageNameTypeFromPath(String imagePath) {
		String type = null;
		String name = null;
		try {
			type = imagePath.substring(imagePath.lastIndexOf(".") + 1,
					imagePath.length());
		} catch (Exception e) {
			type = null;
			e.printStackTrace();
		}
		try {
			name = imagePath.substring(imagePath.lastIndexOf("/") + 1,
					imagePath.lastIndexOf("."));
		} catch (Exception e) {
			name = null;
			e.printStackTrace();
		}
		Image image = new Image();
		image.setImagePath(imagePath);
		image.setImageType(type);
		image.setImageName(name);
		return image;

	}

}
