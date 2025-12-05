package br.sp.jandira.senai.jparking_javafx; // Ajuste o pacote se necessário

import br.sp.jandira.senai.jparking_javafx.screen.JParkingScreen;
import javafx.application.Application;

public class Launcher {
    public static void main(String[] args) {
        // Esse comando carrega as bibliotecas antes de iniciar o JavaFX
        Application.launch(JParkingScreen.class, args);
    }
}