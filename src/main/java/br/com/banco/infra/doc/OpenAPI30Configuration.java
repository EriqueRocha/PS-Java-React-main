package br.com.banco.infra.doc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "SpringBoot - extrato bancário -  API", version = "${api.version}",
                contact = @Contact(name = "Erique Rocha", email = "eriquebit@gmail.com", url = "https://www.linkedin.com/in/erique-rocha-dev"),
                license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0"), termsOfService = "TOS",
                description = "API para emissão de extrato bancário"),
        servers = {
                @Server(url = "http://localhost:8080/", description = "Development"),
                @Server(url = "http://localhost:8080/", description = "Production")})
public class OpenAPI30Configuration {
/**
 * Configure the OpenAPI components.
 *
 * @return Returns fully configure OpenAPI object
 * @see io.swagger.v3.oas.models.OpenAPI
 */
}