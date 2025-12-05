package br.sp.jandira.senai.jparking_javafx.screen;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Objects;


public class JParkingSaida extends Application {

    @Override
    public void start(Stage stage3) throws IOException {

        stage3.setWidth(1920);
        stage3.setHeight(1080);
        stage3.setResizable(false);
        stage3.setTitle("J-Parking");

        try {
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/J-parking.logo.png")));
            stage3.getIcons().add(icon);
        } catch (Exception e) {
            System.out.println("Erro: Imagem do ícone não encontrada. Verifique se moveu para 'resources'.");
            e.printStackTrace();
        }

        VBox root = new VBox();
        Scene scene = new Scene(root);
        root.setStyle("-fx-background-color: #106DB5;");
        root.setAlignment(Pos.CENTER);
        stage3.setScene(scene);
        stage3.show();


    }
}