package ag.ifpb.pod.rmi.heroku;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class HelloWorldServletHandler extends HttpServlet {
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    resp.getWriter().println("OK");
    resp.getWriter().flush();
  }
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    ServletForwarder forwarder = new ServletForwarder();
    try {
      String port = req.getParameter("forward");
      forwarder.forward(req, resp, Integer.parseInt(port));
    } 
    catch (ServletForwarderException e) {
      e.printStackTrace();
      throw new IOException(e.getMessage());
    }
  }
}
