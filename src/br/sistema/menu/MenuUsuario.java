package br.sistema.menu;

import br.sistema.conta.*;
import br.sistema.BDD.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MenuUsuario {
    private Loja loja;
    //private HistoricoCompras historico;
    private Scanner scanner;
    //private Carrinho carrinho;
    //private Usuario usuario;
    private Cliente cliente;
    private BancoDeDados bdd; 
   

    /*public MenuUsuario(Loja loja, HistoricoCompras historico) {
        this.loja = loja;
        this.historico = historico;
        this.scanner = new Scanner(System.in);
        this.carrinho = new Carrinho();
    }*/

    public MenuUsuario(Loja loja, Cliente cliente /*Usuario usuario*/, BancoDeDados bdd) {
        this.loja = loja;
        //this.usuario = usuario;
        this.cliente = cliente;
        this.bdd = bdd;
        this.scanner = new Scanner(System.in);
    }
    
    public void exibirMenu() {    	
        int opcao;
        do {
        	bdd.SalvarUsuarios();
        	bdd.SalvarEstoque();
        	bdd.SalvarProdutos();
            System.out.println("\n--- Menu do Usuário ---");
            System.out.println("1. Ver todos os produtos");
            System.out.println("2. Buscar produto por nome");
            System.out.println("3. Buscar produto por código");
            System.out.println("4. Buscar produto por fornecedor");
            System.out.println("5. Adicionar produto ao carrinho");
            System.out.println("6. Ver carrinho");
            System.out.println("7. Finalizar compra");
            System.out.println("8. Remover produto do carrinho");
            System.out.println("9. Modificar método de entrega");
            System.out.println("10. Ver histórico de compras");
            System.out.println("11. Buscar compra por código");
            System.out.println("12. Buscar compra por data");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, insira um número válido.");
                scanner.next();
            }

            opcao = scanner.nextInt();
            scanner.nextLine();

            
            switch (opcao) {
                case 1 -> listarProdutos();
                case 2 -> buscarPorNome();
                case 3 -> buscarPorCodigo();
                case 4 -> buscarPorFornecedor();
                case 5 -> adicionarAoCarrinho();
                //case 6 -> usuario.getCarrinho().exibirCarrinho();
                case 6 -> cliente.getCarrinho().exibirCarrinho();
                //case 7 -> usuario.getCarrinho().finalizarCompra(loja);
                case 7 -> cliente.getCarrinho().finalizarCompra(loja);
                case 8 -> removerDoCarrinho();
                case 9 -> modificarMetodoEntrega();
                case 10 -> verHistorico();
                case 11 -> buscarCompraPorCodigo();
                case 12 -> buscarCompraPorData();
                case 0 -> System.out.println("Saindo...");                
                default -> System.out.println("Opção inválida.");
                
            }
            
        
        	
        } while (opcao != 0);
    }

    private void listarProdutos() {
        for (ItemFornecedorProduto item : loja.listarItens()) {
            System.out.println(item.getProduto().getCodigo() + " - " + item.getProduto().getNome() +
                " | Preço: R$" + item.getPreco() +
                " | Quantidade: " + item.getQuantidade() +
                " | Fornecedor: " + item.getFornecedor().getNome());
        }
    }

    private void buscarPorNome() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        List<ItemFornecedorProduto> encontrados = loja.buscarPorNome(nome);
        for (ItemFornecedorProduto item : encontrados) {
            System.out.println(item.getProduto().getCodigo() + " - " + item.getProduto().getNome() +
                " | Preço: R$" + item.getPreco() +
                " | Quantidade: " + item.getQuantidade() +
                " | Fornecedor: " + item.getFornecedor().getNome());
        }
        if (encontrados.isEmpty()) {
            System.out.println("Nenhum produto encontrado.");
        }
    }

    private void buscarPorCodigo() {
        System.out.print("Código: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();
        ItemFornecedorProduto item = loja.buscarPorCodigo(codigo);
        if (item != null) {
            System.out.println(item.getProduto().getNome() + " | R$" + item.getPreco() +
                " | Qtd: " + item.getQuantidade() +
                " | Fornecedor: " + item.getFornecedor().getNome());
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private void buscarPorFornecedor() {
        System.out.print("Nome do fornecedor: ");
        String nome = scanner.nextLine();
        List<Fornecedor> lista = loja.getFornecedores();
        for (Fornecedor f : lista) {
            if (f.getNome().equalsIgnoreCase(nome)) {
                List<ItemFornecedorProduto> itens = loja.buscarPorFornecedor(f);
                for (ItemFornecedorProduto item : itens) {
                    System.out.println(item.getProduto().getCodigo() + " - " + item.getProduto().getNome() +
                        " | Preço: R$" + item.getPreco() +
                        " | Quantidade: " + item.getQuantidade());
                }
                return;
            }
        }
        System.out.println("Fornecedor não encontrado.");
    }

    private void adicionarAoCarrinho() {
        System.out.print("Código do produto: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();

        ItemFornecedorProduto item = loja.buscarPorCodigo(codigo);
        if (item == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Quantidade desejada: ");
        int qtd = scanner.nextInt();
        scanner.nextLine();

        if (qtd <= 0) {
            System.out.println("Quantidade inválida.");
            return;
        }

        if (qtd > item.getQuantidade()) {
            System.out.println("Quantidade indisponível em estoque.");
            return;
        }

        //usuario.getCarrinho().adicionarProduto(item, qtd);
        
        cliente.getCarrinho().adicionarProduto(item, qtd);
    }
    
    public void removerDoCarrinho() {
    	System.out.println("Código do produto: ");
    	int codigo = scanner.nextInt();
    	scanner.nextLine();
    	
    	ItemFornecedorProduto item = loja.buscarPorCodigo(codigo);
    	if (item == null) {
    		System.out.println("Produto não encontrado.");
    	}
    	
    	System.out.println("Deseja remover o item completamente ou apenas diminuir a quantidade?");
    	System.out.println("1: Remover 2: Diminuir quantidade");
    	int opcao = scanner.nextInt();
    	scanner.nextLine();
    	
    	if (opcao==2) {
    		System.out.println("Digite a quantidade a ser removida.");
    		int qtd = scanner.nextInt();
    		scanner.nextLine();
    		
    		if (qtd <= 0) {
    			System.out.println("Quantidade inválida para remoção.");
    			return;
    		}
    		else {
    			cliente.getCarrinho().removerProduto(item, qtd, opcao);
    		}
    		}
    	else if (opcao==1){
    		cliente.getCarrinho().removerProduto(item, 0, opcao);
    	}
    	else {
    		System.out.println("Opcao invalida.");
    		return;
    	}
    }
    
    private void modificarMetodoEntrega() {
    	 System.out.print("Código da compra: ");
         int codigo = scanner.nextInt();
         scanner.nextLine();
         Pedido p = cliente.getHistoricoCompras().buscarPorCodigo(codigo);
         if (p != null) {
             System.out.println("Compra #" + p.getCodigo() + " em " + p.getDataPedido());
             System.out.println("Qual o metodo de entrega a ser utilizado? Transportadora ou Correios?");
             String s = scanner.nextLine();
             if (s.equalsIgnoreCase("Transportadora")) {
            	 p.setMetodo(s);
            	 p.setSituacao("novo");
             }
             else if (s.equalsIgnoreCase("Correios")) {
            	 p.setMetodo(s);
            	 p.setSituacao("novo");
             }
             else {
            	 System.out.println("Metodo de entrega invalido.");
            	 return;
             }
         }
    }

    private void verHistorico() {
        //List<Compra> lista = usuario.getHistoricoCompras().listarCompras();
    	List<Pedido> lista = cliente.getHistoricoCompras().listarCompras();
        if (lista.isEmpty()) {
            System.out.println("Nenhuma compra registrada.");
        } else {
            for (Pedido p : lista) {
                System.out.println("Compra #" + p.getCodigo() + " | Data: " + p.getDataPedido());
            }
        }
    }

    private void buscarCompraPorCodigo() {
        System.out.print("Código da compra: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();
        //Compra c = usuario.getHistoricoCompras().buscarPorCodigo(codigo);
        Pedido p = cliente.getHistoricoCompras().buscarPorCodigo(codigo);
        if (p != null) {
            System.out.println("Compra #" + p.getCodigo() + " em " + p.getDataPedido());
        } else {
            System.out.println("Compra não encontrada.");
        }
    }

    private void buscarCompraPorData() {
        System.out.print("Data da compra (AAAA-MM-DD): ");
        String data = scanner.nextLine();
        //List<Compra> lista = usuario.getHistoricoCompras().buscarPorData(data);
        List<Pedido> lista = cliente.getHistoricoCompras().buscarPorData(data);
        if (lista.isEmpty()) {
            System.out.println("Nenhuma compra nesta data.");
        } else {
            for (Pedido p : lista) {
                System.out.println("Compra #" + p.getCodigo() + " | Data: " + p.getDataPedido());
                if (p.getMetodo().equalsIgnoreCase("Transportadora")) {
                	System.out.println("Data de entrega: " + p.getDataEntregaTransportadora());
                	if (p.getDataEntregaTransportadora().isAfter(LocalDate.now())){
                			System.out.println("O pedido está a caminho");
                	}
                	else if (p.getDataEntregaTransportadora().isBefore(LocalDate.now())) {
                		System.out.println("O pedido foi entregue.");
                		p.setSituacao("entregue");
                	}
                	else {
                		System.out.println("O pedido foi cancelado.");
                		p.setSituacao("cancelado");
                	}
                }
                else if (p.getMetodo().equalsIgnoreCase("Correios")) {
                	System.out.println("Data de entrega: " + p.getDataEntregaCorreios());
                	if (p.getDataEntregaCorreios().isAfter(LocalDate.now())){
            			System.out.println("O pedido está a caminho");
	            	}
	            	else if (p.getDataEntregaCorreios().isBefore(LocalDate.now())) {
	            		System.out.println("O pedido foi entregue.");
	            		p.setSituacao("entregue");
	            	}
	            	else {
	            		System.out.println("O pedido foi cancelado.");
	            		p.setSituacao("cancelado");
	            	}
                }
            }
        }
                }
            }