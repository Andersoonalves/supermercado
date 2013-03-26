package testes;

import static org.junit.Assert.*;

import java.util.Calendar;

import sistema.Faixada;
import vendas.*;

import org.junit.*;

import estoque.Produto;

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
    
    public LinhaVenda setarLinhaVenda(String codigoProduto, String nomeProduto, double quantidade, double desconto){
    	Produto p = new Produto();
    	p.setCodigo(codigoProduto);
    	p.setNome(nomeProduto);
    	return new LinhaVenda( quantidade,  desconto, p);
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
    	Venda cc = setarVenda("Jo�o", 160);
    	f.criarVenda(cc);
    }
    
    @Test(expected = ExceptionGerenteVendas.class) 
    public void criarAMesmaVendaDuasVezes() throws ExceptionGerenteVendas{
    	Venda c = setarVenda("Jos�", 140);
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
    	Venda c = setarVenda("Andr�", 230);
    	f.criarVenda(c);
    	f.cancelarVenda();
    	Assert.assertEquals("Deveria cancelar a venda",c, f.finalizarVendaAprazo(10, c.getCliente()));
    }
       
    @Test// a venda so pode ser procurada depois que finalizar-la
    public void procurarUmaVendaPeloId() throws ExceptionGerenteVendas{
    	Venda c = setarVenda("Andr�", 230);
    	f.criarVenda(c);
    	f.finalizarVendaAvista(0);
    	Assert.assertEquals("Deveria cancelar a venda",c, f.getVenda(c.getId()));
    } 
    
    @Test(expected = ExceptionGerenteVendas.class)
    public void procurarUmaVendaPeloIdNaoFinalizada() throws ExceptionGerenteVendas{
    	Venda c = setarVenda("Andr�", 230);
    	f.criarVenda(c);
    	f.getVenda(c.getId());
    } 
    
    @Test(expected = ExceptionGerenteVendas.class)
    public void procurarVendaInexistente() throws ExceptionGerenteVendas{
    	Venda c = setarVenda("Andr�", 230);
    	f.getVenda(c.getId());
    }
    
    // olhar esses metodos
    @Test
    public void pegarUmaListaDeVendasDeUmCliente() throws ExceptionGerenteVendas{
    	Venda c = setarVenda("Andr�", 230);
    	f.criarVenda(c);
    	f.finalizarVendaAvista(0);
        assertEquals("Deveria possuir uma venda",1,f.getVendas("Andr�").size());
    }
    
    @Test
    public void pegarUmaListaDeVendasPorData() throws ExceptionGerenteVendas{
    	Venda c = setarVenda("Maria", 130);
    	f.criarVenda(c);
    	f.finalizarVendaAvista(0);
    	assertEquals("Era para ter apenas uma venda na lista",1,f.getVendas(Calendar.getInstance()).size());
    }
    @Test
    public void criarLinhaDeVenda() throws ExceptionGerenteVendas{
    	Venda c = setarVenda("Andr�", 230);
    	f.criarVenda(c);
    	LinhaVenda lv = setarLinhaVenda("123", "Cerais", 20, 0);
    	f.criaLinha(lv);
    }
   
    @Test (expected = ExceptionGerenteVendas.class)
    public void criarLinhaDeVendaQueJaExiste() throws ExceptionGerenteVendas{
    	Venda c = setarVenda("Andr�", 230);
    	f.criarVenda(c);
    	LinhaVenda lv = setarLinhaVenda("123", "Cerais", 20, 0);
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
    	Venda c = setarVenda("Andr�", 230);
    	f.criarVenda(c);
    	LinhaVenda lv = setarLinhaVenda("123", "Cerais", 20, 0);
    	f.criaLinha(lv);
    	f.removerLinha(lv);
    }
    
    @Test (expected = ExceptionGerenteVendas.class)
    public void removerLinhaDeVendaEDepoisProcurala() throws ExceptionGerenteVendas{
    	Venda c = setarVenda("Andr�", 230);
    	f.criarVenda(c);
    	LinhaVenda lv = setarLinhaVenda("123", "Cerais", 20, 0);
    	f.criaLinha(lv);
    	f.removerLinha(lv);   
    	assertFalse("Deveria ter removido a linha de venda",f.getLinhaVenda(lv.getId()));
    }	
    
    @Test
    public void finalizarVendaAvista() throws ExceptionGerenteVendas{
    	Venda c = setarVenda("Andr�", 230);
    	f.criarVenda(c);
    	assertNotNull( "Deviria ter finalizado a venda corretamente",f.finalizarVendaAvista(0));
    }
  //olhar pq n�o est� dando certo
//    
    @Test (expected = ExceptionGerenteVendas.class)
    public void finalizarVendaAvistaAntesDeCriala() throws ExceptionGerenteVendas{
    	f.finalizarVendaAvista(0);
    }
    
    @Test
    public void finalizarVendaAprazo() throws ExceptionGerenteVendas{
    	Venda c = setarVenda("Andr�", 230);
    	c.setDependente("Jos�");
    	f.criarVenda(c);
    	assertNotNull( "Deviria ter finalizado a venda corretamente",f.finalizarVendaAprazo(0,c.getCliente(), c.getDependente()));
    }
   
    //olhar se est� certo
    @Test
    public void pegarLinhasDeVendaSemTerAdicionadaNaVenda() throws ExceptionGerenteVendas{
    	Venda c = setarVenda("Andr�", 230);
    	f.criarVenda(c);
    	assertTrue("Nao deve possuir nenhuma linda de venda",c.getLinhas().isEmpty());
    }
    
    @Test
    public void pegarLinhasDeVendaNaVenda() throws ExceptionGerenteVendas{
    	Venda c = setarVenda("Andr�", 230);
    	f.criarVenda(c);
    	LinhaVenda lv = setarLinhaVenda("123", "Cerais", 20, 0);
    	f.criaLinha(lv);
    	assertFalse("Deve possuir pelomenos uma linda de venda",c.getLinhas().isEmpty());
    }
    
    @Test
    public void pegarVendas() throws ExceptionGerenteVendas{
    	Venda c = setarVenda("Andr�", 230);
    	f.criarVenda(c);
    	f.finalizarVendaAvista(0);
    	Venda cc = setarVenda("Maria", 280);
    	f.criarVenda(cc);
    	f.finalizarVendaAvista(0);
    	Venda ccc = setarVenda("Ana", 130);
    	f.criarVenda(ccc);
    	f.finalizarVendaAvista(0);
    	assertEquals("Era para ter apenas tres venda na lista",3,f.getVendas().size());
    }

}