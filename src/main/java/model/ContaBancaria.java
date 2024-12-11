package model;

import java.math.BigDecimal;

public class ContaBancaria {
    private int id;
    private String nome;
    private BigDecimal saldo;

    public ContaBancaria(String nome, BigDecimal saldo) {
        this.nome = nome;
        this.saldo = saldo;
    }

    public ContaBancaria(int id, String nome, BigDecimal saldo) {
        this.id = id;
        this.nome = nome;
        this.saldo = saldo;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "ContaBancaria{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}
