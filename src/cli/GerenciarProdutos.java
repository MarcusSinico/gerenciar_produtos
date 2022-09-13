package cli;

import model.produtos;

import java.util.ArrayList;
import java.util.Scanner;

public class GerenciarProdutos {
    private final ArrayList<produtos> produtoslist = new ArrayList<>();

    public static void main(String[] args) {
        GerenciarProdutos gp = new GerenciarProdutos();
        Scanner sc = new Scanner(System.in);
        int opt = 0;
        while (opt != 5) {
            GerenciarProdutos.exibirMenu();

            opt = Integer.parseInt(sc.nextLine());
            switch (opt) {
                // Cadastro de produto.
                case 1 -> gp.addProduto(cadastroInput(sc));


                // Entrada de produtos.
                case 2 -> {
                    gp.printProdCodigo();
                    produtos prod = getProduto_Codigo(gp.produtoslist, sc);
                    if (!prod.getNome().equals("Produto")) {
                        int qtd = getQtd(sc, prod);

                        prod.addQtde(qtd);
                        System.out.printf("%d novos itens (%s) adicionados. Estoque atual: %d.\n", qtd, prod.getNome(),
                                prod.getQtde());
                    } else {
                        System.out.println("Código de produto inválido!");
                    }
                }

                // Saída de produtos.
                case 3 -> {
                    gp.printProdCodigo();
                    produtos prod1 = getProduto_Codigo(gp.produtoslist, sc);
                    if (!prod1.getNome().equals("Produto")) {
                        int qtd1 = getQtd(sc, prod1);

                        boolean saida = prod1.tiraqtde(qtd1);

                        if (saida)
                            System.out.printf("%d itens (%s) foram retirados. Estoque atual: %d.\n", qtd1, prod1.getNome(),
                                    prod1.getQtde());
                    } else {
                        System.out.println("Código do produto inválido!");
                    }
                }

                // Estoque atual
                case 4 -> getEstoqueAtual(gp.produtoslist);


                // Finalizando o sistema.
                case 5 -> System.out.println("Encerrando");
                default -> System.out.println("opção inválida!");
            }

        }
    }

    private static int getQtd(Scanner sc, produtos prod) {
        System.out.printf("Digite a quantidade de itens (%s): ", prod.getNome());
        return Integer.parseInt(sc.nextLine());
    }

    private static produtos getProduto_Codigo(ArrayList<produtos> produtos, Scanner sc) {
        System.out.print("Digite o código do produto: ");
        int input = Integer.parseInt(sc.nextLine());

        if (produtos.size() > input)
            return produtos.get(input);
        else
            return new produtos();
    }

    private static void getEstoqueAtual(ArrayList<produtos> produtos) {
        double total = 0;
        System.out.println("ESTOQUE");

        for (produtos prod : produtos) {
            System.out.printf("%s \n -- Preço: R$%.2f -- Quantidade: %d -- ValorTotal:  R$%.2f\n\n", prod.getNome(), prod.getPreco(), prod.getQtde(), prod.getmostratotal());
            total += prod.getmostratotal();
        }
        System.out.printf("Valor total estoque: R$%.2f\n\n", total);
    }

    private static produtos cadastroInput(Scanner sc) {
        produtos produto = new produtos();
        System.out.print("Digite o nome do produto: ");
        produto.setNome(sc.nextLine());
        System.out.printf("Digite o preço do produto (%s): R$", produto.getNome());
        produto.setPreco(Double.parseDouble(sc.nextLine()));

        return produto;
    }

    private static void exibirMenu() {
        System.out.println("Sistema de Estoque");
        System.out.println("1 => Cadastrar produtos;");
        System.out.println("2 => Entrada de produtos;");
        System.out.println("3 => Saída de produtos;");
        System.out.println("4 => Estoque atual;");
        System.out.println("5 => Sair.");
        System.out.print("Digite a opção desejada: ");
    }

    public void printProdCodigo() {
        int i = 0;
        System.out.println("PRODUTOS");
        for (produtos prod : this.produtoslist) {
            System.out.printf("Código: %d -- Produto: %s --Quantidade: %d \n", i, prod.getNome(),
                    prod.getQtde());

            i++;
        }
    }

    public void addProduto(produtos produto) {
        boolean status = this.produtoslist.add(produto);
        if (status)
            System.out.println("Produto adicionado com sucesso!");
        else
            System.out.println("Não foi possível adicionar o produto, tente novamente com outros valores!");
    }
}