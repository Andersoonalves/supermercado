/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vendas;


import estoque.Produto;
import java.io.Serializable;


public class LinhaVenda implements Serializable{
   
    private int id = 0;
   
    private String nomeProduto;
  
    private String codigoProduto;
  
    private double quantidadeVendida;
    
    private double valorProduto;
    
    private double desconto;
   
    private double totaldaLinha;
   
    private Venda venda;
    
    private static int nextId = 0;
    
    public LinhaVenda() {
        this.id = getNextId();
    }
    
    public LinhaVenda (double quantidade, double desconto, Produto p){
    	this();
    	this.quantidadeVendida = quantidade;
    	this.desconto = desconto;
    	this.codigoProduto = p.getCodigo();
    	this.nomeProduto = p.getNome();
    	this.valorProduto = p.getValordeVenda();
    	this.totaldaLinha = (valorProduto *quantidadeVendida )- (desconto*quantidadeVendida);
    }

    public int getId() {
        return id;
    }

    private int getNextId(){
    	return nextId++;
    }
    
    
    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public double getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public void setQuantidadeVendida(double quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }

    public double getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(double valorProduto) {
        this.valorProduto = valorProduto;
    }

    public double getTotaldaLinha() {
        return totaldaLinha;
    }

    public void setTotaldaLinha(double totaldaLinha) {
        this.totaldaLinha = totaldaLinha;
    }

    @Override
    public String toString() {
        return "Entidade.LinhaVenda[ id=" + id + " ]";
    }
   
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LinhaVenda other = (LinhaVenda) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    protected Venda getVenda() {
        return venda;
    }

    protected void setVenda(Venda venda) {
        this.venda = venda;
    }
    
}
