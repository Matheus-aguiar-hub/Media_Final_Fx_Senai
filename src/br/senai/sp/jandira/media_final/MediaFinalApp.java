package br.senai.sp.jandira.media_final;

import com.sun.net.httpserver.Request;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;


public class MediaFinalApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Determinar o tamanho do stage
        stage.setWidth(500);
        stage.setHeight(400);

        //Determinar o titulo do stage (Tela/janela)
        stage.setTitle("Média final");

        //Painel raiz (root) - Parte de cima
        BorderPane root = new BorderPane();

        Label labelTitulo = new Label();
        labelTitulo.setText("Escola \"Prof. Vicente Amato\"");
        //Formatação do texto da label
        labelTitulo.setStyle("-fx-text-fill: #005aff;-fx-font-size: 32;-fx-font-weight: bold;");
        labelTitulo.setPadding(new Insets(10, 0, 10, 10));

        //Painel de resultados - Parte de baixo
        VBox painelResultado = new VBox();
        painelResultado.setPadding(new Insets(0, 0, 10, 10));
        Label labelAluno = new Label("Nome do aluno: ");
        Label labelMediaFinal = new Label("Média final: ");
        Label labelSituacao = new Label("Situação: ");
        painelResultado.getChildren().addAll(labelAluno, labelMediaFinal, labelSituacao);

        //Painel de Botões - Parte da direita
        VBox paineldeBotoes = new VBox();
        paineldeBotoes.setPadding(new Insets(20, 10, 10, 0));
        paineldeBotoes.setSpacing(15);
        Button buttonCalcularMedia = new Button("Calcular média");
        buttonCalcularMedia.setPrefWidth(150);
        buttonCalcularMedia.setPrefHeight(50);
        Button buttonLimpar = new Button("Limpar");
        buttonLimpar.setPrefWidth(150);
        buttonLimpar.setPrefHeight(50);
        Button buttonSair = new Button("Sair");
        buttonSair.setPrefWidth(150);
        buttonSair.setPrefHeight(50);
        paineldeBotoes.getChildren().addAll(buttonCalcularMedia, buttonLimpar, buttonSair);


        //Painel formulário - Parte da esquerda
        VBox painelFormulario = new VBox();
        painelFormulario.setPadding(new Insets(0, 0, 10, 10));
        Label labelNome = new Label("Nome do aluno: ");
        Label labelNota1 = new Label("Nota 1 ");
        Label labelNota2 = new Label("Nota 2 ");
        Label labelNota3 = new Label("Nota 3 ");
        Label labelNota4 = new Label("Nota 4 ");
        TextField textFieldNome = new TextField();
        TextField textFieldNota1 = new TextField();
        TextField textFieldNota2 = new TextField();
        TextField textFieldNota3 = new TextField();
        TextField textFieldNota4 = new TextField();
        painelFormulario.getChildren().addAll(
                labelNome,
                textFieldNome,
                labelNota1,
                textFieldNota1,
                labelNota2,
                textFieldNota2,
                labelNota3,
                textFieldNota3,
                labelNota4,
                textFieldNota4
        );

        //BoderPane
        root.setTop(labelTitulo);
        root.setBottom(painelResultado);
        root.setRight(paineldeBotoes);
        root.setLeft(painelFormulario);

        Scene scene = new Scene(root);

        stage.setScene(scene);

        //Mostrar o stage (Tela/Janela)
        stage.show();

        //Eventos de clique dos botões
        buttonCalcularMedia.setOnAction(click -> {
            System.out.println("Botão clicado");
            String nomeDigitado = textFieldNome.getText();
            labelAluno.setText("Nome do aluno: " + nomeDigitado);

            // Calcular média
            //Obter as notas

            //Cria vetor de notas
            double[] notas = new double[4];
            String[] notasStr = new String[4];

            notasStr[0] = textFieldNota1.getText();
            notas[0] = Double.parseDouble(notasStr[0]);

            notasStr[1] = textFieldNota2.getText();
            notas[1] = Double.parseDouble(notasStr[1]);

            notasStr[2] = textFieldNota3.getText();
            notas[2] = Double.parseDouble(notasStr[2]);

            notasStr[3] = textFieldNota4.getText();
            notas[3] = Double.parseDouble(notasStr[3]);

            // USO DE LOOP while (Enquanto)
            double mediaFinal = 0.0;
            int i = 0;
            while (i < notas.length) {
                mediaFinal = mediaFinal + notas[i];
                i = i + 1;
            }

            mediaFinal = mediaFinal / notas.length;


            String mediaFinalstr = String.format("%.2f", mediaFinal);

            labelMediaFinal.setText("Média final: " + mediaFinalstr);

            //Situação do aluno
            if (mediaFinal <= 4) {
                labelSituacao.setText("Situação: " + "Tente novamente");
            } else if (mediaFinal >= 6) {
                labelSituacao.setText("Situação: " + "Aprovado");
            } else {
                labelSituacao.setText("Situação: " + "Recuperação");
            }

        });

        //Limpar
        buttonLimpar.setOnAction(click -> {
            textFieldNome.clear();
            textFieldNota1.clear();
            textFieldNota2.clear();
            textFieldNota3.clear();
            textFieldNota4.clear();
            labelMediaFinal.setText("");
            labelSituacao.setText("");
            labelAluno.setText("");
            textFieldNome.requestFocus();
        });
        //Sair
        buttonSair.setOnAction(click -> {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Confirma saida ?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> botaoPressionado = alerta.showAndWait();

            if(botaoPressionado.get() == ButtonType.YES){
                Alert alerta2 = new Alert(Alert.AlertType.INFORMATION, "Até logo!");
                alerta2.showAndWait();
                System.exit(0);
            }

        });
    }
}







