package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
public class Client extends Thread {

	private Socket conexao;

	public Client(Socket socket) {
		this.conexao = socket;
	}

	public static void main(String args[]) {
		try {
			Socket socket = new Socket("127.0.0.1", 5555);
			PrintStream saida = new PrintStream(socket.getOutputStream());
			BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Digite seu usuario: ");
			String usr = teclado.readLine();
			saida.println(usr);
			
			Thread thread = new Client(socket);
			thread.start();

			String msg;

			while (true) {
				System.out.print("Mensagem > ");
				msg = teclado.readLine();
				saida.println(msg);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			//recebe mensagens de outro cliente através do servidor
			BufferedReader entrada = new BufferedReader(new InputStreamReader(this.conexao.getInputStream()));

			String msg;
			while (true) {
				// pega o que o servidor enviou
				msg = entrada.readLine();
				
				// Conexao encerrada caso a msg venha vazia
				if (msg == null) {
					System.out.println("Conexão encerrada!");
					System.exit(0);
				}

				System.out.println('\n' + msg);
				System.out.print("Responder > ");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}