package br.com.lasa.dcpdesconformidades.config;

import br.com.lasa.dcpdesconformidades.aop.logging.LoggingAspect;

import io.github.jhipster.config.JHipsterConstants;

import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

@Configuration
@EnableAspectJAutoProxy
public class LoggingAspectConfiguration {

    @Bean
    @Profile({ JHipsterConstants.SPRING_PROFILE_DEVELOPMENT, Constants.SPRING_PROFILE_LOCAL })
    public LoggingAspect loggingAspect(Environment env) {
        return new LoggingAspect(env);
    }
}
