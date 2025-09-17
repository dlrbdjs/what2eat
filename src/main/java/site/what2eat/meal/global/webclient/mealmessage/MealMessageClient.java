package site.what2eat.meal.global.webclient.mealmessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;


@Service
@RequiredArgsConstructor
@Slf4j
public class MealMessageClient {

    private final RestClient mealMessageRestClient;

    @Value("${spring.message.endpoint}") String endPoint;
    @Value("${spring.message.callback}") String callback;
    @Value("${spring.message.api-key}") String apiKey;

    public String sendMessage(String msg, String dstPhoneNumber) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("api_key", apiKey);
        formData.add("msg", msg);
        formData.add("subject", "[경기푸른미래관 식단표]");
        formData.add("callback", callback);
        formData.add("dstaddr", dstPhoneNumber);
        formData.add("send_reserve", "0");

        String response = mealMessageRestClient.post()
                .uri(endPoint)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(formData)
                .retrieve()
                .body(String.class);

        log.info("Message API Response: {}", response);

        return response;
    }
}
