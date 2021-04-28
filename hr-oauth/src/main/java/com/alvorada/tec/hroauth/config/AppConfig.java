package com.alvorada.tec.hroauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class AppConfig {

    // Codifica o password
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Converte o token, usando minha palavra secreta
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        // Definição da chave secreta que eu vou assinar os meus tokens
        // TEMPORARIAMENTE a chave está hard-code mas será trocado e salvo no arquivo de configurações do git
        tokenConverter.setSigningKey("MY-SECRET-KEY");
        return tokenConverter;
    }

    // Lê as informações do Token
    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }
}
