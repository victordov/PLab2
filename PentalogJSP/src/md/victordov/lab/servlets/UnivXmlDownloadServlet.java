package md.victordov.lab.servlets;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import md.victordov.lab.services.UnivToXmlParserService;



public class UnivXmlDownloadServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String parent = "Universitate";
			Date dd = new Date();
			String dateString = new SimpleDateFormat("_y_MM_dd_HH_mm_ss")
					.format(dd);
			String outputFileName = parent + dateString + ".xml";

			ServletOutputStream myOut = null;
			byte[] byteBuf = UnivToXmlParserService.parser().getBytes();
			ByteArrayInputStream in = new ByteArrayInputStream(byteBuf);
			BufferedInputStream buf = new BufferedInputStream(in);
			try {
				myOut = response.getOutputStream();
				// set response headers
				response.setContentType("text/xml");
				response.addHeader("Content-Disposition",
						"attachment; filename=" + outputFileName);
				response.setContentLength((int) UnivToXmlParserService.parser()
						.length());

				int readBytes = 0;

				// read from the file; write to the ServletOutputStream
				while ((readBytes = buf.read()) != -1) {
					myOut.write(readBytes);
				}

			} catch (IOException ioe) {

				throw new ServletException(ioe.getMessage());

			} finally {

				// close the input/output streams
				if (myOut != null)
					myOut.close();
				if (buf != null)
					buf.close();

			}

		} catch (ServletException se) {
			System.out.println(se.getMessage());
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}