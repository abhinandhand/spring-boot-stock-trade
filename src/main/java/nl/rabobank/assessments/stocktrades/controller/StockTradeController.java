package nl.rabobank.assessments.stocktrades.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nl.rabobank.assessments.stocktrades.dto.StockRequest;
import nl.rabobank.assessments.stocktrades.dto.StockResponse;
import nl.rabobank.assessments.stocktrades.model.StockTrade;
import nl.rabobank.assessments.stocktrades.services.TradeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller class for the API endpoints
 */
@RestController
@RequiredArgsConstructor
public class StockTradeController {
  private final TradeService tradeService;

  /**
   * Endpoint to create Stock
   * @param stockRequest
   * @return
   */

  @PostMapping("/trades")

  public ResponseEntity<StockResponse> create(@RequestBody @Valid StockRequest stockRequest) {
     return ResponseEntity.status(HttpStatus.CREATED)
       .body(tradeService.create(stockRequest));
  }

  /**
   * Endpoint to fetch  all Stocks or filtered based on params
   * @param type
   * @param userId
   * @return
   */
  @GetMapping("/trades")

  public ResponseEntity<List<StockResponse>> fetchStockTrade(@RequestParam Optional<String> type, @RequestParam Optional<Integer> userId) {

    List<StockResponse> stocksResponse = tradeService.fetchStockTrade(type, userId);
    if (!(stocksResponse.isEmpty())) {
      return ResponseEntity.status(HttpStatus.OK)
        .body(stocksResponse);
    } else {
      return ResponseEntity.ok(stocksResponse);
    }
  }

  /**
   * Endpoint to fetch  all Stocks based on Id
   * @param id
   * @return
   */

    @GetMapping("/trades/{id}")

    public ResponseEntity<StockResponse> fetchStockTradeById(@PathVariable Integer id) {

      StockResponse stockTrade = tradeService.fetchStockTradeById(id);
      if(!(stockTrade==null))
      {
        return ResponseEntity.status(HttpStatus.OK)
          .body(stockTrade);
      }
      else {
        return ResponseEntity.notFound().build();
      }

  }


}