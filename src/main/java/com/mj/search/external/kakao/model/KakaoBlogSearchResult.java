package com.mj.search.external.kakao.model;

import com.google.gson.JsonObject;

public class KakaoBlogSearchResult extends AbstractKakaoModel {

    private final BlogResult[] blogs;

    private final SearchMeta searchMeta;

    private KakaoBlogSearchResult(final Builder builder) {
        super(builder);
        this.blogs = builder.blogs;
        this.searchMeta = builder.searchMeta;
    }

    public BlogResult[] getBlogs() {return blogs;}

    public SearchMeta getMeta() {return searchMeta;}

    @Override
    public Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "KakaoBlogSearchResult{" +
                "blogs=" + blogs +
                "meta=" + searchMeta +
                '}';
    }

    public static final class Builder extends AbstractKakaoModel.Builder {
        private BlogResult[] blogs;

        private SearchMeta searchMeta;

        public Builder setBlogs(BlogResult[] blogs) {
            this.blogs = blogs;
            return this;
        }

        public Builder setMeta(SearchMeta searchMeta) {
            this.searchMeta = searchMeta;
            return this;
        }

        @Override
        public KakaoBlogSearchResult build() {
            return new KakaoBlogSearchResult(this);
        }
    }

    public static final class JsonUtil extends AbstractKakaoModel.JsonUtil<KakaoBlogSearchResult> {
        public KakaoBlogSearchResult createModelObject(JsonObject jsonObject) {
            if (jsonObject == null || jsonObject.isJsonNull()) {
                return null;
            }

            return new KakaoBlogSearchResult.Builder()
                    .setBlogs(
                        hasAndNotNull(jsonObject, "documents")
                            ? new BlogResult.JsonUtil().createModelObjectArray(
                            jsonObject.getAsJsonArray("documents"))
                            : null)
                    .setMeta(
                        hasAndNotNull(jsonObject, "meta")
                            ? new SearchMeta.JsonUtil().createModelObject(
                            jsonObject.getAsJsonObject("meta"))
                            : null)
                    .build();
        }
    }
}
