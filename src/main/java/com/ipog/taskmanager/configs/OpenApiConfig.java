package com.ipog.taskmanager.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI taskManagerOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gerenciamento de Tarefas Pessoais")
                        .description(
                                """
                                   IPOG <br/>
                                   Curso: Análise e Desenvolvimento de Sistema - 3º Período <br/>
                                   Disciplina: Serviços Web e API Rest <br/>
                                   Atividades Processuais 2, 3 e 4. <br/>
                                   Aluno: Helder Alves Tosta
                                """
                        )
                        .version("v1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
