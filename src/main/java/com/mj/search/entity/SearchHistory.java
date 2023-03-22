package com.mj.search.entity;

import com.mj.search.dto.HotKeywordDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "search_history")
@Getter
@Slf4j
@DynamicInsert
@DynamicUpdate
public class SearchHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String keyword;

    private Integer hit;

    @CreatedDate
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    public void updateHit(){
        this.hit++;
    }

    @Builder
    public SearchHistory(Long id, String keyword, Integer hit, Date updatedAt) {
        this.id = id;
        this.keyword = keyword;
        this.hit = hit;
        this.updatedAt = updatedAt;
    }

    public HotKeywordDto toDto(){

        return HotKeywordDto.builder()
                .keyword(this.keyword)
                .count(this.hit)
                .build();
    }
}
