package nl.rabobank.assessments.stocktrades.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.rabobank.assessments.stocktrades.dto.StockRequest;

import nl.rabobank.assessments.stocktrades.dto.StockResponse;
import nl.rabobank.assessments.stocktrades.exceptions.StockNotFountException;
import nl.rabobank.assessments.stocktrades.mappers.StockMappers;
import nl.rabobank.assessments.stocktrades.model.StockTrade;
import nl.rabobank.assessments.stocktrades.repository.StockTradeRepository;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class TradeServiceImpl  implements TradeService {

  private final StockTradeRepository stockTradeRepository;
  private final StockMappers stockMappers;


  @Override
  public StockResponse create(StockRequest stockRequest) {
    log.info("Creating stock trade");
    return stockMappers.convertToDto(
      stockTradeRepository.save(
        stockMappers.convertToEntity(stockRequest)));

  }

  @Override
  public List<StockResponse> fetchStockTrade(Optional<String> type, Optional<Integer> userId) {
   if(type.isPresent() && userId.isPresent()) {
     log.info("Fetching stock trade for type {} and user id {}", type.get(), userId.get());
     return stockMappers.convertToListDto(
       stockTradeRepository.findByTypeAndUserId(type.get(), userId.get()));


   } else if (type.isPresent()) {
     log.info("Fetching stock trade for type {}", type.get());
     return stockMappers.convertToListDto(stockTradeRepository.findByType(type.get()));
   }
      else if (userId.isPresent()) {
        log.info("Fetching stock trade for user id {}", userId.get());
     return stockMappers.convertToListDto( stockTradeRepository.findByUserId(userId.get()));
  }
      else {
        log.info("Fetching All stock trade");
     return stockMappers.convertToListDto(stockTradeRepository.findAll().
       stream().
       sorted(Comparator.comparing(StockTrade::getId)).collect(Collectors.toList()));
   }

  }

  @Override
  public StockResponse fetchStockTradeById(Integer id) {
    log.info("Fetching stock trade for id {}", id);
    return stockMappers.convertToDto(
      stockTradeRepository.findById(id)
      .orElseThrow(() -> new StockNotFountException("Trade not found with id: " + id)));
  }
  }

