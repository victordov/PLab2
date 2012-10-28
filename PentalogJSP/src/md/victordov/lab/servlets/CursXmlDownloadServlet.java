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
import md.victordov.lab.services.CursToXmlParserService;

/**
 * Servlet implementation class CursXmlDownloadServlet
 */
@WebServlet("/CursXmlDownloadServlet")
public class CursXmlDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CursXmlDownloadServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	static Logger logger = LogManager.getLogger(CursXmlDownloadServlet.class);
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("Download Servlet Initialization");
		try {

			String parent = "Curs";
			Date dd = new Date();
			String dateString = new SimpleDateFormat("_y_MM_dd_HH_mm_ss")
					.format(dd);
			//System.out.println(dateString);
			String outputFileName = parent + dateString + ".xml";

			ServletOutputStream myOut = null;
			byte[] byteBuf = null;
			try{
				byteBuf = CursToXmlParserService.parser().getBytes();
			}catch(MyServiceException mse){
				logger.error("Nu se poate de facut Conversia String to Byte", mse);
			}
			
			ByteArrayInputStream in = new ByteArrayInputStream(byteBuf);
			BufferedInputStream buf = new BufferedInputStream(in);
			try {
				myOut = response.getOutputStream();
				// set response headers
				response.setContentType("text/xml");
				response.addHeader("Content-Disposition",
						"attachment; filename=" + outputFileName);
				response.setContentLength((int) CursToXmlParserService.parser()
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
			//System.out.println(se.getMessage());
			logger.error("Exceptie serlet" , se);
		} catch (Exception e) {
			//System.out.println(e);
			logger.error("Eroare necunoscuta" , e);
		}
		logger.info("Download Servlet End");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
