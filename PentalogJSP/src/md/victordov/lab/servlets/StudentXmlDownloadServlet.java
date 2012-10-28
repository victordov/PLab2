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
import md.victordov.lab.services.StudentToXmlParserService;

/**
 * Servlet implementation class StudentXmlDownloadServlet
 */
@WebServlet("/StudentXmlDownloadServlet")
public class StudentXmlDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = LogManager
			.getLogger(StudentXmlDownloadServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentXmlDownloadServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("Creating the output file from class Student Xml");
		try {

			String parent = "Student";
			Date dd = new Date();
			String dateString = new SimpleDateFormat("_y_MM_dd_HH_mm_ss")
					.format(dd);
			String outputFileName = parent + dateString + ".xml";

			ServletOutputStream myOut = null;
			byte[] byteBuf = null;
			try {
				byteBuf = StudentToXmlParserService.parser().getBytes();
			} catch (MyServiceException mse) {
				logger.error(
						"Could not copy Database to String to byteBuffer ", mse);
			}

			ByteArrayInputStream in = new ByteArrayInputStream(byteBuf);
			BufferedInputStream buf = new BufferedInputStream(in);
			try {
				myOut = response.getOutputStream();
				// set response headers
				response.setContentType("text/xml");
				response.addHeader("Content-Disposition",
						"attachment; filename=" + outputFileName);
				response.setContentLength((int) StudentToXmlParserService
						.parser().length());
				int readBytes = 0;

				// read from the file; write to the ServletOutputStream
				while ((readBytes = buf.read()) != -1) {
					myOut.write(readBytes);
				}

			} catch (IOException ioe) {

				throw new ServletException(ioe.getMessage());

			} finally {
				if (myOut != null)
					myOut.close();
				if (buf != null)
					buf.close();

			}

		} catch (ServletException se) {
			logger.error(
					"Servlet Exception, could not set the response header ", se);
		} catch (Exception e) {
			logger.warn("Exception produced", e);
		}
		logger.info("Output file was created from class Student Xml");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
