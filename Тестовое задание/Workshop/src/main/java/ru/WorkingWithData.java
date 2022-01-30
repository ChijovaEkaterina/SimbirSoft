package ru;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;

// Данный класс отвечает за работу с данными.
@Component
public class WorkingWithData {
    public static Statistics web;

    //Конструктор класса, который на вход получает объект класса Statistics
    //использовано автозаполнение
    @Autowired
    public WorkingWithData(Statistics web) {
        this.web = web;
    }

    // Метод, отвечающий за чтение теста с web-страницы. Возращает StringBuilder с HTML кодом страницы.
    public static String text() throws Exception {
        Document doc = Jsoup.connect(web.getLink()).get();
        return doc.text();
    }
}
