package br.sp.jandira.senai.jparking_javafx.screen;

import br.sp.jandira.senai.jparking_javafx.model.Cliente;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import br.sp.jandira.senai.jparking_javafx.util.DashBoardFactory;
import eu.hansolo.tilesfx.Tile;

import java.io.IOException;
import java.util.Objects;

public class JParkingScreen extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        stage.setWidth(1920);
        stage.setHeight(1080);
        stage.setResizable(false);
        stage.setTitle("J-Parking");

        // Logo
        try {
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/J-parking.logo.png")));
            stage.getIcons().add(icon);
        } catch (Exception e) {
            System.out.println("Erro no ícone.");
        }

        //ROOT (Fundo Principal)
        VBox root = new VBox();
        root.setStyle("-fx-background-color: #106DB5;");
        root.setAlignment(Pos.CENTER); // Centraliza tudo na tela

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
        Label labelVagas = new Label("Vagas Livres: 20/50");
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

        GridPane listaEstacionados = new GridPane();

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

        Tile graficoOcupacao = DashBoardFactory.criarMedidorOcupacao(45); // Ex: 45% ocupado
        Tile graficoFaturamento = DashBoardFactory.criarResumoFinanceiro();

        HBox painelDashboard = new HBox(20); // Espaçamento de 20px
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
            try {
                Stage stage3 = new Stage();
                JParkingEntrada telaSaida = new JParkingEntrada();
                telaSaida.start(stage3);

                stage.close();
            } catch (Exception e) {
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


        Button btnHistoricoDeSaidas = new Button("Histórico de Saídas");
        btnHistoricoDeSaidas.setFont(Font.font("Arial", 18));
        btnHistoricoDeSaidas.setPrefSize(300, 80);
        btnHistoricoDeSaidas.setStyle(
                "-fx-background-color: rgba(255,255,255,0.89); " +
                        "-fx-background-radius: 50px; " +
                        "-fx-padding: 5 15 5 15; " +
                        "-fx-text-fill: #106DB5;"
        );

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