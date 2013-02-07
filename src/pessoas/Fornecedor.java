/*
 */
package pessoas;

/**
 * o nome do fornecedor é a razão social da empresa
 * exemplo Vitarela
 * @author Lettiery
 */
public class Fornecedor extends Pessoa{
    private String representante;// um para cada industria
    private Telefone telefoneRepresentante;// telefone direto do representante

    public Fornecedor() {
    }
    
    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }
    
    public Telefone getTelefoneRepresentante() {
        return telefoneRepresentante;
    }

    public void setTelefoneRepresentante(Telefone telefoneRepresentante) {
        this.telefoneRepresentante = telefoneRepresentante;
    }
    
}
