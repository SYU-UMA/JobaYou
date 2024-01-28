package com.jobayour.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;

// 토큰을 생성하고 검증하는 클래스입니다.
// 해당 컴포넌트는 필터클래스에서 사전 검증을 거칩니다.
@Component
@Slf4j
public class JwtTokenProvider {
    private String secretKey = "jobayourkey";
    private final UserDetailsService userDetailsService;
    private long tokenValidTime = 300 * 60 * 1000L; //30분

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }
    // 생성자 주입을 통한 UserDetailsService 설정
    public JwtTokenProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    //레디스에 리프레쉬 토큰 저장
    private void saveRefreshToken(String userId, String refreshToken) {
        redisTemplate.opsForValue().set(userId, refreshToken, tokenValidTime * 2, TimeUnit.MILLISECONDS);
    }

    // 레디스에서 리프레쉬토큰
    public String getRefreshToken(String userId) {
        return (String) redisTemplate.opsForValue().get(userId);
    }

    // JWT 토큰 생성
    public Map<String, String> createToken(String userId, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("roles", roles);
        Date now = new Date();
        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        String refreshToken = Jwts.builder()
                .setSubject(userId)
                .setExpiration(new Date(now.getTime() + tokenValidTime * 2))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        saveRefreshToken(userId, refreshToken);

        Map<String, String> token = new HashMap<>();
        token.put("accessToken", accessToken);
        token.put("refreshToken", refreshToken);

        return token;
    }

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserId(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에서 회원 정보 추출
    public String getUserId(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // Request의 Header에서 token 값을 가져옵니다. "Authorization" : "TOKEN값'
    public String resolveToken(HttpServletRequest request) {
        // Request의 Header에서 "Authorization" 값을 가져옵니다.
        String token = request.getHeader("Authorization");
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7); // "Bearer " 접두사를 제거합니다.
        }
        return null;
    }

    // 위의 조건을 만족하지 않으면 null을 반환합니다.

    //리프레쉬 토큰 삭제
    public void deleteRefreshToken(String userId) {
        String refreshToken = getRefreshToken(userId);
        if (refreshToken != null) {
            addToBlacklist(refreshToken); // 리프레시 토큰을 블랙리스트에 추가
            redisTemplate.delete(userId);
        }
    }
    public void addToBlacklist(String token) { //레디스에 토큰 밴 먹임
        redisTemplate.opsForValue().set("blacklist:" + token, true, tokenValidTime, TimeUnit.MILLISECONDS);
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String token) {
       try {
           Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

           return !claims.getBody().getExpiration().before(new Date());
       }catch (SignatureException e) {
           log.info("SignatureException");
           throw new JwtException(ErrorMessage.WRONG_TYPE_TOKEN.getMsg());
       } catch (MalformedJwtException e) {
           log.info("MalformedJwtException");
           throw new JwtException(ErrorMessage.UNSUPPORTED_TOKEN.getMsg());
       } catch (ExpiredJwtException e) {
           log.info("ExpiredJwtException");
           throw new JwtException(ErrorMessage.EXPIRED_TOKEN.getMsg());
       } catch (IllegalArgumentException e) {
           log.info("IllegalArgumentException");
           throw new JwtException(ErrorMessage.UNKNOWN_ERROR.getMsg());
       }
    }
}