package controller;

import dao.ContaBancariaDAO;
import model.ContaBancaria;
import utils.ConexaoDB;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AplicacaoBancaria {

    public static void main(String[] args) {
        try (Connection conexao = ConexaoDB.getConnection();
             Scanner scanner = new Scanner(System.in)) {

            ContaBancariaDAO contaDAO = new ContaBancariaDAO(conexao);

            while (true) {
                System.out.println("\nEscolha uma opção:");
                System.out.println("1. Criar Conta");
                System.out.println("2. Listar Contas");
                System.out.println("3. Excluir Conta");
                System.out.println("4. Depositar");
                System.out.println("5. Sacar");
                System.out.println("6. Transferir");
                System.out.println("7. Sair");

                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer

                switch (opcao) {
                    case 1:
                        criarConta(contaDAO, scanner);
                        break;
                    case 2:
                        listarContas(contaDAO);
                        break;
                    case 3:
                        excluirConta(contaDAO, scanner);
                        break;
                    case 4:
                        depositar(contaDAO, scanner);
                        break;
                    case 5:
                        sacar(contaDAO, scanner);
                        break;
                    case 6:
                        transferir(contaDAO, scanner);
                        break;
                    case 7:
                        System.out.println("Saindo...");
                        return;
                    default:
                        System.out.println("Opção inválida!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void criarConta(ContaBancariaDAO contaDAO, Scanner scanner) throws SQLException {
        System.out.print("Digite o nome da conta: ");
        String nome = scanner.nextLine();
        contaDAO.criarConta(nome);
        System.out.println("Conta criada com sucesso!");
    }

    private static void listarContas(ContaBancariaDAO contaDAO) throws SQLException {
        List<ContaBancaria> contas = contaDAO.listarContas();
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta encontrada.");
        } else {
            System.out.println("Contas cadastradas:");
            for (ContaBancaria conta : contas) {
                System.out.println(conta);
            }
        }
    }

    private static void excluirConta(ContaBancariaDAO contaDAO, Scanner scanner) throws SQLException {
        System.out.print("Digite o número da conta para excluir: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer
        contaDAO.excluirConta(id);
        System.out.println("Conta excluída com sucesso!");
    }

    private static void depositar(ContaBancariaDAO contaDAO, Scanner scanner) throws SQLException {
        System.out.print("Digite o número da conta: ");
        int id = scanner.nextInt();
        System.out.print("Digite o valor do depósito: ");
        BigDecimal valor = scanner.nextBigDecimal();
        contaDAO.depositar(id, valor);
        System.out.println("Depósito realizado com sucesso!");
    }

    private static void sacar(ContaBancariaDAO contaDAO, Scanner scanner) throws SQLException {
        System.out.print("Digite o número da conta: ");
        int id = scanner.nextInt();
        System.out.print("Digite o valor do saque: ");
        BigDecimal valor = scanner.nextBigDecimal();
        contaDAO.sacar(id, valor);
        System.out.println("Saque realizado com sucesso!");
    }

    private static void transferir(ContaBancariaDAO contaDAO, Scanner scanner) throws SQLException {
        System.out.print("Digite o número da conta de origem: ");
        int idOrigem = scanner.nextInt();
        System.out.print("Digite o número da conta de destino: ");
        int idDestino = scanner.nextInt();
        System.out.print("Digite o valor da transferência: ");
        BigDecimal valor = scanner.nextBigDecimal();
        contaDAO.transferir(idOrigem, idDestino, valor);
        System.out.println("Transferência realizada com sucesso!");
    }
}
