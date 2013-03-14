package vendas;

public enum FormaPagamento {

	 A_VISTA("À VISTA"), A_PRAZO("À_PRAZO");
	
	 public String titulo;
	 private FormaPagamento(String titulo){
		 this.titulo = titulo;
	 }
}
