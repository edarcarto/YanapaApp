package pe.edu.utp.yanapaapp.net;

import okhttp3.RequestBody;
import pe.edu.utp.yanapaapp.net.response.Token;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ServicesRetrofit {
    @Multipart
    @POST("/yanapay-backend/oauth/token")
    Call<Token> postToken(@Part("grant_type") RequestBody grantType,
                         @Part("username") RequestBody username,
                         @Part("password") RequestBody password);
}
