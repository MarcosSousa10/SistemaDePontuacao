package com.br.SistemaDePontuacao.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
        @Autowired
        SecurityFilter securityFilter;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
                return httpSecurity
                                .csrf().and().cors().and().csrf().disable()
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authorizeHttpRequests(authorize -> authorize
                                                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                                                .requestMatchers(HttpMethod.GET,
                                                                "/pc/Salvar/{email}/{senha}/{celular}/{descricao}/{uf}/{dtnasc}/{rg_ie}/{fone}/{profissao}/"
                                                                                +
                                                                                "{bairro}/{cep}/{cidade}/{endereco}/{cnpj}/{aceite_campanha}")
                                                .permitAll()
                                                .requestMatchers(HttpMethod.PUT,
                                                                "/pc/teste/{id}")
                                                .permitAll()
                                                .requestMatchers(HttpMethod.GET,
                                                                "/pc/validar/{cnpj}")
                                                .permitAll()
                                                .requestMatchers(HttpMethod.GET,
                                                                "/images/{id}")
                                                .permitAll()
                                                .requestMatchers(HttpMethod.GET,
                                                                "/images1/{id}")
                                                .permitAll()
                                                .requestMatchers(HttpMethod.GET,
                                                                "/images2/{id}")
                                                .permitAll()
                                                .requestMatchers(HttpMethod.GET,
                                                                "/images3/{id}")
                                                .permitAll()
                                                .requestMatchers(HttpMethod.GET,
                                                                "/imagemCarrossel/{id}")
                                                .permitAll()
                                                .requestMatchers(HttpMethod.GET,
                                                                "/pc/senha/{email}")
                                                .permitAll()
                                                .requestMatchers(HttpMethod.POST,
                                                                "/email")
                                                .permitAll()
                                                .requestMatchers(HttpMethod.GET, "/pc/updatedtinicio/{dtinicio}")
                                                .hasRole("ADMIN")
                                                .requestMatchers(HttpMethod.PUT, "/pc/EditarCampanha/{id}")
                                                .hasRole("ADMIN")
                                                .requestMatchers(HttpMethod.POST, "/pc/SalvarCampanha")
                                                .hasRole("ADMIN")
                                                .requestMatchers(HttpMethod.PUT, "/pc/EditarSaibamais/{id}")
                                                .hasRole("ADMIN")
                                                .requestMatchers(HttpMethod.POST, "/pc/SalvarSaibamais")
                                                .hasRole("ADMIN")
                                                .requestMatchers(HttpMethod.PUT, "/pc/EditarSobre/{id}")
                                                .hasRole("ADMIN")
                                                .requestMatchers(HttpMethod.POST, "/pc/SalvarSobre")
                                                .hasRole("ADMIN")
                                                .requestMatchers(HttpMethod.GET, "/pc/update/{dtfim}").hasRole("ADMIN")
                                                .requestMatchers(HttpMethod.GET, "/pc/select").hasRole("ADMIN")
                                                .requestMatchers(HttpMethod.POST, "/pc/set").hasRole("ADMIN")
                                                .requestMatchers(HttpMethod.GET, "/pc/informacaofitrocnpj/{cnpj}")
                                                .hasRole("ADMIN")
                                                .requestMatchers(HttpMethod.GET,
                                                                "/pc/updatefatordivisao/{fatordivisao}")
                                                .hasRole("ADMIN")
                                                .requestMatchers(HttpMethod.GET, "/pc/tudo").hasRole("ADMIN")
                                                .anyRequest().authenticated())
                                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                                .build();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
                        throws Exception {
                return authenticationConfiguration.getAuthenticationManager();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}
