package site.what2eat.meal.domain.message.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.what2eat.meal.global.jsoup.MealPlanFetcher;
import site.what2eat.meal.global.jsoup.MealPlanParser;
import site.what2eat.meal.global.jsoup.dto.MealPlan;
import site.what2eat.meal.global.webclient.mealmessage.MealMessageClient;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MessageServiceImpl implements MessageService {

    private final MealMessageClient mealMessageClient;
    private final MealPlanFetcher mealPlanFetcher;
    private final MealPlanParser mealPlanParser;

    @Override
    public void sendMessage(String phoneNumber) {
        MealPlan mealPlan = getMealPlan();
        String msg = createMsg(mealPlan);

        mealMessageClient.sendMessage(msg, phoneNumber);
    }

    @Override
    public MealPlan getMealPlan() {
        try {
            return mealPlanParser.parse(mealPlanFetcher.fetchDocument());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String createMsg(MealPlan mealPlan) {
        String day = mealPlan.day();
        String date = mealPlan.date();
        String breakfast = unboxList(mealPlan.breakfast());
        String lunch = unboxList(mealPlan.lunch());
        String dinner = unboxList(mealPlan.dinner());

        String msg =
                date + " (" + day + ")\n\n" +
                        "(아침)\n" +
                        breakfast + "\n" +
                        "--------------------\n" +
                        "(점심)\n" +
                        lunch + "\n" +
                        "--------------------\n" +
                        "(저녁)\n" +
                        dinner;
        log.info("msg = {}", msg);

        return msg;
    }

    private String unboxList(List<String> mealPlan) {
        StringBuilder menus = new StringBuilder();
        for (String menu : mealPlan) {
            menus.append("- ");
            menus.append(menu);
            menus.append("\n");
        }
        return menus.toString();
    }
}
