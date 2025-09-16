package br.edu.progiitrabalho.service;

import br.edu.progiitrabalho.model.Endereco;

public class Buscador {
    public Endereco buscar(String cep) throws Exception {
        if (!cep.matches("\\d{5}-\\d{3}")) throw new Exception("CEP em formato inválido");
        if (cep.equals("01001-000")) return new Endereco(cep, "Praça da Sé", "", "São Paulo", "SP");
        if (cep.equals("20040-020")) return new Endereco(cep, "Rua da Quitanda", "", "Rio de Janeiro", "RJ");
        throw new Exception("CEP não encontrado");
    }
}
