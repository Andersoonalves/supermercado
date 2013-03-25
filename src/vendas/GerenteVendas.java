package vendas;

import Sistema.Faixada;
import estoque.Produto;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import pessoas.ExceptionGerentePessoa;

public class GerenteVendas {

    //private List<LinhaVenda> linhas;
    private List<Venda> vendas;
    private Venda atual = null;
  
    public GerenteVendas() {
    	vendas = new LinkedList<Venda>();
    }

     public Venda getVenda(int id) throws ExceptionGerenteVendas {
        for (Venda v : vendas) {
            if (v.getId() == (id)) {
                return v;
            }
        }
        throw new ExceptionGerenteVendas("Venda não encontrada");
    }

    public List<Venda> getVendas(String nomeCliente) throws ExceptionGerenteVendas {
        List<Venda> vends = new ArrayList<Venda>();
        for (Venda v : vendas) {
            if (v.getCliente().equalsIgnoreCase(nomeCliente)) {
                vends.add(v);
            }
        }
        if(vends.isEmpty()){
        	throw new ExceptionGerenteVendas("Nenhuma venda encontrada neste cliente");
        }
        return vends;
    }

    public List<Venda> getVendas (Date d) throws ExceptionGerenteVendas{
        List<Venda> vends = new ArrayList<Venda>();
        for (Venda v : vendas) {
            if (v.getData().equals(d)) {
                vends.add(v);
            }
        }
        if(vends.isEmpty()){
        	throw new ExceptionGerenteVendas("Nenhuma venda encontrada nesta data");
        }
        return vends;
    }
    
    
    public boolean contensVenda(int id) {
        try {
        	getVenda(id);
			return true;
		} catch (ExceptionGerenteVendas e) {
			return false;
		}
    }

  
    public boolean getLinhaVenda(int id) throws ExceptionGerenteVendas {
       
         if (atual.getLinhaVenda(id)) {
                return true;
            }
     
    	throw new ExceptionGerenteVendas("Nenhuma venda encontrada nesta data");
    }
	
    /*
    public boolean contensLinhaVenda(int id) {
        return (getLinhaVenda(id) != null);
    }
    */
 /*
 private List<LinhaVenda> getLinhas(int idVenda) {
        List<LinhaVenda> lin = new ArrayList<LinhaVenda>();
        if (contensVenda(idVenda)) {
            for (LinhaVenda lv : linhas) {

                if (lv.getVenda().equals(idVenda)) {
                    lin.add(lv);
                }
            }
        }
        return lin;
    }
    */
/*
    public boolean recolherLinhas(Venda v) throws ExceptionGerenteVendas {
        double total = v.getTotal();
        double desconto = v.getDesconto();
        v.setTotal(0.0);
        v.zerarDesconto();
        if(!v.getLinhaVendaList().isEmpty()){
            v.setLinhaVendaList(new ArrayList<LinhaVenda>());
    }
        for (LinhaVenda lv : getLinhas(v.getId())) {
            v.addLinha(lv);
        }
        if(v.getLinhaVendaList().isEmpty()){
            throw new ExceptionGerenteVendas("A venda n�o possue nenhuma linha de venda registrada");
        }
        if (v.getTotal() == total && v.getDesconto() == desconto) {
            return true;
        }
        return false;
    }
*/
    
    // cria e seta esta venda como atual, porem a venda atual ja deve ter sido finalizada
    public void criar(Venda v) throws ExceptionGerenteVendas{
    	if(atual != null){
    		throw new ExceptionGerenteVendas("Venda atual já criada, deve ser finalizada ou cancelada antes de recriala");
    	}
    	if (v != null) {
    		try {
				getVenda(v.getId());
			} catch (ExceptionGerenteVendas e) {
				 v.setData(Calendar.getInstance());
		         atual = v;
		         return;
			}
    		throw new ExceptionGerenteVendas("Venda já cadastrada");
        }
    	throw new ExceptionGerenteVendas("Venda nula");
    }

    public void criar(LinhaVenda lv) throws ExceptionGerenteVendas {
        if (lv != null) {
            atual.addLinhaVenda(lv);
        }
    }

    public boolean criarLinhaVenda(Produto p, double quantidade, double desconto) throws ExceptionGerenteVendas {
        if (p != null && quantidade > 0 && desconto >= 0) {
            LinhaVenda lv = new LinhaVenda();
            lv.setNomeProduto(p.getNome());
            lv.setValorProduto(p.getValordeVenda());
            lv.setCodigoProduto(p.getCodigo());
            lv.setDesconto(desconto);
            lv.setQuantidadeVendida(quantidade);
            lv.setTotaldaLinha((quantidade * p.getValordeVenda()) - (desconto*quantidade));
            criar(lv);
        }
        return false;
    }

    public void removerLinhaVenda(LinhaVenda lv, int idVenda) throws ExceptionGerenteVendas {
    	Venda v = getVenda(idVenda);
    	v.removerLinhaVenda(lv);
    }
    
    public void removerLinhaVenda(LinhaVenda lv) throws ExceptionGerenteVendas {
    	if(atual == null){
    		throw new  ExceptionGerenteVendas("Venda atual vazia");
    	}
    	atual.removerLinhaVenda(lv);
    }

      public void cancelarVenda() throws ExceptionGerenteVendas {
    	if(atual == null){
          throw new ExceptionGerenteVendas("Venda atual vazia");
        }
    	atual = null;
    }

      // retorna a venda finalizada e cria uma nova venda para a atual;
    private Venda finazilarVenda(double desconto, String formaPagamento, String cliente, String dependente) throws ExceptionGerenteVendas {
        if(atual != null){
        	atual.addDesconto(desconto);
            atual.setFormaPagamento(formaPagamento);
            atual.setCliente(cliente);
            atual.setDependente(dependente);
           
            this.vendas.add(atual);
            Venda aux = atual;
            atual = null;
            return aux;
        }
        throw new ExceptionGerenteVendas( "A venda deve ser criada antes de ser finalizada" );
    }

    public Venda finalizarVendaAvista(double desconto) throws ExceptionGerenteVendas {
       return this.finazilarVenda(desconto, FormaPagamento.A_VISTA.titulo, atual.getCliente(), atual.getDependente());
    }

    public Venda finalizarVendaAprazo(double desconto, String cliente) throws ExceptionGerenteVendas {
       return this.finazilarVenda(desconto, FormaPagamento.A_PRAZO.titulo, cliente, "Não Possue");
    }

    public Venda finalizarVendaAprazo(double desconto, String cliente, String dependente) throws ExceptionGerenteVendas {
       return this.finazilarVenda(desconto, FormaPagamento.A_PRAZO.titulo, cliente, dependente);
    }

    public Venda getVenda() throws ExceptionGerenteVendas{
        if(atual == null){
        	throw new ExceptionGerenteVendas("Venda atual vazia");
        }
        return atual;
    }

    public List<Venda> getVendas() throws ExceptionGerenteVendas {
    	if (vendas.isEmpty()){
    		throw new ExceptionGerenteVendas("Não existem vendas cadastradas");
    	}
        return vendas;
    }

 
}