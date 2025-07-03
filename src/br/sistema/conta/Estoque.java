package br.sistema.conta;

import java.util.ArrayList;
import java.util.List;

public class Estoque {
	private List<Produto> produtos = new ArrayList<>();

	public void adicionarProduto(Produto produto) {
		produtos.add(produto);
	}

	public List<Produto> listarProdutos() {
		return produtos;
	}

	public Produto buscarPorCodigo(int codigo) {
		for (Produto p : produtos) {
			if (p.getCodigo() == codigo)
				return p;
		}
		return null;
	}

	public List<Produto> buscarPorNome(String nome) {
		List<Produto> encontrados = new ArrayList<>();
		for (Produto p : produtos) {
			if (p.getNome().equalsIgnoreCase(nome)) {
				encontrados.add(p);
			}
		}
		return encontrados;
	}

	public boolean removerProduto(int codigo) {
		Produto p = buscarPorCodigo(codigo);
		if (p != null) {
			produtos.remove(p);
			return true;
		}
		return false;
	}

	public List<Produto> buscarPorFornecedor(Fornecedor fornecedor) {
		List<Produto> lista = new ArrayList<>();
		for (Produto p : produtos) {
			if (p.getFornecedor().equals(fornecedor)) {
				lista.add(p);
			}
		}
		return lista;
	}
}