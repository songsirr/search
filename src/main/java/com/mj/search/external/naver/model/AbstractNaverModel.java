package com.mj.search.external.naver.model;

import com.google.gson.*;
import com.mj.search.external.IModel;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;

public abstract class AbstractNaverModel implements IModel {

    protected AbstractNaverModel(final Builder builder) {
        assert (builder != null);
    }

    @Override
    public abstract String toString();

    public static abstract class Builder implements IModel.Builder {
    }

    public static abstract class JsonUtil<T> implements IJsonUtil<T> {

        public boolean hasAndNotNull(final JsonObject jsonObject, final String memberName) {
            return jsonObject.has(memberName) && !jsonObject.get(memberName).isJsonNull();
        }

        public T createModelObject(final String json) {
            if (json == null) {
                return null;
            } else {
                return createModelObject(JsonParser.parseString(json).getAsJsonObject());
            }
        }

        public T[] createModelObjectArray(final JsonArray jsonArray) {
            @SuppressWarnings("unchecked")
            T[] array = (T[]) Array.newInstance((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0], jsonArray.size());

            for (int i = 0; i < jsonArray.size(); i++) {
                JsonElement jsonElement = jsonArray.get(i);

                if (jsonElement instanceof JsonNull) {
                    array[i] = null;
                } else {
                    JsonObject jsonObject = jsonElement.getAsJsonObject();
                    array[i] = createModelObject(jsonObject);
                }
            }

            return array;
        }

        public T[] createModelObjectArray(final String json) {
            return createModelObjectArray(JsonParser.parseString(json).getAsJsonArray());
        }

        public T[] createModelObjectArray(final String json, final String key) {
            return createModelObjectArray(JsonParser.parseString(json).getAsJsonObject().get(key).getAsJsonArray());
        }

        @SuppressWarnings("unchecked")
        public <X> X[] createModelObjectArray(final JsonArray jsonArray, Class<X> clazz) {
            X[] array = (X[]) Array.newInstance(clazz, jsonArray.size());

            for (int i = 0; i < jsonArray.size(); i++) {
                JsonElement jsonElement = jsonArray.get(i);
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                array[i] = (X) createModelObject(jsonObject);
            }

            return array;
        }
    }
}
