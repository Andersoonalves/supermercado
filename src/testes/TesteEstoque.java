package testes;



import static org.junit.Assert.*;

import org.junit.*;

import Sistema.Faixada;
import estoque.*;

public class TesteEstoque {
	private Faixada faixada;
	private Produto p;
	private Produto p2;
	private Promocao pm;
	private Promocao pm2;
	
	@Before
	public void iniciarEstoqueProduto(){
		faixada = Faixada.getInstance();
		p = new Produto();
		p2 = new Produto();
		pm = new Promocao();
		pm2 = new Promocao();
	}
	@After
	public void limparDados(){
		faixada.reiniciarFachada();
	}
	
	@Test
	public void procurarPromocaoComListaVazia() {
		assertNull("A promoção deve ser Null",faixada.getPromocao(123));
	}
	
	@Test(expected = ExceptionGerenteEstoque.class)
	public void criarPromocaoSemProduto() throws ExceptionGerenteEstoque {
		assertNull("A promoção nao pode ser criada",faixada.adicionarPromocao(pm,"123"));
	}
	
	@Test
	public void criarPromocao() throws ExceptionGerenteEstoque{
		setProduto(p,"Arroz","123");
		faixada.adicionarProduto(p);
		setPromocao(pm2, 123);
		assertTrue("Promoca deve ser criada",faixada.adicionarPromocao(pm2, "123"));
	}
	
	@Test
	public void criarERecuperarPromocaoPeloCodigo() throws ExceptionGerenteEstoque{
		setProduto(p,"Arroz","123");
		faixada.adicionarProduto(p);
		setPromocao(pm, 123);
		faixada.adicionarPromocao(pm,"123");
		assertEquals("As promoções devem ser iguais",pm,faixada.getPromocao(123));
	}
	
	@Test
	public void procurarPromocaoNaoCriada() {
		assertNull("A promocao deveria não existir",faixada.getPromocao(321));
	}
	
	@Test
	public void alterarCodigoDePromocaoEProcurar() throws ExceptionGerenteEstoque{
		setProduto(p,"Arroz","123");
		faixada.adicionarProduto(p);
		setPromocao(pm, 123);
		faixada.adicionarPromocao(pm,"123");
		setPromocao(pm, 12345);
		assertEquals("A promocao deveria ser encontrado",pm,faixada.getPromocao(12345));
	}
	
	@Test
	public void alterarNomeDeProdutoEProcurarPromocao() throws ExceptionGerenteEstoque{
		setProduto(p,"Arroz","123");
		faixada.adicionarProduto(p);
		setPromocao(pm, 123);
		faixada.adicionarPromocao(pm,"123");
		p.setNome("Macarrao");
		assertEquals("A promocao deveria ser encontrado",pm,faixada.getPromocao("Macarrao"));
	}
	
	@Test
	public void removerPromocao() throws ExceptionGerenteEstoque{
		setProduto(p,"Feijão","123");
		faixada.adicionarProduto(p);
		setPromocao(pm2, 909);
		faixada.adicionarPromocao(pm2,"123");
		assertTrue("A promocao deveria ser removido",faixada.remover(pm2));
	}
		
	public void setPromocao(Promocao promocao,int id){
		promocao.setId(id);
	}
	
	@Test
	public void procurarProdutoComAListaVazia() {
		assertNull("O produto deveriar ser Null",faixada.getProduto("Qualquer_Produto"));
	}
	
	@Test
	public void criarProduto() throws ExceptionGerenteEstoque {
		setProduto(p,"Feijão","123");
		assertTrue("O produto deveriar ser criado normalmente",faixada.adicionarProduto(p));
	}
	
	@Test
	public void criarProdutoComMesmoNome() throws ExceptionGerenteEstoque{
		setProduto(p,"Feijão","123");
		setProduto(p2,"Feijão","321");
		faixada.adicionarProduto(p);
		assertFalse("O produto deveriar não poder ser criado por que já existe",faixada.adicionarProduto(p2));
	}
	
	@Test
	public void criarProdutoComMesmoCodigo() throws ExceptionGerenteEstoque{
		setProduto(p,"Feijão","123");
		setProduto(p2,"Arroz","123");
		faixada.adicionarProduto(p);
		assertFalse("O produto deveriar não poder ser criado por que já existe",faixada.adicionarProduto(p2));
	}
	
	@Test
	public void criar2Produto() throws ExceptionGerenteEstoque{
		setProduto(p,"Feijão","123");
		setProduto(p2,"Arroz","321");
		faixada.adicionarProduto(p);
		assertTrue("O produto deveriar poder ser criado",faixada.adicionarProduto(p2));
	}
	
	@Test
	public void criarERecuperarProdutoPeloNome() throws ExceptionGerenteEstoque{
		setProduto(p,"Feijão","123");
		faixada.adicionarProduto(p);
		assertEquals("Os produtos deveriam ser iguais",p,faixada.getProduto("Feijão"));
	}
	
	@Test
	public void criarERecuperarProdutoPeloCodigo() throws ExceptionGerenteEstoque{
		setProduto(p,"Feijão","123");
		faixada.adicionarProduto(p);
		assertEquals("Os produtos deveriam ser iguais",p,faixada.getProduto("123"));
	}
	
	@Test
	public void procurarProdutoInexistente() throws ExceptionGerenteEstoque{
		setProduto(p,"Feijão","123");
		faixada.adicionarProduto(p);
		assertNull("O produto deveria não existir",faixada.getProduto("Queijo"));
	}
	
	@Test
	public void procurarProdutoNaoAdicionado() {
		setProduto(p,"Feijão","123");
		assertNull("O produto deveria não existir",faixada.getProduto("Queijo"));
	}
	
	@Test
	public void alterarNomeDoProdutoEProcurar()throws ExceptionGerenteEstoque {
		setProduto(p,"Feijão","123");
		faixada.adicionarProduto(p);
		p.setNome("Queijo");
		assertEquals("O produto deveria ser encontrado",p,faixada.getProduto("Queijo"));
	}
	
	@Test
	public void alterarCodigoDoProdutoEProcurar() throws ExceptionGerenteEstoque{
		setProduto(p,"Feijão","123");
		faixada.adicionarProduto(p);
		p.setCodigo("321");
		assertEquals("O produto deveria ser encontrado",p,faixada.getProduto("321"));
	}
	
	@Test
	public void removerProduto() throws ExceptionGerenteEstoque{
		setProduto(p,"Feijão","123");
		faixada.adicionarProduto(p);
		assertTrue("O produto deveria ser removido",faixada.removerProduto(p));
	}
	
	@Test
	public void procurarProdutoRemovido() throws ExceptionGerenteEstoque{
		setProduto(p,"Feijão","123");
		faixada.adicionarProduto(p);
		faixada.removerProduto(p);
		assertNull("O produto deveria não existir poque já foi removido",faixada.getProduto("Queijo"));
	}
	
	@Test
	public void removerProdutoDepoisDeAlterarONomeEOCodigo() throws ExceptionGerenteEstoque{
		setProduto(p,"Feijão","123");
		faixada.adicionarProduto(p);
		p.setNome("Queijo");
		p.setCodigo("321");
		assertTrue("O produto deveria ser removido",faixada.removerProduto(p));
	}
	
	@Test
	public void setFornecedorDoProduto() throws ExceptionGerenteEstoque{
		setProduto(p,"Feijão","123");
		faixada.adicionarProduto(p);
		assertTrue("Deveria poder ser setado o fornecedor do produto",faixada.setFornecedor("Tropeiro", p.getNome()));
	}
	
	@Test
	public void zerarQuantidadeVendidoDosProdutos() throws ExceptionGerenteEstoque {
		setProduto(p,"Feijão","123");
		faixada.adicionarProduto(p);
		p.setQuantidedeVendido(3);
		setProduto(p2,"Queijo","321");
		faixada.adicionarProduto(p2);
		p2.setQuantidedeVendido(5);
		faixada.zerarQuantidadeVendido();
		assertTrue("Deveria ter zerado a quantidadeVendido dos produtos",(p.getQuantidedeVendido()  == p2.getQuantidedeVendido() && p2.getQuantidedeVendido() == 0));
	}
	
	@Test
	public void zerarQuantidadeNegativaDosProdutos() throws ExceptionGerenteEstoque{
		setProduto(p,"Feijão","123");
		faixada.adicionarProduto(p);
		p.setQuantidadeemEstoque(-2);
		setProduto(p2,"Queijo","321");
		faixada.adicionarProduto(p2);
		p2.setQuantidadeemEstoque(-8);
		faixada.zerarQuantidadeNegativa();
		assertTrue("Deveria ter zerado a quantidadeemEstoque dos produtos",(p.getQuantidadeemEstoque()  == p2.getQuantidadeemEstoque() && p2.getQuantidadeemEstoque() == 0));
	}
	
	@Test
	public void zerarQuantidadeNegativaDeUmProduto() throws ExceptionGerenteEstoque{
		setProduto(p,"Feijão","123");
		faixada.adicionarProduto(p);
		p.setQuantidadeemEstoque(-7);
		assertEquals("Deveria ter zerado a quantidadeemEstoque dos produtos",1,faixada.zerarQuantidadeNegativa());
	}
	
	public void setProduto(Produto p, String nome, String codigo){
		p.setNome(nome);
		p.setCodigo(codigo);
	}
	
	@Test(expected = ExceptionGerenteEstoque.class)
	public void criarProdutoSemCodigo() throws ExceptionGerenteEstoque{
		p.setNome("Feijão");
		faixada.adicionarProduto(p);
	}
	
	@Test(expected = ExceptionGerenteEstoque.class)
	public void criarProdutoSemNome() throws ExceptionGerenteEstoque{
		p.setCodigo("123");
		faixada.adicionarProduto(p);
	}
	
	@Test(expected = ExceptionGerenteEstoque.class)
	public void criarProdutoSemNomeESemCodigo() throws ExceptionGerenteEstoque{
		faixada.adicionarProduto(p);
	}
	
}
