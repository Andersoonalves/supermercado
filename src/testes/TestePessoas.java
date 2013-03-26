package testes;

import java.util.Calendar;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import estoque.ExceptionGerenteEstoque;
import estoque.Produto;
import estoque.Promocao;

import pessoas.Cliente;
import pessoas.Endereco;
import pessoas.ExceptionGerentePessoa;
import pessoas.ExceptionPessoa;
import pessoas.Fornecedor;
import pessoas.Funcionario;
import pessoas.Telefone;
import pessoas.TipoFuncionario;
import sistema.Faixada;



public class TestePessoas {
	
	private Faixada faixada = Faixada.getInstance();;
	
	@After
	public void limparDados(){
		faixada.reiniciarFachada();
	}
	
	public Cliente setarCliente( String nome, String cpf){
		Cliente c = new Cliente();
		c.setNome(nome);
		c.setCpf(cpf);
		c.setDataNascimento(Calendar.getInstance());
		return c;
	}
	
	public Funcionario setarFuncionario( String nome, String cpf, TipoFuncionario tipo){
		Funcionario fu = new Funcionario();
		fu.setNome(nome);
		fu.setCpf(cpf);
		fu.setTipo(tipo);
		return fu;
	}
	
	public Fornecedor setarFornecedor( String razao, String representante){
		Fornecedor fo = new Fornecedor();
		fo.setNome(razao);
		fo.setRepresentante(representante);
		return fo;
	}
	
	@Test(expected = ExceptionGerentePessoa.class)
	public void procurarClienteComListaVazia() throws ExceptionGerentePessoa {
		faixada.getCliente("cliente");
	}
	
	@Test(expected = ExceptionGerentePessoa.class)
	public void removerClienteComListaVazia() throws ExceptionGerentePessoa {
		faixada.removerCliente("cliente");
	}
	
	@Test
	public void adicionarUmCliente() {
		Cliente c = setarCliente("João", "000.000.000-00");
		Assert.assertTrue("Era para poder adicionar o cliente", faixada.adicionar(c));
	}	
	
	@Test
	public void adicionarUmClienteEProcura() throws ExceptionGerentePessoa {
		Cliente c = setarCliente("João", "000.000.000-00");
		faixada.adicionar(c);
		Assert.assertEquals("Era para ter encontrado o cliente", c, faixada.getCliente("João"));
	}
	
	@Test(expected = ExceptionGerentePessoa.class)
	public void adicionarUmClienteEProcuraComNomeDiferente() throws ExceptionGerentePessoa {
		Cliente c = setarCliente("João", "000.000.000-00");
		faixada.adicionar(c);faixada.getCliente("Carlos");
	}
	
	@Test(expected = ExceptionGerentePessoa.class)
	public void criarUmClienteENaoAdicionarMaisProcurar() throws ExceptionGerentePessoa {
		Cliente c = setarCliente("João", "000.000.000-00");
		faixada.getCliente("João");
	}
	
	@Test
	public void adicionarMaisDeUmCliente() throws ExceptionGerentePessoa {
		Cliente c = setarCliente("João", "000.000.000-00");
		Cliente c2 = setarCliente("Carlos","000.000.000-00");
		Assert.assertTrue("Era para ter adicionado os clientes", faixada.adicionar(c)&&faixada.adicionar(c2));
	}
	
	@Test
	public void adicionarMaisDeUmClienteEProcurar() throws ExceptionGerentePessoa {
		Cliente c = setarCliente("João", "000.000.000-00");
		faixada.adicionar(c);
		Cliente c2 = setarCliente("Carlos","000.000.000-00");
		faixada.adicionar(c2);
		Assert.assertEquals("Era para ter encontrado os clientes", c,faixada.getCliente("João"));
		Assert.assertEquals("Era para ter encontrado os clientes", c2,faixada.getCliente("Carlos"));
	}
	
	@Test
	public void procurarClienteComLetraMinuscula() throws ExceptionGerentePessoa {
		Cliente c = setarCliente("João", "000.000.000-00");
		faixada.adicionar(c);
		Assert.assertEquals("Era para ter encontrado os clientes", c,faixada.getCliente("João"));
	}
	
	@Test
	public void mudarONomeDoClienteEProcurar() throws ExceptionGerentePessoa {
		Cliente c = setarCliente("João", "000.000.000-00");
		faixada.adicionar(c);
		c.setNome("Jos��");
		Assert.assertEquals("Era para ter encontrado os clientes", c,faixada.getCliente("Jos��"));
	}
	
	@Test(expected = ExceptionGerentePessoa.class)
	public void adicionarUmClienteRemoveloEProcurar() throws ExceptionGerentePessoa {
		Cliente c = setarCliente("João", "000.000.000-00");
		faixada.adicionar(c);
		faixada.removerCliente("João");
		faixada.getCliente("João");
	}
	
	@Test
	public void adicionarUmClienteRemovelo() throws ExceptionGerentePessoa {
		Cliente c = setarCliente("João", "000.000.000-00");
		faixada.adicionar(c);
		Assert.assertTrue("Era para ter removido os clientes", faixada.removerCliente("João"));
	}
	
	@Test(expected = ExceptionGerentePessoa.class)
	public void removerUmClienteSemAdicionar() throws ExceptionGerentePessoa {
		Cliente c = setarCliente("João", "000.000.000-00");
		faixada.removerCliente("João");
	}
	
	@Test(expected = ExceptionGerentePessoa.class)
	public void removerUmClienteSemTerCriado() throws ExceptionGerentePessoa {
		faixada.removerCliente("João");
	}
	
	@Test
	public void removerDoisClientes() throws ExceptionGerentePessoa {
		Cliente c = setarCliente("João", "000.000.000-00");
		faixada.adicionar(c);
		Cliente c2 = setarCliente("Carlos","000.000.000-00");
		faixada.adicionar(c2);
		Assert.assertTrue("Era para ter removido os clientes", faixada.removerCliente("João")&&faixada.removerCliente("Carlos"));
	}
	
	@Test
	public void adicionarDebitoAoCliente() throws ExceptionGerentePessoa {
		Cliente c = setarCliente("João", "000.000.000-00");
		faixada.adicionar(c);
		faixada.addDebito(c.getNome(), 25.4);
		Assert.assertEquals("Era para ter adicionado o d��bito no cliente", 25.4, faixada.getCliente("João").getDebito(),0);
	}
	
	@Test(expected = ExceptionPessoa.class)
	public void adicionarDebitoAoClienteNegativo() throws ExceptionGerentePessoa, ExceptionPessoa {
		Cliente c = setarCliente("João", "000.000.000-00");
		faixada.adicionar(c);
		faixada.addDebito(c.getNome(),-2.3);
	}
	
	@Test
	public void removerDebitoDoCliente() throws ExceptionGerentePessoa {
		Cliente c = setarCliente("João", "000.000.000-00");
		faixada.adicionar(c);
		faixada.addDebito(c.getNome(),25.4);
		faixada.diminuirDebito(c.getNome(),20);
		Assert.assertEquals("Era para ter removido o d��bito no cliente", 5.4, faixada.getCliente("João").getDebito(),0.001);
	}
	
	@Test(expected = ExceptionPessoa.class)
	public void removerDebitoDoClienteSemAdicionalo() throws ExceptionGerentePessoa, ExceptionPessoa {
		Cliente c = setarCliente("João", "000.000.000-00");
		faixada.adicionar(c);
		faixada.diminuirDebito(c.getNome(),20);
	}

	@Test(expected = ExceptionPessoa.class)
	public void removerDebitoDoClienteMaiorDoQueOPresente() throws ExceptionGerentePessoa, ExceptionPessoa {
		Cliente c = setarCliente("João", "000.000.000-00");
		faixada.adicionar(c);
		faixada.addDebito(c.getNome(),25.4);
		faixada.diminuirDebito(c.getNome(),29);
	}
	
	@Test
	public void adicionarUmTelefoneNoCliente() throws ExceptionGerentePessoa {
		Cliente c = setarCliente("João", "000.000.000-00");
		Telefone t = new Telefone("083","0000-0000");
		c.setTelefone(t);
		faixada.adicionar(c);
		Assert.assertEquals("Era para ter encontrado o telefone do cliente", t,faixada.getCliente("João").getTelefone());
	}
	
	@Test
	public void alterarOTelefoneDoCliente() throws ExceptionGerentePessoa {
		Cliente c = setarCliente("João", "000.000.000-00");
		Telefone t = new Telefone("083","0000-0000");
		c.setTelefone(t);
		faixada.adicionar(c);
		faixada.getCliente("João").getTelefone().setTelefone("0000-0001");
		Assert.assertEquals("Era para ter alterado o telefone do cliente","0000-0001" ,faixada.getCliente("João").getTelefone().getTelefone());
	}
	
	@Test
	public void removerOTelefoneDoCliente() throws ExceptionGerentePessoa {
		Cliente c = setarCliente("João", "000.000.000-00");
		Telefone t = new Telefone("083","0000-0000");
		c.setTelefone(t);
		faixada.adicionar(c);
		faixada.getCliente("João").setTelefone(null);
		Assert.assertNull("N��o era para ter encontrado um telefone no cliente",faixada.getCliente("João").getTelefone());
	}
	
	@Test
	public void procurarUmTelefoneNaoAdicionadoNoCliente() throws ExceptionGerentePessoa {
		Cliente c = setarCliente("João", "000.000.000-00");
		Telefone t = new Telefone("083","0000-0000");
		faixada.adicionar(c);
		Assert.assertNull("Era para ter alterado o telefone do cliente",faixada.getCliente("João").getTelefone());
	}
	
	@Test
	public void adicionarUmEnderecoDoCliente() throws ExceptionGerentePessoa {
		Cliente c = setarCliente("João", "000.000.000-00");
		Endereco e = new Endereco("PB","João Pessoa","Centro","Rua la de Casa","00","58000-000","Beco sem saida");
		c.setEndereco(e);
		faixada.adicionar(c);
		Assert.assertEquals("Era para ter encontrado o endereco do cliente", e,faixada.getCliente("João").getEndereco());
	}
	
	@Test
	public void alterarOEnderecoDoCliente() throws ExceptionGerentePessoa {
		Cliente c = setarCliente("João", "000.000.000-00");
		Endereco e = new Endereco("PB","João Pessoa","Centro","Rua la de Casa","00","58000-000","Beco sem saida");
		c.setEndereco(e);
		faixada.adicionar(c);
		faixada.getCliente("João").getEndereco().setRua("Rua da casa dele");
		Assert.assertEquals("Era para ter alterado o endereco do cliente","Rua da casa dele" ,faixada.getCliente("João").getEndereco().getRua());
	}
	
	@Test
	public void removerOEnderecoDoCliente() throws ExceptionGerentePessoa {
		Cliente c = setarCliente("João", "000.000.000-00");
		Endereco e = new Endereco("PB","João Pessoa","Centro","Rua la de Casa","00","58000-000","Beco sem saida");
		c.setEndereco(e);
		faixada.adicionar(c);
		faixada.getCliente("João").setEndereco(null);
		Assert.assertNull("N��o era para ter encontrado um endereco no cliente",faixada.getCliente("João").getEndereco());
	}
	
	@Test
	public void procurarOEnderecoNaoAdicionadoNoCliente() throws ExceptionGerentePessoa {
		Cliente c = setarCliente("João", "000.000.000-00");
		Endereco e = new Endereco("PB","João Pessoa","Centro","Rua la de Casa","00","58000-000","Beco sem saida");
		faixada.adicionar(c);
		Assert.assertNull("Era para ter alterado o endereco do cliente",faixada.getCliente("João").getEndereco());
	}
	
	@Test
	public void adicionarUmDependenteAoCliente() throws ExceptionGerentePessoa {
		Cliente c = setarCliente("João", "000.000.000-00");
		faixada.adicionar(c);
		c.adicionarDependente("João Jr.");
		Assert.assertTrue("Era para ter encontrado o dependente do cliente", faixada.getCliente("João").getDependentes().contains("João Jr."));
	}
	
	@Test
	public void adicionarDoisDependenteAoCliente() throws ExceptionGerentePessoa {
		Cliente c = setarCliente("João", "000.000.000-00");
		faixada.adicionar(c);
		c.adicionarDependente("João Jr.");
		c.adicionarDependente("Maria");
		Assert.assertTrue("Era para ter encontrado os dependentes do cliente", faixada.getCliente("João").getDependentes().contains("João Jr."));
	}
	
	@Test
	public void adicionarDoisDependenteIdenticosAoMesmoCliente() throws ExceptionGerentePessoa {
		Cliente c = setarCliente("João", "000.000.000-00");
		faixada.adicionar(c);
		c.adicionarDependente("João Jr.");
		Assert.assertFalse("Era para ter encontrado o dependente do cliente", c.adicionarDependente("João Jr."));
	}
	
	@Test
	public void adicionarDoisClientesComDependentesIquais() throws ExceptionGerentePessoa {
		Cliente c = setarCliente("João", "000.000.000-00");
		Cliente c2 = setarCliente("Carlos","000.000.000-00");
		faixada.adicionar(c);
		faixada.adicionar(c2);
		Assert.assertTrue("Era para ter adicionado os dependentes dos clientes", c.adicionarDependente("João Jr.")&&c2.adicionarDependente("João Jr."));
	}
	
	@Test
	public void procurarDependente() throws ExceptionGerentePessoa {
		Cliente c = setarCliente("João", "000.000.000-00");
		c.adicionarDependente("João Jr.");
		faixada.adicionar(c);boolean dependenteEncontrado = false;
		for(String dependente: c.getDependentes()){
			if(dependente.equalsIgnoreCase("Maria")){
				dependenteEncontrado = true; break;
			}
		}
		Assert.assertFalse("N��o era para ter encontrado o dependente no cliente", dependenteEncontrado);
	}
	
	@Test
	public void procurarDependenteInexistente() throws ExceptionGerentePessoa {
		Cliente c = setarCliente("João", "000.000.000-00");
		c.adicionarDependente("João Jr.");
		faixada.adicionar(c);
		Assert.assertFalse("N��o era para ter encontrado o dependente no cliente", c.contensDependente("João Jr"));
	}
	
	@Test
	public void recupererListaDeDependentesSemTerAdicionadoNenhum() throws ExceptionGerentePessoa {
		Cliente c = setarCliente("João", "000.000.000-00");
		faixada.adicionar(c);
		Assert.assertTrue("N��o era para ter encontrado nenhum dependente no cliente", c.getDependentes().isEmpty());
	}
	
	@Test
	public void removerDependenteSemTerAdicionado() throws ExceptionGerentePessoa {
		Cliente c = setarCliente("João", "000.000.000-00");
		faixada.adicionar(c);
		Assert.assertFalse("N��o era para ter encontrado nenhum dependente no cliente", c.removerDependente("João Jr."));
	}
	
	@Test
	public void removerDependente() throws ExceptionGerentePessoa {
		Cliente c = setarCliente("João", "000.000.000-00");
		faixada.adicionar(c);
		c.adicionarDependente("João Jr.");
		Assert.assertTrue("Era para ter removido o dependente do cliente", c.removerDependente("João Jr."));
	}
	
	@Test
	public void removerDependenteEDepoisProcuralo() throws ExceptionGerentePessoa {
		Cliente c = setarCliente("João", "000.000.000-00");
		faixada.adicionar(c);
		c.adicionarDependente("João Jr.");c.removerDependente("João Jr.");
		Assert.assertFalse("Era para ter removido o dependente do cliente", c.contensDependente("João Jr."));
	}
	
	@Test
	public void removerDependenteDeUmClientesTendoAdicionadoEmOutro() throws ExceptionGerentePessoa {
		Cliente c = setarCliente("João", "000.000.000-00");
		faixada.adicionar(c);
		Cliente c2 = setarCliente("Carlos","000.000.000-00");
		faixada.adicionar(c2);
		c.adicionarDependente("João Jr.");
		Assert.assertFalse("Era para ter removido o dependente do cliente", c2.removerDependente("João Jr."));
	}
	//Funcionario
	
	@Test
	public void adicionarUmFuncionario() throws ExceptionGerentePessoa {
		Funcionario fu = setarFuncionario("Z��", "000.000.000-00", TipoFuncionario.GERENTE);
		faixada.setarSenhaFuncionario(fu, "123", null);
		Assert.assertTrue("Era para ter adicionado o Funcionario", faixada.adicionarFuncionario(fu));
	}
	
	@Test
	public void adicionarUmFuncionarioComOMesmoNome() throws ExceptionGerentePessoa {
		Funcionario fu = setarFuncionario("Z��", "000.000.000-00", TipoFuncionario.GERENTE);
		faixada.setarSenhaFuncionario(fu, "123", null);
		faixada.adicionarFuncionario(fu);
		Funcionario fu2 = setarFuncionario("Z��", "000.000.000-00", TipoFuncionario.GERENTE);
		faixada.setarSenhaFuncionario(fu2, "123", null);
		Assert.assertFalse("N��o era para ter adicionado o segundo Funcionario", faixada.adicionarFuncionario(fu2));
	}
	
	@Test
	public void adicionarUmFuncionarioDuasVezes() throws ExceptionGerentePessoa {
		Funcionario fu = setarFuncionario("Z��", "000.000.000-00", TipoFuncionario.GERENTE);
		faixada.setarSenhaFuncionario(fu, "123", null);
		faixada.adicionarFuncionario(fu);
		Assert.assertFalse("N��o era para ter adicionado o Funcionario", faixada.adicionarFuncionario(fu));
	}
	
	@Test
	public void adicionarDoisFuncionariosComONomeDiferentes() throws ExceptionGerentePessoa {
		Funcionario fu = setarFuncionario("Z��", "000.000.000-00", TipoFuncionario.GERENTE);
		faixada.setarSenhaFuncionario(fu, "123", null);
		faixada.adicionarFuncionario(fu);
		Funcionario fu2 = setarFuncionario( "Carlos", "000.000.000-00", TipoFuncionario.GERENTE);
		faixada.setarSenhaFuncionario(fu2, "123", null);
		Assert.assertTrue("Era para ter adicionado os Funcionarios", faixada.adicionarFuncionario(fu2));
	}
	
	@Test
	public void removerFuncionario() throws ExceptionGerentePessoa {
		Funcionario fu = setarFuncionario("Z��", "000.000.000-00", TipoFuncionario.GERENTE);
		faixada.setarSenhaFuncionario(fu, "123", null);
		faixada.adicionarFuncionario(fu);
		Assert.assertTrue("Era para ter removido os Funcionarios", faixada.removerFuncionario(fu.getNome()));
	}
	
	@Test(expected = ExceptionGerentePessoa.class)
	public void removerFuncionarioNaoAdicionado() throws ExceptionGerentePessoa {
		Funcionario fu = setarFuncionario("Z��", "000.000.000-00", TipoFuncionario.GERENTE);
		faixada.setarSenhaFuncionario(fu, "123", null);
		faixada.removerFuncionario(fu.getNome());
	}
	
	@Test(expected = ExceptionGerentePessoa.class)
	public void removerFuncionarioDuasVezes() throws ExceptionGerentePessoa {
		Funcionario fu = setarFuncionario("Z��", "000.000.000-00", TipoFuncionario.GERENTE);
		faixada.setarSenhaFuncionario(fu, "123", null);
		faixada.removerFuncionario(fu.getNome());
		faixada.removerFuncionario(fu.getNome());
	}
	
	@Test
	public void recuperarFuncionarioAdicionado() throws ExceptionGerentePessoa {
		Funcionario fu = setarFuncionario("Z��", "000.000.000-00", TipoFuncionario.GERENTE);
		faixada.setarSenhaFuncionario(fu, "123", null);
		faixada.adicionarFuncionario(fu);
		Assert.assertEquals("Era para ter removido os Funcionarios", fu,faixada.getFuncionario("Z��"));
	}
	
	@Test(expected = ExceptionGerentePessoa.class)
	public void recuperarFuncionarioNaoAdicionado() throws ExceptionGerentePessoa {
		Funcionario fu = setarFuncionario("Z��", "000.000.000-00", TipoFuncionario.GERENTE);
		faixada.setarSenhaFuncionario(fu, "123", null);
		faixada.getFuncionario("Z��");
	}
	
	@Test(expected = ExceptionGerentePessoa.class)
	public void recuperarFuncionarioNaoCriado() throws ExceptionGerentePessoa {
		faixada.getFuncionario("Z��");
	}
	
	@Test
	public void validarSenhaFuncionario() throws ExceptionGerentePessoa {
		Funcionario fu = setarFuncionario("Z��", "000.000.000-00", TipoFuncionario.GERENTE);
		faixada.setarSenhaFuncionario(fu, "123", null);
		Assert.assertTrue("Era pra ter validado a senha",faixada.validarSenhaFuncionario(fu, "123"));
	}
	
	@Test
	public void naoValidarSenhaFuncionario() throws ExceptionGerentePessoa {
		Funcionario fu = setarFuncionario("Z��", "000.000.000-00", TipoFuncionario.GERENTE);
		faixada.setarSenhaFuncionario(fu, "123", null);
		Assert.assertFalse("Era pra ter validado a senha",faixada.validarSenhaFuncionario(fu, "321"));
	}

	@Test
	public void alterarSenhaFuncionario() throws ExceptionGerentePessoa {
		Funcionario fu = setarFuncionario("Z��", "000.000.000-00", TipoFuncionario.GERENTE);
		faixada.setarSenhaFuncionario(fu, "123", null);
		faixada.setarSenhaFuncionario(fu, "321","123");
		Assert.assertTrue("Era pra ter alterado a senha",faixada.validarSenhaFuncionario(fu, "321"));
	}
	
	@Test(expected = ExceptionGerentePessoa.class)
	public void alterarSenhaFuncionarioParaNull() throws ExceptionGerentePessoa {
		Funcionario fu = setarFuncionario("Z��", "000.000.000-00", TipoFuncionario.GERENTE);
		faixada.setarSenhaFuncionario(fu, "123", null);
		faixada.setarSenhaFuncionario(fu, null,"123");
	}
	
	@Test(expected = ExceptionGerentePessoa.class)
	public void alterarSenhaFuncionarioComSenhaAntigaInvalida() throws ExceptionGerentePessoa {
		Funcionario fu = setarFuncionario("Z��", "000.000.000-00", TipoFuncionario.GERENTE);
		faixada.setarSenhaFuncionario(fu, "123", null);
		faixada.setarSenhaFuncionario(fu, "321","000");
	}
	
	@Test
	public void alterarSenhaFuncionarioParaAMesma() throws ExceptionGerentePessoa {
		Funcionario fu = setarFuncionario("Z��", "000.000.000-00", TipoFuncionario.GERENTE);
		faixada.setarSenhaFuncionario(fu, "123", null);
		faixada.setarSenhaFuncionario(fu, "123","123");
		Assert.assertTrue("Era pra ter alterado a senha",faixada.validarSenhaFuncionario(fu, "123"));
	}
	
	// Fornecedor
	
	@Test
	public void adicionarFornecedor() throws ExceptionGerentePessoa {
		Fornecedor fo = setarFornecedor("Feij��o Podre LTDA", "Sebasti��o");
		Assert.assertTrue("Era para ter adicionado o Fornecedor", faixada.adicionarFornecedor(fo));
	}
	
	@Test
	public void adicionarFornecedorComOMesmoNome() throws ExceptionGerentePessoa {
		Fornecedor fo = setarFornecedor("Feij��o Podre LTDA", "Sebasti��o");
		faixada.adicionarFornecedor(fo);
		Fornecedor fo2 = setarFornecedor( "Feij��o Podre LTDA", "Paulo");
		Assert.assertFalse("N��o era para ter adicionado o segundo Fornecedor", faixada.adicionarFornecedor(fo2));
	}
	
	@Test
	public void adicionarFornercedorDuasVezes() throws ExceptionGerentePessoa {
		Fornecedor fo = setarFornecedor("Feij��o Podre LTDA", "Sebasti��o");
		faixada.adicionarFornecedor(fo);
		Assert.assertFalse("N��o era para ter adicionado o Fornecedor", faixada.adicionarFornecedor(fo));
	}
	
	@Test
	public void adicionarDoisFornecedoresComONomeRepetidoERepresentanteDiferente() throws ExceptionGerentePessoa {
		Fornecedor fo = setarFornecedor("Feij��o Podre LTDA", "Sebasti��o");
		faixada.adicionarFornecedor(fo);
		Fornecedor fo2 = setarFornecedor( "Feij��o Podre LTDA", "Paulo");
		Assert.assertFalse("N��o era para ter adicionado o segundo Fornecedor", faixada.adicionarFornecedor(fo2));
	}
	
	@Test
	public void adicionarDoisFornecedoresComONomeDiferentes() throws ExceptionGerentePessoa {
		Fornecedor fo = setarFornecedor("Feij��o Podre LTDA", "Sebasti��o");
		faixada.adicionarFornecedor(fo);
		Fornecedor fo2 = setarFornecedor("Arroz Ruim LTDA", "Zezo");
		Assert.assertTrue("Era para ter adicionado os Fornecedores", faixada.adicionarFornecedor(fo2));
	}
	
	@Test
	public void removerFornecedo() throws ExceptionGerentePessoa {
		Fornecedor fo = setarFornecedor("Feij��o Podre LTDA", "Sebasti��o");
		faixada.adicionarFornecedor(fo);
		Assert.assertTrue("Era para ter removido os Fornecedores", faixada.removerFornecedor(fo.getNome()));
	}
	
	@Test(expected = ExceptionGerentePessoa.class)
	public void removerFornecedorNaoAdicionado() throws ExceptionGerentePessoa {
		Fornecedor fo = setarFornecedor("Feij��o Podre LTDA", "Sebasti��o");
		faixada.removerFornecedor(fo.getNome());
	}
	
	@Test(expected = ExceptionGerentePessoa.class)
	public void removerFornecedorDuasVezes() throws ExceptionGerentePessoa {
		Fornecedor fo = setarFornecedor("Feij��o Podre LTDA", "Sebasti��o");
		faixada.removerFornecedor(fo.getNome());
		faixada.removerFornecedor(fo.getNome());
	}
	
	@Test
	public void recuperarFornecedorAdicionado() throws ExceptionGerentePessoa {
		Fornecedor fo = setarFornecedor("Feij��o Podre LTDA", "Sebasti��o");
		faixada.adicionarFornecedor(fo);
		Assert.assertEquals("Era para ter removido os Fornecedores", fo,faixada.getFornecedor("Feij��o Podre LTDA"));
	}
	
	@Test(expected = ExceptionGerentePessoa.class)
	public void recuperarFornecedorNaoAdicionado() throws ExceptionGerentePessoa {
		Fornecedor fo = setarFornecedor("Feij��o Podre LTDA", "Sebasti��o");
		faixada.getFornecedor("Arroz Ruim LTDA");
	}
	
	@Test(expected = ExceptionGerentePessoa.class)
	public void recuperarFornecedorNaoCriado() throws ExceptionGerentePessoa {
		faixada.getFornecedor("Feij��o Podre LTDA");
	}
	
	@Test
	public void adicionarTelefoneNoFornecedor() throws ExceptionGerentePessoa {
		Fornecedor fo = setarFornecedor("Feij��o Podre LTDA", "Sebasti��o");
		fo.setTelefone(new Telefone("083","0000-0000"));
		faixada.adicionarFornecedor(fo);
		Assert.assertEquals("Era para ter adicionado o telefone  no Fornecedore", "0000-0000",faixada.getFornecedor("Feij��o Podre LTDA").getTelefone().getTelefone());
	}
	
	@Test
	public void adicionarTelefoneRepresentante() throws ExceptionGerentePessoa {
		Fornecedor fo = setarFornecedor("Feij��o Podre LTDA", "Sebasti��o");
		fo.setTelefoneRepresentante(new Telefone("083","0000-0000"));
		faixada.adicionarFornecedor(fo);
		Assert.assertEquals("Era para ter adicionado o telefone no Representante", "0000-0000",faixada.getFornecedor("Feij��o Podre LTDA").getTelefoneRepresentante().getTelefone());
	}
	
	@Test
	public void removerTelefoneNoFornecedor() throws ExceptionGerentePessoa {
		Fornecedor fo = setarFornecedor("Feij��o Podre LTDA", "Sebasti��o");
		fo.setTelefone(new Telefone("083","0000-0000"));
		faixada.adicionarFornecedor(fo);
		fo.setTelefone(null);
		Assert.assertNull("Era para ter removido o telefone do Fornecedor", faixada.getFornecedor("Feij��o Podre LTDA").getTelefone());
	}
	
	@Test
	public void removerTelefoneRepresentante() throws ExceptionGerentePessoa {
		Fornecedor fo = setarFornecedor("Feij��o Podre LTDA", "Sebasti��o");
		fo.setTelefoneRepresentante(new Telefone("083","0000-0000"));
		faixada.adicionarFornecedor(fo);
		fo.setTelefoneRepresentante(null);
		Assert.assertNull("Era para ter removido o telefone do Representante", faixada.getFornecedor("Feij��o Podre LTDA").getTelefoneRepresentante());
	}
	
	@Test
	public void adicionarEnderecoNoFornecedor() throws ExceptionGerentePessoa {
		Fornecedor fo = setarFornecedor("Feij��o Podre LTDA", "Sebasti��o");
		Endereco e = new Endereco("PB","João Pessoa","Centro","Rua da Industria","00","58000-000","Beco sem saida");
		fo.setEndereco(e);
		faixada.adicionarFornecedor(fo);
		Assert.assertEquals("Era para ter adicionado o Endereco no Fornecedore", e,faixada.getFornecedor("Feij��o Podre LTDA").getEndereco());
	}
	
	@Test
	public void removerEnderecoNoFornecedor() throws ExceptionGerentePessoa {
		Fornecedor fo = setarFornecedor("Feij��o Podre LTDA", "Sebasti��o");
		Endereco e = new Endereco("PB","João Pessoa","Centro","Rua da Industria","00","58000-000","Beco sem saida");
		fo.setEndereco(e);
		faixada.adicionarFornecedor(fo);
		fo.setEndereco(null);
		Assert.assertNull("Era para ter removido o Endereco do Fornecedor", faixada.getFornecedor("Feij��o Podre LTDA").getEndereco());
	}
}
