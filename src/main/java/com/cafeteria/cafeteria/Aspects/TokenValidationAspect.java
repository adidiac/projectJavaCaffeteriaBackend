package com.cafeteria.cafeteria.Aspects;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.cafeteria.cafeteria.Utils.JwtTokenUtil;
import com.cafeteria.cafeteria.ViewModels.UserAuthModel;

@Aspect
@Component
public class TokenValidationAspect {

    private final JwtTokenUtil jwtTokenUtil;
    public TokenValidationAspect(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Before("@annotation(ValidateTokenAdmin)")
    public void validateTokenBeforeMethodExecution(JoinPoint jointPoint) {
        // Get token from the request header or wherever it's present
        // String token = request.getHeader("Authorization");
        Object[] args = jointPoint.getArgs();
        String token = "";
        for (Object arg : args) {
            if (arg instanceof String) {
                token = (String) arg;
            }
        }

        token  = token.substring(7);

        // Validate token
        if (!jwtTokenUtil.validateTokenAdmin(token)) {
            throw new RuntimeException("Invalid token");
        }

       //  return jointPoint.proceed();
    }

    @Before("@annotation(ValidateTokenUser)")
    public void validateTokenBeforeMethodExecutionUser(JoinPoint jointPoint) {
        // Get token from the request header or wherever it's present
        // String token = request.getHeader("Authorization");
        Object[] args = jointPoint.getArgs();
        String token = "";
        UserAuthModel user = null;
        for (Object arg : args) {
            if (arg instanceof String) {
                token = (String) arg;
            }
        }
        for (Object arg : args) {
            if (arg instanceof UserAuthModel) {
                user = (UserAuthModel) arg;
            }
        }

        if(user == null){
            throw new RuntimeException("Invalid token");
        }

        token  = token.substring(7);

        if (!jwtTokenUtil.getUsernameUser(token).equals(user.username)) {
            throw new RuntimeException("Invalid token");
        }

        // Validate token
        if (!jwtTokenUtil.validateTokenUser(token)) {
            throw new RuntimeException("Invalid token");
        }

       // return jointPoint.proceed();
    }
}
