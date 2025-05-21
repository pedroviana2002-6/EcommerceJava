package br.sistema.conta;

public class Produto {
	private static int contador = 1;
	private int codigo;
    private String nome;
    private double preco;
    private int quantidade;
    private Fornecedor fornecedor;

    public Produto(String nome, double preco, int quantidade, Fornecedor fornecedor) {
    	this.codigo = contador++;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.fornecedor = fornecedor;
    }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public int getCodigo() {
	    return codigo;
	}
    
}