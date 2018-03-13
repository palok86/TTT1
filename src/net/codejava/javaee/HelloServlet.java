package net.codejava.javaee;

import java.io.IOException;
import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.*;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/helloServlet")
public class HelloServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() 
	{
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
		{
		PrintWriter out = response.getWriter();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		/*Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
		Matcher matcher = pattern.matcher(username);
		if (matcher.matches()) 
		{
			out.println("<h1>User Name: "+ username + "<h1>");
		}
		else
		{
			out.println("<h1>User Name: "+ "Please enter only alphabets and numbers"+ "</h1>");
		}
				//String user1=username.replaceAll("(?i)<script.*?>.*?</script.*?>", "");  // case 1
			     //.replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "") // case 2
			    // .replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "");     // case 3
		
		//out.println("div");
		//out.println("<p>User Name: "+ user1 + "</p>");
		//out.println("<h1>Hello " + username + "</h1>");
		out.println("<h1>Your Password: "+ password + "</h1>");
		
		//SQL Injection
		
		//out.close();*/
		
		try {

            Connection conn = null;
            String url = "jdbc:mysql://192.168.2.128:3306/";
            String dbName = "anvayaV2";
            String driver = "com.mysql.jdbc.Driver";
            String userName = "root";
            String passwd = "";
            try {
                Class.forName(driver).newInstance();
                conn = DriverManager.getConnection(url + dbName, userName, passwd);

                //Statement st = conn.createStatement();
                //String query = "SELECT * FROM  User where userId= "+ username;
				PreparedStatement st = conn.prepareStatement("SELECT * FROM  User where userId=?");
				st.setString(1, username); 
                //out.println("Query : " + query);
                //System.out.printf(query);
                //ResultSet res = st.executeQuery(query);
				ResultSet res = st.executeQuery();

                out.println("Results");
                while (res.next()) {
                    String s = res.getString("username");
                    out.println("\t\t" + s);
                }
                conn.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            out.close();
        }
		//out.close();
	}

}
