package ru.models;


import lombok.AllArgsConstructor;


@lombok.Data
@AllArgsConstructor
// класс для работы с готовой статистикой
public class Data {
    private String Word; // слово
    private int Quantity; // колличество повторений
}
