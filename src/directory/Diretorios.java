package directory;

import java.io.File;

public class Diretorios 
{
	private String path;
	
	public Diretorios(String path)
	{
		this.path = path;
		File novo = new File(path);
		novo.mkdir();
	}
	
	public void criaPasta(String nome)
	{
		//cria arquivo apenas se ele nao existir
		String caminho = this.path + "/" + nome;
		File arq = new File(caminho); 
		
		if (!arq.exists())
		{
			arq.mkdirs();			
		}
	}	

	public boolean delete(String nome)
	{
		String caminho = this.path + '/' + nome;
		File arq = new File(caminho);
		
		if (arq.exists())
		{
			arq.delete();
			return true;
		}
		
		return false;
	}
	
	public String[] listar()
	{
		return new File(this.path).list();
	}
	
	public void up()
	{
		int last = this.path.lastIndexOf("/");
		path = this.path.substring(0, last);
	}
	
	public boolean down(String pasta)
	{
		String caminho = this.path + "/" + pasta;
		if (new File(caminho).exists())
		{
			this.path += '/' + pasta;
			return true;
		}
			
		return false;
	}
}
