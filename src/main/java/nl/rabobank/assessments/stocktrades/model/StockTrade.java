package nl.rabobank.assessments.stocktrades.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity Class for persisting data
 */

@Entity
@NoArgsConstructor
@Data
@Table(name = "stockInfo")
public class StockTrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String type;
    private Integer userId;
    private String symbol;
    @Min(1)
    @Max(100)
    private Integer shares;
    private Integer price;
    private Long timestamp;

    public StockTrade(String type, Integer userId, String symbol, Integer shares, Integer price, Long timestamp) {
        this.type = type;
        this.userId = userId;
        this.symbol = symbol;
        this.shares = shares;
        this.price = price;
        this.timestamp = timestamp;
    }


}
