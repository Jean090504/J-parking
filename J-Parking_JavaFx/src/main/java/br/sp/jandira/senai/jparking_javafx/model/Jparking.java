package br.sp.jandira.senai.jparking_javafx.model;

import br.sp.jandira.senai.jparking_javafx.repository.Cliente;
import br.sp.jandira.senai.jparking_javafx.repository.RecebimentoDados;

public class Jparking {

    RecebimentoDados recebimentoDados = new RecebimentoDados();

    public void exibirDados(Cliente cliente){
        System.out.println("ID: " + cliente.id);
        System.out.println("Nome: " + cliente.nomeProprietario);
        System.out.println("Modelo: " + cliente.modeloVeiculo);
        System.out.println("Cor do Veículo: " + cliente.cor);
        System.out.println("Marca do Veículo: " + cliente.marcaVeiculo);
        System.out.println("Placa do Veículo: " + cliente.placaVeiculo);


    }

}
