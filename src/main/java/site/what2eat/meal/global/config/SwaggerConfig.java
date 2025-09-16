package site.what2eat.meal.global.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI What2EatAPI() {
        Info info = new Info()
                .title("What2Eat API")
                .description("This is What2Eat!")
                .version("1.0");

        return new OpenAPI()
                .addServersItem(new Server().url("/"))
                .info(info);
    }
}
