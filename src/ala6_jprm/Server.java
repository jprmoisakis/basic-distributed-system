package ala6_jprm;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
 
public class Server implements IServer {

    public Server() throws RemoteException {
    }
 
    public String skeletonMethod() throws RemoteException {
        return null;
    }
 
    public static void main(String args[]) throws Exception {

        System.out.println("RMI server started");
        
        //RMI Server
        Server srv = new Server();
        int num = 27;
        
        try { 
            
        	IServer stub = (IServer) UnicastRemoteObject.exportObject(srv, 0);
            Registry reg;
            
            try {
            	reg = LocateRegistry.createRegistry(num);

            } catch(Exception e) {
            	reg = LocateRegistry.getRegistry();
            }
            
        	reg.rebind("RMI Server", stub);

        } catch (RemoteException e) {
        	e.printStackTrace();
        }
    }
}