package ru;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

@Component
public class Statistics {

    @Value("${separators}")
    char[] separators; // Список разделителей.
    @Value("${link}")
    String link; //URL-ссылка.

    // Метод, отечающий за разбиение текста web-страницы на слова и подсчет статистику по
    // количеству уникальных слов.
    public  static HashMap<String, Integer> CreatingStatistics (Statistics web) throws Exception {

        if (isURL(web.link)){
            // Парсим текст страницы.
            String gps = WorkingWithData.text().toString();
            String[] words = gps.split("[" + ListOfSeparators(web.separators).toString() + "]+");

            // Записываем слова в HashMap.
            HashMap<String, Integer> wordToCount = new HashMap<>();
            for (String word : words) {
                if (!wordToCount.containsKey(word)) {
                    wordToCount.put(word, 0);
                }
                wordToCount.put(word, wordToCount.get(word) + 1);
            }
            return wordToCount;
        }
        else {
            System.out.println("Некорректный адрес web-страницы");
            return null;
        }
    }

    // Метод, проверяющий, является ли введенная пользователем строка URL-ссылкой.
    private static boolean isURL(String str) {
        try {
            URL address = new URL(str);
            return true;
        }
        catch  (MalformedURLException e) {
            return false;
        }
    }

    // Метод, которые отвечает за создание регулярного выражения из списка разделителей.
    public static StringBuilder ListOfSeparators (char[] separators){
        StringBuilder delimiter = new StringBuilder();
        for (char d : separators) {
            delimiter.append("|\\" + d);
        }
        return delimiter;
    }

    // метод для получения ссылки
    public String getLink() {
        return link;
    }
}
