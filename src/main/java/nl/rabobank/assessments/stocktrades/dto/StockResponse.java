package nl.rabobank.assessments.stocktrades.dto;

/**
 * Response POJO record
 * @param id
 * @param type
 * @param userId
 * @param symbol
 * @param shares
 * @param price
 * @param timestamp
 */
public record StockResponse(Integer id,
                            String type,
                            Integer userId,
                            String symbol,
                            Integer shares,
                            Integer price,
                            Long timestamp )  {

}
