package dao;

import model.ContaBancaria;

import java.sql.Connection;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContaBancariaDAO {
    private Connection connection;

    public ContaBancariaDAO(Connection connection) {
        this.connection = connection;
    }

    public void criarConta(String nome) throws SQLException {
        String sql = "INSERT INTO conta (nome, saldo) VALUES (?, 0)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.executeUpdate();
        }
    }

    public List<ContaBancaria> listarContas() throws SQLException {
        List<ContaBancaria> contas = new ArrayList<>();
        String sql = "SELECT * FROM conta";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                BigDecimal saldo = rs.getBigDecimal("saldo");
                contas.add(new ContaBancaria(id, nome, saldo));
            }
        }
        return contas;
    }

    public void depositar(int id, BigDecimal valor) throws SQLException {
        String sql = "UPDATE conta SET saldo = saldo + ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setBigDecimal(1, valor);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    public void sacar(int id, BigDecimal valor) throws SQLException {
        String sql = "UPDATE conta SET saldo = saldo - ? WHERE id = ? AND saldo >= ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setBigDecimal(1, valor);
            stmt.setInt(2, id);
            stmt.setBigDecimal(3, valor);
            stmt.executeUpdate();
        }
    }

    public void transferir(int idOrigem, int idDestino, BigDecimal valor) throws SQLException {
        connection.setAutoCommit(false); // Início da transação
        try {
            sacar(idOrigem, valor);
            depositar(idDestino, valor);
            connection.commit(); // Confirma a transação
        } catch (SQLException e) {
            connection.rollback(); // Reverte a transação em caso de erro
            throw e;
        } finally {
            connection.setAutoCommit(true); // Restaura o estado padrão
        }
    }

    public void excluirConta(int id) throws SQLException {
        String sql = "DELETE FROM conta WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
