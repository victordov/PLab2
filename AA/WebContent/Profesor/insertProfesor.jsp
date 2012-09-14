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
<title>Profesor Insert</title>
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

		String numeProfesor = request.getParameter("Nume");
		String prenumeProfesor = request.getParameter("Prenume");
		String adresaProfesor = request.getParameter("Adresa");
		prof.setProfesorId(idProfesor);
		prof.setNume(numeProfesor);
		prof.setPrenume(prenumeProfesor);
		prof.setAdresa(adresaProfesor);
		if (genService.createFunction(prof) >= 0) {
	%>
	<p>A fost adaugat Profesor</p>
	<%
		} else {
	%>
	<p>Eroare</p>
	<%
		}
	%>
	<a href="<%=request.getContextPath()%>/Profesor/ProfesortJSP.jsp">Apasa
		aici: <strong>Profesor</strong>
	</a>
	<!-- Footer -->
	<%@ include file="/footerJSP.jsp"%>