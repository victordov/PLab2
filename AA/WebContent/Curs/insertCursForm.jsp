<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="md.*"%>
<%@ page import="md.victordov.lab.vo.*"%>
<%@ page import="md.victordov.lab.common.exception.MyDaoException"%>
<%@ page import="md.victordov.lab.dao.CursDAO"%>
<%@ page import="md.victordov.lab.services.GenericService"%>
<%@ page import="md.victordov.lab.services.CursService"%>
<%@ page import="md.victordov.lab.vo.Curs"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.util.ArrayList"%>

<html>
	<head>
		<link href="<%=request.getContextPath()%>/style.css" rel="stylesheet" type="text/css">
		<title>Curs Insert Form</title>
	</head>
<body>

<!-- Header -->
<%@ include file="/headerJSP.jsp"%>

<form method="post" action="insertCurs.jsp">
	<%
		GenericService<Curs> genService = new CursService(
				new CursDAO());
		Curs univ = new Curs();
		ArrayList<Curs> arrayCurs = new ArrayList<Curs>();
		arrayCurs = genService.getAll();
	%>
	<table>
		<caption>Curs Insert</caption>
		<tr>
			<th>ID</th>
			<th>Nume</th>
			<th>Univer ID</th>
			<th>Prof ID</th>
		</tr>
		<tr>
			<td><input type="text" name="id"
				value="<%=genService.getAll().size() + 1%>"></td>
			<td><input type="text" name="Nume" value=""></td>
			<td><input type="text" name="UniverID" value=""></td>
			<td><input type="text" name="ProfID" value=""></td>
		</tr>
		<tr>
			<td><input type="submit" name="Submit" value="Insert"
				style="background-color: #49743D; font-weight: bold; color: #ffffff;"></td>
		</tr>

	</table>
</form>

<!-- Footer -->
<%@ include file="/footerJSP.jsp"%>