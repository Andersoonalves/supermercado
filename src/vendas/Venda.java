/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vendas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Venda implements Serializable {

    private int id;

    private int indeceCaixa;

    private String funcionario;

    private String formaPagamento;

    private String cliente;

    private String dependente;

    private Calendar data;

    protected List<LinhaVenda> linhas;

    private double desconto;

    private double total;


    protected void addLinha(LinhaVenda lv){
        if(linhas == null){
            linhas = new ArrayList<LinhaVenda>();
        }
        linhas.add(lv);
        total+= lv.getTotaldaLinha();
        desconto+=lv.getDesconto();
    }

    protected boolean removerLinha(LinhaVenda lv){
        if(linhas == null || linhas.isEmpty()){
            return false;
        }
        linhas.remove(lv);
        total-= lv.getTotaldaLinha();
        desconto-= lv.getDesconto();
        return true;
    }

    public List<LinhaVenda>getLinhas(){
        return linhas;
    }

    public Venda() {
    }

    private Venda(int id) {
        this.id = id;
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
        return linhas;
    }

    public void setLinhaVendaList(List<LinhaVenda> linhaVendaList) {
        this.linhas = linhaVendaList;
    }

    }
