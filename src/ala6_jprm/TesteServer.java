package ala6_jprm;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TesteServer {
	public static void main(String []args) throws IOException {			   
		try{
			//Cria os objetos necessário para instânciar o servidor
			int serverPort = 12345;
			ArrayList<BufferedWriter> clientes;
			ServerSocket server = new ServerSocket(serverPort);
			clientes = new ArrayList<BufferedWriter>();
			System.out.println("Servidor ativo na porta: "+ serverPort);


			while(true){
				System.out.println("Aguardando conexão...");
				Socket con = server.accept();
				System.out.println("Cliente conectado...");
				Thread t = new Server(con);
				t.start();   
			}

		}catch (Exception e) {

			e.printStackTrace();
		}                       
	}// Fim do método main                      
}
