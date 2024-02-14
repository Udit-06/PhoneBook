package com.servlet;

import java.io.IOException;

import com.conn.DbConnect;
import com.dao.ContactDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/delete")
public class DeleteContact extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int cid = Integer.parseInt(req.getParameter("cid"));

		ContactDAO dao = new ContactDAO(DbConnect.getConn());
		boolean f = dao.deleteContactById(cid);
		HttpSession session = req.getSession();
		if (f) {
			session.setAttribute("succMsg", "Contact Delete Successfully...");
			resp.sendRedirect("viewContact.jsp");
		} else {
			session.setAttribute("failedMsg", "Something wrong on server...");
			resp.sendRedirect("viewContact.jsp");
		}
	}
}
