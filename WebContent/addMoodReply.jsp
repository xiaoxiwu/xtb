<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>modifyGoodsAddr</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="easyTalk">
<meta http-equiv="description" content="This is my page">
</head>
<body>

	<form action="rest/mood/replyMood.do" method="post">
		<input type="text" name="apiKey" value="apiKey"> 
		<input type="text" name="userId" value="selfId">
		<input type="text" name="moodId" value="moodId">
		<input type="text" name="commentContent" value="commentContent">
		 <input type="submit" value="submit">
	</form>
</body>
</html>