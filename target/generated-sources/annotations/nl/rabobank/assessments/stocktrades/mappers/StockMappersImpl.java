package nl.rabobank.assessments.stocktrades.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import nl.rabobank.assessments.stocktrades.dto.StockRequest;
import nl.rabobank.assessments.stocktrades.dto.StockResponse;
import nl.rabobank.assessments.stocktrades.model.StockTrade;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-03T13:18:42+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (Homebrew)"
)
@Component
public class StockMappersImpl implements StockMappers {

    @Override
    public StockTrade convertToEntity(StockRequest request) {
        if ( request == null ) {
            return null;
        }

        StockTrade stockTrade = new StockTrade();

        stockTrade.setType( request.type() );
        stockTrade.setUserId( request.userId() );
        stockTrade.setSymbol( request.symbol() );
        stockTrade.setShares( request.shares() );
        stockTrade.setPrice( request.price() );
        stockTrade.setTimestamp( request.timestamp() );

        return stockTrade;
    }

    @Override
    public List<StockResponse> convertToListDto(List<StockTrade> stockTrades) {
        if ( stockTrades == null ) {
            return null;
        }

        List<StockResponse> list = new ArrayList<StockResponse>( stockTrades.size() );
        for ( StockTrade stockTrade : stockTrades ) {
            list.add( convertToDto( stockTrade ) );
        }

        return list;
    }

    @Override
    public StockResponse convertToDto(StockTrade stockTrade) {
        if ( stockTrade == null ) {
            return null;
        }

        Integer id = null;
        String type = null;
        Integer userId = null;
        String symbol = null;
        Integer shares = null;
        Integer price = null;
        Long timestamp = null;

        id = stockTrade.getId();
        type = stockTrade.getType();
        userId = stockTrade.getUserId();
        symbol = stockTrade.getSymbol();
        shares = stockTrade.getShares();
        price = stockTrade.getPrice();
        timestamp = stockTrade.getTimestamp();

        StockResponse stockResponse = new StockResponse( id, type, userId, symbol, shares, price, timestamp );

        return stockResponse;
    }
}
