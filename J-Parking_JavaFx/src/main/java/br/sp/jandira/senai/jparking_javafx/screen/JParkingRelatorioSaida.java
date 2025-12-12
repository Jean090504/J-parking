package br.sp.jandira.senai.jparking_javafx.screen;

import br.sp.jandira.senai.jparking_javafx.repository.RecebimentoDados;
import br.sp.jandira.senai.jparking_javafx.repository.RepositoryHistoricoVeiculo;
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

public class JParkingRelatorioSaida extends Application {

    @Override
    public void start(Stage stageRelatorioSaida) throws IOException {

        try {
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/J-parking.logo.png")));
            stageRelatorioSaida.getIcons().add(icon);
        } catch (Exception e) {
            System.out.println("Erro: Imagem do ícone não encontrada. Verifique se moveu para 'resources'.");
            e.printStackTrace();
        }

        stageRelatorioSaida.setTitle("J-Parking - Relatório de Saída de Veículos");
        stageRelatorioSaida.setMinHeight(600);
        stageRelatorioSaida.setMinWidth(800);

        Label titulo = new Label("Saída de Veículos");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        titulo.setTextFill(Color.web("#106DB5"));

        ListView<RepositoryHistoricoVeiculo> listaHistorico = new ListView<>();
        listaHistorico.setMinHeight(500);

        RecebimentoDados repositorio = new RecebimentoDados();

        List<RepositoryHistoricoVeiculo> registros = repositorio.lerHistoricoSaidas();
        listaHistorico.getItems().addAll(registros);

        listaHistorico.setCellFactory(lv -> new javafx.scene.control.ListCell<RepositoryHistoricoVeiculo>() {
            @Override
            protected void updateItem(RepositoryHistoricoVeiculo item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.getLinhaFormatada());
            }
        });

        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: white;");

        root.getChildren().addAll(titulo, listaHistorico);

        Scene scene = new Scene(root);
        stageRelatorioSaida.setScene(scene);
        stageRelatorioSaida.show();
    }
}
