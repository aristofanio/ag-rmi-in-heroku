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

  public void testApp() throws InterruptedException {
    try {
      //
      RMISocketFactory.setSocketFactory(new sun.rmi.transport.proxy.RMIHttpToCGISocketFactory());
      //
      Registry registry = LocateRegistry.getRegistry(
          "ag-rmi-in-heroku.herokuapp.com", 1099, RMISocketFactory.getSocketFactory()
          //"127.0.0.1", 1099, RMISocketFactory.getSocketFactory()
      );
      //
      final HelloWorldRemote remote = (HelloWorldRemote) registry.lookup(HelloWorldRemote.SERVICE_NAME);
      System.out.println(remote.helloWorld());
      //força bruta... ;)
      for (int i = 0; i < 100; i++) {//com 100 ok, mas com atraso. com 1000 quebra a execução
        Thread thread = new Thread("pod-thread-" + i){
          @Override
          public void run() {
            try {
              System.out.println(Thread.currentThread().getName() + ": " + remote.helloWorld());
            } catch (RemoteException e) {
              e.printStackTrace();
            }
          }
        };
        thread.start();
      }
      //
      Thread.sleep(5000);
    } catch (RemoteException e) {
      e.printStackTrace();
    } catch (NotBoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
