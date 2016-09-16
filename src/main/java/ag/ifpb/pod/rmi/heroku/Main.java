package ag.ifpb.pod.rmi.heroku;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Hello world!
 *
 */
public class Main {
  
  public static void main(String[] args) throws NumberFormatException, RemoteException, AlreadyBoundException {
    Integer port = 8080;
    String _port = System.getenv("PORT");
    if (_port != null){
      port = Integer.parseInt(_port);
    }
    Registry registry = LocateRegistry.createRegistry(port);
    registry.bind(HelloWorldRemote.SERVICE_NAME, new HelloWorldRemoteImpl());    
  }
}
