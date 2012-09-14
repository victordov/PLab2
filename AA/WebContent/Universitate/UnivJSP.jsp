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
<title>Universitate</title>
</head>
<body>
	<!-- Header -->
	<%@ include file="/headerJSP.jsp"%>
	<!-- Script pentru Update -->
	<script>
function editRecord(idUniversitate){
    var f=document.form;
    f.method="post";
    f.action='editUniv.jsp?idUniversitate='+idUniversitate;
    f.submit();
}
</script>

	<script>
function deleteRecord(idUniversitate){
    var f=document.form;
    f.method="post";
    f.action='deleteUniv.jsp?idUniversitate='+idUniversitate;
    f.submit();
}
</script>

	<%
		GenericService<Universitate> genService = new UniversitateService(
				new UnivDAO());
		ArrayList<Universitate> univList = new ArrayList<Universitate>();
		univList = genService.getAll();
	%>
	</br>
	</br>
	</br>
	</br>
	<form method="post" name="form">
		<table border="0" cellpadding="3">
		<caption>Universitate</caption>
			<thead>
				<tr>
					<th>ID</th>
					<th>Denumire</th>
					<th>Adresa</th>
					<th>Telefon</th>
					<th>Edit</th>
					<th>Delete</th>
					<th>Insert</th>
				</tr>
			</thead>
			<%
				int countID = 0;
				for (int i = 0; i < univList.size(); i++) {
			%>
			<tr>
				<td><%=univList.get(i).getUniversitateId()%></td>
				<td><%=univList.get(i).getNumeUniversitate()%></td>
				<td><%=univList.get(i).getAdresa()%></td>
				<td><%=univList.get(i).getTelefon()%></td>
				<td><input type="button" name="edit" value="Edit"
					style="background-color: green; font-weight: bold; color: white;"
					onclick="editRecord(<%=univList.get(i).getUniversitateId()%>);"></td>
				<td><input type="button" name="delete" value="Delete"
					style="background-color: red; font-weight: bold; color: white;"
					onclick="deleteRecord(<%=univList.get(i).getUniversitateId()%>);"></td>
				<%
					if (i == 0) {
							out.print("<td rowspan = \""
									+ univList.size()
									+ "\"><a href=\"insertUnivForm.jsp\"><input type=\"button\" name=\"insert\"");
							out.print(" value=\"Insert\" style=\"background-color:blue;font-weight:bold;color:white;\" ></a></td>");
						}
				%>
			</tr>
			<%
				}
			%>
		</table>
	</form>
	<%@ include file="/footerJSP.jsp"%>