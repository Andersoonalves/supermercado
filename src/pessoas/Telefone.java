/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pessoas;

/**
 *
 * @author Lettiery
 */

public class Telefone  {
    private String ddd;
    private String telefone;
    
    public Telefone() {
    }
    
    public Telefone(String ddd, String telefone) {
    	this.ddd = ddd;
    	this.telefone = telefone;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }    
}
