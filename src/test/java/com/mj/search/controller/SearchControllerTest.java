package com.mj.search.controller;

import com.mj.search.external.error.KakaoErrorCode;
import com.mj.search.external.exception.ExternalSearchServiceException;
import com.mj.search.external.exception.KakaoExternalSearchServiceException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class SearchControllerTest {

    @Autowired
    SearchController searchController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(searchController).build();
    }

    @Test
    void defaultParamTest() throws Exception {
        mockMvc.perform(get("/api/v1/search?query=hello")).andExpect(status().isOk());
    }

    @Test
    void withoutParamTest() {
        Assertions.assertThatThrownBy(() -> mockMvc.perform(get("/api/v1/search")))
                .hasCause(new KakaoExternalSearchServiceException(KakaoErrorCode.KAKAO_BAD_REQUEST, "query parameter required"));
    }

    @Test
    void invalidPageTest() {
        Assertions.assertThatThrownBy(() -> mockMvc.perform(get("/api/v1/search?query=hello&page=-1")))
                .hasCause(new KakaoExternalSearchServiceException(KakaoErrorCode.KAKAO_BAD_REQUEST, "page is less than min"));

        Assertions.assertThatThrownBy(() -> mockMvc.perform(get("/api/v1/search?query=hello&page=9999999")))
                .hasCause(new KakaoExternalSearchServiceException(KakaoErrorCode.KAKAO_BAD_REQUEST, "page is more than max"));
    }

    @Test
    void invalidSizeTest(){
        Assertions.assertThatThrownBy(() -> mockMvc.perform(get("/api/v1/search?query=hello&size=-1")))
                .hasCause(new KakaoExternalSearchServiceException(KakaoErrorCode.KAKAO_BAD_REQUEST, "size is less than min"));

        Assertions.assertThatThrownBy(() -> mockMvc.perform(get("/api/v1/search?query=hello&size=9999999")))
                .hasCause(new KakaoExternalSearchServiceException(KakaoErrorCode.KAKAO_BAD_REQUEST, "size is more than max"));
    }
}
