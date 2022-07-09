/*package pe.edu.utp.yanapaapp.utils;

import android.util.Log;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class Jwt {
    public JwtModel decodeToken (String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret"); //use more secure key
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            return (JwtModel) jwt;
        }catch (JWTVerificationException exception){
            Log.d("exception",exception.getMessage());
        }
        return null;
    }
}*/