<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>modifyGoodsCategory</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="easyTalk">
<meta http-equiv="description" content="This is my page">
</head>
<body>

	<form action="rest/goods/modifyGoodsCategory.do" method="post">
		<input type="text" name="apiKey" value="apiKey"> 
		<input type="text" name="MyId" value="MyId">
		<input type="text" name="goodsId" value="goodsId">
		<input type="text" name="goodsCategoryId" value="goodsCategoryId">
		 <input type="submit" value="submit">
	</form>
</body>
</html>