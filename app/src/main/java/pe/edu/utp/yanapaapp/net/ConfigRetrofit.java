package pe.edu.utp.yanapaapp.net;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import pe.edu.utp.yanapaapp.utils.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigRetrofit {
    public static Retrofit getConfig(){
        return new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit getConfigBasicAuth(OkHttpClient client, Gson gson){
        return new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
