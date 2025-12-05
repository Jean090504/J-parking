package br.sp.jandira.senai.jparking_javafx.screen;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Objects;


public class JParkingScreen extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        stage.setWidth(1920);
        stage.setHeight(1080);
        stage.setResizable(false);
        stage.setTitle("J-Parking");

        try {
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/J-parking.logo.png")));
            stage.getIcons().add(icon);
        } catch (Exception e) {
            System.out.println("Erro: Imagem do ícone não encontrada. Verifique se moveu para 'resources'.");
            e.printStackTrace();
        }

        VBox root = new VBox();
        Scene scene = new Scene(root);
        root.setStyle("-fx-background-color: #106DB5;");
        root.setAlignment(Pos.CENTER);
        stage.setScene(scene);

        stage.show();

        VBox cardContainer = new VBox(10);
        cardContainer.setPadding(new Insets(20));

        cardContainer.setMaxWidth(780);
        cardContainer.setMinWidth(780);
        cardContainer.setMinHeight(880);

        cardContainer.setStyle(
                "-fx-background-color: #106DB5; " +
                        "-fx-border-color: white; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 10px; " +
                        "-fx-background-radius: 10px;"
        );

        Label labelVagas = new Label("Vagas Livres: 20/50");
        labelVagas.setTextFill(Color.WHITE);
        labelVagas.setFont(Font.font("Arial", 30));
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

        GridPane listaEStacionados = new GridPane();
        listaEStacionados.setHgap(10);
        listaEStacionados.setVgap(10);
        listaEStacionados.setAlignment(Pos.CENTER);

        cardContainer.getChildren().addAll(headerTop, headerTitle, linha, listaEStacionados);

        root.getChildren().add(cardContainer);

    }
}