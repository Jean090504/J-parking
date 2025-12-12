package br.sp.jandira.senai.jparking_javafx.screen;

import br.sp.jandira.senai.jparking_javafx.repository.RecebimentoDados;
import br.sp.jandira.senai.jparking_javafx.repository.RepositoryVeiculo;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class JParkingRelatorioEstacionados extends Application {

    @Override
    public void start(Stage stageRelatorio) throws IOException {

        try {
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/J-parking.logo.png")));
            stageRelatorio.getIcons().add(icon);
        } catch (Exception e) {
            System.out.println("Erro: Imagem do ícone não encontrada. Verifique se moveu para 'resources'.");
            e.printStackTrace();
        }

        stageRelatorio.setTitle("J-Parking - Relatório de Veículos Estacionados");
        stageRelatorio.setMinHeight(600);
        stageRelatorio.setMinWidth(800);

        Label titulo = new Label("Veículos Estacionados Atualmente");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        titulo.setTextFill(Color.web("#106DB5"));

        ListView<RepositoryVeiculo> listaRelatorio = new ListView<>();
        listaRelatorio.setPrefHeight(800);

        RecebimentoDados repositorio = new RecebimentoDados();

        List<RepositoryVeiculo> veiculosRegistros = repositorio.lerVeiculosEstacionados();
        listaRelatorio.getItems().addAll(veiculosRegistros);


        listaRelatorio.setCellFactory(lv -> new javafx.scene.control.ListCell<RepositoryVeiculo>() {
            @Override
            protected void updateItem(RepositoryVeiculo item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.getLinhaFormatada()); // Exibe a string formatada
            }
        });

        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: white;");

        root.getChildren().addAll(titulo, listaRelatorio);

        Scene scene = new Scene(root);
        stageRelatorio.setScene(scene);
        stageRelatorio.show();
    }
}