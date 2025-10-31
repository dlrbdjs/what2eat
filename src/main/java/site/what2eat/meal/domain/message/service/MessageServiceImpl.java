package site.what2eat.meal.domain.message.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.what2eat.meal.global.jsoup.MealPlanFetcher;
import site.what2eat.meal.global.jsoup.MealPlanParser;
import site.what2eat.meal.global.jsoup.dto.MealPlan;
import site.what2eat.meal.global.webclient.mealmessage.MealMessageClient;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    public void sendMessage(String msg, String phoneNumber) {
        mealMessageClient.sendMessage(msg, phoneNumber);
    }

    @Override
    public void sendMessageTest(String msg, String phoneNumber) {
        List<MealPlan> mealPlan = getMealPlan(5);
        String test = createMsg(mealPlan);
        log.info("test={}", test);
//        mealMessageClient.sendMessage(test, phoneNumber);
    }

    @Override
    public List<MealPlan> getMealPlan(int repeat) {
        LocalDate date = LocalDate.now(ZoneId.of("Asia/Seoul"));
        List<MealPlan> mealPlans =  new ArrayList<>();
        try {
            for (int i = 1; i <= repeat; i++) {
                mealPlans.add(mealPlanParser.parse(mealPlanFetcher.fetchDocument(date), date));
                date = date.plusDays(1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return mealPlans;
    }

    @Override
    public List<MealPlan> getMealPlanOnce(LocalDate date) {
        List<MealPlan> mealPlans =  new ArrayList<>();
        try {
            mealPlans.add(mealPlanParser.parse(mealPlanFetcher.fetchDocument(date), date));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return mealPlans;
    }

    @Override
    public String createMsg(List<MealPlan> mealPlans) {
        
        StringBuilder sb = new StringBuilder();

        for (MealPlan mealPlan : mealPlans) {
            String day = mealPlan.day();
            String date = mealPlan.date();
            String breakfast = unboxList(mealPlan.breakfast());
            String lunch = unboxList(mealPlan.lunch());
            String dinner = unboxList(mealPlan.dinner());

            String msg = getMsg(date, day, breakfast, lunch, dinner);

            sb.append(msg);
        }

        log.info("msg = {}", sb);

        return sb.toString();
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

    private String getMsg(String date, String day, String breakfast, String lunch, String dinner) {
        return "\n====================\n" +
                date + " (" + day + ")\n" +
                "--------------------\n" +
                "(아침)\n" +
                breakfast +
                "--------------------\n" +
                "(점심)\n" +
                lunch +
                "--------------------\n" +
                "(저녁)\n" +
                dinner +
                "====================\n";
    }
}
