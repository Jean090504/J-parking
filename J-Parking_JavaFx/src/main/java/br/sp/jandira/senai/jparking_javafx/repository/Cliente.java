package br.sp.jandira.senai.jparking_javafx.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.UUID;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Cliente {

    public String id;
    public String nomeProprietario;
    public String modeloVeiculo;
    public String cor;
    public String marcaVeiculo;
    public String placaVeiculo;


    public void gravarCliente() {
        UUID id = UUID.randomUUID();


        Path arquivo = Path.of("C:\\Users\\25203640\\Desktop\\J-parking\\J-Parking_JavaFx\\src\\main\\resources\\arquivos\\veiculosEstacionados.csv");
        try {
            Files.writeString(arquivo,   id +
                    nomeProprietario + ";" + modeloVeiculo + ";" + cor + ";" + marcaVeiculo + ";" + placaVeiculo+ ";" + , StandardOpenOption.APPEND);
            System.out.println("Nova entrada!!!");
        } catch (IOException e) {
            System.out.println("Erro ao abrir o arquivo!!!");
            System.out.println(e.getMessage());
        }
    }

}

