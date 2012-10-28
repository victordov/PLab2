package md.victordov.lab.servlets;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import md.victordov.lab.common.exception.MyServiceException;
import md.victordov.lab.services.ProfesorToXmlParser;

/**
 * Servlet implementation class ProfesorXmlDownloadServlet
 */
@WebServlet("/ProfesorXmlDownloadServlet")
public class ProfesorXmlDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = LogManager
			.getLogger(ProfesorXmlDownloadServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfesorXmlDownloadServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("Start from doGet to Create the download file");
		try {

			String parent = "Profesor";
			Date dd = new Date();
			String dateString = new SimpleDateFormat("_y_MM_dd_HH_mm_ss")
					.format(dd);
			String outputFileName = parent + dateString + ".xml";

			ServletOutputStream myOut = null;
			byte[] byteBuf = null;
			try {
				byteBuf = ProfesorToXmlParser.parser().getBytes();
			} catch (MyServiceException e) {
				logger.error("eroare in profexor xml parser", e);

			}

			ByteArrayInputStream in = new ByteArrayInputStream(byteBuf);
			BufferedInputStream buf = new BufferedInputStream(in);
			try {
				myOut = response.getOutputStream();
				// set response headers
				response.setContentType("text/xml");
				response.addHeader("Content-Disposition",
						"attachment; filename=" + outputFileName);
				response.setContentLength((int) ProfesorToXmlParser.parser()
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
			logger.error("Could not create the download file", se);
		} catch (Exception e) {
			logger.warn("Exception occured in Profesor Xml Download Servlet", e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
