package br.sistema.conta;

import java.util.ArrayList;
import java.util.List;

public class HistoricoCompras {
    private List<Pedido> pedidos = new ArrayList<>();    
    
    public HistoricoCompras()
    {
    	this.pedidos = new ArrayList<Pedido>();
    }
    
    public List<Pedido> getCompras() {
        return pedidos;
    }

    
    public void registrarCompra(Pedido pedido) {
        pedidos.add(pedido);
    }

    public List<Pedido> listarCompras() {
        return pedidos;
    }

    public Pedido buscarPorCodigo(int codigo) {
        for (Pedido p : pedidos) {
            if (p.getCodigo() == codigo) return p;
        }
        return null;
    }

    public List<Pedido> buscarPorData(String data) {
        List<Pedido> resultado = new ArrayList<>();
        for (Pedido p : pedidos) {
            if (p.getDataPedido().toString().equals(data)) {
                resultado.add(p);
            }
        }
        return resultado;
    }
}
