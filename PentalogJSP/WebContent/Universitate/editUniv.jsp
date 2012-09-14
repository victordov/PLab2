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
<title>Universitate Edit</title>
</head>
<body>
	<%@ include file="/headerJSP.jsp"%>
	<form method="post" action="editUniv.jsp">
		<%
			GenericService<Universitate> genService = new UniversitateService(
					new UnivDAO());
			Universitate univ = new Universitate();
			String id = request.getParameter("idUniversitate");
			Long no = Long.parseLong(id);
			univ = genService.getOne(no);
		%>
		<table>
			<tr>
				<th>Denumire</th>
				<th>Adresa</th>
				<th>Telefon</th>
			</tr>
			<tr>
				<td><input type="text" name="Denumire"
					value="<%=univ.getNumeUniversitate()%>"></td>
				<td><input type="text" name="Adresa"
					value="<%=univ.getAdresa()%>"></td>
				<td><input type="text" name="Telefon"
					value="<%=univ.getTelefon()%>"></td>
				<td><input type="hidden" name="idUniversitate" value="<%=no%>"></td>
			</tr>
			<tr>
				<td><input type="submit" name="Submit" value="Update"
					style="background-color: #49743D; font-weight: bold; color: #ffffff;"></td>
			</tr>

		</table>
	</form>

	<%
		if ("POST".equalsIgnoreCase(request.getMethod())
				&& request.getParameter("idUniversitate") != null
				&& (request.getParameter("Denumire") != null)
				&& (request.getParameter("Adresa") != null)
				&& (request.getParameter("Telefon") != null)) {
			String idUniverString = request.getParameter("idUniversitate");
			Long idUniversitate = Long.parseLong(idUniverString);

			String denumUniver = request.getParameter("Denumire");
			String adresaUniver = request.getParameter("Adresa");
			String telefonUniver = request.getParameter("Telefon");

			univ.setUniversitateId(idUniversitate);
			univ.setNumeUniversitate(denumUniver);
			univ.setAdresa(adresaUniver);
			univ.setTelefon(telefonUniver);
			if (genService.updateFunction(univ) == true) {
				out.print("<p>A fost Reiinoit</p>");
			} else {
				out.print("<p>Eroare</p>");
			}

		}
	%>

	<a href="<%=request.getContextPath()%>/Universitate/UnivJSP.jsp">Apasa
		aici: <strong>Universitate</strong>
	</a>

	<%@ include file="/footerJSP.jsp"%>