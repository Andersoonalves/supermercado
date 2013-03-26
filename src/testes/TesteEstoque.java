package testes;

import static org.junit.Assert.*;

import org.junit.*;

import sistema.Faixada;

import estoque.*;

public class TesteEstoque {
	private Faixada faixada;
	
	public Produto setProduto(String nome, String codigo){
		Produto p = new Produto();
		p.setNome(nome);
		p.setCodigo(codigo);
		return p;
	}
	
	public Promocao setPromocao(int id){
		Promocao promocao = new Promocao();
		promocao.setId(id);
		return promocao;
	}
	
	public Produto adicionarProdutoNaFaixada(String nome, String codigo) throws ExceptionGerenteEstoque {
		Produto p = setProduto(nome,codigo);
		faixada.adicionarProduto(p);
		return p;
	}
	
	@Before
	public void iniciarEstoqueProduto(){
		faixada = Faixada.getInstance();
	}
	@After
	public void limparDados(){
		faixada.reiniciarFachada();
	}
	
	@Test
	public void procurarPromocaoComListaVazia() {
		assertNull("A promo����o deve ser Null",faixada.getPromocao(123));
	}
	
	@Test(expected = ExceptionGerenteEstoque.class)
	public void criarPromocaoSemProduto() throws ExceptionGerenteEstoque {
		Promocao pm = this.setPromocao(321);
		assertNull("A promo����o nao pode ser criada",faixada.adicionarPromocao(pm,"123"));
	}
	
	@Test
	public void criarPromocao() throws ExceptionGerenteEstoque{
		Produto p = adicionarProdutoNaFaixada("Aroz", "123");
		Promocao pm = setPromocao(123);
		assertTrue("Promoca deve ser criada",faixada.adicionarPromocao(pm, "123"));
	}
	
	@Test
	public void criarERecuperarPromocaoPeloCodigo() throws ExceptionGerenteEstoque{
		Produto p = adicionarProdutoNaFaixada("Feijão","123");
		Promocao pm = setPromocao(123);
		faixada.adicionarPromocao(pm,"123");
		assertEquals("As promo����es devem ser iguais",pm,faixada.getPromocao(123));
	}
	
	@Test
	public void procurarPromocaoNaoCriada() {
		assertNull("A promocao deveria n��o existir",faixada.getPromocao(321));
	}
	
	@Test
	public void alterarCodigoDePromocaoEProcurar() throws ExceptionGerenteEstoque{
		adicionarProdutoNaFaixada("Arroz", "123");
		Promocao pm = setPromocao(123);
		faixada.adicionarPromocao(pm,"123");
		assertEquals("A promocao deveria ser encontrado",pm,faixada.getPromocao(123));
	}
	
	@Test
	public void alterarNomeDeProdutoEProcurarPromocao() throws ExceptionGerenteEstoque{
		Produto p = adicionarProdutoNaFaixada("Arroz", "123");
		Promocao pm = setPromocao(123);
		faixada.adicionarPromocao(pm,"123");
		faixada.setNomeProduto(p, "Macarrao");
		assertEquals("A promocao deveria ser encontrado",pm,faixada.getPromocao("Macarrao"));
	}
	
	@Test
	public void removerPromocao() throws ExceptionGerenteEstoque{
		Produto p = adicionarProdutoNaFaixada("Feijão","123");
		Promocao pm = setPromocao(909);
		faixada.adicionarPromocao(pm ,"123");
		assertTrue("A promocao deveria ser removido",faixada.remover(pm));
	}
		
	
	@Test
	public void procurarProdutoComAListaVazia() {
		assertNull("O produto deveriar ser Null",faixada.getProduto("Qualquer_Produto"));
	}
	
	@Test
	public void criarProduto() throws ExceptionGerenteEstoque {
		Produto p = setProduto("Feijão","123");
		assertTrue("O produto deveriar ser criado normalmente",faixada.adicionarProduto(p));
	}
	
	@Test
	public void criarProdutoComMesmoNome() throws ExceptionGerenteEstoque{
		Produto p = adicionarProdutoNaFaixada("Feijão","123");
		Produto p2 = setProduto("Feijão","321");
		assertFalse("O produto deveriar n��o poder ser criado por que j�� existe",faixada.adicionarProduto(p2));
	}
	
	@Test
	public void criarProdutoComMesmoCodigo() throws ExceptionGerenteEstoque{
		Produto p = adicionarProdutoNaFaixada("Feijão","123");
		Produto p2 = setProduto("Arroz","123");
		assertFalse("O produto deveriar n��o poder ser criado por que j�� existe",faixada.adicionarProduto(p2));
	}
	
	@Test
	public void criar2Produto() throws ExceptionGerenteEstoque{
		Produto p = adicionarProdutoNaFaixada("Feijão","123");
		Produto p2 = setProduto("Arroz","321");
		assertTrue("O produto deveriar poder ser criado",faixada.adicionarProduto(p2));
	}
	
	@Test
	public void criarERecuperarProdutoPeloNome() throws ExceptionGerenteEstoque{
		Produto p = adicionarProdutoNaFaixada("Feijão","123");
		assertEquals("Os produtos deveriam ser iguais",p,faixada.getProduto("Feijão"));
	}
	
	@Test
	public void criarERecuperarProdutoPeloCodigo() throws ExceptionGerenteEstoque{
		Produto p = adicionarProdutoNaFaixada("Feijão","123");
		assertEquals("Os produtos deveriam ser iguais",p,faixada.getProduto("123"));
	}
	
	@Test
	public void procurarProdutoInexistente() throws ExceptionGerenteEstoque{
		Produto p = adicionarProdutoNaFaixada("Feijão","123");
		assertNull("O produto deveria n��o existir",faixada.getProduto("Queijo"));
	}
	
	@Test
	public void procurarProdutoNaoAdicionado() {
		Produto p = setProduto("Feijão","123");
		assertNull("O produto deveria n��o existir",faixada.getProduto("Queijo"));
	}
	
	@Test
	public void alterarNomeDoProdutoEProcurar()throws ExceptionGerenteEstoque {
		Produto p = adicionarProdutoNaFaixada("Feijão","123");
		faixada.setNomeProduto(p,"Queijo");
		assertEquals("O produto deveria ser encontrado",p,faixada.getProduto("Queijo"));
	}
	
	@Test
	public void alterarCodigoDoProdutoEProcurar() throws ExceptionGerenteEstoque{
		Produto p = adicionarProdutoNaFaixada("Feijão","123");
		p.setCodigo("321");
		assertEquals("O produto deveria ser encontrado",p,faixada.getProduto("321"));
	}
	
	@Test
	public void removerProduto() throws ExceptionGerenteEstoque{
		Produto p = adicionarProdutoNaFaixada("Feijão","123");
		assertTrue("O produto deveria ser removido",faixada.removerProduto(p));
	}
	
	@Test
	public void procurarProdutoRemovido() throws ExceptionGerenteEstoque{
		Produto p = adicionarProdutoNaFaixada("Feijão","123");
		faixada.removerProduto(p);
		assertNull("O produto deveria n��o existir poque j�� foi removido",faixada.getProduto("Queijo"));
	}
	
	@Test
	public void removerProdutoDepoisDeAlterarONomeEOCodigo() throws ExceptionGerenteEstoque{
		Produto p = adicionarProdutoNaFaixada("Feijão","123");
		p.setNome("Queijo");
		p.setCodigo("321");
		assertTrue("O produto deveria ser removido",faixada.removerProduto(p));
	}
	
	@Test
	public void setFornecedorDoProduto() throws ExceptionGerenteEstoque{
		Produto p = adicionarProdutoNaFaixada("Feijão","123");
		assertTrue("Deveria poder ser setado o fornecedor do produto",faixada.setFornecedor("Tropeiro", p.getNome()));
	}
	
	@Test
	public void zerarQuantidadeVendidoDosProdutos() throws ExceptionGerenteEstoque {
		Produto p = adicionarProdutoNaFaixada("Feijão","123");
		p.setQuantidedeVendido(3);
		Produto p2 = adicionarProdutoNaFaixada("Arroz","321");
		p2.setQuantidedeVendido(5);
		faixada.zerarQuantidadeVendido();
		assertTrue("Deveria ter zerado a quantidadeVendido dos produtos",(p.getQuantidedeVendido()  == p2.getQuantidedeVendido() && p2.getQuantidedeVendido() == 0));
	}
	
	@Test
	public void zerarQuantidadeNegativaDosProdutos() throws ExceptionGerenteEstoque{
		Produto p = adicionarProdutoNaFaixada("Feijão","123");
		p.setQuantidadeemEstoque(-2);
		Produto p2 = adicionarProdutoNaFaixada("Arroz","321");
		p2.setQuantidadeemEstoque(-8);
		faixada.zerarQuantidadeNegativa();
		assertTrue("Deveria ter zerado a quantidadeemEstoque dos produtos",(p.getQuantidadeemEstoque()  == p2.getQuantidadeemEstoque() && p2.getQuantidadeemEstoque() == 0));
	}
	
	@Test
	public void zerarQuantidadeNegativaDeUmProduto() throws ExceptionGerenteEstoque{
		Produto p = adicionarProdutoNaFaixada("Feijão","123");
		p.setQuantidadeemEstoque(-7);
		assertEquals("Deveria ter zerado a quantidadeemEstoque dos produtos",1,faixada.zerarQuantidadeNegativa());
	}
	
	
	@Test(expected = ExceptionGerenteEstoque.class)
	public void criarProdutoSemCodigo() throws ExceptionGerenteEstoque{
		Produto p = new Produto();
		p.setNome("Feijão");
		faixada.adicionarProduto(p);
	}
	
	@Test(expected = ExceptionGerenteEstoque.class)
	public void criarProdutoSemNome() throws ExceptionGerenteEstoque{
		Produto p = new Produto();
		p.setCodigo("123");
		faixada.adicionarProduto(p);
	}
	
	@Test(expected = ExceptionGerenteEstoque.class)
	public void criarProdutoSemNomeESemCodigo() throws ExceptionGerenteEstoque{
		Produto p = new Produto();
		faixada.adicionarProduto(p);
	}
	
}
