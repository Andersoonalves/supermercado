package pessoas;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class GerentePessoa {

	private List<Cliente> clientes;
    private List<Funcionario> funcionarios;
    private List<Fornecedor> fornecedores;

    public GerentePessoa() {
        clientes = new ArrayList<Cliente>();
        funcionarios = new ArrayList<Funcionario>();
        fornecedores = new ArrayList<Fornecedor>();
    }
    
    private boolean contensCliente(String nome) {
        for (Cliente c : clientes) {
            if (c.getNome().equalsIgnoreCase(nome)) {
                return true;
            }
        }
        return false;
    }
    private boolean contensFornecedor(String razao) {
        for (Fornecedor f : fornecedores) {
            if (f.getNome().equalsIgnoreCase(razao)) {
                return true;
            }
        }
        return false;
    }

    private boolean contensFuncionario(String nome) {
    	for (Funcionario f : funcionarios) {
            if (f.getNome().equalsIgnoreCase(nome)) {
                return true;
            }
        }
        return false;
    }

    public boolean adicionar(Cliente c) {
        if (!contensCliente(c.getNome())) {
        	c.setDataCadastronoSistema(Calendar.getInstance());
            return clientes.add(c);
        }
        return false;
    }

    public boolean removerCliente(String nome) throws ExceptionGerentePessoa {
        return clientes.remove(getCliente(nome));
    }

    public Cliente getCliente(String nome) throws ExceptionGerentePessoa{
    	for (Cliente c : clientes) {
            if (c.getNome().equalsIgnoreCase(nome)) {
                return c;
            }
        }
    	throw new ExceptionGerentePessoa("Cliente n��o cadastrado");
    }
    
    public boolean adicionarDependente(String nome, String nomeCliente) throws ExceptionGerentePessoa {
       return getCliente(nomeCliente).adicionarDependente(nome);
    }

    public boolean removerDependente(String nome, String nomeCliente) throws ExceptionGerentePessoa{
        return getCliente(nomeCliente).removerDependente(nome);
    }
    
    public boolean adicionar(Funcionario f){
        if (!contensFuncionario(f.getNome())){
            return funcionarios.add(f);
        }
        return false;
    }

    public boolean removerFuncionario(String nome) throws ExceptionGerentePessoa {
        Funcionario f = getFuncionario(nome);
        return funcionarios.remove(f);
    }
    
    public Funcionario getFuncionario(String nome)  throws ExceptionGerentePessoa{
        for (Funcionario f : funcionarios) {
            if (f.getNome().equalsIgnoreCase(nome)) {
                return f;
            }
        }
        throw new ExceptionGerentePessoa("Funcion��rio n��o cadastrado");
    }
    
    public boolean adicionarFornecedor(Fornecedor f){
        if (!contensFornecedor(f.getNome())){
            return fornecedores.add(f);
        }
        return false;
    }

    public boolean removerFornecedor(String razao) throws ExceptionGerentePessoa {
        return fornecedores.remove(getFornecedor(razao));
    }
    
    public Fornecedor getFornecedor(String razao) throws ExceptionGerentePessoa{
        for (Fornecedor f : fornecedores) {
            if (f.getNome().equalsIgnoreCase(razao)) {
                return f;
            }
        }
        throw new ExceptionGerentePessoa ("Fornecedor n��o cadastrado");
    }
   
    
    public void setarSenhaFuncionario(Funcionario f, String senha, String senhaAntiga)throws ExceptionGerentePessoa{
    	if(senha == null){
    		throw new ExceptionGerentePessoa("Valor da senha inv��lido");
    	}else if(f.getSenha() ==  null || f.getSenha().equalsIgnoreCase(senhaAntiga)){
    		f.setSenha(senha);
    	}else{
    		throw new ExceptionGerentePessoa("Senha do funcionario errada");
    	}
    }
    public boolean validarSenhaFuncionario(Funcionario f, String senha){
    	return f.getSenha().equalsIgnoreCase(senha);
    }
    
    public void addDebito(String nomeCliente, double acrecimo)throws ExceptionGerentePessoa{
    	Cliente c = getCliente(nomeCliente);
        if(acrecimo >0){
        	c.addDebito(acrecimo);
        }else{
        	throw new ExceptionPessoa("Tentativa de adocionar d��bito com valor negativo");
        }
    }
    
    public void diminuirDebito(String nomeCliente, double valor)throws ExceptionGerentePessoa{
    	Cliente c = getCliente(nomeCliente);
        if(valor<=c.getDebito()){
        	c.diminuirDebito(valor);
        }else{
        	throw new ExceptionPessoa("Tentativa de diminuir d��bito acima do valor existente");
        }
    }
    
}
