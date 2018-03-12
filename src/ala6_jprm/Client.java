package ala6_jprm;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client { 
 
	public static void main(String args[]) throws Exception {
    
		Registry reg = LocateRegistry.getRegistry("localhost");
        
		IServer srv = (IServer) reg.lookup("Server");
        
		// skeletonMethod 
    }
}