<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
	<head>
		<link href="<%=request.getContextPath()%>/style.css" rel="stylesheet" type="text/css">
		<title>Home Page</title>
	</head>
<body>
<!-- Header -->
<%@ include file="/headerJSP.jsp"%>
<div id="wrapper">

<h1>Acest website in JSP este un sablon pentru afisarea datelor HTML dintr-o baza de date</h1>
<p>Pentru a utiliza si a naviga pe site apasati in <strong>Meniul</strong> de sus.</p>
<br>
<br>
<h2><a href="<%=request.getContextPath() %>/UploadJSP.jsp">Insereaza Date Noi</a></h2>
</div>
<br>
<%out.print(getServletContext().getRealPath("/data"));%>
<!-- Footer -->
<%@ include file="/footerJSP.jsp"%>