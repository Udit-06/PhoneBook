package com.servlet;

import java.io.IOException;

import com.conn.DbConnect;
import com.dao.UserDAO;
import com.entity.User;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{

		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		User u = new User(name, email, password);

		UserDAO dao = new UserDAO(DbConnect.getConn());
		boolean f = dao.userRegister(u);

		HttpSession session=req.getSession();
	
		
		if (f) {
			session.setAttribute("sucssMsg", "User Register Successfully...");
			resp.sendRedirect("register.jsp");
//			System.out.println("User Register Successfully...");
		} else {
			session.setAttribute("errorMsg", "Something wrong on server...");
			resp.sendRedirect("register.jsp");
//			System.out.println("Something wrong on server...");
		}

	}
}
