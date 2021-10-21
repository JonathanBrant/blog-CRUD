package com.DigitalHouse.blog.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity //primeira annotation informando que aqui usamos spring security
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired //injeção de dependencia de segurança nessa classe - spring security
    private UserDetailsService userDetailsService;

    //gerenciar a autorização de acesso
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService);
    }

    @Bean //tipo de encriptação de dados ou de senha
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //Configuração mesmo - olhando as requisições http ou simplesmente url's
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/usuario/logar").permitAll()
                .antMatchers("/usuario/cadastrar").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().cors()
                .and().csrf().disable();
    }

    //comentarios
  /*
        //o tipo de politica de sessão é STATELESS, não guarda sessão
        //vamos utilizar o basic para fazer o token padrão
        //vamos desabilitar porque
//CORS - > O CORS deve ser processado antes do Spring Security porque a solicitação
// pré-voo não conterá nenhum cookie (ou seja, o JSESSIONID). Se a solicitação não contiver
// nenhum cookie e o Spring Security for o primeiro, a solicitação determinará que o usuário
// não está autenticado (já que não há cookies na solicitação) e a rejeitará.

    }
//Cross-site request forgery (CSRF) é um tipo de ataque ao qual nossa aplicação estava vulnerável
// pelo menos até adicionarmos o Spring Security nela. Portanto, é graças à proteção que o
// Spring Security passou a oferecer contra esse tipo de ataque que essa vulnerabilidade
// passou a ser nosso problema também.
*/

}
