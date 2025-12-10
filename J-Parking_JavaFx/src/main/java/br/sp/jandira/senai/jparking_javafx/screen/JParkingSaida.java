package br.sp.jandira.senai.jparking_javafx.screen;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.Objects;
import java.util.Optional;

public class JParkingSaida {


    public static class NovaTela extends Application {


        final String boxStyle = "-fx-background-color: transparent; -fx-border-color: white; -fx-border-radius: 15; -fx-text-fill: white; -fx-prompt-text-fill: #cccccc; -fx-border-width: 2px; -fx-padding: 10px 20px;";



        @Override
        public void start(Stage stage) {

            stage.setWidth(800);
            stage.setHeight(600);
            stage.setResizable(true);
            stage.setTitle("J-Parking");




            try {
                Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/J-parking.logo.png")));
                stage.getIcons().add(icon);
            } catch (Exception e) {
                System.out.println("Erro: Imagem do ícone não encontrada.");

            }



            Label titulo = new Label("Bem-vindo à Nova Tela!");
            titulo.setFont(Font.font("Arial", 24));
            titulo.setStyle("-fx-text-fill: white;"); // Para ser visível no fundo azul


            TextField tempoPermanencia = new TextField();
            tempoPermanencia.setPromptText("Tempo de permanencia: ");
            tempoPermanencia.setStyle(boxStyle);
            tempoPermanencia.setMaxWidth(150);

            HBox horasTotais = new HBox(30);
            horasTotais.setAlignment(Pos.CENTER);
            horasTotais.getChildren().addAll(tempoPermanencia);
            //horasTotais.setFont(Font.font("Arial", 18));
            horasTotais.setPrefSize(300, 60); // Altura ajustada
            horasTotais.setStyle(
                    "-fx-background-color: rgba(255,255,255,0.89); " +
                            "-fx-background-radius: 50px; " +
                            "-fx-padding: 5 15 5 15; " +
                            "-fx-text-fill: #106DB5;"
            );

/*
            Button btnOpcao1 = new Button("Opção 1");
            btnOpcao1.setPrefSize(300, 60);


            Button btnOpcao2 = new Button("Opção 2");
            btnOpcao2.setPrefSize(300, 60);
            */


            Button btnFechar = new Button("Fechar Tela");
            btnFechar.setPrefSize(300, 60);
/*
            btnOpcao1.setOnAction(e -> {
                titulo.setText("Botão 1 Clicado!");
            });

            btnOpcao2.setOnAction(e -> {
                titulo.setText("Botão 2 Clicado!");
            });

            /*btnHistoricoDeSaidas.setOnAction(e -> {
                titulo.setText("Histórico Clicado!");
            });
            */


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


            VBox rootPrincipal = new VBox(20); // Espaçamento de 20px
            rootPrincipal.setStyle("-fx-background-color: #106DB5;");
            rootPrincipal.setAlignment(Pos.CENTER);
            rootPrincipal.setPadding(new Insets(40)); // Margem interna


            rootPrincipal.getChildren().addAll(
                    titulo,
                    //btnHistoricoDeSaidas,
                    // btnOpcao1,
                    // btnOpcao2,
                    btnFechar
            );

            Scene sceneFinal = new Scene(rootPrincipal);
            stage.setScene(sceneFinal);
            stage.show(); // Exibe a janela
        }

        public static void main(String[] args) {
            launch(args);
        }
    }
}









