package org.sunjc.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.List;

public class HistoryController {

  @FXML
  private ListView historyList;

  public void initializeCalculation(List<String> calculationHistory) {
    calculationHistory.forEach((calculation) -> {
      historyList.getItems().add(calculation);
    });
  }

}
