package com.andylai.retrofitsample;

import androidx.annotation.Nullable;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

// if apiservice function return type is String, adapt Call<String> to String
public class StringCallAdapterFactory extends CallAdapter.Factory {
    @Nullable
    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        if (returnType == String.class) return new StringCallAdapter();
        return null;
    }

    class StringCallAdapter implements CallAdapter<JsonObject, String> {

        @Override
        public Type responseType() {
            return String.class;
        }

        @Override
        public String adapt(Call<JsonObject> call) {
            try {
                return call.execute().body().toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }
    }
}
