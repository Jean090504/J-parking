package br.sp.jandira.senai.jparking_javafx.model;

import br.sp.jandira.senai.jparking_javafx.repository.Cliente;
import br.sp.jandira.senai.jparking_javafx.repository.RecebimentoDados;

public class Jparking {

    RecebimentoDados recebimentoDados = new RecebimentoDados();
    Cliente cliente = new Cliente();

    public void mostrarDados(){
        System.out.println(cliente.nomeProprietario);
        System.out.println(cliente.modeloVeiculo);
        System.out.println(cliente.cor);
        System.out.println(cliente.marcaVeiculo);
        System.out.println(cliente.placaVeiculo);


    }

}
