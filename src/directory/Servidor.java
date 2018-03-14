package directory;

import java.io.DataOutputStream;
import java.io.File;
import java.io.DataInputStream;
import java.net.Socket;
import java.net.ServerSocket;

public class Servidor {
	public static void main(String[] args) throws Exception {
		ServerSocket servidor = new ServerSocket(2000);

		//Diretório raiz pré-definido.
		String path = "Raiz";
		File file = new File(path);
		file.mkdir();

		//Aviso ao Usuario.
		String msg = " ";

		while (true) {	
			Socket socket = servidor.accept();

			DataInputStream socketIn = new DataInputStream(socket.getInputStream()); 
			String comando = socketIn.readUTF();

			if (comando.toLowerCase().startsWith("new")) { 
				String nome = comando.substring(4);

				File temp = new File(path + "/" + nome); 

				if (!temp.exists())	{ 
					temp.mkdirs();			
					msg ="Pasta "+nome+ " criada! \r\n";
				} else msg ="Pasta "+nome+ " já existe! \r\n";

			} else if (comando.toLowerCase().startsWith("delete")) {
				String nome = comando.substring(7);

				File aux = new File(path + '/' + nome);

				if (aux.exists()) {
					delete(aux);
					msg = "Pasta "+nome+ " deletada! \r\n";
				} else	msg = "Pasta "+nome+" não encontrada! \r\n";

			} else if (comando.toLowerCase().startsWith("list")) {
				String listar[] = new File(path).list();

				msg = "Lista: " + '\n' + "------" + '\n';
				for (int i = 0; i < listar.length; i++) msg += listar[i] + "\r\n";
				msg += "------" + '\n';

			} else if (comando.toLowerCase().startsWith("up")) {
				path = path.substring(0, path.lastIndexOf("/"));
				msg = "Local: "+path;

			} else if (comando.toLowerCase().startsWith("down")) {
				String nome = comando.substring(5);

				if (new File(path + "/" + nome).exists()) {
					path += '/' + nome;
					msg = "Local: "+path;
				} else msg = "Pasta "+nome+" não encontrada! \r\n";

			}

			//Avisando ao usuario o que ocorreu.
			DataOutputStream socketOut = new DataOutputStream(socket.getOutputStream());
			socketOut.writeUTF(msg);
			msg = ""; //Limpando String de retorno, para o proximo comando.

			socket.close();
			socketIn.close();
			socketOut.close();
		}		
	}
	//Método à parte para deletar todas as subpastas, caso existam 
	public static boolean delete(File temp) {
		if (temp.exists()) {
			File[] files = temp.listFiles();
			if (files != null) {
				for (int i = 0; i < files.length ;i++) {
					if (files[i].isDirectory()) {
						delete(files[i]);
					} else {
						files[i].delete();
					}
				}
			}
		}
		return (temp.delete());
	}
}