import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.sql.*;

@WebServlet(value="/moderator/assignticket")
public class AssignTicketController extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int ticketId=Integer.valueOf(request.getParameter("ticketId"));
		int assignTo=Integer.valueOf(request.getParameter("assignTo"));
		
		String sql = "UPDATE tickets SET status='Pending', assigned_to=? WHERE ID=?";
		
		try {
			PreparedStatement prepStatement = Helper.getPreparedStatement(sql);
			prepStatement.setInt(1, assignTo);
			prepStatement.setInt(2, ticketId);
			prepStatement.executeUpdate();
		}
		catch (SQLException e) {
			response.sendError(500, "Server Error");
			e.printStackTrace();
		}			
	}
}
