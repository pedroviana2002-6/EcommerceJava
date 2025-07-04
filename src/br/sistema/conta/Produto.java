package br.sistema.conta;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Produto {
    private int contador = 1;
    private int codigo;
    private String nome;

    public Produto() {
    	this.codigo = contador++;
    }
    
    public Produto(String nome) {
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

	public Object getFornecedor() {
		return null;
	}
}
