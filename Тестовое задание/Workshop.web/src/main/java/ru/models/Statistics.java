package ru.models;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
// класс для работы с объектом статистики, под которым в данном проекте подразумевается
// ссылка на web-страницу и список разделилелей
public class Statistics {
    private String link;
    private char[] separators;
}
