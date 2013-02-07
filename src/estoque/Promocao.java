package estoque;

import java.util.Calendar;

public class Promocao {
    
    
    private int id;
    
    private Calendar inici;
   
    private Calendar fim;
  
    private double desconto;
    
    private double maximodeVendas;

    public Promocao() {
    }

    public Promocao(Integer id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getInici() {
        return inici;
    }

    public void setInici(Calendar inici) {
        this.inici = inici;
    }

    public Calendar getFim() {
        return fim;
    }

    public void setFim(Calendar fim) {
        this.fim = fim;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public Double getMaximodeVendas() {
        return maximodeVendas;
    }

    public void setMaximodeVendas(Double maximodeVendas) {
        this.maximodeVendas = maximodeVendas;
    }
  
}
