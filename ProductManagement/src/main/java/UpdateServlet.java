
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comProductManagement.DBConnection;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// update product set pname="Modem",price=50,qty=1,total=50 where pid=4;
		int id=Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("pName");
		int qty = Integer.parseInt(request.getParameter("qty"));
		int price = Integer.parseInt(request.getParameter("price"));
		int total = Integer.parseInt(request.getParameter("tot"));
		PrintWriter out = response.getWriter();

		try {
			Connection c = DBConnection.getMyConnection();
			String str = "update product set pname=?,price=?,qty=?,total=? where pid=?";
			PreparedStatement ps = c.prepareStatement(str);
			ps.setString(1, name);
			ps.setInt(2, price);
			ps.setInt(3, qty);
			ps.setInt(4, total);
			ps.setInt(5, id);
			int flag = ps.executeUpdate();
			if (flag > 0) {
				out.println("<html><body>");
				out.println("<h2>Values updated</h2>");
				out.println("</body></html>");
			} else {
				out.println("<html><body>");
				out.println("<h2>No Updation</h2>");
				out.println("</body></html>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
