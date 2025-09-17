package site.what2eat.meal.global.jsoup;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import site.what2eat.meal.global.utils.SslUtil;

@Slf4j
@Service
@RequiredArgsConstructor
public class MealPlanFetcher {

    private final Connection mealPlanCrawlerJsoupConnection;

    public Document fetchDocument() throws Exception {
        // 경기푸른미래관 인증서 이슈
        SslUtil.trustAllSslCertificates();
        return mealPlanCrawlerJsoupConnection.get();
    }
}
