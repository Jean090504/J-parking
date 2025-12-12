package br.sp.jandira.senai.jparking_javafx.repository;

public class RepositoryHistoricoVeiculo {
        private String linhaCsvOriginal;
        private String linhaFormatada;

        public RepositoryHistoricoVeiculo(String original, String formatada) {
            this.linhaCsvOriginal = original;
            this.linhaFormatada = formatada;
        }

        public String getLinhaFormatada() {
            return linhaFormatada;
        }

    }
