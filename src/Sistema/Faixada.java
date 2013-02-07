package Sistema;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pessoas.*;
import estoque.*;


public class Faixada {

	private static Faixada faixada = null;
	private GerenteEstoque estoque;
	private GerentePessoa pessoas;
	
	private Faixada(){
		estoque = new GerenteEstoque();
		pessoas = new GerentePessoa();
	}
	
	public static Faixada getInstance(){
		if(faixada == null){
			faixada = new Faixada();
		}
		return faixada;
	}
	
	//metodos de efeitos para teste
	public void reiniciarFachada(){
		faixada = new Faixada(); 
	}
	
	//Estoque
	public Produto getProduto(String nomeOUcodigo) {
		return estoque.getProduto(nomeOUcodigo);
    }
   
    public boolean adicionarProduto(Produto p) throws ExceptionGerenteEstoque {
    	return estoque.adicionarProduto(p);
    }

    public boolean removerProduto(Produto p) {
    	return estoque.remover(p);
    }
  
    public void zerarQuantidadeVendido(){
    	estoque.zerarQuantidadeVendido();
    }
    
    public int zerarQuantidadeNegativa(){
    	return estoque.zerarQuantidadeNegativa();
    }
    
    public boolean setFornecedor(String razao, String nomeOUCodigo){
    	return estoque.setFornecedor(razao, nomeOUCodigo);
    }
        
    public boolean adicionarPromocao(Promocao p, String nomeOUcodigo) throws ExceptionGerenteEstoque {
    	return estoque.adicionarPromocao(p, nomeOUcodigo);
    }
       
    public boolean contensPromocao(String nomeOUcodigo) {
    	return estoque.contensPromocao(nomeOUcodigo);
    }
    
    public boolean remover(Promocao p) {
    	return estoque.remover(p);
    }
    
    public Promocao getPromocao(int id) {
    	return estoque.getPromocao(id);
    }

    public Promocao getPromocao(String nomeOUcodigo) {
    	return estoque.getPromocao(nomeOUcodigo);
    }

    
	//Pessoas
    
    public boolean adicionar(Cliente c) {
        return pessoas.adicionar(c);
    }

    public boolean removerCliente(String nome) throws ExceptionGerentePessoa {
        return pessoas.removerCliente(nome);
    }

    public Cliente getCliente(String nome) throws ExceptionGerentePessoa{
    	return pessoas.getCliente(nome);
    }
    
    public boolean adicionarDependente(String nome, String nomeCliente) throws ExceptionGerentePessoa {
    	return pessoas.adicionarDependente(nome, nomeCliente);
    }
    

    public boolean removerDependente(String nome, String nomeCliente) throws ExceptionGerentePessoa{
    	return pessoas.removerDependente(nome, nomeCliente);
    }
    
    public boolean adicionarFuncionario(Funcionario f){
    	return pessoas.adicionar(f);
    }

    public void setarSenhaFuncionario(Funcionario f, String senha, String senhaAntiga) throws ExceptionGerentePessoa{
    	pessoas.setarSenhaFuncionario(f, senha, senhaAntiga);
    }
    
    public boolean removerFuncionario(String nome) throws ExceptionGerentePessoa {
    	return pessoas.removerFuncionario(nome);
    }
    
    public Funcionario getFuncionario(String nome)  throws ExceptionGerentePessoa{
    	return pessoas.getFuncionario(nome);
    }
    
    public boolean validarSenhaFuncionario(Funcionario f, String senha){
    	return pessoas.validarSenhaFuncionario(f, senha);
    }
    
    public boolean adicionarFornecedor(Fornecedor f){
    	return pessoas.adicionarFornecedor(f);
    }

    public boolean removerFornecedor(String razao) throws ExceptionGerentePessoa {
    	return pessoas.removerFornecedor(razao);
    }
    
    public Fornecedor getFornecedor(String razao) throws ExceptionGerentePessoa{
    	return pessoas.getFornecedor(razao);
    }
    
	
}
