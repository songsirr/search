package com.mj.search.external.naver.model;

import com.google.gson.JsonObject;

import java.util.Arrays;

public class NaverBlogSearchResult extends AbstractNaverModel {

    private final String lastBuildDate;

    private final Integer total;

    private final Integer start;

    private final Integer display;

    private final BlogResult[] items;

    private NaverBlogSearchResult(final Builder builder) {
        super(builder);

        this.lastBuildDate = builder.lastBuildDate;
        this.total = builder.total;
        this.start = builder.start;
        this.display = builder.display;
        this.items = builder.items;
    }

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public Integer getTotal() {
        return total;
    }

    public Integer getStart() {
        return start;
    }

    public Integer getDisplay() {
        return display;
    }

    public BlogResult[] getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "KakaoBlogSearchResult{" +
                "lastBuildDate='" + lastBuildDate + '\'' +
                ", total=" + total +
                ", start=" + start +
                ", display=" + display +
                ", items=" + Arrays.toString(items) +
                '}';
    }

    @Override
    public Builder builder() {return new Builder();}

    public static final class Builder extends AbstractNaverModel.Builder {
        private String lastBuildDate;
        private Integer total;
        private Integer start;
        private Integer display;
        private BlogResult[] items;

        public Builder setLastBuildDate(String lastBuildDate) {
            this.lastBuildDate = lastBuildDate;
            return this;
        }

        public Builder setTotal(Integer total) {
            this.total = total;
            return this;
        }

        public Builder setStart(Integer start) {
            this.start = start;
            return this;
        }

        public Builder setDisplay(Integer display) {
            this.display = display;
            return this;
        }

        public Builder setItems(BlogResult[] items) {
            this.items = items;
            return this;
        }

        @Override
        public NaverBlogSearchResult build() {
            return new NaverBlogSearchResult(this);
        }
    }

    public static final class JsonUtil extends AbstractNaverModel.JsonUtil<NaverBlogSearchResult> {
        public NaverBlogSearchResult createModelObject(JsonObject jsonObject) {
            if (jsonObject == null || jsonObject.isJsonNull()) {
                return null;
            }

            return new NaverBlogSearchResult.Builder()
                    .setLastBuildDate(
                            hasAndNotNull(jsonObject, "lastBuildDate")
                            ? jsonObject.get("lastBuildDate").getAsString()
                            : null
                    )
                    .setStart(
                            hasAndNotNull(jsonObject, "start")
                            ? jsonObject.get("start").getAsInt()
                            : null
                    )
                    .setTotal(
                            hasAndNotNull(jsonObject, "total")
                            ? jsonObject.get("total").getAsInt()
                            : null
                    )
                    .setDisplay(
                            hasAndNotNull(jsonObject, "display")
                            ? jsonObject.get("display").getAsInt()
                            : null
                    )
                    .setItems(
                            hasAndNotNull(jsonObject, "items")
                            ? new BlogResult.JsonUtil().createModelObjectArray(
                            jsonObject.getAsJsonArray("items"))
                            : null
                    )
                    .build();
        }
    }
}
