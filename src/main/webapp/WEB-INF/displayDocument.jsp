<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Display Document</title>
</head>
<body>
	<form action="uploadDocument" method="post"
		enctype="multipart/form-data">
		<pre>
Id: <input type="text" name="id" />
Name: <input type="file" name="document" />
<input type="submit" value="upload" />

</pre>
	</form>
	<table>
		<tr>
			<th>id</th>
			<th>Name</th>
			<th>link</th>
		</tr>
		<c:forEach items="${documents}" var="document">
			<tr>

				<td>${document.id}</td>
				<td>${document.name}</td>
				<td><a href="downloadDocument?id=${document.id}">download</a></td>


			</tr>
		</c:forEach>
	</table>


</body>
</html>