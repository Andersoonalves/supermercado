package vendas;

public enum FormaPagamento {

	 A_VISTA("� VISTA"), A_PRAZO("�_PRAZO");
	
	 public String titulo;
	 private FormaPagamento(String titulo){
		 this.titulo = titulo;
	 }
}
