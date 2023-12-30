package com.cafeteria.cafeteria.Aspects;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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

    @Around("@annotation(ValidateTokenAdmin)")
    public void validateTokenBeforeMethodExecution(ProceedingJoinPoint jointPoint) {
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
    }

    @Around("@annotation(ValidateTokenUser)")
    public void validateTokenBeforeMethodExecutionUser(ProceedingJoinPoint jointPoint) {
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
        if (!jwtTokenUtil.validateTokenAdmin(token)) {
            throw new RuntimeException("Invalid token");
        }
    }
}
