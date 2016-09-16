package ag.ifpb.pod.rmi.heroku;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMISocketFactory;

import junit.framework.TestCase;

@SuppressWarnings("restriction")
public class RemoteTest extends TestCase {

  public void testApp() {
    try {
      //
      RMISocketFactory.setSocketFactory(
          new sun.rmi.transport.proxy.RMIHttpToCGISocketFactory());
      //
      Registry registry = LocateRegistry.getRegistry(
          "ag-rmi-in-heroku.herokuapp.com", 80,
          RMISocketFactory.getSocketFactory());
      //
      HelloWorldRemote remote = (HelloWorldRemote) registry
          .lookup(HelloWorldRemote.SERVICE_NAME);
      //
      System.out.println(remote.helloWorld());
      //
    } catch (RemoteException e) {
      e.printStackTrace();
    } catch (NotBoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
