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

    public String fetchHtml(String symd) throws Exception {
        SslUtil.trustAllSslCertificates();
        Connection conn = mealPlanCrawlerJsoupConnection.newRequest();

        String queryParam = "?symd=";
        if (symd != null) {
            queryParam += symd;
        }
        conn.url(mealPlanCrawlerJsoupConnection.request().url() + queryParam);

        Document doc = conn.get();

        return doc.html();
    }
}
