package jChat.chat01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/talk")
public class ChatTalk extends HttpServlet {
	
	String message[];
	int index = 0;
	int size = 10;
	
	//채팅메시지가 몇개 까지 나올지 셋팅
    public void init() {
    	message = new String[size];
    	for(int i=0; i < size; i++) {
    		message[i] = "";
    	}
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		req.setCharacterEncoding("utf-8");
		
	    String first = req.getParameter("first");
	    if(first != null) {
	    	String username = req.getParameter("username");
	    	synchronized(message) {
	    		message[index] = "<p>["+username+"] 님이 입장하셨습니다.</p>";
	    		index = (index + 1) % size;
	    	}
	    }
	    
	    out.println("<html><head>");
	    out.println("<link rel='stylesheet' href='https://fonts.googleapis.com/css2?family=Sunflower:wght@300;500;700&display=swap'>");
	    out.println("<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">");
	    out.println("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/remixicon/4.2.0/remixicon.min.css\" integrity=\"sha512-MqL4+Io386IOPMKKyplKII0pVW5e+kb+PI/I3N87G3fHIfrgNNsRpzIXEi+0MQC0sR9xZNqZqCYVcC61fL5+Vg==\" crossorigin=\"anonymous\" referrerpolicy=\"no-referrer\" />");
	    out.println("<link rel=\"stylesheet\" href=\"css/style.css\">");
	    out.println("<meta http-equiv='pragma' content='no-cache'>");
	    out.println("<meta http-equiv='chche-control' content='no-cache'>");
	    out.print("<meta http-equiv='refresh' ");
	    out.println("content='2;URL=talk'>");
	    out.println("<title>채팅</title></head>");
	    out.println("<body class='talk'><h1>mychat</h1>");
	    out.println("<ul class='talk-box'>");
	    int i = index;
	    while(true) {
	    	out.print("<li>"+message[i]+"</li>");
	    	
	    	i = ++i % size;
	    	if(i == index -1) {
	    		break;
	    	}
	    	if(index == 0 && i == size-1) {
	    		break;
	    	}
	    }
	    out.print("<li>"+message[i]+"</li>");
	    out.println("</ul>");
	    out.println("</body></html>");
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String username = req.getParameter("username");
		String msg = req.getParameter("msg");
		if(msg != null && msg.trim().length() != 0) {
			synchronized(message){
				message[index] = "[" + username + "] " + msg;
				index = (index + 1) % size;
			}
		}
		doGet(req, res);
	}

}
