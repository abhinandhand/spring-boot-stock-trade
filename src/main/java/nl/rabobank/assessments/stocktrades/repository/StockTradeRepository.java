package nl.rabobank.assessments.stocktrades.repository;

import nl.rabobank.assessments.stocktrades.model.StockTrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockTradeRepository extends JpaRepository<StockTrade, Integer> {

  List<StockTrade> findByTypeAndUserId(String s, Integer integer);

  List<StockTrade> findByType(String type);

  List<StockTrade> findByUserId(Integer integer);
}
