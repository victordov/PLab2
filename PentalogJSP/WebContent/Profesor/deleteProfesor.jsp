<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="md.*"%>
<%@ page import="md.victordov.lab.vo.*"%>
<%@ page import="md.victordov.lab.common.exception.MyDaoException"%>
<%@ page import="md.victordov.lab.dao.ProfesorDAO"%>
<%@ page import="md.victordov.lab.services.GenericService"%>
<%@ page import="md.victordov.lab.services.ProfesorService"%>
<%@ page import="md.victordov.lab.vo.Profesor"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.util.ArrayList"%>

<html>
<head>
<link href="<%=request.getContextPath()%>/style.css" rel="stylesheet"
	type="text/css">
<title>Profesor Delete</title>
</head>
<body>

	<!-- Header -->
	<%@ include file="/headerJSP.jsp"%>

	<%
		GenericService<Profesor> genService = new ProfesorService(
				new ProfesorDAO());
		Profesor prof = new Profesor();
		String idProfesorString = request.getParameter("id");
		Long idProfesor = Long.parseLong(idProfesorString);

		if (genService.deleteFunction(idProfesor) == true) {
	%>
	<p>Profesorul fost sters</p>

	<%
		} else {
	%>
	<p>Eroare, nu a putut fi sters</p>
	<p>Stergeti referinta din alt tabel</p>
	<%
		}
	%>
	<a href="<%=request.getContextPath()%>/Profesor/ProfesorJSP.jsp">Apasa
		aici: <strong>Profesor</strong>
	</a>