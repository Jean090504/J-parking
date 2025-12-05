package br.sp.jandira.senai.jparking_javafx.screen;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Objects;


public class JParkingEntrada extends Application {

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

        //LAYOUT PRINCIPAL
        VBox mainLayout = new VBox(30);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(20));

        root.getChildren().add(mainLayout);



    }
}