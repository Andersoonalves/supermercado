package pessoas;

public abstract class Pessoa {

		protected String nome;
		protected Telefone telefone;
		protected Endereco endereco;
	
		public String getNome(){
			return nome;
		}
		
		public void setNome(String nome){
			this.nome = nome;
		}
	    
		public Telefone getTelefone(){
			return telefone;
		}
		
		public void setTelefone(Telefone telefone){
			this.telefone = telefone;
		}
	    
		public Endereco getEndereco(){
			return endereco;
		}

		public void setEndereco(Endereco endereco){
			this.endereco = endereco;
		}
	  
	
	
	
}
