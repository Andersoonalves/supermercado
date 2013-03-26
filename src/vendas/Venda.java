/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vendas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class Venda implements Serializable {

    private int id;

    private int indeceCaixa;

    private String funcionario;

    private String formaPagamento;

    private String cliente;

    private String dependente;

    private Calendar data;

    private List<LinhaVenda> linhas = new ArrayList<LinhaVenda>();

    private double desconto;

    private double total;
    
    private static int nextId = 0;
    
    //meto private que get

    protected void addLinhaVenda2(LinhaVenda lv) throws ExceptionGerenteVendas{
    	if(linhas.contains(lv)){
    		throw new ExceptionGerenteVendas("Linha de Venda j� foi inserida nesta venda");
    	}else{
    		linhas.add(lv);
    		lv.setVenda(this);
    	}
    }
    
    public void addLinhaVenda(LinhaVenda lv) throws ExceptionGerenteVendas{
        if(linhas.contains(lv)){
    		throw new ExceptionGerenteVendas("Linha de Venda j� foi inserida nesta venda");
    	}else{
    		linhas.add(lv);
    		lv.setVenda(this);
            total+= lv.getTotaldaLinha();
            desconto+=lv.getDesconto();
    	}
    }
        
    public boolean getLinhaVenda(int id)throws ExceptionGerenteVendas {
        for (LinhaVenda v : linhas) {
            if (v.getId()==(id)) {
                return true;
            }
        }
        throw new ExceptionGerenteVendas("Linha de venda n�o existe!");
    }

    protected void removerLinhaVenda(LinhaVenda lv) throws ExceptionGerenteVendas{
    	if(!linhas.contains(lv)){
    		throw new ExceptionGerenteVendas("Linha de Venda n�o existe para esta venda");
    	}else{
    		linhas.remove(lv);
    		lv.setVenda(null);
    		total-= lv.getTotaldaLinha();
            desconto-= lv.getDesconto();
    	}	
    }

    public List<LinhaVenda>getLinhas(){
        return linhas;
    }

        
    public Venda(){
        this.id = Venda.getNextId();
    }
    
    private static int getNextId(){
        return nextId++;
    }

    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public int getIndeceCaixa() {
        return indeceCaixa;
    }

    public void setIndeceCaixa(int indeceCaixa) {
        this.indeceCaixa = indeceCaixa;
    }

    public String getFuncionario() {
        return funcionario;
    }

    protected void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDependente() {
        return dependente;
    }

    public void setDependente(String dependente) {
        this.dependente = dependente;
    }

    @Override
    public String toString() {
        return "Entidade.Venda[ id=" + id + ", total="+total+" ]";
    }

    public double getDesconto() {
        return desconto;
    }

    public void addDesconto(double desconto) {
        this.desconto += desconto;
        this.total-=desconto;
    }

    public void zerarDesconto() {
        this.total+=desconto;
        this.desconto = 0;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public List<LinhaVenda> getLinhaVendaList() {
        return Collections.unmodifiableList(linhas);
    }

    public boolean isMesmoDia(Calendar dia){// nao vai funcionar com compareTo nem equls por causa das horas
    	if(this.data.get(Calendar.YEAR) == dia.get(Calendar.YEAR) ){
    		if(this.data.get(Calendar.MONTH) == dia.get(Calendar.MONTH) ){
        		if(this.data.get(Calendar.DAY_OF_MONTH) == dia.get(Calendar.DAY_OF_MONTH) ){
            		return true;
            	}
        	}
    	}
    	return false;
    }
    

    }
