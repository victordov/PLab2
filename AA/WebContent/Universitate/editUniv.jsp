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

<%
	/* Header */
%>
<%@ include file="/headerJSP.jsp"%>
<form method="post" action="updateUniv.jsp">
	<%
		GenericService<Universitate> genService = new UniversitateService(
				new UnivDAO());
		Universitate univ = new Universitate();
		String id = request.getParameter("id");
		Long no = Long.parseLong(id);
		univ = genService.getOne(no);
	%>
	<table>
		<tr>
			<td><input type="text" name="Denumire"
				value="<%=univ.getNumeUniversitate()%>"></td>
			<td><input type="text" name="Adresa"
				value="<%=univ.getAdresa()%>"></td>
			<td><input type="text" name="Telefon"
				value="<%=univ.getTelefon()%>"></td>
			<td><input type="hidden" name="id" value="<%=no%>"></td>
		</tr>
		<tr>
			<td><input type="submit" name="Submit" value="Update"
				style="background-color: #49743D; font-weight: bold; color: #ffffff;"></td>
		</tr>

	</table>