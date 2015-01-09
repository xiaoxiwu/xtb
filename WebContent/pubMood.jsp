<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>login</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="easyTalk">
<meta http-equiv="description" content="This is my page">
</head>
<body>

	<%
		request.setAttribute("name", "teng");
	%>
	<form action="rest/mood/publishMood.do" method="post">
		<table>
			<tr>
				<td>userId</td>
				<td><input type="text" name="userId" value="1"></td>
			</tr>
			<tr>
				<td>apiKey</td>
				<td><input type="text" name="apiKey" value="123456"></td>
			</tr>
			<tr>
				<td>moodContent</td>
				<td><input type="text" name="moodContent"
					value="hello,i am teng"></td>
			</tr>
			<tr>
				<td>moodImagePath</td>
				<td><input type="text" name="moodImagePath"
					value="http://www.aliyun.com/file/pict/happy.jpg"></td>
			</tr>
			<tr>
				<td><input type="reset" value="reset"></td>
				<td><input type="submit" value="submit"></td>
			</tr>
		</table>
	</form>
</body>
</html>