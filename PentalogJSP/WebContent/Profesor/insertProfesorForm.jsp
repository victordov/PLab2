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
		<link href="<%=request.getContextPath()%>/style.css" rel="stylesheet" type="text/css">
		<title>Profesor Insert Form</title>
	</head>
<body>

<!-- Header -->
<%@ include file="/headerJSP.jsp"%>

<form method="post" action="insertProfesorForm.jsp">
	<%
		GenericService<Profesor> genService = new ProfesorService(
				new ProfesorDAO());
		Profesor prof = new Profesor();
		ArrayList<Profesor> arrayProf = new ArrayList<Profesor>();
		arrayProf = genService.getAll();
	%>
	<table>
		<caption>Profesor Insert</caption>
		<tr>
			<th>ID</th>
			<th>Nume</th>
			<th>Prenume</th>
			<th>Adresa</th>
		</tr>
		<tr>
			<td><input type="text" name="id"
				value="<%=genService.getAll().size() + 1%>"></td>
			<td><input type="text" name="Nume" value=""></td>
			<td><input type="text" name="Prenume" value=""></td>
			<td><input type="text" name="Adresa" value=""></td>
		</tr>
		<tr>
			<td><input type="submit" name="Submit" value="Insert"
				style="background-color: #49743D; font-weight: bold; color: #ffffff;"></td>
		</tr>

	</table>
</form>

<!-- Insert form funcition -->
<%
		if ("POST".equalsIgnoreCase(request.getMethod())
				&& request.getParameter("id") != null
				&& (request.getParameter("Nume") != null)
				&& (request.getParameter("Prenume") != null)
				&& (request.getParameter("Adresa") != null)) {

			String idProfesorString = request.getParameter("id");
			Long idProfesor = Long.parseLong(idProfesorString);

			String numeProfesor = request.getParameter("Nume");
			String prenumeProfesor = request.getParameter("Prenume");
			String adresaProfesor = request.getParameter("Adresa");

			prof.setProfesorId(idProfesor);
			prof.setNume(numeProfesor);
			prof.setPrenume(prenumeProfesor);
			prof.setAdresa(adresaProfesor);
			if (genService.createFunction(prof) >=0) {
				out.print("<p>A fost adaugat cu succes</p>");
			} else {
				out.print("<p>Eroare: Nu a putut fi adaugat</p>");
			}
		}
	%>
	<a href="<%=request.getContextPath()%>/Profesor/ProfesorJSP.jsp">Apasa
		aici: <strong>Profesor</strong>
	</a>

<!-- Footer -->
<%@ include file="/footerJSP.jsp"%>