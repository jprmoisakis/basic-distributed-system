package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;


public class Server extends Thread {

	private static Vector CLIENTES;
	private Socket socket;
	private String nomeCliente;
	private static List LISTA_DE_NOMES = new ArrayList();

	public Server(Socket socket) {
		this.socket = socket;
	}

	//testa se nomes são iguais, se for retorna true
	public boolean armazena(String newName){
		for (int i=0; i < LISTA_DE_NOMES.size(); i++){
			if(LISTA_DE_NOMES.get(i).equals(newName))
				return true;
		}
		LISTA_DE_NOMES.add(newName);
		return false;
	}

	//remove da lista os CLIENTES que já deixaram o chat
	public void remove(String oldName) {
		for (int i=0; i< LISTA_DE_NOMES.size(); i++){
			if(LISTA_DE_NOMES.get(i).equals(oldName))
				LISTA_DE_NOMES.remove(oldName);
		}
	}

	public static void main(String args[]) {

		CLIENTES = new Vector();
		
		try {
			ServerSocket server = new ServerSocket(5555);
			System.out.println("ServerSocket rodando na porta 5555");

			// loop de espera para conexões de usuarios
			while (true) { 
				Socket conexao = server.accept();

				Thread t = new Server(conexao);
				t.start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			// objetos que permitem controlar fluxo de comunicação que vem do cliente
			BufferedReader entrada = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

			PrintStream saida = new PrintStream(this.socket.getOutputStream());
			this.nomeCliente = entrada.readLine();

			//verificação de usuarios no chat
			if (armazena(this.nomeCliente)){
				saida.println("Este nome ja existe! Conecte novamente com outro Nome.");
				CLIENTES.add(saida);
				this.socket.close();
				return;
			} else {
				System.out.println(this.nomeCliente + " : Conectado ao Servidor!");
			}

			if (this.nomeCliente == null) {
				return;
			}
			//adiciona os dados de saida do cliente no objeto CLIENTES
			CLIENTES.add(saida);
			//recebe a mensagem do cliente
			String msg = entrada.readLine();

			while (msg != null && !(msg.trim().equals(""))) {
				sendToAll(saida, " escreveu: ", msg);
				msg = entrada.readLine();
			}

			//se cliente enviar linha em branco, mostra a saida no servidor
			System.out.println(this.nomeCliente + " saiu do bate-papo!");
			sendToAll(saida, " saiu", " do bate-papo!");
			remove(this.nomeCliente);
			CLIENTES.remove(saida);
			this.socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendToAll(PrintStream saida, String acao, String msg) throws IOException {
		Enumeration e = CLIENTES.elements();
		while (e.hasMoreElements()) {
			// obtém o fluxo de saída de um dos CLIENTES
			PrintStream chat = (PrintStream) e.nextElement();
			// envia para todos, menos para o próprio usuário
			if (chat != saida) {
				chat.println(this.nomeCliente + acao + msg);
			}
		}
	}
}