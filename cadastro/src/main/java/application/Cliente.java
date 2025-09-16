/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author filip
 */package application;

public class Cliente {
    private static int contador = 1;

    private Integer codigo;
    private String nome;
    private Endereco endereco;
    private String telefone;

    public Cliente(String nome, Endereco endereco, String telefone) {
        this.codigo = contador++;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public Integer getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public Endereco getEndereco() { return endereco; }
    public String getTelefone() { return telefone; }
}
