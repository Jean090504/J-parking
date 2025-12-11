package br.sp.jandira.senai.jparking_javafx.repository;

import br.sp.jandira.senai.jparking_javafx.screen.JParkingEntrada;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


public class RecebimentoDados {

    public LocalDateTime horaAtual;
    public DateTimeFormatter formatador;
    public String horaEntrada;

    public void gravarCliente(Cliente cliente) {

        horaAtual = LocalDateTime.now();
        formatador = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        horaEntrada = horaAtual.format(formatador);

        Path arquivo = Path.of("C:\\Users\\25203640\\Desktop\\J-parking\\J-Parking_JavaFx\\src\\main\\resources\\arquivos\\veiculosEstacionados.csv");
        try {
            Files.writeString(arquivo,   cliente.id + cliente.nomeProprietario + ";" + cliente.modeloVeiculo + ";" + cliente.cor + ";" + cliente.marcaVeiculo + ";" + cliente.placaVeiculo+ ";" + horaEntrada + "\n", StandardOpenOption.APPEND);
            System.out.println("Nova entrada!!!");
        } catch (IOException e) {
            System.out.println("Erro ao abrir o arquivo!!!");
            System.out.println(e.getMessage());
        }
    }


    public void receberDados(TextField nomeProprietario, TextField placaVeiculo, TextField corVeiculo, TextField marcaVeiculo, TextField modeloVeiculo) {

        JParkingEntrada jParkingEntrada =  new JParkingEntrada();
        Cliente cliente = new Cliente();

        cliente.id = String.valueOf(UUID.randomUUID());
        cliente.nomeProprietario = nomeProprietario.getText();
        cliente.modeloVeiculo = modeloVeiculo.getText();
        cliente.cor = corVeiculo.getText();
        cliente.marcaVeiculo = marcaVeiculo.getText();
        cliente.placaVeiculo = placaVeiculo.getText();

        gravarCliente(cliente);

    }
}
