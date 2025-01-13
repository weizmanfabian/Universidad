package weiz.code.Universidad.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Universidad API", version = "1.0", description = "Documentación para endpoints de la API de Universidad")
)
public class OpenApiConfig {
}
