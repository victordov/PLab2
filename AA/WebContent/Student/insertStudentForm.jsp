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
<%@ include file="/headerJSP.jsp"%>

<form method="post" action="insertStudent.jsp">
	<%
		GenericService<Student> genService = new StudentService(
				new StudentDAO());
		Student univ = new Student();
		ArrayList<Student> arrayUniv = new ArrayList<Student>();
		arrayUniv = genService.getAll();
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