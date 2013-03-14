package testes;

import Sistema.Faixada;
import vendas.*;
import org.junit.*;

public class TesteVendas {

    private Faixada f = Faixada.getInstance();
    
    @After
    public void limparDados(){
    	f.reiniciarFachada();
    }
    
    public Venda setarVenda(String cliente, double total, String formaPagamento){
    	Venda v = new Venda();
    	v.setCliente(cliente);
    	v.setFormaPagamento(formaPagamento);
    	v.setTotal(total);
    	return v;
    }
    
//    @Test(expected = ExceptionGerenteVendas.class)
//    public void procurarVendaComListaVazia() throws ExceptionGerenteVendas {
//        f.getVenda();
//    }
     
    @Test
    public void criarUmaVenda(){
    	Venda c = setarVenda("Maria", 130, FormaPagamento.A_VISTA.titulo);
    	Assert.assertTrue("Deveria criar uma venda", f.criarVenda(c));
    }
    
    @Test
    public void criarDoisVendas(){
    	Venda c = setarVenda("Maria", 130, FormaPagamento.A_VISTA.titulo);
    	Assert.assertTrue("Deveria criar uma venda", f.criarVenda(c));
    	Venda cc = setarVenda("João", 160, FormaPagamento.A_VISTA.titulo);
    	Assert.assertTrue("Deveria criar uma venda", f.criarVenda(cc));
    }
    
    @Test 
    public void CriarAMesmaVendaDuasVezes(){

    }
    
    @Test 
    public void cancelarUmaVendaQueInexistente(){
    	f.cancelarVenda();
    }
    
    @Test
    public void cancelarUmaVenda(){
    	
    }
    
    @Test
    public void cancelarUmaVendaDuasVezes(){
    	
    }
    
    @Test
    public void cancelarUmaVendaEDepoisFinalizala(){
    	
    }
    
    @Test
    public void procurarUmaVendaPeloId(){
    	
    } 
    
    @Test
    public void procurarUmaVendaPeloIdInexiste(){
    	
    } 
    
    @Test
    public void procurarVendaInexistente(){
    	//contens
    }
    
    @Test
    public void pegarUmaListaDeVendasDeUmCliente(){
    	
    }
    
    @Test
    public void pegarUmaListaDeVendasPorData(){
    	
    }
    
    @Test
    public void criarLinhaDeVenda(){
    	
    }
   
    @Test
    public void removerLinhaDeVendaInexistente(){
    	
    }
   
    @Test
    public void removerLinhaDeVenda(){
    	
    }
    
    @Test
    public void removerLinhaDeVendaEDepoisProcurala(){
    	
    }
    
    @Test
    public void finalizarVendaAvista(){
    	
    }
    
    @Test
    public void finalizarVendaAprazo(){
    	
    }
   
    //olhar isso
    @Test
    public void pegarLinhasDeVendaDeUmaVenda(){
    	
    }
    
    @Test
    public void pegarLinhasDeVendaDeUmaVendaSemExistirVenda(){
    	
    }
    
    @Test
    public void pegarVendas(){
    	//getVendas
    }

}