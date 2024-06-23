package demo.com.sb_final_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import demo.com.sb_final_project.entity.StockListEntity;

@Repository
public interface StockListReposiotry extends JpaRepository<StockListEntity,String>{
  
}
