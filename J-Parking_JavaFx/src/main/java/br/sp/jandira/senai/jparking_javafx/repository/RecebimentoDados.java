package br.sp.jandira.senai.jparking_javafx.repository;

import br.sp.jandira.senai.jparking_javafx.screen.JParkingEntrada;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

    public List<RepositoryVeiculo> lerVeiculosEstacionados() {

        // Opção 1 : C:\\Users\\felix\\J-parking\\J-Parking_JavaFx\\src\\main\\resources\\arquivos\\veiculosEstacionados.csv
        // Opção 2 : C:\\Users\\25203640\\Desktop\\J-parking\\J-Parking_JavaFx\\src\\main\\resources\\arquivos\\veiculosEstacionados.csv

        Path arquivo = Path.of("C:\\Users\\felix\\J-parking\\J-Parking_JavaFx\\src\\main\\resources\\arquivos\\veiculosEstacionados.csv");

        try {
            return Files.readAllLines(arquivo)
                    .stream()
                    .map(linha -> {
                        String[] campos = linha.split(";");

                        if (campos.length < 7) {
                            return new RepositoryVeiculo(linha, "Dados corrompidos ou linha incompleta.");
                        }

                        String linhaFormatada = String.format(
                                "[Placa: %s] - %s (%s/%s/%s) | Entrada: %s",
                                campos[5], campos[1], campos[4], campos[2], campos[3], campos[6]
                        );

                        // Retorna a linha original e a linha formatada
                        return new RepositoryVeiculo(linha, linhaFormatada);
                    })
                    .collect(Collectors.toList()).reversed();

            } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de veículos estacionados: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<RepositoryHistoricoVeiculo> lerHistoricoSaidas() {
        Path arquivo = Path.of("C:\\Users\\felix\\J-parking\\J-Parking_JavaFx\\src\\main\\resources\\arquivos\\saidaVeiculos.csv");

        try {
            return Files.readAllLines(arquivo)
                    .stream()
                    .map(linha -> {
                        String[] campos = linha.split(";");

                        if (campos.length < 8) {
                            return new RepositoryHistoricoVeiculo(linha, "Dados de histórico corrompidos.");
                        }

                        String linhaFormatada = String.format(
                                "[PLACA: %s] | Saída: %s | Cliente: %s | Entrada: %s",
                                campos[5],      // Placa
                                campos[7],      // Hora Saída
                                campos[1],      // Nome Proprietário
                                campos[6]       // Hora Entrada
                        );

                        return new RepositoryHistoricoVeiculo(linha, linhaFormatada);
                    })
                    .collect(Collectors.toList());

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de histórico de saídas: " + e.getMessage());
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

    public void gravarSaida(String linhaCsvOriginal) {
        // Opção 1 : C:\\Users\\felix\\J-parking\\J-Parking_JavaFx\\src\\main\\resources\\arquivos\\veiculosEstacionados.csv
        // Opção 2 : C:\\Users\\25203640\\Desktop\\J-parking\\J-Parking_JavaFx\\src\\main\\resources\\arquivos\\veiculosEstacionados.csv

            String horaSaida = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            String registroSaida = linhaCsvOriginal + ";" + horaSaida;

            Path arquivoSaida = Path.of("C:\\Users\\felix\\J-parking\\J-Parking_JavaFx\\src\\main\\resources\\arquivos\\saidaVeiculos.csv");
            try {
                Files.writeString(arquivoSaida, registroSaida + "\n", StandardOpenOption.APPEND);
                System.out.println("Registro de Saída adicionado ao histórico.");
            } catch (IOException e) {
                System.out.println("Erro ao gravar no histórico de saídas.");
                System.out.println(e.getMessage());
            }
        }

    public int contarVeiculosEstacionados() {
        Path arquivo = Path.of("C:\\Users\\felix\\J-parking\\J-Parking_JavaFx\\src\\main\\resources\\arquivos\\veiculosEstacionados.csv");
        int contador = 0;
        try {
            contador = (int) Files.readAllLines(arquivo)
                    .stream()
                    .filter(linha -> linha.split(";").length >= 7)
                    .count();
        } catch (IOException e) {
            System.err.println("Erro ao contar veículos estacionados: " + e.getMessage());
        }
        return contador;
    }

    public void removerVeiculo(String placaVeiculo) {
        Path arquivo = Path.of("C:\\Users\\felix\\J-parking\\J-Parking_JavaFx\\src\\main\\resources\\arquivos\\veiculosEstacionados.csv");
        List<String> linhasAtualizadas = new ArrayList<>();

        try {
            List<String> todasAsLinhas = Files.readAllLines(arquivo);
            for (String linha : todasAsLinhas) {
                String[] campos = linha.split(";");

                if (campos.length >= 6 && !campos[5].trim().equalsIgnoreCase(placaVeiculo)) {
                    linhasAtualizadas.add(linha);
                }
            }

            Files.write(arquivo, linhasAtualizadas, StandardOpenOption.TRUNCATE_EXISTING);

        } catch (IOException e) {
            System.err.println("Erro ao remover veículo do arquivo: " + e.getMessage());
        }
    }

    public double calcularFaturamentoDiario() {
        Path arquivo = Path.of("C:\\Users\\felix\\J-parking\\J-Parking_JavaFx\\src\\main\\resources\\arquivos\\saidaVeiculos.csv");
        double precoPrimeiraHora = 10.00;
        double precoHoraSubsequente = 5.00;
        int limiteDeTempoArredondado = 5;
        double faturamentoTotal = 0.0;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDate hoje = LocalDate.now();

        try {
            List<String> todasAsLinhas = Files.readAllLines(arquivo);

            for (String linha : todasAsLinhas) {
                String[] campos = linha.split(";");

                if (campos.length >= 8) {
                    try {
                        LocalDateTime entrada = LocalDateTime.parse(campos[6].trim(), formatter);
                        LocalDateTime saida = LocalDateTime.parse(campos[7].trim(), formatter);


                        if (saida.toLocalDate().isEqual(hoje)) {
                            Duration duracao = Duration.between(entrada, saida);
                            long minutosPermanencia = duracao.toMinutes();

                            int horasCobradas = 0;
                            double valorPago = 0.0;

                            if (minutosPermanencia > 0) {
                                horasCobradas = 1;
                                long minutosRestantes = minutosPermanencia - 60;

                                if (minutosRestantes > 0) {
                                    long horasInteiras = minutosRestantes / 60;
                                    long minutosFracao = minutosRestantes % 60;

                                    horasCobradas += horasInteiras;
                                    if (minutosFracao >= limiteDeTempoArredondado) {
                                        horasCobradas += 1;
                                    }
                                }
                            }

                            if (horasCobradas == 1) {
                                valorPago = precoPrimeiraHora;
                            } else if (horasCobradas > 1) {
                                valorPago = precoPrimeiraHora + ((horasCobradas - 1) * precoHoraSubsequente);
                            } else {
                                valorPago = 0.0;
                            }

                            faturamentoTotal += valorPago;
                        }
                    } catch (DateTimeParseException e) {
                        System.err.println("Erro de formato de data no CSV: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo de histórico para faturamento: " + e.getMessage());
        }
        return faturamentoTotal;
    }


}
