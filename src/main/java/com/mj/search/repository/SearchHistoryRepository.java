package com.mj.search.repository;

import com.mj.search.entity.SearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {

    SearchHistory findByKeyword(String keyword);

    List<SearchHistory> findTop10ByOrderByHitDesc();
}
