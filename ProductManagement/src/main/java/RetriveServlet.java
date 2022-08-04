
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comProductManagement.DBConnection;

@WebServlet("/RetriveServlet")
public class RetriveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RetriveServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Connection c = DBConnection.getMyConnection();
			String str = "select * from product";
			Statement ps = c.createStatement();
			ResultSet ans = ps.executeQuery(str);
			PrintWriter out = response.getWriter();
			out.println("<table border=2>");
			out.println("<tr><th>ID</th><th>Product Name</th><th>Quantity</th><th>Price</th><th>Total</th></tr>");
			while (ans.next()) {
				out.println("<tr>");
				out.print("<td>" + ans.getInt("pid") + "</td>");
				out.print("<td>" + ans.getString("pName") + "</td>");
				out.print("<td>" + ans.getInt("qty") + "</td>");
				out.print("<td>" + ans.getInt("price") + "</td>");
				out.print("<td>" + ans.getInt("total") + "</td>");
				out.println("</tr>");
			}
			out.println("</table>");
			c.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
