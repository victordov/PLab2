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

<%
	/* Header */
%>
<%@ include file="/headerJSP.jsp"%>
<form method="post" action="updateStudent.jsp">
	<%
		GenericService<Student> genService = new StudentService(
				new StudentDAO());
		Student stud = new Student();
		String id = request.getParameter("id");
		Long no = Long.parseLong(id);
		stud = genService.getOne(no);
	%>
	</br></br>
	<table>
	<caption>Student Edit</caption>
	<th>Nume</th>
	<th>Prenume</th>
	<th>Grupa</th>
	<th>Email</th>
	<th>Telefon</th>
		<tr>
			<td><input type="text" name="Nume" 		value="<%=stud.getNume()%>"></td>
			<td><input type="text" name="Prenume"	value="<%=stud.getPrenume()%>"></td>
			<td><input type="text" name="Grupa" 	value="<%=stud.getGrupa()%>"></td>
			<td><input type="text" name="Email" 	value="<%=stud.getEmail()%>"></td>
			<td><input type="text" name="Telefon" 	value="<%=stud.getTelFix()%>"></td>
			<td><input type="hidden" name="id" 		value="<%=no%>"></td>
		</tr>
		<tr>
			<td><input type="submit" name="Submit" value="Update"
				style="background-color: #49743D; font-weight: bold; color: #ffffff;"></td>
		</tr>

	</table>
</form>