package ru.services;

import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.models.Data;
import ru.models.Statistics;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    // Список разделителей.
    char[] separators={',', '.', ' ', '!', '?', '"', ';', ':', '[', ']', '(', ')', '»', '«', '\n', '\r', '\t'};

    // Создание объекта статистики.
    private Statistics statistic= new Statistics("", separators);
    private List<Data> data =new ArrayList<>(); // список уникальных слов

    // метод для передачи списка слов на tflh страницу .
    public List <Data> info() {
        return data; }

    // Метод для создания объекта статистики
    public Statistics saveProduct(Statistics statistics) {
        statistic.setLink(statistics.getLink());
        return statistic;
    }

    // Метод для добавления нового слова в статистику
    public List <Data> saveStatistic(Data slovo) {
        data.add(slovo);
        return data;
    }

    // Метод, отечающий за разбиение текста web-страницы на слова и подсчет статистику по
    // количеству уникальных слов.
    public  static HashMap<String, Integer> CreatingStatistics (Statistics web) throws Exception {

        if (isURL(web.getLink())){
            // Парсинг текст страницы.
            Document doc = Jsoup.connect(web.getLink()).get();
            String gps = doc.text();
            String[] words = gps.split("[" + ListOfSeparators(web.getSeparators()).toString() + "]+");

            // Запись слова в HashMap.
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

    // Метод, который отвечает за создание регулярного выражения из списка разделителей.
    public static StringBuilder ListOfSeparators (char[] separators){
        StringBuilder delimiter = new StringBuilder();
        for (char d : separators) {
            delimiter.append("|\\" + d);
        }
        return delimiter;
    }

}
