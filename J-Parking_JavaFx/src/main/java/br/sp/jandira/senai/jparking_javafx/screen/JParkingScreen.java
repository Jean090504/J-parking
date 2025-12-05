package br.sp.jandira.senai.jparking_javafx.screen;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane; // Usando Pane vazio por enquanto
import javafx.stage.Stage;

import java.io.IOException;


public class JParkingScreen extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        Pane root = new Pane();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.setWidth(660);
        stage.setHeight(700);
        stage.setResizable(false);
        stage.setTitle("J-Parking");

        stage.show();
    }
}