package com.czy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.czy.dao.ShoppingCartDao;
import com.czy.factory.DAOFactory;


public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckOutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String id = request.getParameter("id");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String ex = request.getParameter("expiration");
	    Date expiration = Date.valueOf(ex);
	    
	    String strUid = (String) request.getSession().getAttribute("uid");
		int uId = 0;
		if (strUid != null) 
		{
		uId = Integer.parseInt(strUid);
		}
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) request.getSession().getAttribute("mIdList");
		String path = "";
			try {
				ShoppingCartDao dao = DAOFactory.getShoppingCartServiceInstance();
					if(dao.creditCards(id, firstname, lastname, expiration)&&dao.sales(uId, list)) {
						request.getSession().setAttribute("clearCart", "yes");
						path = "../jsp/shoppingCart.jsp";
						response.sendRedirect(path);		
					}
					else {
						String message = "Failed, retry!";
						path = "jsp/buyGoods.jsp";
						PrintWriter out = response.getWriter();
						out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
						out.println("<HTML>");
						out.println("  <HEAD><TITLE>Wrong</TITLE>");
						out.println("<meta http-equiv=\"refresh\" content=\"5;url="+ path + "\">");
						out.println("</HEAD>");
						out.println(" <BODY>");
						out.print("<div align=\"center\">");
						out.print(message);
						out.print("<br/>");
						out.print("Will automatically jump back to the original page.");
						out.print("<br/>");
						out.print("Click here to jump back ");
						out.print("<a href=\"" + path+"\"" +">"+"</a>");
						out.print("</div>");
						out.println("  </BODY>");
						out.println("</HTML>");
						out.flush();
						out.close();
						response.sendRedirect(path);
					}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
