<%@ page import="java.io.*"%>
<%@page import="javax.xml.parsers.*,org.w3c.dom.*,org.xml.sax.*"%>

<%!String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
				.getChildNodes();

		Node nValue = (Node) nlList.item(0);

		return nValue.getNodeValue();
	}%>
<%
	//to get the content type information from JSP Request Header
	String contentType = request.getContentType();
	out.print("Content Type:</br>");
	out.print(contentType);
	out.print("</br>Fininshing content type</br></br>");
	out.print("Input Stream");
	out.print("<HR>");
	out.print(request.getInputStream());
	out.print("</br>Finish of Input Stream<HR></br>");

	//here we are checking the content type is not equal to Null and as well as the passed data from mulitpart/form-data is greater than or equal to 0
	if ((contentType != null)
			&& (contentType.indexOf("multipart/form-data") >= 0)) {
		DataInputStream in = new DataInputStream(
				request.getInputStream());
		//we are taking the length of Content type data
		int formDataLength = request.getContentLength();
		byte dataBytes[] = new byte[formDataLength];
		int byteRead = 0;
		int totalBytesRead = 0;
		//this loop converting the uploaded file into byte code
		while (totalBytesRead < formDataLength) {
			byteRead = in.read(dataBytes, totalBytesRead,
					formDataLength);
			totalBytesRead += byteRead;
		}
		String file = new String(dataBytes);
		if(file.indexOf("vnd.ms-excel ")>-1){
			out.print("</br>You have uploaded Excel spreadsheet</br>");
		}
		if(file.indexOf("Content-Type: text/xm")>0){
			out.print("</br>Found, its xml file</br>");
		}
		
		System.out.println(file);
		//for saving the file name
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

		/* out.print("Print databytes</br>" + dataBytes + "</br>");
		out.print("Print FileOut</br>" + fileOut + "</br>"); */

		out.print("Afisarea contentului xml file");
		fileOut.flush();
		fileOut.close();
		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(saveFile);
			doc.getDocumentElement().normalize();

			out.println("</br>Xml File :"
					+ doc.getDocumentElement().getNodeName() + "<br>");
			NodeList nList = doc.getElementsByTagName("student");
			out.println("-----------------------</br>");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					out.println("First Name : "
							+ getTagValue("nume", eElement) + "</br>");
					out.println("Last Name : "
							+ getTagValue("prenume", eElement)
							+ "</br>");
					out.println("Groupe : "
							+ getTagValue("grupa", eElement) + "</br>");
					out.println("Phone : "
							+ getTagValue("telFix", eElement) + "</br>");
					long numar = Long.parseLong(getTagValue("s_id",
							eElement));
					out.print("Id-ul extras este: " + numar + "</br>");
					out.print("----------</br>");

				}
			}
		} catch (Exception e) {
			out.println("</br></br>Eroare Parser XML</br></br>");
		}
%>
<%
	
%>
</br>
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
