package jwt;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

public class JwtController {

    private static final SecretKey KEY = Keys.hmacShaKeyFor(
            System.getenv("secret").equals(null) ?
            "nEzar5W(j[tv^X-6.v;Ha(3fr0f!gYO.ND[B0q0--seX8X+Zx-5Jk`Bg&pknH}g".getBytes(StandardCharsets.UTF_8) :
                    System.getenv("secret").getBytes(StandardCharsets.UTF_8));

    public static String generate(String subject){
        return Jwts.builder().setSubject(subject).signWith(JwtController.KEY, SignatureAlgorithm.HS256).compact();
    }

    public static void validate(String token) throws JwtException {
        Jwts.parserBuilder()
                .setSigningKey(JwtController.KEY)
                .build()
                .parseClaimsJws(token);
    }
}
