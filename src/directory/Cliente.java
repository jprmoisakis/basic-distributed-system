package directory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		
		while(true)	{
			Socket socket = new Socket("localhost", 2000);
			
			String operacao = in.nextLine();
					
			DataOutputStream dado = new DataOutputStream(socket.getOutputStream());
			dado.writeUTF(operacao);
			
			DataInputStream socketIn = new DataInputStream(socket.getInputStream()); 
			System.out.println(socketIn.readUTF());
			socket.close();
		}
	}
}
