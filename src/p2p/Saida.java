package p2p;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Saida implements Runnable{

	private Socket socket;
	
	public Saida(Socket socket){

		this.socket = socket;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		Scanner in = new Scanner(System.in);
		while(true){
			String msg = in.nextLine() + '\n';
			try {
				DataOutputStream saida = new DataOutputStream(this.socket.getOutputStream());
				saida.write(msg.getBytes());
				
			
			} catch (IOException e) {
		
				System.out.println("Não Foi possivel ser entregue");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
