/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pessoas;

/**
 *
 * @author Lettiery
 */
public class Funcionario extends Pessoa{
    private String senha;
    private TipoFuncionario tipo;
    private int cargaHoraria;
    private String cpf;

    public Funcionario() {
    }
    
    protected String getSenha() {
        return senha;
    }

    protected void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoFuncionario getTipo() {
        return tipo;
    }

    public void setTipo(TipoFuncionario tipo) {
        this.tipo = tipo;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    
}
