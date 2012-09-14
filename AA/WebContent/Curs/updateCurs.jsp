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
		<title>Curs Update</title>
	</head>
<body>

<!-- Header -->
<%@ include file="/headerJSP.jsp"%>
<%
	GenericService<Curs> genService = new CursService(
			new CursDAO());
	Curs curs = new Curs();
	String idCursString = request.getParameter("id");
	Long idCurs = Long.parseLong(idCursString);

	String numeCurs = request.getParameter("Nume");
	Long univerID = Long.parseLong(request.getParameter("UniverID"));
	Long profID = Long.parseLong(request.getParameter("ProfID"));

	curs.setCursId(idCurs);
	curs.setNumeCurs(numeCurs);
	curs.setUniversitateId(univerID);
	curs.setProfesorId(profID);
	if (genService.updateFunction(curs) == true) {
%>
<p>A fost reiinoit</p>
<%
	} else {
%>
<p>Eroare</p>
<%
	}
%>
<p>Apasa aici: <a href= "<%=request.getContextPath() %>/Curs/CursJSP.jsp">Curs Meniu</a></p>
<!-- Footer -->
<%@ include file="/footerJSP.jsp"%>