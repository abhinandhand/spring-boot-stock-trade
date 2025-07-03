package nl.rabobank.assessments.stocktrades.mappers;

import nl.rabobank.assessments.stocktrades.dto.StockRequest;
import nl.rabobank.assessments.stocktrades.model.StockTrade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StockMappersTest {

  private final StockMappers mapper = Mappers.getMapper(StockMappers.class);
  @Test
  void convertToEntity() {
    StockRequest request = new StockRequest(
      "sell",
      24,
      "AAC",
      12,
      133,
      1511522701000L);
    assertEquals(133, mapper.convertToEntity(request).getPrice());
  }

  @Test
  void convertToListDto()
  {
    StockTrade request = new StockTrade(
      "sell",
      24,
      "AAC",
      12,
      133,
      1511522701000L);
    assertEquals(1, mapper.convertToListDto(List.of(request)).size());

  }
}