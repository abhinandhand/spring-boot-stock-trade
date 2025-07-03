package nl.rabobank.assessments.stocktrades.mappers;


import nl.rabobank.assessments.stocktrades.dto.StockRequest;
import nl.rabobank.assessments.stocktrades.dto.StockResponse;
import nl.rabobank.assessments.stocktrades.model.StockTrade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface StockMappers {

  @Mapping(target = "id", ignore = true)
  StockTrade convertToEntity(StockRequest request);

  List<StockResponse> convertToListDto(List<StockTrade> stockTrades);
  StockResponse convertToDto(StockTrade stockTrade);


}
