package site.what2eat.meal.global.jsoup;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import site.what2eat.meal.global.utils.SslUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@RequiredArgsConstructor
public class MealPlanFetcher {

//    private final Connection mealPlanCrawlerJsoupConnection;
    public static final int MAX_RETRY_TIMES = 5;
    public static final String BASE_URL = "https://www.gbfh.co.kr/0206/cafeteria/menu/w2/";

    public Document fetchDocument(LocalDate date) throws Exception {
        // 경기푸른미래관 인증서 이슈
        SslUtil.trustAllSslCertificates();

        String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        for (int i = 1; i <= MAX_RETRY_TIMES; i++) {
            try {
                log.info("[MealPLanFetcher] 식단표 가져오기 {}회 시도", i);
                return Jsoup.connect(BASE_URL)
                        .data("symd", formattedDate)
                        .timeout(30_000)
                        .get();
//                log.info("uri={}", con.request().url().toString());
            } catch (Exception e) {
                if (i == MAX_RETRY_TIMES) {
                    log.error("[MealPlanFetcher] 식단표를 가져올 수 없습니다.");
                    throw e;
                }
                log.warn("[MealPlanFetcher] 가져오기 실패, 10초 후 재시도...");
                Thread.sleep(10000);
            }
        }
        return null;
    }
}
