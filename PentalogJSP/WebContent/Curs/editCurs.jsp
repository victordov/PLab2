<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="md.*"%>
<%@ page import="md.victordov.lab.vo.*"%>
<%@ page import="md.victordov.lab.common.exception.MyDaoException"%>
<%@ page import="md.victordov.lab.dao.CursDAO"%>
<%@ page import="md.victordov.lab.services.GenericService"%>
<%@ page import="md.victordov.lab.services.CursService"%>
<%@ page import="md.victordov.lab.vo.Curs"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.util.ArrayList"%>

<html>
<head>
<link href="<%=request.getContextPath()%>/style.css" rel="stylesheet"
	type="text/css">
<title>Curs Edit</title>
</head>
<body>

	<!-- Header -->
	<%@ include file="/headerJSP.jsp"%>
	<div id="wrapper">
		<form method="post" action="updateCurs.jsp">
			<%
				GenericService<Curs> genService = new CursService(new CursDAO());
				Curs curs = new Curs();
				String id = "0";
				try {
					id = request.getParameter("id");
				} catch (Exception e) {
					id = "0";
				}

				Long no = 0L;
				try {
					no = Long.parseLong(id);
				} catch (Exception e) {
					no = 0L;
				}
				curs = genService.getOne(no);
			%>
			</br> </br>
			<table>
				<caption>Curs Edit</caption>
				<thead>
					<tr>
						<th>Nume</th>
						<th>Prenume</th>
						<th>Adresa</th>
					</tr>
				</thead>
				<tr>
					<td><input type="text" name="Nume"
						value="<%=curs.getNumeCurs()%>"></td>
					<td><input type="text" name="UniverID"
						value="<%=curs.getUniversitateId()%>"></td>
					<td><input type="text" name="ProfID"
						value="<%=curs.getProfesorId()%>"></td>
					<td><input type="hidden" name="id" value="<%=no%>"></td>
				</tr>
				<tr>
					<td><input type="submit" name="Submit" value="Update"
						style="background-color: #49743D; font-weight: bold; color: #ffffff;"></td>
				</tr>
			</table>
		</form>
	</div>

	<!-- Edit form function -->
	<%
		if ("POST".equalsIgnoreCase(request.getMethod())
				&& request.getParameter("id") != null
				&& (request.getParameter("Nume") != null)
				&& (request.getParameter("UniverID") != null)
				&& (request.getParameter("ProfID") != null)) {
			String idCursString = request.getParameter("id");
			Long idCurs = Long.parseLong(idCursString);

			String numeCurs = request.getParameter("Nume");
			Long univerID = Long
					.parseLong(request.getParameter("UniverID"));
			Long profID = Long.parseLong(request.getParameter("ProfID"));

			curs.setCursId(idCurs);
			curs.setNumeCurs(numeCurs);
			curs.setUniversitateId(univerID);
			curs.setProfesorId(profID);
			if (genService.updateFunction(curs) == true) {
				out.print("<p>A fost reinnoit cu succes</>");
			} else {
				out.print("<p>Eroare: Nu a fost reinnoit</p>");
			}
		}
	%>
	<a href="<%=request.getContextPath()%>/Curs/CursJSP.jsp">Apasa
		aici: Curs</a>
	<!-- Footer -->
	<%@ include file="/footerJSP.jsp"%>