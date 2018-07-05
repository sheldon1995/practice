package com.czy.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.czy.bean.Goods;
import com.czy.dao.GoodsDao;
import com.czy.factory.DAOFactory;

/**
 * Servlet implementation class AdvancedServlet
 */
public class AdvancedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdvancedServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		String title = request.getParameter("title");
		Integer year = null;
		try 
		{
		year = Integer.parseInt(request.getParameter("year"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		String director = request.getParameter("director");
		String name = request.getParameter("name");
		try {
			GoodsDao search = DAOFactory.getGoodsServiceInstance();
			List<Goods> movieList = search.getAllMoviesByAdvancedSearch(title, year, name, director);
			request.getSession().setAttribute("searchResult", movieList);
			String path = "jsp/advancedShow.jsp";
			String truePath = request.getContextPath() + "/" + path;
			response.sendRedirect(truePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
