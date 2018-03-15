package p2p;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Send implements Runnable{

	private Socket socket;
	
	public Send(Socket socket){

		this.socket = socket;
	}

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
