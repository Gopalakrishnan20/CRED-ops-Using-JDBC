

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comProductManagement.DBConnection;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id=Integer.parseInt(request.getParameter("id"));
		PrintWriter out=response.getWriter();
		try {
			Connection c= DBConnection.getMyConnection();
			String str="delete from product where pid=?";
			PreparedStatement  ps=c.prepareStatement(str);
			ps.setInt(1, id);
			int flag=ps.executeUpdate();
			if(flag>0)
			{
				out.println("<html><body>");
				out.println("<h2>Row Deleted</h2>");
				out.println("</body></html>");
			}
			else
			{
				out.println("<html><body>");
				out.println("<h2>No Deletion</h2>");
				out.println("</body></html>");
			}
			c.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
