package nl.rabobank.assessments.stocktrades.services;

import nl.rabobank.assessments.stocktrades.dto.StockRequest;
import nl.rabobank.assessments.stocktrades.dto.StockResponse;
import nl.rabobank.assessments.stocktrades.exceptions.StockNotFountException;
import nl.rabobank.assessments.stocktrades.mappers.StockMappers;
import nl.rabobank.assessments.stocktrades.model.StockTrade;
import nl.rabobank.assessments.stocktrades.repository.StockTradeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TradeServiceImplTest {

  @InjectMocks
  private TradeServiceImpl tradeService;
  @Mock
  private StockTradeRepository stockTradeRepository;
  @Mock
  private StockRequest stockRequest;
  @Mock
  private StockMappers stockMappers;
  @Mock
  private StockTrade stockTrade;
  @Mock
  private StockResponse stockResponse;

  @Test
  void createTest() {
    when(stockMappers.convertToEntity(stockRequest)).thenReturn(stockTrade);
    when(stockMappers.convertToDto(stockTrade)).thenReturn(stockResponse);
    when(stockTradeRepository.save(stockTrade)).thenReturn(stockTrade);
    when(stockResponse.id()).thenReturn(1);
    assertEquals(1, tradeService.create(stockRequest).id());

  }

  @Test
  void fetchStockTradeTestFetchAllConditions() {
    when(stockMappers.convertToListDto(any())).thenReturn(List.of(stockResponse));
    when(stockTradeRepository.findAll()).thenReturn(List.of(stockTrade));
    when(stockTradeRepository.findByTypeAndUserId("buy",123)).thenReturn(List.of(stockTrade));
    when(stockTradeRepository.findByType("buy")).thenReturn(List.of(stockTrade));
    when(stockTradeRepository.findByUserId(123)).thenReturn(List.of(stockTrade));
    assertEquals(1, tradeService.fetchStockTrade(Optional.empty(), Optional.empty()).size());
    assertEquals(1, tradeService.fetchStockTrade(Optional.of("buy"), Optional.of(123)).size());
    assertEquals(1, tradeService.fetchStockTrade(Optional.empty(), Optional.of(123)).size());
    assertEquals(1, tradeService.fetchStockTrade(Optional.of("buy"), Optional.empty()).size());

  }

  @Test
  void  fetchStockById()
  {
    when(stockMappers.convertToDto(stockTrade)).thenReturn(stockResponse);
    when(stockTradeRepository.findById(1)).thenReturn((Optional.of(stockTrade)));
    assertNotNull(tradeService.fetchStockTradeById(1));
  }
  @Test
  void  fetchStockByIdException()
  {

    when(stockTradeRepository.findById(1)).thenReturn((Optional.empty()));
    assertThrows(StockNotFountException.class, () -> tradeService.fetchStockTradeById(1));
  }

}