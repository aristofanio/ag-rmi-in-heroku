package ag.ifpb.pod.rmi.heroku;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class HelloWorldServletHandler extends HttpServlet {
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    ServletForwarder forwarder = new ServletForwarder();
    try {
      forwarder.forward(req, resp);
    } 
    catch (ServletForwarderException e) {
      e.printStackTrace();
      throw new IOException(e.getMessage());
    }
  }
}
