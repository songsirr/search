package com.mj.search.external.kakao.model;

import com.google.gson.JsonObject;

public class BlogResult extends AbstractKakaoModel{

    private final String title;

    private final String contents;

    private final String url;

    private final String blogname;

    private final String thumbnail;

    private final String datetime;

    private BlogResult(final Builder builder) {
        super(builder);

        this.title = builder.title;
        this.contents = builder.contents;
        this.url = builder.url;
        this.blogname = builder.blogname;
        this.thumbnail = builder.thumbnail;
        this.datetime = builder.datetime;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public String getUrl() {
        return url;
    }

    public String getBlogname() {
        return blogname;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getDatetime() {
        return datetime;
    }

    @Override
    public String toString() {
        return "KaKaoBlogSearchModel{" +
                "title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", url='" + url + '\'' +
                ", blogname='" + blogname + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", datetime=" + datetime +
                '}';
    }

    @Override
    public Builder builder() {return new Builder();}

    public static final class Builder extends AbstractKakaoModel.Builder {
        private String title;
        private String contents;
        private String url;
        private String blogname;
        private String thumbnail;
        private String datetime;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContents(String contents) {
            this.contents = contents;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setBlogname(String blogname) {
            this.blogname = blogname;
            return this;
        }

        public Builder setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
            return this;
        }

        public Builder setDatetime(String datetime) {
            this.datetime = datetime;
            return this;
        }

        @Override
        public BlogResult build() {
            return new BlogResult(this);
        }
    }

    public static final class JsonUtil extends AbstractKakaoModel.JsonUtil<BlogResult> {
        public BlogResult createModelObject(JsonObject jsonObject) {
            if (jsonObject == null || jsonObject.isJsonNull()) {
                return null;
            }

            return new BlogResult.Builder()
                    .setTitle(
                            hasAndNotNull(jsonObject, "title")
                            ? jsonObject.get("title").getAsString()
                            : null
                    )
                    .setContents(
                            hasAndNotNull(jsonObject, "contents")
                            ? jsonObject.get("contents").getAsString()
                            : null
                    )
                    .setUrl(
                            hasAndNotNull(jsonObject, "url")
                            ? jsonObject.get("url").getAsString()
                            : null
                    )
                    .setBlogname(
                            hasAndNotNull(jsonObject, "blogname")
                            ? jsonObject.get("blogname").getAsString()
                            : null
                    )
                    .setThumbnail(
                            hasAndNotNull(jsonObject, "thumbnail")
                            ? jsonObject.get("thumbnail").getAsString()
                            : null
                    )
                    .setDatetime(
                            hasAndNotNull(jsonObject, "datetime")
                            ? jsonObject.get("datetime").getAsString()
                            : null
                    )
                    .build();
        }
    }
}
