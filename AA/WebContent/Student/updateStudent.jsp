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

<!-- Header -->
<%@ include file="/headerJSP.jsp"%>
<%
	GenericService<Student> genService = new StudentService(
			new StudentDAO());
	Student stud = new Student();
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
%>
<p>A fost reiinoit</p>
<a href="<%=request.getContextPath()%>/Student/StudentJSP.jsp">Apasa
	aici: <strong>Student</strong>
</a>

<%
	} else {
%>
<p>Eroare</p>
<%
	}
%>

<!-- Footer -->
<%@ include file="/footerJSP.jsp"%>
