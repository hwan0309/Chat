package jChat.chat01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/chattroom")
public class ChattRoom extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
      doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		req.setCharacterEncoding("utf-8");
		
		String username = req.getParameter("username");
		if(username == null || username.trim().length() == 0) {
			res.sendRedirect("index.html");
		}
		
		String body = "<!DOCTYPE html>\r\n"
				+ "<html lang=\"ko\">\r\n"
				+ "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <title>Document</title>\r\n"
				+ "    <link href=\"https://fonts.googleapis.com/css2?family=Sunflower:wght@300;500;700&display=swap\" rel=\"stylesheet\">\r\n"
				+ "    <link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">\r\n"
				+ "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/remixicon/4.2.0/remixicon.min.css\" integrity=\"sha512-MqL4+Io386IOPMKKyplKII0pVW5e+kb+PI/I3N87G3fHIfrgNNsRpzIXEi+0MQC0sR9xZNqZqCYVcC61fL5+Vg==\" crossorigin=\"anonymous\" referrerpolicy=\"no-referrer\" />\r\n"
				+ "    <link rel=\"stylesheet\" href=\"css/style.css\">\r\n"
				+ "\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "    <h1 class=\"text-center mt-5\">My Java Chat</h1>\r\n"
				+ "    <div class=\"chatbox\">\r\n"
				+ "        <div class=\"viewbox\">\r\n"
				+ "           <iframe src=\"talk?username="+username+"&first=true\" class=\"iframe\" name=\"mychat\"></iframe>\r\n"
				+ "        </div>\r\n"
				+ "        <div class=\"chatinsert\">\r\n";				
	String body2 = "    </div>\r\n"
				+ "    </div>\r\n"
				+ "   \r\n"
				+ "\r\n"
				+ "    <script src=\"js/jquery.min.js\"></script>\r\n"
				+ "    <script src=\"js/popper.min.js\"></script>\r\n"
				+ "    <script src=\"js/bootstrap.min.js\"></script>\r\n"
				+ "    <script src=\"js/script.js\"></script>\r\n"
				+ "</body>\r\n"
				+ "</html>";
		
		out.println(body);
		out.println("<form name=form method=post action=talk");
		out.println("onSubmit='return send(this)' target='mychat'>");
		out.println("<input type=text class='form-control' name='temp' id='temp'>");
		out.println("<input type=hidden name='msg' id='msg'>");
		out.println("<input type=hidden name=username value='"+username+"'>");
		out.println("</form>");
		out.println(body2);
	}

}
