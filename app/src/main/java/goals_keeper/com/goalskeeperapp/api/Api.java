package goals_keeper.com.goalskeeperapp.api;

import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import goals_keeper.com.goalskeeperapp.R;
import goals_keeper.com.goalskeeperapp.utils.Constants;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by kady on 15/12/15.
 *
 * @author kady
 */
public class Api {

    public static ApiRoutes publicRoutes(Context context) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        retrofitBuilder.baseUrl(context.getString(R.string.api_base_url));

        retrofitBuilder.addConverterFactory(GsonConverterFactory.create(gson));

        OkHttpClient httpClient = new OkHttpClient();
        httpClient.networkInterceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder().addHeader("Accept", "application/json").build();
                return chain.proceed(request);
            }
        });

        retrofitBuilder.client(httpClient);

        Retrofit retrofit = retrofitBuilder.build();

        return retrofit.create(ApiRoutes.class);

    }

    public static ApiRoutes privateRoutes(final Context context) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        retrofitBuilder.baseUrl(context.getString(R.string.api_base_url));

        retrofitBuilder.addConverterFactory(GsonConverterFactory.create(gson));

        OkHttpClient httpClient = new OkHttpClient();
        httpClient.networkInterceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder().addHeader("Accept", "application/json")
                        .addHeader("Authorization", context.getSharedPreferences(Constants.SHARED_PREFS_KEY, Context.MODE_PRIVATE).getString(Constants.FACEBOOK_TOKEN, null))
                        .build();
                return chain.proceed(request);
            }
        });

        retrofitBuilder.client(httpClient);

        Retrofit retrofit = retrofitBuilder.build();

        return retrofit.create(ApiRoutes.class);

    }


}
