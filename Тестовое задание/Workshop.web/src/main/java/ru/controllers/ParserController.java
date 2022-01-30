package ru.controllers;

import ru.models.Data;
import ru.models.Statistics;
import ru.services.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.TreeMap;

@Controller
@RequiredArgsConstructor
public class ParserController {
    private final StatisticsService statisticsService;

    @GetMapping("/")
    public String statistics(Model model) {
        model.addAttribute("parser", statisticsService.info());
        return "parser";
    }

    // Метод, который при нажатии на кнопку на странице обеспечивает создание статистики,
    // ее вывод на экран и загрузку в БД
    @PostMapping("/URL/get")
    public String createProduct(Statistics statistics) throws Exception {

        // Создание элемента статистики с ссылкой, полученной от пользователя
        Statistics text = statisticsService.saveProduct(statistics);
        Data data;

        // Создание статистики
        HashMap<String, Integer> wordToCount = new HashMap<>(StatisticsService.CreatingStatistics(text));
        // Сортировка
        TreeMap<String, Integer> sortedMap = new TreeMap<>(wordToCount);

        for (String word : sortedMap.keySet()) {
            data = new Data(word, sortedMap.get(word));
            statisticsService.saveStatistic(data);
        }
        return "redirect:/";
    }

}
