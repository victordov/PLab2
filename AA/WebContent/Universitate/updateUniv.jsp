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

<%
	/* Header */
%>
<%@ include file="/headerJSP.jsp"%>
<%
	GenericService<Universitate> genService = new UniversitateService(
			new UnivDAO());
	Universitate univ = new Universitate();
	String idUniverString = request.getParameter("id");
	Long idUniversitate = Long.parseLong(idUniverString);

	String denumUniver = request.getParameter("Denumire");
	String adresaUniver = request.getParameter("Adresa");
	String telefonUniver = request.getParameter("Telefon");
	univ.setUniversitateId(idUniversitate);
	univ.setNumeUniversitate(denumUniver);
	univ.setAdresa(adresaUniver);
	univ.setTelefon(telefonUniver);
	if(genService.updateFunction(univ)==true){
%>
<p>A fost reiinoit</p>
<% } else{%>
<p>Eroare</p>
<%}%>
<%@ include file="/footerJSP.jsp"%>
<%response.sendRedirect("/AA/Universitate/UnivJSP.jsp");%>
