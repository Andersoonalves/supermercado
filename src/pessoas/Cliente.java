/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pessoas;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;



/**
 *
 * @author Lettiery
 */

public class Cliente extends Pessoa {
   
    private double debito = 0;
    private String cpf;
    private Calendar dataNascimento;
    private int valorMedioCompradoMes = 0;
    private Calendar dataCadastronoSistema;
    private List<String> dependentes = new ArrayList<String>();

    public Cliente() {
    }

    public Cliente(String nome) {
        this.nome = nome;
    }

    public double getDebito() {
        return debito;
    }
    
    protected void addDebito(double acrecimo)throws ExceptionPessoa{
        if(acrecimo >0){
        	this.debito += acrecimo;
        }else{
        	throw new ExceptionPessoa("Tentativa de adocionar d��bito com valor negativo");
        }
    }
    
    protected void diminuirDebito(double valor)throws ExceptionPessoa{
        if(valor<=debito){
        	this.debito -= valor;
        }else{
        	throw new ExceptionPessoa("Tentativa de diminuir d��bito acima do valor existente");
        }
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Calendar getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Calendar dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getValorMedioCompradoMes() {
        return valorMedioCompradoMes;
    }

    public void setValorMedioCompradoMes(int valorMedioCompradoMes) {
        this.valorMedioCompradoMes = valorMedioCompradoMes;
    }

    public Calendar getDataCadastronoSistema() {
        return dataCadastronoSistema;
    }

    public void setDataCadastronoSistema(Calendar dataCadastronoSistema) {
        this.dataCadastronoSistema = dataCadastronoSistema;
    }

    
    public boolean adicionarDependente(String nomeDependente){
    	for(String d: dependentes){
    		if( d.equalsIgnoreCase(nomeDependente) ){
    			return false;
    		}
    	}
    	return dependentes.add(nomeDependente);
    }

    public boolean removerDependente(String nomeDependente){
    	for(String d: dependentes){
    		if( d.equalsIgnoreCase(nomeDependente) ){
    			dependentes.remove(nomeDependente);
    			return true;
    		}
    	}
    	return false;
    }
    
    public List<String> getDependentes(){
    	return Collections.unmodifiableList(dependentes);
    }
    
    public boolean contensDependente(String nomeDependente){
    	for(String d: dependentes){
    		if( d.equalsIgnoreCase(nomeDependente) ){
    			return true;
    		}
    	}
    	return false;
    }
    

}
