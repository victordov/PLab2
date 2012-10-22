<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="md.*"%>
<%@ page import="md.victordov.lab.vo.*"%>
<%@ page import="md.victordov.lab.common.exception.MyDaoException"%>
<%@ page import="md.victordov.lab.dao.UnivDAO"%>
<%@ page import="md.victordov.lab.services.GenericService"%>
<%@ page import="md.victordov.lab.services.UniversitateService"%>
<%@ page import="md.victordov.lab.vo.Universitate"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.util.ArrayList"%>

<html>
<head>
<link href="<%=request.getContextPath()%>/style.css" rel="stylesheet"
	type="text/css">
<title>Home Page</title>
</head>
<body>

	<!-- Header -->
	<%@ include file="/headerJSP.jsp"%>

	<%
		GenericService<Universitate> genService = new UniversitateService(
				new UnivDAO());
		Universitate univ = new Universitate();
		String idUniverString = request.getParameter("idUniversitate");
		Long idUniversitate = Long.parseLong(idUniverString);

		if (genService.deleteFunction(idUniversitate) == true) {
	%>
	<p>A fost sters</p>

	<%
		} else {
	%>
	<p>Eroare, nu a putut fi sters</p>
	<%
		}
	%>

	<a href="<%=request.getContextPath()%>/Universitate/UnivJSP.jsp">Apasa
		aici: <strong>Universitate</strong>
	</a>

	<!-- Footer -->
	<%@ include file="/footerJSP.jsp"%>