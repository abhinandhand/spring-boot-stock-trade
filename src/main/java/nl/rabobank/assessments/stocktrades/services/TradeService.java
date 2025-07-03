package nl.rabobank.assessments.stocktrades.services;

import nl.rabobank.assessments.stocktrades.dto.StockRequest;
import nl.rabobank.assessments.stocktrades.dto.StockResponse;
import nl.rabobank.assessments.stocktrades.model.StockTrade;

import java.util.List;
import java.util.Optional;

public interface TradeService {

  StockResponse create(StockRequest stockRequest);
  List<StockResponse> fetchStockTrade(Optional<String> type, Optional<Integer> userId);
  StockResponse fetchStockTradeById(Integer id);
}
