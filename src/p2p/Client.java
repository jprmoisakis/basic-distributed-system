package p2p;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Client {

	public static void main(String[] args) throws IOException {
		Scanner strScanner = new Scanner(System.in);	
		int port = 8000;
		int portServer = 8001;
		String address = "127.0.0.1"; // testando em um mesmo pc
		
		try{	
			ServerSocket tmpsocket = new ServerSocket(portServer);
			String connect = strScanner.nextLine();
			if(connect.equals("conectar")){
				Socket socketClient = new Socket(address,port);
				Socket socket = tmpsocket.accept();
				Receive rcv = new Receive(socket);
				Send snd = new Send(socketClient);
				Thread tSnd = new Thread(snd);
				Thread tRcv = new Thread(rcv);
				tRcv.start();
				tSnd.start();
			}
			
		}catch (BindException e){
			System.out.println("Endereco em uso"); 
		}catch (Exception e){
			System.out.println("erro " + e);
		};
	}

}
