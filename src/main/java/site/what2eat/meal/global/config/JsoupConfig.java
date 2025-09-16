package site.what2eat.meal.global.config;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JsoupConfig {

    @Bean(name = "mealPlanCrawlerJsoupConnection")
    public Connection mealPlanCrawlerJsoupConnection(
            @Value("${spring.meal.base-url}") String baseUrl,
            @Value("${spring.meal.endpoint}") String endPoint
    ) {
        // 기본 요청 틀을 만들어 Bean으로 관리
        return Jsoup.connect(baseUrl + endPoint)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/140.0.0.0 Safari/537.36 Edg/140.0.0.0")
                .referrer(baseUrl + endPoint)
                .timeout(15000)
                .ignoreHttpErrors(true)
                .followRedirects(true);
    }
}
