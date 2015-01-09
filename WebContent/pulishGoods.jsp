<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>publishGoods</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="easyTalk">
<meta http-equiv="description" content="This is my page">
</head>
<body>

	<form action="rest/goods/publishGoods.do" method="post">
		<input type="text" name="apiKey" value="apiKey"> 
		<input type="text" name="MyId" value="MyId">
		<input type="text" name="goodsName" value="goodsName">
		<input type="text" name="goodsSort" value="goodsSort"> 
		<input type="text" name="goodsImagePath" value="http://121.0.0.7/a.jpg"> 
		<input type="text" name="goodsProvince" value="110000"> 
		<input type="text" name="goodsCity" value="131000"> 
		<input type="text" name="goodsWeight" value="1"> 
		<input type="text" name="goodsPostageType" value="1"> 
		<input type="text" name="tag" value="C++"> 
		<input type="text" name="goodsExpireTime" value="1"> 
	<!-- 	<input type="text" name="receiverId" value="9">     -->
		<input type="text" name="description" value="编程">  
		<input type="submit" value="submit">
	</form>
</body>
</html>