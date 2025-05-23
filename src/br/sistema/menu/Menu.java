package br.sistema.menu;

import br.sistema.conta.Estoque;
import br.sistema.conta.Fornecedor;
import br.sistema.conta.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Estoque estoque;
    private Scanner scanner;
    private List<Fornecedor> fornecedores = new ArrayList<>();

    public Menu(Estoque estoque) {
        this.estoque = estoque;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n------ Menu Principal ------");
            System.out.println("1. Adicionar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Alterar Produto");
            System.out.println("4. Excluir Produto");
            System.out.println("5. Buscar Produto por Nome");
            System.out.println("6. Buscar Produto por Código");
            System.out.println("7. Listar Produtos de um Fornecedor");
            System.out.println("8. Listar todos os fornecedores");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, insira um número válido.");
                scanner.next();
                System.out.print("Escolha: ");
            }

            opcao = scanner.nextInt();
            scanner.nextLine();
            System.out.print("\n\n");

            switch (opcao) {
                case 1 -> adicionarProduto();
                case 2 -> listarProdutos();
                case 3 -> alterarProduto();
                case 4 -> excluirProduto();
                case 5 -> buscarPorNome();
                case 6 -> buscarPorCodigo();
                case 7 -> listarPorFornecedor();
                case 8 -> listarFornecedores();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void adicionarProduto() {
        System.out.print("Nome do Produto: ");
        String nome = scanner.nextLine();

        System.out.print("Preço: ");
        double preco = scanner.nextDouble();

        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        Fornecedor fornecedor = selecionarOuCriarFornecedor();
        Produto produto = new Produto(nome, preco, quantidade, fornecedor);

        estoque.adicionarProduto(produto);

        System.out.println("Produto adicionado com sucesso! Código: " + produto.getCodigo());
        System.out.print("\n\n");
    }


    private void listarProdutos() {
        for (Produto p : estoque.listarProdutos()) {
            System.out.println("Produto: " + p.getNome() + " | Preço: R$" + p.getPreco() +
                " | Quantidade: " + p.getQuantidade() +
                " | Fornecedor: " + p.getFornecedor().getNome() + " | Código: " + p.getCodigo());
            System.out.print("\n\n");
        }
    }
    
    private Fornecedor selecionarOuCriarFornecedor() {
        System.out.println("Fornecedores disponíveis:");
        for (int i = 0; i < fornecedores.size(); i++) {
            System.out.println((i + 1) + ". " + fornecedores.get(i).getNome() + " (" + fornecedores.get(i).getCnpj() + ")");
        }
        System.out.println("0. Criar novo fornecedor");
        System.out.print("Escolha: ");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha > 0 && escolha <= fornecedores.size()) {
            return fornecedores.get(escolha - 1);
        } 
        else{
            System.out.print("Nome do novo fornecedor: ");
            String nome = scanner.nextLine();
            System.out.print("CNPJ do novo fornecedor: ");
            String cnpj = scanner.nextLine();
            System.out.print("Endereço do novo fornecedor: ");
            String endereco = scanner.nextLine();
            Fornecedor novo = new Fornecedor(nome, cnpj, endereco);
            fornecedores.add(novo);
            return novo;
        }
    }

    
    private void alterarProduto() {
        System.out.print("Digite o código do produto: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();
        Produto p = estoque.buscarPorCodigo(codigo);
        if (p != null) {
            System.out.print("Novo nome: ");
            p.setNome(scanner.nextLine());
            System.out.print("Novo preço: ");
            p.setPreco(scanner.nextDouble());
            System.out.print("Nova quantidade: ");
            p.setQuantidade(scanner.nextInt());
            scanner.nextLine();
            System.out.println("Produto atualizado.");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private void excluirProduto() {
        System.out.print("Digite o código do produto: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();
        if (estoque.removerProduto(codigo)) {
            System.out.println("Produto removido.");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private void buscarPorNome() {
        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();
        List<Produto> encontrados = estoque.buscarPorNome(nome);
        if (encontrados.isEmpty()) {
            System.out.println("Nenhum produto encontrado.");
        } else {
            for (Produto p : encontrados) {
                System.out.println(p.getCodigo() + " - " + p.getNome() + " - R$" + p.getPreco() + " - " + p.getFornecedor().getNome());
            }
        }
    }

    private void buscarPorCodigo() {
        System.out.print("Código do produto: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();
        Produto p = estoque.buscarPorCodigo(codigo);
        if (p != null) {
            System.out.println("Nome: " + p.getNome());
            System.out.println("Preço: " + p.getPreco());
            System.out.println("Quantidade: " + p.getQuantidade());
            System.out.println("Fornecedor: " + p.getFornecedor().getNome());
            System.out.print("\n\n");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private void listarPorFornecedor() {
        Fornecedor f = selecionarOuCriarFornecedor();
        List<Produto> lista = estoque.buscarPorFornecedor(f);
        if (lista.isEmpty()) {
            System.out.println("Nenhum produto para este fornecedor.");
        } else {
            for (Produto p : lista) {
            	 System.out.println("Produto: " + p.getNome() + " | Preço: R$" + p.getPreco() +
                         " | Quantidade: " + p.getQuantidade() + " | Código: " + p.getCodigo());
                     System.out.print("\n\n");
            }
        }
    }
    
    private void listarFornecedores() {
    	 System.out.println("Fornecedores disponíveis:");
         for (int i = 0; i < fornecedores.size(); i++) {
             System.out.println((i + 1) + ". " + fornecedores.get(i).getNome() + "\nCNPJ: " + fornecedores.get(i).getCnpj() + "\nEndereco: " + fornecedores.get(i).getEndereco());
         }
    }
}
