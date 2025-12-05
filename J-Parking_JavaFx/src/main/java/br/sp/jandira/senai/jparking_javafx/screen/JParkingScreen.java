package br.sp.jandira.senai.jparking_javafx.screen;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Objects;


public class JParkingScreen extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        stage.setWidth(660);
        stage.setHeight(700);
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
        stage.setScene(scene);

        stage.show();

        VBox header = new VBox();
        header.setStyle("-fx-padding: 10; -fx-background-color:F8F8F8 ");





    }
}