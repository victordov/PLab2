<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*"%>
<%@page import="javax.xml.parsers.*,org.w3c.dom.*,org.xml.sax.*"%>
<%@ page import="md.*"%>
<%@ page import="md.victordov.lab.vo.*"%>
<%@ page import="md.victordov.lab.common.exception.MyDaoException"%>
<%@ page import="md.victordov.lab.dao.*"%>
<%@ page import="md.victordov.lab.services.*"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.util.ArrayList"%>

<%@ page import="javax.xml.*"%>
<%@ page import="javax.xml.transform.OutputKeys"%>
<%@ page import="javax.xml.transform.Transformer"%>
<%@ page import="javax.xml.transform.TransformerFactory"%>
<%@ page import="javax.xml.transform.dom.DOMSource"%>
<%@ page import="javax.xml.transform.stream.StreamResult"%>
<%@ page import="md.victordov.lab.services.LabXmlParser"%>
<%@ page import="md.victordov.lab.common.other.GetTagValue"%>
<%@ page import="md.victordov.lab.common.exception.*"%>
<html>
<head>
<link href="<%=request.getContextPath()%>/style.css" rel="stylesheet"
	type="text/css">
<title>Home Page</title>
</head>
<body>
	<!-- Header -->
	<%@ include file="/headerJSP.jsp"%>


	<%
		//to get the content type information from JSP Request Header
		String contentType = request.getContentType();

		//here we are checking the content type is not equal to Null and as well as the passed data from mulitpart/form-data is greater than or equal to 0
		if ((contentType != null)
				&& (contentType.indexOf("multipart/form-data") >= 0)) {
			
			DataInputStream in = new DataInputStream(request.getInputStream());
			//we are taking the length of Content type data
			int formDataLength = request.getContentLength();
			byte dataBytes[] = new byte[formDataLength];
			int byteRead = 0;
			int totalBytesRead = 0;
			//this loop converting the uploaded file into byte code
			while (totalBytesRead < formDataLength) {
				byteRead = in.read(dataBytes, totalBytesRead,formDataLength);
				totalBytesRead += byteRead;
			}
			String file = new String(dataBytes);

			System.out.print(file);

			if (file.indexOf("Content-Type: text/xml") > 0) {
				out.print("</br>Found, its xml file</br>");
			}

			
			out.print("The file content</br><HR></br>");
			out.print(file + "</br>Finishing the file content<HR></br>");
			String saveFile = file
					.substring(file.indexOf("filename=\"") + 10);
			out.print("</br>SaveFile Substring filename=\"</br>" + saveFile
					+ "</br>");

			saveFile = saveFile.substring(0, saveFile.indexOf("\n"));
			out.print("</br>SaveFile Substring indexOF \\n</br>" + saveFile
					+ "</br>");

			saveFile = saveFile.substring(saveFile.lastIndexOf("\\") + 1,
					saveFile.indexOf("\""));
			out.print("</br>SaveFile Substring Last Index of \\</br>"
					+ saveFile + "</br>");
			int lastIndex = contentType.lastIndexOf("=");

			String boundary = contentType.substring(lastIndex + 1,
					contentType.length());
			out.print("</br>String boundary" + boundary + "</br>");

			int pos;
			//extracting the index of file 
			pos = file.indexOf("filename=\"");
			pos = file.indexOf("\n", pos) + 1;
			pos = file.indexOf("\n", pos) + 1;
			pos = file.indexOf("\n", pos) + 1;
			int boundaryLocation = file.indexOf(boundary, pos) - 4;
			int startPos = ((file.substring(0, pos)).getBytes()).length;
			int endPos = ((file.substring(0, boundaryLocation)).getBytes()).length;
			// creating a new file with the same name and writing the content in new file

			out.print("</br>SaveFile Final Out File</br>" + saveFile
					+ "</br>");
			FileOutputStream fileOut = new FileOutputStream(saveFile);
			fileOut.write(dataBytes, startPos, (endPos - startPos));
	
			fileOut.flush();
			fileOut.close();
			LabXmlParser labXmlParser = null;
			try{
				labXmlParser =new LabXmlParser(saveFile);	
			}catch(NullPointerException npe){
				out.print("<br/>Fisierul incarcat nu a fost gasit<br/>");
			}

			
			
			/*
			 * Try pentru adaugarea cursului
			 */
			try {
				labXmlParser.parseCursXML();
			} catch (MyServiceException l2Exc) {
				out.print(l2Exc.toString());
			}

			/*
			 * Try pentru adaugarea studentului
			 */
			try {
				labXmlParser.parseStudentXML();
			} catch (MyServiceException l2Exc) {
				out.print(l2Exc.toString());
			}

			/*
			 * Try pentru adaugarea profesorului
			 */
			try {
				labXmlParser.parseProfesorXML();
			} catch (MyServiceException l2Exc) {
				out.println(l2Exc.toString());
			}

			/*
			 * Try pentru adaugarea universitatii
			 */
			try {
				labXmlParser.parseUniversitateXML();
			} catch (MyServiceException l2Exc) {
				out.println(l2Exc.toString());
			}
	%>


	<br>
	<table border="2">
		<tr>
			<td><b>You have successfully upload the file by the name of:</b>
				<%
					out.println(saveFile);
				%></td>
		</tr>
	</table>
	<%
		}
	%>
	<h1>
		<a href="<%=request.getContextPath()%>/index.jsp">Pagina
			Principala</a>
	</h1>
	<!-- Footer -->
	<%@ include file="/footerJSP.jsp"%>