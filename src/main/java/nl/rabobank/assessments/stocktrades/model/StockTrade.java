package nl.rabobank.assessments.stocktrades.model;

public class StockTrade {
    private Integer id;
    private String type;
    private Integer userId;
    private String symbol;
    private Integer shares;
    private Integer price;
    private Long timestamp;

    public StockTrade(String type, Integer userId, String symbol, Integer shares, Integer price, Long timestamp) {
    }

    public Integer getId() {
        return null;
    }

    public String getType() {
        return null;
    }

    public void setType(String type) {
    }

    public void setShares(int shares) {
    }
}
