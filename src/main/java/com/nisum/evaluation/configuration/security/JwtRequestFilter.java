package com.nisum.evaluation.configuration.security;

import com.nisum.evaluation.service.JwtTokenService;
import com.nisum.evaluation.service.JwtUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUserDetailsService jwtUserDetailsService;
    private final JwtTokenService jwtTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final var requestTokenHeader = request.getHeader("Authorization");

        if (StringUtils.startsWith(requestTokenHeader,"Bearer")) {
            var jwtToken = extractJwtToken(requestTokenHeader);

            try {
                var usernameOpt = jwtTokenService.getUsernameFromToken(jwtToken);
                var authentication = SecurityContextHolder.getContext().getAuthentication();

                if (usernameOpt.isPresent() && (authentication == null)) {

                    var userDetails = jwtUserDetailsService.loadUserByUsername(usernameOpt.get());

                    if (jwtTokenService.validateToken(jwtToken, userDetails.getUsername())) {

                        var usernamePasswordAuthenticationToken =
                                getAuthenticationToken(request, userDetails, userDetails.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
                }
            } catch (IllegalArgumentException e) {
                logger.error("Unable to fetch JWT Token");
            } catch (ExpiredJwtException e) {
                logger.error("JWT Token is expired");
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
        }
        chain.doFilter(request, response);
    }

    private String extractJwtToken(String requestTokenHeader) {
        return requestTokenHeader.substring(7);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request,
                                                                       UserDetails userDetails,
                                                                       Collection<? extends GrantedAuthority> authorities) {
        var usernamePasswordAuthenticationToken = new
                UsernamePasswordAuthenticationToken(userDetails, null, authorities);
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return usernamePasswordAuthenticationToken;
    }

}