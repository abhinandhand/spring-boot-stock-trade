package nl.rabobank.assessments.stocktrades.exceptions;

public class StockNotFountException  extends RuntimeException {
  public StockNotFountException(String message) {
    super(message);
  }
}
