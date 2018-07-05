package com.czy.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.czy.dao.GoodsDao;
import com.czy.factory.DAOFactory;
/**
 * Servlet implementation class DashboardProcess
 */

public class DashboardProcess extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public DashboardProcess() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		String stars = request.getParameter("starname");
		int year = Integer.parseInt(request.getParameter("birthYear"));
		String path = "../jsp/dashboard.jsp";
		String message="";
		try {
			GoodsDao insertStars = DAOFactory.getGoodsServiceInstance();
			boolean flag = insertStars.addStars(stars, year);
			if (flag) {
				message = "You successfully add a new star!!!";
				request.getSession().setAttribute("message", message);
				response.sendRedirect(path);
		}
			else {
				message = "Wopps, you unsuccessfully add a new star!!!";
				request.getSession().setAttribute("message", message);
				response.sendRedirect(path);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
