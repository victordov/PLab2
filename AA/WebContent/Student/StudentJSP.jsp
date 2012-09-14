<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="md.*"%>
<%@ page import="md.victordov.lab.vo.*"%>
<%@ page import="md.victordov.lab.common.exception.MyDaoException"%>
<%@ page import="md.victordov.lab.dao.StudentDAO"%>
<%@ page import="md.victordov.lab.services.GenericService"%>
<%@ page import="md.victordov.lab.services.StudentService"%>
<%@ page import="md.victordov.lab.vo.Student"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.util.ArrayList"%>

<html>
<head>
<link href="<%=request.getContextPath()%>/style.css" rel="stylesheet"
	type="text/css">
<title>Student</title>
</head>
<body>
	<!-- Header -->
	<%@ include file="/headerJSP.jsp"%>

	<!-- Script pentru Update -->
	<script >
function editRecord(id){
    var f=document.form;
    f.method="post";
    f.action='editStudent.jsp?id='+id;
    f.submit();
}
</script>

	<script>
function deleteRecord(id){
    var f=document.form;
    f.method="post";
    f.action='deleteStudent.jsp?id='+id;
    f.submit();
}
</script>

	<%
		GenericService<Student> genService = new StudentService(
				new StudentDAO());
		ArrayList<Student> studList = new ArrayList<Student>();
		studList = genService.getAll();
	%>
	</br>
	</br>
	</br>
	</br>
	<form method="post" name="form">
		<table border="0" cellpadding="3">
			<caption>Student</caption>
			<thead>
				<tr>
					<th>ID</th>
					<th>Nume</th>
					<th>Prenume</th>
					<th>Grupa</th>
					<th>Email</th>
					<th>Telefon</th>
					<th>Edit</th>
					<th>Delete</th>
					<th align="center">Insert</th>
				</tr>
			</thead>
			<%
				int countID = 0;
				for (int i = 0; i < studList.size(); i++) {
			%>
			<tr>
				<td><%=studList.get(i).getStudentId()%></td>
				<td><%=studList.get(i).getNume()%></td>
				<td><%=studList.get(i).getPrenume()%></td>
				<td><%=studList.get(i).getGrupa()%></td>
				<td><%=studList.get(i).getEmail()%></td>
				<td><%=studList.get(i).getTelFix()%></td>
				<td><input type="button" name="edit" value="Edit"
					style="background-color: green; font-weight: bold; color: white;"
					onclick="editRecord(<%=studList.get(i).getStudentId()%>);"></td>
				<td><input type="button" name="delete" value="Delete"
					style="background-color: red; font-weight: bold; color: white;"
					onclick="deleteRecord(<%=studList.get(i).getStudentId()%>);"></td>
			</tr>
			<%
				}
			%>
			<tr>
				<td align="right" colspan="<%=studList.size()%>">
					<%
						out.print("<td rowspan = \""
								+ studList.size()
								+ "\"><a href=\"insertStudentForm.jsp\"><input type=\"button\" name=\"insert\"");
						out.print(" value=\"Insert\" style=\"background-color:blue;font-weight:bold;color:white;\" ></a></td>");
					%>
				</td>
			</tr>
		</table>
	</form>

	<!-- Footer -->
	<%@ include file="/footerJSP.jsp"%>