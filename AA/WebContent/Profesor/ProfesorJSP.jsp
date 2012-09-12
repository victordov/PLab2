<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="md.*"%>
<%@ page import="md.victordov.lab.vo.*"%>
<%@ page import="md.victordov.lab.common.exception.MyDaoException"%>
<%@ page import="md.victordov.lab.dao.ProfesorDAO"%>
<%@ page import="md.victordov.lab.services.GenericService"%>
<%@ page import="md.victordov.lab.services.ProfesorService"%>
<%@ page import="md.victordov.lab.vo.Universitate"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.util.ArrayList"%>
<%@ include file="/headerJSP.jsp"%>


<%
	/* Header */
%>
<!-- Script pentru Update -->
<script language="javascript">
function editRecord(id){
    var f=document.form;
    f.method="post";
    f.action='editProfesor.jsp?id='+id;
    f.submit();
}
</script>

<script language="javascript">
function deleteRecord(id){
    var f=document.form;
    f.method="post";
    f.action='deleteProfesor.jsp?id='+id;
    f.submit();
}
</script>

<%
	GenericService<Profesor> genService = new ProfesorService(
			new ProfesorDAO());
	ArrayList<Profesor> profList = new ArrayList<Profesor>();
	profList = genService.getAll();
%>
</br>
</br>
</br>
<form method="post" name="form">
	<table border="0" cellpadding="3">
	<caption>Profesor</caption>
		<thead>
			<th>ID</th>
			<th>Nume</th>
			<th>Prenume</th>
			<th>Adresa</th>
			<th>Edit</th>
			<th>Delete</th>
			<th align="center">Insert</th>
		</thead>
		<%
			int countID = 0;
			for (int i = 0; i < profList.size(); i++) {
		%>
		<tr>
			<td><%=profList.get(i).getProfesorId()%></td>
			<td><%=profList.get(i).getNume()%></td>
			<td><%=profList.get(i).getPrenume()%></td>
			<td><%=profList.get(i).getAdresa()%></td>
			<td><input type="button" name="edit" value="Edit"
				style="background-color: green; font-weight: bold; color: white;"
				onclick="editRecord(<%=i + 1%>);"></td>
			<td><input type="button" name="delete" value="Delete"
				style="background-color: red; font-weight: bold; color: white;"
				onclick="deleteRecord(<%=profList.get(i).getProfesorId()%>);"></td>
		</tr>
		<%
			}
		%>
		<tr>
			<td align="right" colspan="<%=profList.size()%>">
				<%
					out.print("<td rowspan = \""
							+ profList.size()
							+ "\"><a href=\"insertProfesorForm.jsp\"><input type=\"button\" name=\"insert\"");
					out.print(" value=\"Insert\" style=\"background-color:blue;font-weight:bold;color:white;\" ></a></td>");
				%>
			</td>
		</tr>
	</table>
</form>

<!-- Footer -->
<%@ include file="/footerJSP.jsp"%>