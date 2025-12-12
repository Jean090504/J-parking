package br.sp.jandira.senai.jparking_javafx.repository;

import br.sp.jandira.senai.jparking_javafx.screen.JParkingEntrada;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


public class RecebimentoDados {

    public LocalDateTime horaAtual;
    public DateTimeFormatter formatador;
    public String horaEntrada;

    public void gravarCliente(Cliente cliente) {

        horaAtual = LocalDateTime.now();
        formatador = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        horaEntrada = horaAtual.format(formatador);

        Path arquivo = Path.of("C:\\Users\\felix\\J-parking\\J-Parking_JavaFx\\src\\main\\resources\\arquivos\\veiculosEstacionados.csv");
        try {
            Files.writeString(arquivo,   cliente.id + ";" + cliente.nomeProprietario + ";" + cliente.modeloVeiculo + ";" + cliente.cor + ";" + cliente.marcaVeiculo + ";" + cliente.placaVeiculo+ ";" + horaEntrada + "\n", StandardOpenOption.APPEND);
            System.out.println("Nova entrada!!!");
        } catch (IOException e) {
            System.out.println("Erro ao abrir o arquivo!!!");
            System.out.println(e.getMessage());
        }

    }

    public List<String> lerVeiculosEstacionados() {

        // Opção 1 : C:\\Users\\felix\\J-parking\\J-Parking_JavaFx\\src\\main\\resources\\arquivos\\veiculosEstacionados.csv
        // Opção 2 : C:\\Users\\25203640\\Desktop\\J-parking\\J-Parking_JavaFx\\src\\main\\resources\\arquivos\\veiculosEstacionados.csv

        Path arquivo = Path.of("C:\\Users\\felix\\J-parking\\J-Parking_JavaFx\\src\\main\\resources\\arquivos\\veiculosEstacionados.csv");

        try {
            return Files.readAllLines(arquivo)
                    .stream()
                    .map(linha -> {
                        String[] campos = linha.split(";");

                        if (campos.length < 7) {
                            return "Dados corrompidos ou linha incompleta.";
                        }

                        return String.format(
                                "[Placa: %s] - %s (%s/%s/%s) | Entrada: %s",
                                campos[5],  // Placa
                                campos[1],  // Nome Proprietário
                                campos[4],  // Marca Veículo
                                campos[2],  // Modelo Veículo
                                campos[3],  // Cor Veículo
                                campos[6]   // Hora Entrada
                        );
                    })
                    .collect(Collectors.toList());

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de veículos estacionados: " + e.getMessage());
            return new ArrayList<>();
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

    public void gravarSaida(String registroSaida) {
        // Opção 1 : C:\\Users\\felix\\J-parking\\J-Parking_JavaFx\\src\\main\\resources\\arquivos\\veiculosEstacionados.csv
        // Opção 2 : C:\\Users\\25203640\\Desktop\\J-parking\\J-Parking_JavaFx\\src\\main\\resources\\arquivos\\veiculosEstacionados.csv

        Path arquivoSaida = Path.of("C:\\Users\\felix\\J-parking\\J-Parking_JavaFx\\src\\main\\resources\\arquivos\\saidaVeiculos.csv");
        try {
            Files.writeString(arquivoSaida, registroSaida + "\n", StandardOpenOption.APPEND);
            System.out.println("Registro de Saída adicionado ao histórico.");
        } catch (IOException e) {
            System.out.println("Erro ao gravar no histórico de saídas.");
            System.out.println(e.getMessage());
        }
    }

}
