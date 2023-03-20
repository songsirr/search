package com.mj.search.external;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public interface IModel {

    Builder builder();

    @JsonPOJOBuilder(withPrefix = "set")
    interface Builder {

        IModel build();
    }

    interface IJsonUtil<T> {

        boolean hasAndNotNull(final JsonObject jsonObject, final String memberName);

        T createModelObject(final JsonObject jsonObject);

        T createModelObject(final String json);

        T[] createModelObjectArray(final JsonArray jsonArray);

        T[] createModelObjectArray(final String json);

        T[] createModelObjectArray(final String json, final String key);

        <X> X[] createModelObjectArray(final JsonArray jsonArray, final Class<X> clazz);
    }
}
