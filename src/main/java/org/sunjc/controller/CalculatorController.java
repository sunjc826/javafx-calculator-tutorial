package org.sunjc.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import org.sunjc.App;
import org.sunjc.utils.EvaluateString;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CalculatorController {
  @FXML
  private Label expression;

  @FXML
  private Label result;

  private List<String> calculationHistory = new ArrayList<>();

  public void insertNumber(String number) {
    expression.setText(expression.getText() + number);
  }

  public void insertOperator(String op) {
    expression.setText(expression.getText() + " " + op + " ");
  }

  public void insertAnswer(String answer) {
    expression.setText(expression.getText() + answer);
  }

  public void deleteLast() {
    if (!getExpression().getText().isEmpty()) {
      StringBuilder text = new StringBuilder(getExpression().getText());
      text.deleteCharAt(text.length() - 1);
      getExpression().setText(text.toString());
    }
  }

  public void clearExpression() {
    expression.setText("");
  }

  public Label getExpression() {
   return expression;
  }

  public Label getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result.setText("= " + result);
  }


  public void openHistoryWindow() {
    try {
      FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../history.fxml"));
      Parent root = loader.load();

      // do this instead of new HistoryController()
      HistoryController historyController = loader.getController();
      historyController.initializeCalculation(calculationHistory);

      App.getHistoryStage().setScene(new Scene(root));
      App.getHistoryStage().show();
    } catch (IOException e) {

    }
  }

  public void addCalculation(String expression, String result) {
    this.calculationHistory.add(expression + " = " + result);
  }

  public void onMouseClick(MouseEvent mouseEvent) {
    Button button = (Button) mouseEvent.getSource();
    String btnText = button.getText();
    switch(btnText) {
      case "1": case "2": case "3": case "4": case "5": case "6": case "7": case "8": case "9": case "0":
        insertNumber(btnText);
        break;
      case "+": case "-": case "*": case "/":
        insertOperator(btnText);
        break;
      case "CLEAR":
        clearExpression();
        break;
      case "=":
        int result = EvaluateString.evaluate(this.getExpression().getText());
        addCalculation(this.getExpression().getText(), String.valueOf(result));
        setResult(String.valueOf(result));
        break;
      case "ANS":
        insertAnswer(this.getResult().getText().substring(2));
        break;
      case "DELETE":
        deleteLast();
        break;
      case "HIST":
        openHistoryWindow();
        break;
    }

  }
}
