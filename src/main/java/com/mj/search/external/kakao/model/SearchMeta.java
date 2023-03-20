package com.mj.search.external.kakao.model;

import com.google.gson.JsonObject;

public class SearchMeta extends AbstractKakaoModel {

    private final Integer totalCount;

    private final Integer pageableCount;

    private final Boolean end;

    private SearchMeta(final Builder builder) {
        super(builder);
        this.totalCount = builder.totalCount;
        this.pageableCount = builder.pageableCount;
        this.end = builder.end;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public Integer getPageableCount() {
        return pageableCount;
    }

    public Boolean getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "Meta{" +
                "totalCount=" + totalCount +
                ", pageableCount=" + pageableCount +
                ", end=" + end +
                '}';
    }

    @Override
    public Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractKakaoModel.Builder {
        private Integer totalCount;
        private Integer pageableCount;
        private boolean end;

        public Builder setTotalCount(Integer totalCount) {
            this.totalCount = totalCount;
            return this;
        }

        public Builder setPageableCount(Integer pageableCount) {
            this.pageableCount = pageableCount;
            return this;
        }

        public Builder setEnd(boolean end) {
            this.end = end;
            return this;
        }

        @Override
        public SearchMeta build() {
            return new SearchMeta(this);
        }
    }

    @SuppressWarnings("unchecked")
    public static final class JsonUtil extends AbstractKakaoModel.JsonUtil<SearchMeta> {
        public SearchMeta createModelObject(final JsonObject jsonObject) {
            if (jsonObject == null || jsonObject.isJsonNull()) {
                return null;
            }

            return new SearchMeta.Builder()
                    .setTotalCount(
                            hasAndNotNull(jsonObject, "total_count")
                                    ? jsonObject.get("total_count").getAsInt()
                                    : 0)
                    .setPageableCount(
                            hasAndNotNull(jsonObject, "pageable_count")
                                    ? jsonObject.get("pageable_count").getAsInt()
                                    : 0)
                    .setEnd(
                            hasAndNotNull(jsonObject, "is_end")
                                    ? jsonObject.get("is_end").getAsBoolean()
                                    : true)
                    .build();
        }
    }
}
