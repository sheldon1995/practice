package com.czy.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.czy.bean.User;
import com.czy.dao.UserDao;
import com.czy.factory.DAOFactory;
import org.jasypt.util.password.StrongPasswordEncryptor;

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
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
		String uname = request.getParameter("uname");
		String passwd = request.getParameter("passwd");
		String identity=request.getParameter("identity");
		User user = null;
		String message = "";
		String path = "jsp/login.jsp";
		boolean success = false;
		try {
			
			UserDao dao = DAOFactory.getUserServiceInstance();
			if ((user = dao.queryByName(uname, identity))!= null) {
				String encryptedPassword = user.getPasswd();
				success = new StrongPasswordEncryptor().checkPassword(passwd, encryptedPassword);
				if (success) {
					String lastLoginTime = user.getLastLogin();
					dao.editLoginTime(user.getUid());
					request.getSession().setAttribute("uname", uname);
					request.getSession().setAttribute("uid",String.valueOf(user.getUid()));
					request.getSession().setAttribute("lastLoginTime",lastLoginTime);
					if(identity.equals("customers")) {
						path = "jsp/index.jsp";
					}
					else {
						path = "jsp/dashboard.jsp";
					}
					
				} else {
					message = "Wrong password, correct passwd ";
				}
			} 
			else {
				message = "User doesn't exist";
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		String truePath = request.getContextPath() + "/" + path;
		if ("".equals(message))// if message equals null, which means the Email and passwd is both right
			{
			response.sendRedirect(truePath);
		} 
		else {
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE>Wrong</TITLE>");
			out.println("<meta http-equiv=\"refresh\" content=\"5;url="+ truePath + "\">");
			out.println("</HEAD>");
			out.println(" <BODY>");
			out.print("<div align=\"center\">");
			out.print(message);
			out.print("<br/>");
			out.print("Will automatically jump back to the login page.");
			out.print("<br/>");
			out.print("Click here to jump back ");
			out.print("<a href=\"" + truePath+"\"" +">Login"+"</a>");
			out.print("</div>");
			out.println("  </BODY>");
			out.println("</HTML>");
			out.flush();
			out.close();
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
