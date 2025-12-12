package br.sp.jandira.senai.jparking_javafx.screen;

import br.sp.jandira.senai.jparking_javafx.repository.RecebimentoDados;
import br.sp.jandira.senai.jparking_javafx.repository.RepositoryVeiculo;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Objects;

public class JParkingSaida {

    public static Button btnVoltarMain;
    private RecebimentoDados repositorio = new RecebimentoDados(); // Instância de repositório

    public static class NovaTela extends Application {

        private int totalDeHorasPagas;
        private double valorTotal = 0.0;
        private RepositoryVeiculo veiculoSelecionado;
        private JParkingScreen telaPrincipal;

        final String boxStyle = "-fx-background-color: transparent;" +
                " -fx-border-color: white;" +
                " -fx-border-radius: 25;" +
                " -fx-text-fill: white;" +
                " -fx-prompt-text-fill: white; " +
                "-fx-border-width: 2px;" +
                " -fx-padding: 10px 20px;" +
                " -fx-font-size: 30;";


        // Construtor: Inicializa dados e executa o cálculo
        public NovaTela(RepositoryVeiculo veiculo, JParkingScreen mainScreen) {
            this.veiculoSelecionado = veiculo;
            this.telaPrincipal = mainScreen;
            calcularCobranca();
        }

        private void calcularCobranca() {
            String linha = veiculoSelecionado.getLinhaCsvOriginal();
            String horaEntradaStr = linha.split(";")[6].trim();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime entrada = LocalDateTime.parse(horaEntradaStr, formatter);
            LocalDateTime saida = LocalDateTime.now();

            java.time.Duration duracao = java.time.Duration.between(entrada, saida);
            long minutosPermanencia = duracao.toMinutes();


            double precoPrimeiraHora = 10.00;
            double precoHoraSubsequente = 5.00;
            int limiteDeTempoArredondado = 5;

            int horasCobradas = 0;

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
            } else {
                horasCobradas = 0;
            }

            if (horasCobradas == 1) {
                valorTotal = precoPrimeiraHora;
            } else if (horasCobradas > 1) {
                valorTotal = precoPrimeiraHora + ((horasCobradas - 1) * precoHoraSubsequente);
            } else {
                valorTotal = 0.0;
            }

            this.totalDeHorasPagas = horasCobradas;
        }

        @Override
        public void start(Stage stage) {
            System.err.println("Atenção: JParkingSaida.NovaTela deve ser iniciada pelo método iniciar(Stage).");
        }

        public void iniciar(Stage stage) {

            stage.setWidth(1920);
            stage.setHeight(1080);
            stage.setResizable(true);
            stage.setTitle("J-Parking - Saída");

            String linha = veiculoSelecionado.getLinhaCsvOriginal();
            String[] campos = linha.split(";");
            String nome = campos[1];
            String placa = campos[5];
            String horaEntrada = campos[6];

            VBox rootPrincipal = new VBox(20);
            rootPrincipal.setStyle("-fx-background-color: #106DB5;");
            rootPrincipal.setAlignment(Pos.CENTER);
            rootPrincipal.setPadding(new Insets(40));

            // Logo Icon
            try {
                Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/J-parking.logo.png")));
                stage.getIcons().add(icon);
            } catch (Exception e) {
                System.out.println("Erro: Imagem do ícone não encontrada.");
            }

            // Botão Voltar
            btnVoltarMain = new Button("Voltar");
            btnVoltarMain.setStyle("-fx-background-color: #A52424; -fx-text-fill: white; -fx-font-size: 18px; -fx-background-radius: 15px;");
            btnVoltarMain.setFont(Font.font("Arial", FontWeight.BOLD, 20));
            btnVoltarMain.setPrefSize(100, 50);
            btnVoltarMain.setTranslateX(-750);
            btnVoltarMain.setTranslateY(-100);

            btnVoltarMain.setOnAction(eventoVoltar -> {
                try {
                    telaPrincipal.start(new Stage());
                    stage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            Label titulo = new Label("Saída do Veículo");
            titulo.setFont(Font.font("Arial", 50));
            titulo.setStyle("-fx-text-fill: #ffffff;");

            Label infoCliente = new Label();
            infoCliente.setText("Cliente: " + nome);
            infoCliente.setStyle(" -fx-text-fill: white; -fx-font-size: 30;");

            Label exibirPlaca = new Label();
            exibirPlaca.setText("Placa: " + placa);
            exibirPlaca.setStyle("-fx-text-fill: white; -fx-font-size: 30;");

            VBox clienteBox = new VBox(10);
            clienteBox.setAlignment(Pos.CENTER);
            clienteBox.setStyle("-fx-background-color: transparent; -fx-border-color: white; -fx-border-radius: 25; -fx-text-fill: white; -fx-border-width: 2px; -fx-padding: 5px 5px; -fx-font-size: 30;");
            clienteBox.setMaxWidth(300);
            clienteBox.setMinHeight(150);
            clienteBox.getChildren().addAll(infoCliente, exibirPlaca);

            Label tempoPermanenciaLabel = new Label();
            tempoPermanenciaLabel.setText("Tempo de permanencia (Cobrado): " + totalDeHorasPagas + " Hrs");
            tempoPermanenciaLabel.setStyle(boxStyle);

            HBox horasTotais = new HBox(30);
            horasTotais.setAlignment(Pos.CENTER);
            horasTotais.getChildren().addAll(tempoPermanenciaLabel);

            Label totalPagarLabel = new Label();
            totalPagarLabel.setText("Total a Pagar: R$ " + String.format("%.2f", valorTotal));
            totalPagarLabel.setStyle(boxStyle);

            HBox totalFrame = new HBox(30);
            totalFrame.setAlignment(Pos.CENTER);
            totalFrame.getChildren().addAll(totalPagarLabel);


            Button btnSaida = new Button();
            btnSaida.setText("Confirmar Saída");
            btnSaida.setStyle("-fx-background-color: #136E16; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold; -fx-background-radius: 20px;");
            btnSaida.setPrefSize(300, 80);

            btnSaida.setOnAction(eventoSaida -> {
                JParkingSaida parentInstance = new JParkingSaida();

                try {
                    // 1. Processamento de Persistência
                    parentInstance.repositorio.gravarSaida(linha); // Usa a linha CSV original
                    parentInstance.repositorio.removerVeiculo(placa); // Remove do arquivo

                    Platform.runLater(() -> {
                        // A tela principal precisa ser atualizada após a remoção
                        telaPrincipal.processarSaidaConfirmada(veiculoSelecionado);
                    });

                    Alert sucesso = new Alert(Alert.AlertType.INFORMATION, "Pagamento de R$ " + String.format("%.2f", valorTotal) + " recebido. Veículo liberado!");
                    sucesso.showAndWait();

                    stage.close();

                    telaPrincipal.start(new Stage());
                    stage.close();

                } catch (Exception e) {
                    System.err.println("Erro ao processar a saída!!");
                    e.printStackTrace();
                }
            });


            rootPrincipal.getChildren().addAll(
                    btnVoltarMain,
                    titulo,
                    clienteBox,
                    horasTotais,
                    totalFrame,
                    btnSaida
            );

            Scene sceneFinal = new Scene(rootPrincipal);
            stage.setScene(sceneFinal);
            stage.show();
        }

    }
}