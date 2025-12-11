package br.sp.jandira.senai.jparking_javafx;

import br.sp.jandira.senai.jparking_javafx.model.Jparking;
import br.sp.jandira.senai.jparking_javafx.screen.JParkingScreen;
import br.sp.jandira.senai.jparking_javafx.util.DashBoardFactory;
import javafx.application.Application;

public class JparkingApp {

    public static void main(String[] args) {
        Application.launch(JParkingScreen.class, args);
    }
    Jparking jparkingExecutavel = new Jparking();

    public void teste(){
        jparkingExecutavel.mostrarDados();




    }



}