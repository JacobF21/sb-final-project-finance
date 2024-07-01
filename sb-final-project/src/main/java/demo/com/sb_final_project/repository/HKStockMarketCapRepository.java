package demo.com.sb_final_project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import demo.com.sb_final_project.entity.HKStockMarketCapEntity;

@Repository
public interface HKStockMarketCapRepository extends JpaRepository<HKStockMarketCapEntity,String>{
  List<HKStockMarketCapEntity> findTop10ByOrderByMarketCapDesc();
}
