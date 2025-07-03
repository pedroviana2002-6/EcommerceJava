package br.sistema.conta;

import java.time.LocalDate;

public class Pedido {
    private static int contador = 1;
    private int codigo;
    private LocalDate dataPedido;
    private LocalDate dataEntregaCorreios;
    private LocalDate dataEntregaTransportadora;
    private String situacao;
    private String metodo;

    public Pedido() {
        this.codigo = contador++;
        this.dataPedido = LocalDate.now();
        this.dataEntregaCorreios = LocalDate.now().plusDays(17);
        this.dataEntregaTransportadora = LocalDate.now().plusDays(12);
    }

    public int getCodigo() {
        return codigo;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }
    
    public LocalDate getDataEntregaCorreios() {
    	return dataEntregaCorreios;
    }
    
    public LocalDate getDataEntregaTransportadora() {
    	return dataEntregaTransportadora;
    }

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
    
	public String getMetodo() {
		return metodo;
	}
	
	public void setMetodo (String metodo) {
		this.metodo = metodo;
	}
}

