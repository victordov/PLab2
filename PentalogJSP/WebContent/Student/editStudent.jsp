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
<title>Student Edit</title>
</head>
<body>

	<%@ include file="/headerJSP.jsp"%>
	<form method="post" action="editStudent.jsp">
		<%
			GenericService<Student> genService = new StudentService(
					new StudentDAO());
			Student stud = new Student();
			String id = request.getParameter("id");
			Long no = Long.parseLong(id);
			stud = genService.getOne(no);
		%>
		</br> </br>
		<table>
			<caption>Student Edit</caption>
			<th>Nume</th>
			<th>Prenume</th>
			<th>Grupa</th>
			<th>Email</th>
			<th>Telefon</th>
			<tr>
				<td><input type="text" name="Nume" value="<%=stud.getNume()%>"></td>
				<td><input type="text" name="Prenume"
					value="<%=stud.getPrenume()%>"></td>
				<td><input type="text" name="Grupa"
					value="<%=stud.getGrupa()%>"></td>
				<td><input type="text" name="Email"
					value="<%=stud.getEmail()%>"></td>
				<td><input type="text" name="Telefon"
					value="<%=stud.getTelFix()%>"></td>
				<td><input type="hidden" name="id" value="<%=no%>"></td>
			</tr>
			<tr>
				<td><input type="submit" name="Submit" value="Update"
					style="background-color: #49743D; font-weight: bold; color: #ffffff;"></td>
			</tr>

		</table>
	</form>

	<!-- Update Funtion -->
	<%
		if ("POST".equalsIgnoreCase(request.getMethod())
				&& (request.getParameter("id") != null)
				&& (request.getParameter("Nume") != null)
				&& (request.getParameter("Prenume") != null)
				&& (request.getParameter("Grupa") != null)
				&& (request.getParameter("Email") != null)
				&& (request.getParameter("Telefon") != null)) {

			String idStudentString = request.getParameter("id");
			Long idStudent = Long.parseLong(idStudentString);
			String numeStudent = request.getParameter("Nume");
			String prenumeStudent = request.getParameter("Prenume");
			String grupaStudent = request.getParameter("Grupa");
			String emailStudent = request.getParameter("Email");
			String telefonStudent = request.getParameter("Telefon");
			stud.setStudentId(idStudent);

			stud.setNume(numeStudent);
			stud.setPrenume(prenumeStudent);
			stud.setGrupa(grupaStudent);
			stud.setEmail(emailStudent);
			stud.setTelFix(telefonStudent);
			if (genService.updateFunction(stud) == true) {
				out.print("<p>A fost reiinoit</p>");
			} else {
				out.print("<p>Nu a fost reiinoit</p>");
			}
		}
	%>


	<a href="<%=request.getContextPath()%>/Student/StudentJSP.jsp">Apasa
		aici: <strong>Student</strong>
	</a>

	<!-- Footer -->
	<%@ include file="/footerJSP.jsp"%>