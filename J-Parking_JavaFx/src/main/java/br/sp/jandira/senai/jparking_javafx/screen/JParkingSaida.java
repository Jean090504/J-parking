package br.sp.jandira.senai.jparking_javafx.screen;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.Optional;

public class JParkingSaida {


    public static class NovaTela extends Application {


        final String boxStyle = "-fx-background-color: transparent; -fx-border-color: white; -fx-border-radius: 25; -fx-text-fill: white; -fx-prompt-text-fill: white; -fx-border-width: 2px; -fx-padding: 10px 20px; -fx-font-size: 30;";



        @Override
        public void start(Stage stage) {

            stage.setWidth(1920);
            stage.setHeight(1080);
            stage.setResizable(true);
            stage.setTitle("J-Parking");

            VBox rootPrincipal = new VBox(20); // Espaçamento de 20px
            rootPrincipal.setStyle("-fx-background-color: #106DB5;");
            rootPrincipal.setAlignment(Pos.CENTER);
            rootPrincipal.setPadding(new Insets(40)); // Margem interna




            try {
                Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/J-parking.logo.png")));
                stage.getIcons().add(icon);
            } catch (Exception e) {
                System.out.println("Erro: Imagem do ícone não encontrada.");

            }


            Button btnVoltarMain = new Button("Voltar");
            btnVoltarMain.setStyle("-fx-text-fill: black; -fx-font-size: 12px; -fx-background-radius: 5px;");
            btnVoltarMain.setFont(Font.font("Arial", FontWeight.BOLD, 20));
            btnVoltarMain.setPrefSize(50, 25);
            btnVoltarMain.setTranslateX(40);
            btnVoltarMain.setTranslateY(100);

            btnVoltarMain.setOnAction(evento -> {
                try {
                    Stage stageInicial = new Stage();
                    JParkingScreen telaInicial = new JParkingScreen();
                    telaInicial.start(stageInicial);

                    stage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });


            Label titulo = new Label("Saída do Veículo");
            titulo.setFont(Font.font("Arial", 24));
            titulo.setStyle("-fx-text-fill: #ffffff;"); // Para ser visível no fundo azul


            Label tempoPermanencia = new Label();
            tempoPermanencia.setText("Tempo de permanencia: ");
            tempoPermanencia.setStyle(boxStyle);
            tempoPermanencia.setMinWidth(600);
            tempoPermanencia.setMinHeight(60);

            HBox horasTotais = new HBox(30);
            horasTotais.setAlignment(Pos.CENTER);
            horasTotais.getChildren().addAll(tempoPermanencia);
            horasTotais.setStyle(
                    "-fx-background-color: #106DB5FF; " +
                            "-fx-background-radius: 50px; " +
                            "-fx-padding: 5 15 5 15; " +
                            "-fx-text-fill: #106DB5;"


            );


            Label totalPagar = new Label();
            totalPagar.setText("Total a Pagar: R$25,00");
            totalPagar.setWrapText(true);
            totalPagar.setPrefWidth(300);
            totalPagar.setStyle(boxStyle);
            totalPagar.setMinWidth(150);
            totalPagar.setMinHeight(150);


            HBox totalFrame = new HBox(30);
            totalFrame.setAlignment(Pos.CENTER);
            totalFrame.getChildren().addAll(totalPagar);
            totalFrame.setStyle(
                    "-fx-background-color: #106DB5FF; " +
                            "-fx-background-radius: 50px; " +
                            "-fx-padding: 5 15 5 15; " +
                            "-fx-text-fill: #106DB5;"


            );


            Button btnFechar = new Button();
            btnFechar.setOnAction(e -> {
                Alert alertaFechar = new Alert(
                        Alert.AlertType.CONFIRMATION, "Confirma a saída do sistema?",
                        ButtonType.YES,
                        ButtonType.NO
                );
                Optional<ButtonType> resposta = alertaFechar.showAndWait();
                if (resposta.isPresent() && resposta.get() == ButtonType.YES) {
                    Platform.exit();
                }

            });

            Button btnSaida = new Button("Saída");
            btnSaida.setStyle("-fx-background-color: #A52424;-fx-font-weight:40; fx-text-fill: white; -fx-font-size: 18px; -fx-background-radius: 20px;");
            btnSaida.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 40));
            btnSaida.setPrefSize(300, 80);




            rootPrincipal.getChildren().addAll(
                    btnVoltarMain,
                    titulo,
                    horasTotais,
                    totalFrame,
                    btnSaida


            );

            Scene sceneFinal = new Scene(rootPrincipal);
            stage.setScene(sceneFinal);
            stage.show(); // Exibe a janela
        }

    }
}
