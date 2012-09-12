package com.victordov.explore;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.victordov.includes.Includes;

import md.victordov.lab.common.exception.MyDaoException;
import md.victordov.lab.dao.UnivDAO;
import md.victordov.lab.services.GenericService;
import md.victordov.lab.services.UniversitateService;
import md.victordov.lab.vo.Universitate;

/**
 * Servlet implementation class Universitate
 */
@WebServlet("/Universitate")
public class Univer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Univer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		genService = new UniversitateService(new UnivDAO());
		PrintWriter out = response.getWriter();
		out.print("<HTML><HEAD><TITLE>Home Page</TITLE></HEAD><BODY>");
		out.print(Includes.HEADER_INDEX);

		ArrayList<Universitate> uniList = new ArrayList<Universitate>();
		try {
			uniList = genService.printAll();
		} catch (MyDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print("</br></br></br></br></br>");
		out.print("<table border=\"1\" align=\"center\">");
		out.print("<tr><th>ID</th><th>Nume Univer</th>");
		out.print("<th>Adresa</th>");
		out.print("<th>Telefon</th>");
		out.print("<th>Edit</th>");
		out.print("<th>Delete</th>");
		out.print("<th>Insert</th></tr>");
		for (int i = 0; i < uniList.size(); i++) {
			Universitate tempUniver = new Universitate();
			tempUniver = uniList.get(i);
			out.print("<tr>");
			out.print("<td>" + (tempUniver).getUniversitateId() + "</td>");
			out.print("<td>" + (tempUniver).getNumeUniversitate() + "</td>");
			out.print("<td>" + (tempUniver).getAdresa() + "</td>");
			out.print("<td>" + (tempUniver).getTelefon() + "</td>");
			out.print("<td><form><button name=\"editButton\" value=\"Submit\" type=\"button\">Edit</button></td>");
			out.print("<td><button  name=\"editButton\" value=\"Submit\" type=\"button\">Delete</button></form></td>");
			if(i==0){
				out.print("<td rowspan=\""+uniList.size()+"\"><form><button  name=\"insertButton\" value=\"Submit\" type=\"button\">Insert</button></form></td>");
			}
			out.print("</tr>");
			
		}
		//out.print("<tr><td></td><td></td><td></td><td></td><td></td><td></td>");
		
		out.print("</table>");
		out.print("</BODY></HTML>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	GenericService genService = null;
}
