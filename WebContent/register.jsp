<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>register</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="easyTalk">
<meta http-equiv="description" content="This is my page">
</head>
<body>

	<form action="rest/user/register.do" method="post">
		<table>
			<tr>
				<td>phone</td>
				<td><input type="text" name="phone"></td>
			</tr>
			<tr>
				<td>password</td>
				<td><input type="text" name="password" value="111"></td>
			</tr>
			<tr>
				<td>nickName</td>
				<td><input type="text" name="nickName"></td>
			</tr>
			<tr>
				<td>simpleCode</td>
				<td><input type="text" name="simpleWord" value="1234"></td>
			</tr>
		</table>
		<input type="submit" value="submit">
	</form>
</body>
</html>