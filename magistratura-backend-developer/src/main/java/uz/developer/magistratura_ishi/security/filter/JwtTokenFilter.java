package uz.developer.magistratura_ishi.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.developer.magistratura_ishi.entity.StudentEntity;
import uz.developer.magistratura_ishi.entity.TeacherEntity;
import uz.developer.magistratura_ishi.model.response.base.ApiResponse;
import uz.developer.magistratura_ishi.service.ApplicationUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private ApplicationUserDetailsService applicationUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        String headerToken = httpServletRequest.getHeader("Authorization");

        if (headerToken == null) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        String token = headerToken.replace("Bearer ", "");
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey("rWHdVPWbqsBDDcQs0PdArBwcZLkH5yykj3l4I")
                    .parseClaimsJws(token).getBody();


            Date expirationDate = claims.getExpiration();

            if (expirationDate.getTime() <= System.currentTimeMillis()) {
                filterChain.doFilter(httpServletRequest, httpServletResponse);
                return;
            }

            String username = claims.getSubject();

            if (username.contains("student")) {
                StudentEntity studentEntity = (StudentEntity) applicationUserDetailsService.loadUserByUsername(username);
                if (Objects.nonNull(studentEntity)) {
                    if (!studentEntity.isEnabled()) {
                        filterChain.doFilter(httpServletRequest, httpServletResponse);
                        return;
                    }

                    Authentication authentication =
                            new UsernamePasswordAuthenticationToken(
                                    username,
                                    null,
                                    studentEntity.getRoles());

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    filterChain.doFilter(httpServletRequest, httpServletResponse);
                }
            } else {
                TeacherEntity teacherEntity = (TeacherEntity) applicationUserDetailsService.loadUserByUsername(username);
                if (Objects.nonNull(teacherEntity)) {
                    if (!teacherEntity.isEnabled()) {
                        filterChain.doFilter(httpServletRequest, httpServletResponse);
                        return;
                    }

                    Authentication authentication =
                            new UsernamePasswordAuthenticationToken(
                                    username,
                                    null,
                                    teacherEntity.getRoles());

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    filterChain.doFilter(httpServletRequest, httpServletResponse);
                }
            }

        } catch (ExpiredJwtException e) {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpServletResponse.setContentType("application/json");
            httpServletResponse.getOutputStream().write(new ObjectMapper().writeValueAsBytes(new ApiResponse("token vaqti o'tib ketdi", false, 401)));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
