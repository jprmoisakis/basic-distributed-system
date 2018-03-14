package ala6_jprm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class Client {
	private String ip;
	private int port;
	private String nome;
	private Socket socket;
	private BufferedWriter bfw;

	public Client(String ip, int port, String nome) {
		this.ip = ip;
		this.port = port;
		this.nome = nome;
	}
	
	public void conectar() throws IOException{
        
		  socket = new Socket(ip,port);
		  bfw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		  bfw.write(nome +"\r\n");
		  bfw.flush();
		}
	
	public void enviarMensagem(String msg) throws IOException{
        
	    if(msg.equals("Sair")){
	      bfw.write("Desconectado \r\n");
	      System.out.println("Desconectado");
	    }else{
	      bfw.write(msg+"\r\n");
	      System.out.println( nome + " diz -> " + msg);
	    }
	     bfw.flush();
	}
	
	
	public void escutar() throws IOException{
        
		   BufferedReader bfr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		   String msg = "";
		                          
		    while(!"Sair".equalsIgnoreCase(msg))
		                                     
		       if(bfr.ready()){
		         msg = bfr.readLine();
		       if(msg.equals("Sair"))
		         System.out.println("Servidor caiu! \r\n");
		        else
		         System.out.println(msg+"\r\n");         
		        }
		}          
	
	
	 public void sair() throws IOException{
         
		    enviarMensagem("Sair");
		    bfw.close();
		    socket.close();
		 }
}