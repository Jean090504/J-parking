package br.sp.jandira.senai.jparking_javafx.screen;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import javax.swing.*;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
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

       /* //LAYOUT PRINCIPAL
        VBox mainLayout = new VBox(30);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(20));

        root.getChildren().add(mainLayout);

        // TEXTFIELDS
        TextField NomeProprietario = new TextField();
        NomeProprietario.setPromptText("Digite seu nome aqui");

        TextField PlacaVeiculo = new TextField();
        TextField CorVeiculo = new TextField();
        TextField MarcaVeiculo = new TextField();
        TextField ModeloVeiculo = new TextField();



        // GRID E FORMATAÇÃO
        GridPane gridFormulario = new GridPane();
        gridFormulario.setHgap(10);
        gridFormulario.setVgap(10);
        gridFormulario.setAlignment(Pos.CENTER);




        // ADICIONAR CAMPOS AO GRID
        gridFormulario.add(NomeProprietario, 1, 0);
        gridFormulario.add(PlacaVeiculo, 2,0 );
        gridFormulario.add(CorVeiculo, 1, 1);
        gridFormulario.add(MarcaVeiculo, 1, 2);
        gridFormulario.add(ModeloVeiculo, 2, 2);

        // ADICIONAR GRID AO MAINLAYOUT
        mainLayout.getChildren().add(gridFormulario);*/



        // Estilo para o TextField
        final String cor = "-fx-background-color: transparent; -fx-border-color: white; -fx-border-radius: 15; -fx-text-fill: white; -fx-prompt-text-fill: #cccccc; -fx-border-width: 2px; -fx-padding: 10px 20px;";

        // Estilo para o Botão
        final String corBnt = "-fx-background-color: #38761D; -fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold; -fx-padding: 15px 40px; -fx-background-radius: 10;";

        // TEXTFIELDS
        // Aplicando o estilo TF_STYLE
        TextField NomeProprietario = new TextField();
        NomeProprietario.setPromptText("Nome do Proprietário");
        NomeProprietario.setStyle(cor);
        NomeProprietario.setMaxWidth(500);

        TextField PlacaVeiculo = new TextField();
        PlacaVeiculo.setPromptText("Placa do Veículo");
        PlacaVeiculo.setStyle(cor);
        PlacaVeiculo.setMaxWidth(500);

        TextField CorVeiculo = new TextField();
        CorVeiculo.setPromptText("Cor do Veículo");
        CorVeiculo.setStyle(cor);
        CorVeiculo.setMaxWidth(500);

        TextField MarcaVeiculo = new TextField();
        MarcaVeiculo.setPromptText("Marca do Veículo");
        MarcaVeiculo.setStyle(cor);
        MarcaVeiculo.setMaxWidth(500);

        TextField ModeloVeiculo = new TextField();
        ModeloVeiculo.setPromptText("Modelo do Veículo");
        ModeloVeiculo.setStyle(cor);
        ModeloVeiculo.setMaxWidth(500);

        // Para replicar o layout, você precisará de um botão no final
        javafx.scene.control.Button btnEnviar = new javafx.scene.control.Button("ENVIAR");
        btnEnviar.setStyle(corBnt);
        btnEnviar.setMinHeight(100);


        // ORGANIZAÇÃO DO LAYOUT EM LINHAS (HBox)

        // Linha 1: Nome e Placa (lado a lado)
        HBox linha1 = new HBox(30); // 30 é o espaçamento
        linha1.setAlignment(Pos.CENTER);
        linha1.getChildren().addAll(NomeProprietario, PlacaVeiculo);

        // Linha 2: Cor (centralizado)
        HBox linha2 = new HBox(30);
        linha2.setAlignment(Pos.CENTER);
        linha2.getChildren().add(CorVeiculo);

        // Linha 3: Marca e Modelo (lado a lado)
        HBox linha3_MarcaModelo = new HBox(30);
        linha3_MarcaModelo.setAlignment(Pos.CENTER);
        linha3_MarcaModelo.getChildren().addAll(MarcaVeiculo, ModeloVeiculo);

        // Linha 4: Cor (repetição de layout)
        HBox linha4 = new HBox(30);
        linha4.setAlignment(Pos.CENTER);


        // LAYOUT PRINCIPAL (VBox)

        VBox layoutOrganizado = new VBox(40); // 25 é o espaçamento vertical entre as linhas
        layoutOrganizado.setAlignment(Pos.CENTER);
        layoutOrganizado.getChildren().addAll(linha1, linha2, linha3_MarcaModelo, linha4,  btnEnviar);

        //ATUALIZAÇÃO DO ROOT

        // Remova quaisquer linhas que tentem adicionar GridPane ou outro layout ao root.
        // Adicione apenas o VBox recém-criado.
        root.getChildren().clear(); // Limpa o root para garantir que não haja elementos duplicados
        root.getChildren().add(layoutOrganizado);

        stage2.show();



    }


}