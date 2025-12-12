package br.sp.jandira.senai.jparking_javafx.repository;

public class RepositoryVeiculo {
        private String linhaCsvOriginal;
        private String linhaFormatada;

        public RepositoryVeiculo(String original, String formatada) {
            this.linhaCsvOriginal = original;
            this.linhaFormatada = formatada;
        }

        public String getLinhaCsvOriginal() {
            return linhaCsvOriginal;
        }

        public String getLinhaFormatada() {
            return linhaFormatada;
        }
}

