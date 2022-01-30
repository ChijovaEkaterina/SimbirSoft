package ru;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;


// В данном классе осуществляется получение информации из созданнных объектов
public class Parser  {
    public static void main(String[] args) throws Exception {

        // Подключение аппликешн контекта.
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

        // Получение бина статистики.
        Statistics text = context.getBean("statistics", Statistics.class);

        // Создание статистики.
        HashMap<String, Integer> wordToCount = new HashMap<>(Statistics.CreatingStatistics(text));

        // Вывод статистики в консоль.
        for (String word : wordToCount.keySet()) {
            System.out.println(word + " " + wordToCount.get(word));
        }
    }
}

