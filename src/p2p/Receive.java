package p2p;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Receive implements Runnable{

	private Socket cliente;
	
	public Receive(Socket client){
		this.cliente = client;
	}
	
	public void run() {
		
		try {
			while(true){
				InputStreamReader entrada = new InputStreamReader(this.cliente.getInputStream());
				BufferedReader le = new BufferedReader(entrada);
				String resposta = le.readLine();
				if(resposta != null){
					System.out.println("User: " + resposta);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
