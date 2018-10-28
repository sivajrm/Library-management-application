package com.app.library.auth.security;

import com.app.library.auth.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import static com.app.library.auth.Constants.SECRET;

@Component
public class JwtValidator {

    private static final Logger logger = LogManager.getLogger(JwtValidator.class);

    public JwtUser validate(String token) {
        JwtUser jwtUser = null;

        try {
            Claims body = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJwt(token)
                    .getBody();

            jwtUser = new JwtUser();

            jwtUser.setUserName(body.getSubject());
            jwtUser.setId(Long.parseLong((String) body.get("userId")));
            jwtUser.setRole((String) body.get("role"));
        }
        catch (Exception e){
            logger.error("User does not exist");
            logger.error(e);
        }

        return jwtUser;

    }
}
