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
<title>Universitate Edit</title>
</head>
<body>
	<%@ include file="/headerJSP.jsp"%>
	<form method="post" action="insertStudentForm.jsp">
		<%
			GenericService<Student> genService = new StudentService(
					new StudentDAO());
			Student stud = new Student();
			ArrayList<Student> arrayStud = new ArrayList<Student>();
			arrayStud = genService.getAll();
		%>
		<table>
			<caption>Student Insert</caption>
			<th>ID</th>
			<th>Nume</th>
			<th>Prenume</th>
			<th>Grupa</th>
			<th>Email</th>
			<th>Telefon</th>
			<tr>
				<td><input type="text" name="id"
					value="<%=genService.getAll().size() + 1%>"></td>
				<td><input type="text" name="Nume" value=""></td>
				<td><input type="text" name="Prenume" value=""></td>
				<td><input type="text" name="Grupa" value=""></td>
				<td><input type="text" name="Email" value=""></td>
				<td><input type="text" name="Telefon" value=""></td>
			</tr>
			<tr>
				<td><input type="submit" name="Submit" value="Insert"
					style="background-color: #49743D; font-weight: bold; color: #ffffff;"></td>
			</tr>

		</table>
	</form>

	<!-- Insert Function -->
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

			if (genService.createFunction(stud) >= 0) {
				out.print("<p>A fost adaugat cu succes<p>");
			} else {
				out.print("<p>Eroare:Nu a fost adaugat nimic<p>");
			}

		}
	%>
	<a href="<%=request.getContextPath()%>/Student/StudentJSP.jsp">Apasa
		aici: <strong>Student</strong>
	</a>

	<!-- Footer -->
	<%@ include file="/footerJSP.jsp"%>