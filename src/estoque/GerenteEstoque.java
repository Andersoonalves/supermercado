package estoque;

import java.util.LinkedList;
import java.util.List;

public class GerenteEstoque {
	 private List<Produto> produtos;
	    private List<Promocao> promocoes;

	    public GerenteEstoque() {
	    	produtos = new LinkedList<Produto>();
	    	promocoes = new LinkedList<Promocao>();
	    }

	    public Produto getProduto(String nomeOUcodigo) {
	        for (Produto p : produtos) {
	            if (p.getNome().equalsIgnoreCase(nomeOUcodigo) || p.getCodigo().equalsIgnoreCase(nomeOUcodigo)) {
	                return p;
	            }
	        }
	        return null;
	    }

	    private boolean contensProduto(String nomeOUcodigo) {
	        return (getProduto(nomeOUcodigo) != null);
	    }

	   
	    public boolean adicionarProduto(Produto p) throws ExceptionGerenteEstoque {
	    	
	    	if(p.getNome() == null || p.getCodigo() == null){
	    		throw new ExceptionGerenteEstoque("Nome ou Codigo não inicializados");
	    	}
	        if (!contensProduto(p.getCodigo()) && !contensProduto(p.getNome())) {
	            return produtos.add(p);
	        }
	        return false;
	    }

	    public boolean remover(Produto p) {
	        if (contensProduto(p.getCodigo()) && contensProduto(p.getNome())) {
	            return produtos.remove(p);
	        }
	        return false;
	    }

	  
	    public void zerarQuantidadeVendido(){
	        for (Produto p : produtos) {
	            p.setQuantidedeVendido(0.0);
	        }
	    }
	    
	    public int zerarQuantidadeNegativa(){
	        int cont = 0;
	        for (Produto p : produtos) {
	             if(p.getQuantidadeemEstoque()<0){
	                 p.setQuantidadeemEstoque(0.0);
	                 cont++;
	             }
	        }
	        return cont;
	    }
	    
	    public boolean setFornecedor(String razao, String nomeOUCodigo){
	        Produto p = getProduto(nomeOUCodigo);
	        if(p!= null && razao != null){
	            p.setFornecedor(razao);
	            return true;
	        }
	        return false;
	    }
	        
	    public boolean adicionarPromocao(Promocao p, String nomeOUcodigo) throws ExceptionGerenteEstoque {
	    	    	
	    	if (getProduto(nomeOUcodigo)== null){
	    		throw new ExceptionGerenteEstoque("Produto  não exite");
	    	}
	    	if (p.getId() == 0){
	    		throw new ExceptionGerenteEstoque(" Promocao não exite");
	    	}
	    	 Produto prod = getProduto(nomeOUcodigo);
	        
	         if (prod.getPromocao() != 0) {
	            remover(getPromocao(prod.getPromocao()));
	        }
	         
	        prod.setPromocao(p.getId());
	        return promocoes.add(p);
	       }
	       
	    private boolean contensPromocao(int id) {
	        return (getPromocao(id) != null);
	    }
	    
	    public boolean contensPromocao(String nomeOUcodigo) {
	        return (getPromocao(nomeOUcodigo) != null);
	    }
	    
	    public boolean remover(Promocao p) {
	        if (contensPromocao(p.getId())) {
	           return promocoes.remove(p);
	        }
	        return false;
	    }
	    
	    public Promocao getPromocao(int id) {
	        for (Promocao p : promocoes) {
	            if (p.getId() == id) {
	                return p;
	            }
	        }
	        return null;
	    }

	    public Promocao getPromocao(String nomeOUcodigo) {
	        Produto prod = getProduto(nomeOUcodigo);
	        if (prod.getPromocao() != 0) {
	            for (Promocao p : promocoes) {
	                if (p.getId() == prod.getPromocao()) {
	                    return p;
	                }
	            }
	        }
	        return null;
	    }
}
