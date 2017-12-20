<%@page import="practice.sv.bai1.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show</title>
</head>
<body>
	<%List<Student>listSt=(List<Student>)request.getAttribute("listStudent");
		for(Student st:listSt){
	%>
		<tr>
		<td>
		<%=st.toString()%>
		</td>
		<br>
		</tr>
	<%}%>
</body>
</html>