<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="md.*"%>
<%@ page import="md.victordov.lab.vo.*"%>
<%@ page import="md.victordov.lab.common.exception.MyDaoException"%>
<%@ page import="md.victordov.lab.dao.ProfesorDAO"%>
<%@ page import="md.victordov.lab.services.GenericService"%>
<%@ page import="md.victordov.lab.services.ProfesorService"%>
<%@ page import="md.victordov.lab.vo.Profesor"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.util.ArrayList"%>

<%
	/* Header */
%>
<%@ include file="/headerJSP.jsp"%>
<form method="post" action="updateProfesor.jsp">
	<%
		GenericService<Profesor> genService = new ProfesorService(
				new ProfesorDAO());
		Profesor prof = new Profesor();
		String id ="0";
		try{
			id = request.getParameter("id");
		}catch(Exception e){
			id = "0";
		}
		
		Long no = 0L;
		try{
			no = Long.parseLong(id);
		}catch(Exception e){
			no = 0L;
		}
		prof = genService.getOne(no);
	%>
	</br></br>
	<table>
	<caption>Profesor Edit</caption>
	<th>Nume</th>
	<th>Prenume</th>
	<th>Adresa</th>
		<tr>
			<td><input type="text" name="Nume" 		value="<%=prof.getNume()%>"></td>
			<td><input type="text" name="Prenume"	value="<%=prof.getPrenume()%>"></td>
			<td><input type="text" name="Adresa" 	value="<%=prof.getAdresa()%>"></td>
			<td><input type="hidden" name="id" 		value="<%=no%>"></td>
		</tr>
		<tr>
			<td><input type="submit" name="Submit" value="Update"
				style="background-color: #49743D; font-weight: bold; color: #ffffff;"></td>
		</tr>
	</table>
</form>
<!-- Footer -->
<%@ include file="/footerJSP.jsp"%>