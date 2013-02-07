package estoque;

public class Produto {
   
	private String nome;
	
	private String codigo;
	   
    private double quantidadeemEstoque;
  
    private double quantidedeVendido;
   
    private String fornecedor = null;
    
    private double valordeVenda = 0;

    private int promocao;
   
    public Produto() {
    }
    
    public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public double getValordeVenda() {
        return valordeVenda;
    }

    public void setValordeVenda(double valordeVenda) {
        this.valordeVenda = valordeVenda;
    }

	public void setPromocao(int promocao) {
		this.promocao = promocao;
	}

    public double getQuantidadeemEstoque() {
        return quantidadeemEstoque;
    }

    public void setQuantidadeemEstoque(double quantidadeemEstoque) {
        this.quantidadeemEstoque = quantidadeemEstoque;
    }

    public double getQuantidedeVendido() {
        return quantidedeVendido;
    }

    public void setQuantidedeVendido(double quantidedeVendido) {
        this.quantidedeVendido = quantidedeVendido;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public int getPromocao() {
        return promocao;
    }

    public void setPromocao(Integer promocao) {
        this.promocao = promocao;
    }
}
