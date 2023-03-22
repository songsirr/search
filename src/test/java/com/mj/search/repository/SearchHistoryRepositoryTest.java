package com.mj.search.repository;

import com.mj.search.entity.SearchHistory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestConstructor;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class SearchHistoryRepositoryTest {

    private final SearchHistoryRepository searchHistoryRepository;

    public SearchHistoryRepositoryTest(SearchHistoryRepository searchHistoryRepository) {
        this.searchHistoryRepository = searchHistoryRepository;
    }

    @Test
    void save(){
        // given
        SearchHistory searchHistory = SearchHistory.builder()
                .keyword("안녕하세요")
                .hit(1)
                .build();

        // when
        searchHistoryRepository.save(searchHistory);

        // then
        SearchHistory s = searchHistoryRepository.findByKeyword(searchHistory.getKeyword());
        Assertions.assertNotNull(s);
    }

    @Test
    void get(){
        // given
        SearchHistory searchHistory = SearchHistory.builder()
                .keyword("안녕하세요")
                .hit(1)
                .build();

        // when
        searchHistoryRepository.save(searchHistory);

        //then
        SearchHistory s = searchHistoryRepository.findByKeyword(searchHistory.getKeyword());
        Assertions.assertEquals(s.getKeyword(), searchHistory.getKeyword());
        Assertions.assertEquals(s.getHit(), 1);
    }
}
