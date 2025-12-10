package br.sp.jandira.senai.jparking_javafx.screen;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Objects;


public class JParkingEntrada extends Application {

    public TextField nomeProprietario;
    public TextField placaVeiculo;
    public TextField corVeiculo;
    public TextField marcaVeiculo;
    public TextField modeloVeiculo;

    @Override
    public void start(Stage stage2) throws IOException {

        stage2.setWidth(1920);
        stage2.setHeight(1080);
        stage2.setResizable(false);
        stage2.setTitle("J-Parking");

        try {
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/J-parking.logo.png")));
            stage2.getIcons().add(icon);
        } catch (Exception e) {
            System.out.println("Erro: Imagem do ícone não encontrada. Verifique se moveu para 'resources'.");
            e.printStackTrace();
        }

        VBox root = new VBox();
        Scene scene = new Scene(root);
        root.setStyle("-fx-background-color: #106DB5;");
        root.setAlignment(Pos.CENTER);
        stage2.setScene(scene);
        stage2.show();

        final String cor = "-fx-background-color: transparent; -fx-border-color: white; -fx-border-radius: 15; -fx-text-fill: white; -fx-prompt-text-fill: #cccccc; -fx-border-width: 2px; -fx-padding: 10px 20px; -fx-font-size: 18px";

        final String corBnt = "-fx-background-color: #136E16; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 15px 40px; -fx-background-radius: 20;";

        nomeProprietario = new TextField();
        nomeProprietario.setPromptText("Nome do Proprietário");
        nomeProprietario.setStyle(cor);
        nomeProprietario.setMinWidth(400);
        nomeProprietario.setMinHeight(80);

        placaVeiculo = new TextField();
        placaVeiculo.setPromptText("Placa do Veículo");
        placaVeiculo.setStyle(cor);
        placaVeiculo.setMinWidth(400);
        placaVeiculo.setMinHeight(80);

        corVeiculo = new TextField();
        corVeiculo.setPromptText("Cor do Veículo");
        corVeiculo.setStyle(cor);
        corVeiculo.setMinWidth(400);
        corVeiculo.setMinHeight(80);

        marcaVeiculo = new TextField();
        marcaVeiculo.setPromptText("Marca do Veículo");
        marcaVeiculo.setStyle(cor);
        marcaVeiculo.setMinWidth(400);
        marcaVeiculo.setMinHeight(80);

        modeloVeiculo = new TextField();
        modeloVeiculo.setPromptText("Modelo do Veículo");
        modeloVeiculo.setStyle(cor);
        modeloVeiculo.setMinWidth(400);
        modeloVeiculo.setMinHeight(80);

        Button btnEnviar = new Button("ENVIAR");
        btnEnviar.setStyle(corBnt);
        btnEnviar.setMinWidth(600);
        btnEnviar.setMinHeight(80);

        btnEnviar.setOnAction(evento -> {
            try {
                Stage stageInicial = new Stage();
                JParkingScreen telaInicial = new JParkingScreen();
                telaInicial.start(stageInicial);

                stage2.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setStyle("-fx-text-alignment: left; -fx-background-color: #cccccc;");
        btnVoltar.setMinWidth(20);
        btnVoltar.setMinHeight(20);

        btnVoltar.setOnAction(evento -> {
            try {
                Stage stageInicial = new Stage();
                JParkingScreen telaInicial = new JParkingScreen();
                telaInicial.start(stageInicial);

                stage2.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        HBox linha1 = new HBox(30);
        linha1.setAlignment(Pos.CENTER);
        linha1.getChildren().addAll(nomeProprietario, placaVeiculo);


        HBox linha2 = new HBox(30);
        linha2.setAlignment(Pos.CENTER);
        linha2.getChildren().add(corVeiculo);


        HBox linha3_MarcaModelo = new HBox(30);
        linha3_MarcaModelo.setAlignment(Pos.CENTER);
        linha3_MarcaModelo.getChildren().addAll(marcaVeiculo, modeloVeiculo);


        HBox linha4 = new HBox(30);
        linha4.setAlignment(Pos.CENTER);

        VBox layoutOrganizado = new VBox(40);
        layoutOrganizado.setAlignment(Pos.CENTER);
        layoutOrganizado.getChildren().addAll(btnVoltar, linha1, linha2, linha3_MarcaModelo, linha4,  btnEnviar);

        root.getChildren().clear();
        root.getChildren().add(layoutOrganizado);

        stage2.show();


    }


}