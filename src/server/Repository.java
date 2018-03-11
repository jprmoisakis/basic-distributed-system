package server;

public class Repository {
	
	private User userArray[];
	private int index;
	
	public Repository(User[] userArray){
		this.userArray = userArray;
		this.index = 0;
	}
	
	public boolean add(User user){
		for(int i = 0; i < this.index;i++){
			if(this.userArray[i].getCpf() == user.getCpf()){
				return false;
			}
		}
		this.userArray[index] = user;
		this.index++;
		return true;
	}
	
	public boolean remove(User user){
		for(int i = 0; i < this.index;i++){
			if(this.userArray[i].getCpf() == user.getCpf()){
				this.userArray[i].setCpf(null);
				this.userArray[i].setNome(null);
				this.userArray[i].setIdade(null);
				return true;
			}
		}
		return false;
	}
	public boolean edit(User user){
		for(int i = 0; i < this.index;i++){
			if(this.userArray[i].getCpf() == user.getCpf()){
				this.userArray[i].setNome(user.getNome());
				this.userArray[i].setIdade(user.getIdade());
				return true;
			}
		}
		return false;
	}		
}
