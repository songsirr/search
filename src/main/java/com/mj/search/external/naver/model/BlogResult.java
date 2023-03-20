package com.mj.search.external.naver.model;

import com.google.gson.JsonObject;

public class BlogResult extends AbstractNaverModel {

    private final String title;

    private final String link;

    private final String description;

    private final String bloggername;

    private final String bloggerlink;

    private final String postdate;

    private BlogResult(final Builder builder) {
        super(builder);

        this.title = builder.title;
        this.link = builder.link;
        this.description = builder.description;
        this.bloggername = builder.bloggername;
        this.bloggerlink = builder.bloggerlink;
        this.postdate = builder.postdate;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getBloggername() {
        return bloggername;
    }

    public String getBloggerlink() {
        return bloggerlink;
    }

    public String getPostdate() {
        return postdate;
    }

    @Override
    public String toString() {
        return "BlogResult{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", bloggername='" + bloggername + '\'' +
                ", bloggerlink='" + bloggerlink + '\'' +
                ", postdate='" + postdate + '\'' +
                '}';
    }

    @Override
    public Builder builder() {return new Builder();}

    public static final class Builder extends AbstractNaverModel.Builder {
        private String title;
        private String link;
        private String description;
        private String bloggername;
        private String bloggerlink;
        private String postdate;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setLink(String link) {
            this.link = link;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setBloggername(String bloggername) {
            this.bloggername = bloggername;
            return this;
        }

        public Builder setBloggerlink(String bloggerlink) {
            this.bloggerlink = bloggerlink;
            return this;
        }

        public Builder setPostdate(String postdate) {
            this.postdate = postdate;
            return this;
        }

        @Override
        public BlogResult build() {
            return new BlogResult(this);
        }
    }

    public static final class JsonUtil extends AbstractNaverModel.JsonUtil<BlogResult> {
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
                    .setDescription(
                            hasAndNotNull(jsonObject, "description")
                            ? jsonObject.get("description").getAsString()
                            : null
                    )
                    .setLink(
                            hasAndNotNull(jsonObject, "link")
                            ? jsonObject.get("link").getAsString()
                            : null
                    )
                    .setBloggername(
                            hasAndNotNull(jsonObject, "bloggername")
                            ? jsonObject.get("bloggername").getAsString()
                            : null
                    )
                    .setBloggerlink(
                            hasAndNotNull(jsonObject, "bloggerlink")
                            ? jsonObject.get("bloggerlink").getAsString()
                            : null
                    )
                    .setPostdate(
                            hasAndNotNull(jsonObject, "postdate")
                            ? jsonObject.get("postdate").getAsString()
                            : null
                    )
                    .build();
        }
    }
}
