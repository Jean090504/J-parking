package br.sp.jandira.senai.jparking_javafx.screen;

import br.sp.jandira.senai.jparking_javafx.repository.RecebimentoDados;
import br.sp.jandira.senai.jparking_javafx.repository.RepositoryVeiculo;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import br.sp.jandira.senai.jparking_javafx.util.DashBoardFactory;
import eu.hansolo.tilesfx.Tile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class JParkingScreen extends Application {
    RecebimentoDados repositorio = new RecebimentoDados();
    int VAGAS_TOTAIS = 50;

    private ListView<RepositoryVeiculo> listaEstacionados;
    private Label labelVagas;

    public void processarSaidaConfirmada(RepositoryVeiculo veiculo) {
        this.listaEstacionados.getItems().remove(veiculo);
        atualizarContadorVagas(this.labelVagas);
        System.out.println("Saída Confirmada! Veículo: " + veiculo.getLinhaFormatada());
    }

    private void atualizarContadorVagas(Label labelVagas) {
        int veiculosEstacionados = repositorio.contarVeiculosEstacionados();
        int novasVagasLivres = VAGAS_TOTAIS - veiculosEstacionados;
        labelVagas.setText("Vagas Livres: " + novasVagasLivres + "/" + VAGAS_TOTAIS);
    }



    @Override
    public void start(Stage stage) throws IOException {

        stage.setWidth(1980);
        stage.setHeight(1040);
        stage.setResizable(true);
        stage.setTitle("J-Parking");

        // Logo
         try {
            Image logo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/J-parking.logo.png")));
            stage.getIcons().add(logo);
        } catch (Exception e) {
            System.out.println("Erro no ícone.");
        }

        //ROOT (Fundo Principal)
        VBox root = new VBox();
        root.setStyle("-fx-background-color: #106DB5;");
        root.setAlignment(Pos.CENTER);

        //LAYOUT PRINCIPAL
        HBox mainLayout = new HBox(30);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(20));


        VBox cardEsquerda = new VBox(10);
        cardEsquerda.setPadding(new Insets(20));
        cardEsquerda.setMinWidth(700);
        cardEsquerda.setMinHeight(880);
        cardEsquerda.setStyle("-fx-background-color: #106DB5; -fx-border-color: white; -fx-border-width: 2px; -fx-border-radius: 10px; -fx-background-radius: 10px;");

        //Carros Estacionados listView
        int veiculosEstacionados = repositorio.contarVeiculosEstacionados();
        int vagasLivres = VAGAS_TOTAIS - veiculosEstacionados;

        this.labelVagas = new Label("Vagas Livres: " + vagasLivres + "/" + VAGAS_TOTAIS);
        labelVagas.setTextFill(Color.WHITE);
        labelVagas.setFont(Font.font("Arial", 24));
        labelVagas.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-background-radius: 5px; -fx-padding: 5 15 5 15;");
        HBox headerTop = new HBox(labelVagas);
        headerTop.setAlignment(Pos.CENTER);

        Label titulo = new Label("Veículos no Pátio");
        titulo.setTextFill(Color.WHITE);
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        HBox headerTitle = new HBox(titulo);
        headerTitle.setAlignment(Pos.CENTER);

        Separator linha = new Separator();
        linha.setStyle("-fx-background-color: white; -fx-pref-height: 1px;");

        this.listaEstacionados = new ListView<>();
        listaEstacionados.setMinHeight(800);

        List<RepositoryVeiculo> veiculosRegistros = repositorio.lerVeiculosEstacionados();
        listaEstacionados.getItems().addAll(veiculosRegistros);

        listaEstacionados.setCellFactory(lv -> new javafx.scene.control.ListCell<RepositoryVeiculo>() {
            @Override
            protected void updateItem(RepositoryVeiculo item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.getLinhaFormatada());
            }
        });

        cardEsquerda.getChildren().addAll(headerTop, headerTitle, linha, listaEstacionados);

        VBox colunaDireita = new VBox(20);
        colunaDireita.setAlignment(Pos.TOP_CENTER);

        //Card de Faturamento
        VBox cardFaturamento = new VBox(10);
        cardFaturamento.setPadding(new Insets(20));
        cardFaturamento.setMinWidth(700);
        cardFaturamento.setPrefHeight(400);
        cardFaturamento.setStyle("-fx-background-color: #106DB5; -fx-border-color: white; -fx-border-width: 2px; -fx-border-radius: 10px; -fx-background-radius: 10px;");

        Label labelFaturamento = new Label("Faturamento do Dia");
        labelFaturamento.setTextFill(Color.WHITE);
        labelFaturamento.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        cardFaturamento.getChildren().add(labelFaturamento);

        double faturamentoDoDia = repositorio.calcularFaturamentoDiario();
        int porcentagemOcupacao = (int) Math.round((double) veiculosEstacionados / VAGAS_TOTAIS * 100);

        Tile graficoOcupacao = DashBoardFactory.criarMedidorOcupacao(porcentagemOcupacao);
        Tile graficoFaturamento = DashBoardFactory.criarResumoFinanceiro(faturamentoDoDia);

        HBox painelDashboard = new HBox(20);
        painelDashboard.getChildren().addAll(graficoOcupacao, graficoFaturamento);

        //Botões + Relatórios
        HBox areaBotoesERelatorios = new HBox(20);
        areaBotoesERelatorios.setMinWidth(700);
        areaBotoesERelatorios.setPrefHeight(300);

        //Botões (Entrada/Saída)
        VBox grupoBotoes = new VBox(20);
        grupoBotoes.setAlignment(Pos.CENTER);

        Button btnEntrada = new Button("Entrada de Veículo");
        btnEntrada.setStyle("-fx-background-color: #136E16; -fx-text-fill: white; -fx-font-size: 18px; -fx-background-radius: 20px;");
        btnEntrada.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        btnEntrada.setPrefSize(300, 80);

        btnEntrada.setOnAction(evento -> {
            try {
                Stage stage2 = new Stage();
                JParkingEntrada telaEntrada = new JParkingEntrada();
                telaEntrada.start(stage2);

                stage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Button btnSaida = new Button("Saída de Veículo");
        btnSaida.setStyle("-fx-background-color: #A52424; -fx-text-fill: white; -fx-font-size: 18px; -fx-background-radius: 20px;");
        btnSaida.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        btnSaida.setPrefSize(300, 80);

        btnSaida.setOnAction(evento -> {
            RepositoryVeiculo registroSelecionado = listaEstacionados.getSelectionModel().getSelectedItem();

            if (registroSelecionado == null) {
                System.out.println("Nenhum veículo selecionado para saída.");

                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Atenção");
                alerta.setHeaderText(null);
                alerta.setContentText("Por favor, selecione um veículo na lista para processar a saída.");

                try {
                    Stage stageAlerta = (Stage) alerta.getDialogPane().getScene().getWindow();
                    Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/J-parking.logo.png")));
                    stageAlerta.getIcons().add(icon);
                } catch (Exception e) {
                    System.out.println("Erro ao carregar o ícone do alerta.");
                }

                alerta.showAndWait();
                return;
            }


            try {
                Stage Stage3 = new Stage();
                JParkingSaida.NovaTela novaTela = new JParkingSaida.NovaTela(registroSelecionado, this);
                novaTela.iniciar(Stage3);

                Stage stageAtual = (Stage) btnSaida.getScene().getWindow();
                stageAtual.close();

            } catch (Exception e) {
                System.err.println("Erro ao processar a extração da placa!!");
                e.printStackTrace();
            }
        });

        grupoBotoes.getChildren().addAll(btnEntrada, btnSaida);

        // Card de Relatórios
        VBox cardRelatorios = new VBox(10);
        cardRelatorios.setAlignment(Pos.CENTER);
        cardRelatorios.setPrefSize(380, 200);
        cardRelatorios.setStyle("-fx-background-color: #106DB5; -fx-border-color: white; -fx-border-width: 2px; -fx-border-radius: 10px; -fx-background-radius: 10px;");

        Label labelRelatorios = new Label("Gerar relatórios");
        labelRelatorios.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        labelRelatorios.setTextFill(Color.WHITE);

        Button btnVeiculosEstacionados = new Button("Veículos Estacionados Agora");
        btnVeiculosEstacionados.setFont(Font.font("Arial", 18));
        btnVeiculosEstacionados.setPrefSize(300, 80);
        btnVeiculosEstacionados.setStyle(
                "-fx-background-color: rgba(255,255,255,0.89); " +
                        "-fx-background-radius: 50px; " +
                        "-fx-padding: 5 15 5 15; " +
                        "-fx-text-fill: #106DB5;" // A cor do texto entra aqui
        );

        btnVeiculosEstacionados.setOnAction(eventoRelatorioEstacionado -> {
            try {
                Stage stageRelatorio = new Stage();
                JParkingRelatorioEstacionados telaRelatorio = new JParkingRelatorioEstacionados();
                telaRelatorio.start(stageRelatorio);

            } catch (Exception e) {
                System.err.println("Erro ao abrir a tela de relatórios:");
                e.printStackTrace();
            }
        });


        Button btnHistoricoDeSaidas = new Button("Histórico de Saídas");
        btnHistoricoDeSaidas.setFont(Font.font("Arial", 18));
        btnHistoricoDeSaidas.setPrefSize(300, 80);
        btnHistoricoDeSaidas.setStyle(
                "-fx-background-color: rgba(255,255,255,0.89); " +
                        "-fx-background-radius: 50px; " +
                        "-fx-padding: 5 15 5 15; " +
                        "-fx-text-fill: #106DB5;"
        );

        btnHistoricoDeSaidas.setOnAction(eventoRelatorioSaida -> {
            try {
                Stage stageRelatorio = new Stage();
                JParkingRelatorioSaida telaRelatorio = new JParkingRelatorioSaida();
                telaRelatorio.start(stageRelatorio);

            } catch (Exception e) {
                System.err.println("Erro ao abrir a tela de relatórios:");
                e.printStackTrace();
            }
        });

        HBox headerRelatorios = new HBox(btnVeiculosEstacionados, btnHistoricoDeSaidas);
        headerTop.setAlignment(Pos.CENTER);

        cardRelatorios.getChildren().addAll(labelRelatorios, btnVeiculosEstacionados, btnHistoricoDeSaidas);
        cardFaturamento.getChildren().add( painelDashboard);
        areaBotoesERelatorios.getChildren().addAll(grupoBotoes, cardRelatorios);
        colunaDireita.getChildren().addAll(cardFaturamento, areaBotoesERelatorios);
        mainLayout.getChildren().addAll(cardEsquerda, colunaDireita);
        root.getChildren().add(mainLayout);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
