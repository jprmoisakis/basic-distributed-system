package ala6_jprm;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote {
	public String skeletonMethod() throws RemoteException;
}