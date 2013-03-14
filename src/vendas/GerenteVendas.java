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

    private List<LinhaVenda> linhas;
    private List<Venda> vendas;
    private Venda atual = null;
  
    public GerenteVendas() {
    	
    	vendas = new LinkedList<Venda>();
           
    }

     public Venda getVenda(int id) {
        for (Venda v : vendas) {
            if (v.getId() == (id)) {
                if (v.linhas == null) {
                    recolherLinhas(v);
                }
                return v;
            }
        }
        return null;
    }

    public List<Venda> getVendas(String nomeCliente) {
        List<Venda> vends = new ArrayList<Venda>();
        for (Venda v : vendas) {
            if (v.getCliente().equalsIgnoreCase(nomeCliente)) {
                recolherLinhas(v);
                vends.add(v);
            }
        }
        return vends;
    }

    public List<Venda> getVendas (Date d){
        List<Venda> vends = new ArrayList<Venda>();
        for (Venda v : vendas) {
            if (v.getData().equals(d)) {
                recolherLinhas(v);
                vends.add(v);
            }
        }
        return vends;
    }
    
    public boolean contensVenda(int id) {
        return (getVenda(id) != null);
    }

    public LinhaVenda getLinhaVenda(int id) {
        for (LinhaVenda v : linhas) {
            if (v.getId()==(id)) {
                return v;
            }
        }
        return null;
    }

    public boolean contensLinhaVenda(int id) {
        return (getLinhaVenda(id) != null);
    }

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

    private boolean recolherLinhas(Venda v) {
        double total = v.getTotal();
        double desconto = v.getDesconto();
        v.setTotal(0.0);
        v.zerarDesconto();
        for (LinhaVenda lv : getLinhas(v.getId())) {
            v.addLinha(lv);
        }
        if (v.getTotal() == total && v.getDesconto() == desconto) {
            return true;
        }
        return false;
    }

    public boolean criar(Venda v) {
        if (v != null && v.getId() == 0) {
           v.setData(Calendar.getInstance());
           vendas.add(v);
            return true;
        }
        return false;
    }

    private boolean criar(LinhaVenda lv, int idVenda) {
        Venda v = getVenda(idVenda);
        if (lv != null && lv.getId() == 0 && v != null) {
            lv.setVenda(v);
         
            linhas.add(lv);
            return true;
        }
        return false;
    }

    public boolean criarLinhaVenda(Produto p, double quantidade, double desconto) {
        if (p != null && quantidade > 0 && desconto >= 0) {
            LinhaVenda lv = new LinhaVenda();
            lv.setNomeProduto(p.getNome());
            lv.setValorProduto(p.getValordeVenda());
            lv.setCodigoProduto(p.getCodigo());
            lv.setDesconto(desconto);
            lv.setQuantidadeVendida(quantidade);
            lv.setTotaldaLinha((quantidade * p.getValordeVenda()) - (desconto*quantidade));
            lv.setVenda(atual);
            linhas.add(lv);
            atual.addLinha(lv);
            return true;
        }
        return false;
    }

    public boolean removerLinha(LinhaVenda lv) {
        if(contensLinhaVenda(lv.getId())){
            atual.removerLinha(lv);
            }
        return true;
    }

      public boolean cancelarVenda() {
        atual = new Venda();
        criar(atual);
        return true;
    }

    private Venda finazilarVenda(double desconto, String formaPagamento, String cliente, String dependente) {
        atual.addDesconto(desconto);
        atual.setFormaPagamento(formaPagamento);
        atual.setCliente(cliente);
        atual.setDependente(dependente);
       
        Venda aux = atual;
        atual = new Venda();
        criar(atual);
       
        return aux;
    }

    public Venda finalizarVendaAvista(double desconto) {
       
        for(LinhaVenda lv: atual.linhas){
            Produto p = Faixada.getInstance().getProduto(lv.getCodigoProduto());
            if(p == null){
                p = Faixada.getInstance().getProduto(lv.getNomeProduto());
                if(p == null){
                    // produto nao encontrado ao add lucro
                    continue;
                }
            }
           
        }
     
        return this.finazilarVenda(desconto, FormaPagamento.A_VISTA.titulo, null, null);
    }

    public Venda finalizarVendaAprazo(double desconto, String cliente) {
       
        for(LinhaVenda lv: atual.linhas){
            Produto p = Faixada.getInstance().getProduto(lv.getCodigoProduto());
            if(p == null){
                p = Faixada.getInstance().getProduto(lv.getNomeProduto());
                if(p == null){
                    // produto nao encontrado ao add lucro
                    continue;
                }
            }
           
        }
        
        return this.finazilarVenda(desconto, FormaPagamento.A_PRAZO.titulo, cliente, null);
    }

    public Venda finalizarVendaAprazo(double desconto, String cliente, String dependente) {
       
        for(LinhaVenda lv: atual.linhas){
            Produto p = Faixada.getInstance().getProduto(lv.getCodigoProduto());
            if(p == null){
                p = Faixada.getInstance().getProduto(lv.getNomeProduto());
                if(p == null){
                    // produto nao encontrado ao add lucro
                    continue;
                }
            }
            
        }
          return this.finazilarVenda(desconto, FormaPagamento.A_PRAZO.titulo, cliente, dependente);
    }

    public List<LinhaVenda> getLinhas() {
        return this.getLinhas(atual.getId());
    }

    public Venda getVenda() throws ExceptionGerenteVendas{
        return atual;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    private String dateString(Date d) {
        if (d == null) {
            return "";
        }
        DateFormat df = DateFormat.getDateInstance(DateFormat.DEFAULT);
        return df.format(d.getTime());
    }

    private Date parseDate(String data) {
        int dia = Integer.parseInt(data.substring(0, 2));
        int mes = Integer.parseInt(data.substring(3, 5)) - 1;
        int ano = Integer.parseInt(data.substring(6, 10)) - 1900;
        Date d = new Date();
        d.setYear(ano);
        d.setMonth(mes);
        d.setDate(dia);
        return d;
    }
 
}