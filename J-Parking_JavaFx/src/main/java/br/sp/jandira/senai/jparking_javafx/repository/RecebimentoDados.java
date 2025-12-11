package br.sp.jandira.senai.jparking_javafx.repository;

import br.sp.jandira.senai.jparking_javafx.screen.JParkingEntrada;


public class RecebimentoDados {
    public void receberDados(){
        JParkingEntrada jParkingEntrada =  new JParkingEntrada();
        Cliente cliente = new Cliente();

        cliente.nomeProprietario = jParkingEntrada.nomeProprietario.getText();
        cliente.modeloVeiculo = jParkingEntrada.modeloVeiculo.getText();
        cliente.cor = jParkingEntrada.corVeiculo.getText();
        cliente.marcaVeiculo = jParkingEntrada.marcaVeiculo.getText();
        cliente.placaVeiculo = jParkingEntrada.placaVeiculo.getText();


    }

}
