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
<title>Universitate Insert</title>
</head>
<body>

	<form method="post" action="insertUnivForm.jsp">
		<%
			GenericService<Universitate> genService = new UniversitateService(
					new UnivDAO());
			Universitate univ = new Universitate();
			ArrayList<Universitate> arrayUniv = new ArrayList<Universitate>();
			arrayUniv = genService.getAll();
		%>
		<table>
			<caption>Monthly savings</caption>
			<tr>
				<th>ID</th>
				<th>Denumire</th>
				<th>Adresa</th>
				<th>Telefon</th>
			</tr>
			<tr>
				<td><input type="text" name="idUniversitate"
					value="<%=genService.getAll().size() + 1%>"></td>
				<td><input type="text" name="Denumire" value=""></td>
				<td><input type="text" name="Adresa" value=""></td>
				<td><input type="text" name="Telefon" value=""></td>

			</tr>
			<tr>
				<td colspan="6"><input type="submit" name="Submit"
					value="Insert"
					style="background-color: #49743D; font-weight: bold; color: #ffffff;"></td>
			</tr>

		</table>
	</form>

	<%
		if ("POST".equalsIgnoreCase(request.getMethod())
				&& request.getParameter("idUniversitate") != null) {
			String idUniverString = request.getParameter("idUniversitate");
			Long idUniversitate = Long.parseLong(idUniverString);

			String denumUniver = request.getParameter("Denumire");
			String adresaUniver = request.getParameter("Adresa");
			String telefonUniver = request.getParameter("Telefon");
			univ.setUniversitateId(idUniversitate);
			univ.setNumeUniversitate(denumUniver);
			univ.setAdresa(adresaUniver);
			univ.setTelefon(telefonUniver);
			if (genService.createFunction(univ) >= 0) {
				out.print("<p>A fost adaugata universitate</p>");
			} else {
				out.print("<p>Eroare: nu a fost aduagate date</p>");
			}
		}
	%>
	<a href="<%=request.getContextPath()%>/Universitate/UnivJSP.jsp">Apasa
		aici: <strong>Universitate</strong>
	</a>

	<%@ include file="/footerJSP.jsp"%>