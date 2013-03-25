package testes;

import java.util.ArrayList;
import java.util.List;

import Sistema.Faixada;
import vendas.*;

import org.junit.*;

public class TesteVendas {

    private Faixada f = Faixada.getInstance();
    
    @After
    public void limparDados(){
    	
    	f.reiniciarFachada();
    	
    }
    
    public Venda setarVenda(String cliente, double total){
    	Venda v = new Venda();
    	v.setCliente(cliente);
    	v.setTotal(total);
   
    	return v;
    }
    
    private LinhaVenda setarLinhaVenda(String nomeProduto, double valorProduto ) {
		LinhaVenda v = new LinhaVenda();
		v.setNomeProduto(nomeProduto);
		v.setValorProduto(valorProduto);
		return v;
	}
    
    @Test(expected = ExceptionGerenteVendas.class)
    public void procurarVendasComListaVazia() throws ExceptionGerenteVendas {
        f.getVendas();
    }
    
    @Test (expected = ExceptionGerenteVendas.class)
    public void cancelarUmaVendaInexistente() throws ExceptionGerenteVendas{
    	f.cancelarVenda();
    }
    
    @Test
    public void criarUmaVenda() throws ExceptionGerenteVendas{
    	Venda c = setarVenda("Maria", 130);
    	f.criarVenda(c);
    }
    
    @Test
    public void criarDuasVendas() throws ExceptionGerenteVendas{
    	Venda c = setarVenda("Maria", 130);
    	f.criarVenda(c);
    	f.finalizarVendaAvista(0);
    	Venda cc = setarVenda("João", 160);
    	f.criarVenda(cc);
    }
    
    @Test(expected = ExceptionGerenteVendas.class) 
    public void CriarAMesmaVendaDuasVezes() throws ExceptionGerenteVendas{
    	Venda c = setarVenda("José", 140);
    	f.criarVenda(c);
    	f.criarVenda(c);
    }
   
    @Test
    public void cancelarUmaVenda() throws ExceptionGerenteVendas{
    	Venda c = setarVenda("Maria", 130);
    	f.criarVenda(c);
    	f.cancelarVenda();
    }
    
    @Test (expected = ExceptionGerenteVendas.class)
    public void cancelarUmaVendaDuasVezes() throws ExceptionGerenteVendas{
    	Venda c = setarVenda("Maria", 130);
        f.criarVenda(c);
        f.cancelarVenda();
        f.cancelarVenda();
    }
    
    @Test (expected = ExceptionGerenteVendas.class)
    public void cancelarUmaVendaEDepoisFinalizala() throws ExceptionGerenteVendas{
    	Venda c = setarVenda("André", 230);
    	f.criarVenda(c);
    	f.cancelarVenda();
    	Assert.assertEquals("Deveria cancelar a venda",c, f.finalizarVendaAprazo(10, c.getCliente()));
    }
       
    @Test// a venda so pode ser procurada depois que finalizar-la
    public void procurarUmaVendaPeloId() throws ExceptionGerenteVendas{
    	Venda c = setarVenda("André", 230);
    	f.criarVenda(c);
    	f.finalizarVendaAvista(0);
    	Assert.assertEquals("Deveria cancelar a venda",c, f.getVenda(c.getId()));
    } 
    
    @Test(expected = ExceptionGerenteVendas.class)
    public void procurarUmaVendaPeloIdNaoFinalizada() throws ExceptionGerenteVendas{
    	Venda c = setarVenda("André", 230);
    	f.criarVenda(c);
    	f.getVenda(c.getId());
    } 
    
    @Test(expected = ExceptionGerenteVendas.class)
    public void procurarVendaInexistente() throws ExceptionGerenteVendas{
    	Venda c = setarVenda("André", 230);
    	f.getVenda(c.getId());
    }
    
    // olhar esses metodos
    @Test
    public void pegarUmaListaDeVendasDeUmCliente() throws ExceptionGerenteVendas{
    	Venda c = setarVenda("André", 230);
    	f.criarVenda(c);
    	f.finalizarVendaAvista(0);
    	f.getVendas("André");
    }
    
//    @Test
//    public void pegarUmaListaDeVendasPorData() throws ExceptionGerenteVendas{
//    	
//    }
    @Test
    public void criarLinhaDeVenda() throws ExceptionGerenteVendas{
    	Venda c = setarVenda("André", 230);
    	f.criarVenda(c);
    	LinhaVenda lv = setarLinhaVenda("Cerais", 20);
    	f.criaLinha(lv);
    }
   
    @Test (expected = ExceptionGerenteVendas.class)
    public void criarLinhaDeVendaQueJaExiste() throws ExceptionGerenteVendas{
    	Venda c = setarVenda("André", 230);
    	f.criarVenda(c);
    	LinhaVenda lv = setarLinhaVenda("Cerais", 20);
    	f.criaLinha(lv);
    	f.criaLinha(lv);
   }

	@Test (expected = ExceptionGerenteVendas.class)
    public void removerLinhaDeVendaInexistente()throws ExceptionGerenteVendas{
		LinhaVenda lv = new LinhaVenda();
    	f.removerLinha(lv);
    }
   
    @Test
    public void removerLinhaDeVenda() throws ExceptionGerenteVendas{
    	Venda c = setarVenda("André", 230);
    	f.criarVenda(c);
    	LinhaVenda lv = setarLinhaVenda("Cerais", 20);
    	f.criaLinha(lv);
    	f.removerLinha(lv);
    }
    
    @Test (expected = ExceptionGerenteVendas.class)
    public void removerLinhaDeVendaEDepoisProcurala() throws ExceptionGerenteVendas{
    	Venda c = setarVenda("André", 230);
    	f.criarVenda(c);
    	LinhaVenda lv = setarLinhaVenda("Cerais", 20);
    	f.criaLinha(lv);
    	f.removerLinha(lv);   
    	f.getLinhaVenda(lv.getId());
    }	
    
    @Test
    public void finalizarVendaAvista() throws ExceptionGerenteVendas{
    	Venda c = setarVenda("André", 230);
    	f.criarVenda(c);
    	f.finalizarVendaAvista(0);
    }
  //olhar pq não está dando certo
//    
//    @Test (expected = ExceptionGerenteVendas.class)
//    public void finalizarVendaAvistaAntesDeCriala() throws ExceptionGerenteVendas{
//    	f.finalizarVendaAvista(0);
//    }
    
    @Test
    public void finalizarVendaAprazo() throws ExceptionGerenteVendas{
    	Venda c = setarVenda("André", 230);
    	c.setDependente("José");
    	f.criarVenda(c);
    	f.finalizarVendaAprazo(0,c.getCliente(), c.getDependente());
    }
   
    //olhar se está certo
    @Test
    public void pegarLinhasDeVendaDeUmaVenda() throws ExceptionGerenteVendas{
    	Venda c = setarVenda("André", 230);
    	f.criarVenda(c);
    	c.getLinhas();
    }
    
    @Test
    public void pegarVendas() throws ExceptionGerenteVendas{
    	Venda c = setarVenda("André", 230);
    	f.criarVenda(c);
    	f.finalizarVendaAvista(0);
    	Venda cc = setarVenda("Maria", 280);
    	f.criarVenda(cc);
    	f.finalizarVendaAvista(0);
    	Venda ccc = setarVenda("Ana", 130);
    	f.criarVenda(ccc);
    	f.finalizarVendaAvista(0);
    	f.getVendas();
    }

}