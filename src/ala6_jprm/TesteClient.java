package ala6_jprm;

import java.io.IOException;
import java.util.Scanner;

public class TesteClient {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		Client app = new Client("localhost",12345, "paulo");
		app.conectar();
		System.out.println("Cliente conectado");
			System.out.println("digite sua msg: ");
			String msg = in.nextLine();
			app.enviarMensagem(msg);
			app.escutar();
		
	}

}