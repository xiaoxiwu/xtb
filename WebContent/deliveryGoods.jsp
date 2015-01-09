<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>deliveryGoods</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="easyTalk">
<meta http-equiv="description" content="This is my page">
</head>
<body>

	<form action="rest/goods/deliveryGoods.do" method="post">
		<input type="text" name="apiKey" value="apiKey"> 
		<input type="text" name="MyId" value="MyId">
		<input type="text" name="orderId" value="orderId">
		<input type="text" name="notifyId" value="notifyId">
		<input type="text" name="provinceId" value="provinceId">
		<input type="text" name="cityId" value="cityId">
		<input type="text" name="districtId" value="districtId">
		<input type="text" name="personName" value="personName">
		<input type="text" name="telphone" value="telphone">
		<input type="text" name="detailAddr" value="detailAddr">
		<input type="text" name="expressCorpId" value="expressCorpId">
	    <input type="submit" value="submit">
	</form>
</body>
</html>