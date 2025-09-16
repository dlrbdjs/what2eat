package site.what2eat.meal.domain.message.service;

public interface MessageService {

    void sendMessage(String phoneNumber);

    String getHtml();
}
