package ag.ifpb.pod.rmi.heroku;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Hello world!
 *
 */
public class Main {
  
  public static void startServer() throws Exception{
    //
    String webappDirLocation = "src/main/webapp/";
    //
    String webPort = System.getenv("PORT");
    if(webPort == null || webPort.isEmpty()) {
        webPort = "8080";
    }
    //
    WebAppContext root = new WebAppContext();
    root.setContextPath("/cgi-bin/java-rmi.cgi");
    root.setDescriptor(webappDirLocation+"/WEB-INF/web.xml");
    root.setResourceBase(webappDirLocation);
    root.setParentLoaderPriority(true);
    //
    Server server = new Server(Integer.valueOf(webPort));
    server.setHandler(root);
    server.start();
    server.join();   
  }
  
  public static void startService() throws RemoteException, AlreadyBoundException{
    Registry registry = LocateRegistry.createRegistry(1099);
    registry.bind(HelloWorldRemote.SERVICE_NAME, new HelloWorldRemoteImpl());
  }
  
  public static void main(String[] args) throws Exception {
    startService();
    startServer();
  }
}
