package com.ifsc.tds.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class SalarioController {

    @FXML
    private Label Lbl1;

    @FXML
    private TextField TxtHoras;

    @FXML
    private Label Lbl2;

    @FXML
    private TextField TxtSalario;

    @FXML
    private Label Lbl3;

    @FXML
    private Label LblAumento;

    @FXML
    private Label Lbl4;

    @FXML
    private Label LblSalarioFinal;

    @FXML
    private Button BtnCalc;

    @FXML
    private Button CtnClean;

	@FXML
	void onClickCalc(ActionEvent event) {
		try {
			Double horas = Double.parseDouble(TxtHoras.getText());
			Double salarioHoras = Double.parseDouble(TxtSalario.getText());
			double aumento = 0;
			double salarioFinal = 0;
			double horasMais = 0;

			horasMais = horas - 160;

			if (horasMais > 0) {
				salarioFinal = 160 * salarioHoras;
				salarioFinal += horasMais * salarioHoras;
				salarioFinal += horasMais * 0.5;

				aumento = salarioFinal - (salarioHoras * 160);
				
				LblAumento.setText(String.format("%.2f", aumento));
				LblSalarioFinal.setText(String.format("%.2f", salarioFinal));

			} else {
				salarioFinal = horas * salarioHoras;
				
				LblAumento.setText(String.format("%.2f", aumento));
				LblSalarioFinal.setText(String.format("%.2f", salarioFinal));

			}

		} catch (NumberFormatException e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);

			String textoErro = sw.toString();
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Erro");
			alerta.setHeaderText("Aconteceu um erro de conversão numérica!");

			Label label = new Label("Segue a pilha de exceção: ");

			TextArea textArea = new TextArea(textoErro);
			textArea.setEditable(false);
			textArea.setWrapText(true);

			textArea.setMaxWidth(Double.MAX_VALUE);
			textArea.setMaxHeight(Double.MAX_VALUE);

			GridPane.setVgrow(textArea, Priority.ALWAYS);
			GridPane.setHgrow(textArea, Priority.ALWAYS);

			GridPane expConteudo = new GridPane();
			expConteudo.setMaxWidth(Double.MAX_VALUE);
			expConteudo.add(label, 0, 0);
			expConteudo.add(textArea, 0, 1);
			alerta.getDialogPane().setExpandableContent(expConteudo);
			alerta.showAndWait();
		}
	}

	@FXML
	void onClickClean(ActionEvent event) {
		TxtHoras.clear();
		TxtSalario.clear();
		LblAumento.setText("0");
		LblSalarioFinal.setText("0");
		TxtHoras.requestFocus();
	}

	public boolean onCloseQuery() {
		Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);

		alerta.setTitle("Pergunta");
		alerta.setHeaderText("Deseja sair do sistema?");

		ButtonType botaoNao = ButtonType.NO;
		ButtonType botaoSim = ButtonType.YES;

		alerta.getButtonTypes().setAll(botaoSim, botaoNao);

		Optional<ButtonType> opcaoClicada = alerta.showAndWait();

		return opcaoClicada.get() == botaoSim ? true : false;

	}
}