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
<%@ include file="/headerJSP.jsp"%>

<form method="post" action="insertUniv.jsp">
	<%
		GenericService<Universitate> genService = new UniversitateService(
				new UnivDAO());
		Universitate univ = new Universitate();
		ArrayList<Universitate> arrayUniv = new ArrayList<Universitate>();
		arrayUniv = genService.getAll();
		%>
	<table>
	<caption>Monthly savings</caption>
	<th>ID</th>
	<th>Denumire</th>
	<th>Adresa</th>
	<th>Telefon</th>
		<tr>
		<td><input type="text" name="id" value="<%=genService.getAll().size()+1 %>"></td>
			<td><input type="text" name="Denumire"
				value=""></td>
			<td><input type="text" name="Adresa"
				value=""></td>
			<td><input type="text" name="Telefon"
				value=""></td>
			
		</tr>
		<tr>
			<td><input type="submit" name="Submit" value="Insert"
				style="background-color: #49743D; font-weight: bold; color: #ffffff;"></td>
		</tr>

	</table>
	</form>