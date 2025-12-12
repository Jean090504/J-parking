package br.sp.jandira.senai.jparking_javafx.util;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class DashBoardFactory {
    public static Tile criarMedidorOcupacao(int valorAtual) {
        return TileBuilder.create()
                .skinType(Tile.SkinType.GAUGE)
                .prefSize(300, 300)
                .title("Ocupação")
                .unit("%")
                .maxValue(100)
                .value(valorAtual)
                .threshold(80)
                .thresholdVisible(true)
                .titleAlignment(TextAlignment.CENTER)
                .backgroundColor(Color.TRANSPARENT)
                .build();
    }

    public static Tile criarResumoFinanceiro(double faturamentoHoje) {
        return TileBuilder.create()
                .skinType(Tile.SkinType.NUMBER)
                .prefSize(300, 150)
                .title("Faturamento Hoje")
                .text("Total Arrecadado")
                .value(faturamentoHoje)
                .unit("R$")
                .description("Meta: R$ 1.000")
                .textAlignment(TextAlignment.CENTER)
                .backgroundColor(Color.web("#106DB5"))
                .build();
    }
}