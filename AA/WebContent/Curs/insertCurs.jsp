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
		<title>Curs Insert</title>
	</head>
<body>

<!-- Header -->
<%@ include file="/headerJSP.jsp"%>
<%
	GenericService<Curs> genService = new CursService(new CursDAO());
	Curs curs= new Curs();
	String idCursString = request.getParameter("id");
	Long idCurs = Long.parseLong(idCursString);
	

	String numeCurs = request.getParameter("Nume");
	Long idUniver = Long.parseLong(request.getParameter("UniverID"));
	Long idProf = Long.parseLong(request.getParameter("ProfID"));
	curs.setCursId(idCurs);
	curs.setNumeCurs(numeCurs);
	curs.setUniversitateId(idUniver);
	curs.setProfesorId(idProf);
	if (genService.createFunction(curs) >= 0) {
%>
<p>A fost adaugat Curs</p>
<script type="text/JavaScript">
	setTimeout("location.href = '/AA/Curs/CursJSP.jsp';", 1500);
</script>
<%
	} else {
%>
<p>Eroare</p>
<%
	}
%>
<!-- Footer -->
<%@ include file="/footerJSP.jsp"%>
