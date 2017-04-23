package us.freeenergy.util;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.joda.time.DateTime;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil 
{
	private static final JWSHeader JWT_HEADER = new JWSHeader(JWSAlgorithm.HS256);
	
	public static String generateToken(String signingkey, String username, String host, Map<String, Object> tokenParam) throws JOSEException
	{
		long time = System.currentTimeMillis();
		Date now = new Date(time);
		
		Date expDate = DateTime.now().plusDays(365 * 100).toDate();
				 
		/*JWTClaimsSet.Builder claimBuilder = new JWTClaimsSet.Builder();
        claimBuilder.expirationTime(expDate);
        claimBuilder.issueTime(now);
        claimBuilder.issuer(host);

        for (String key : tokenParam.keySet()) {
            claimBuilder.claim(key, tokenParam.get(key));
        }

        claimBuilder.subject(username);
        JWTClaimsSet claimsSet = claimBuilder.build();
        JWSSigner signer = new MACSigner(signingkey);
        SignedJWT jwt = new SignedJWT(JWT_HEADER, claimsSet);
        jwt.sign(signer);

        return jwt.serialize();*/
		
		JwtBuilder builder = Jwts.builder()
					.setSubject(username)
					.setIssuedAt(now)
					.setIssuer(host)
					.setExpiration(expDate)
					.claim("X-Name-Id", tokenParam.get("X-Name-Id"))
					.claim("X-Email-Id", tokenParam.get("X-Email-Id"))
					.signWith(SignatureAlgorithm.HS256, signingkey);
		
		return builder.compact();
	}
	
	/***Decode token section****/

	
	/*public static JWTClaimsSet decodeToken(HttpServletRequest httpServletRequest, String jwtTokenCookieName, String signingKey) throws ParseException, JOSEException 
	{
		String token = CookieUtil.getValue(httpServletRequest, jwtTokenCookieName);
		if (token == null){
			System.out.println(token);
			return null;
		}
		
		System.out.println("d");
		SignedJWT signedJWT = SignedJWT.parse(token);
		System.out.println("e");
        if (signedJWT.verify(new MACVerifier(signingKey))) {
        	System.out.println("f");
            return signedJWT.getJWTClaimsSet();
        } else {
        	System.out.println("g");
            throw new JOSEException("Signature verification failed");
        }
    }*/
	
	public static Claims decodeToken(HttpServletRequest httpServletRequest, String jwtTokenCookieName, String signingKey)
	{
		String token = CookieUtil.getValue(httpServletRequest, jwtTokenCookieName);
		if (token == null)
			return null;
		
		return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody();
	}
}
