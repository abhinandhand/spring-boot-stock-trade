package nl.rabobank.assessments.stocktrades.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

/**
 * Request DTO record
 * @param type
 * @param userId
 * @param symbol
 * @param shares
 * @param price
 * @param timestamp
 */
public record StockRequest(@Pattern(regexp = "buy|sell", message = "Valid types are buy or sell")
                           String type,
                           Integer userId,
                           String symbol,
                           @Max(value = 100, message ="Shares should not exceed 100" )
                           @Min(value = 1, message ="Shares should not  be less than 1" )
                           Integer shares,
                           Integer price,
                           Long timestamp) {
}
