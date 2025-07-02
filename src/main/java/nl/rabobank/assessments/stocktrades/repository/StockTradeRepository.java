package nl.rabobank.assessments.stocktrades.repository;

import nl.rabobank.assessments.stocktrades.model.StockTrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockTradeRepository extends JpaRepository<StockTrade, Integer> {

}
