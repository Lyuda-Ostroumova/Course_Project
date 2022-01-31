package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.LocalDate;
import java.time.Year;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {

    }

    public static Faker faker = new Faker(new Locale("en"));

    @Value
    @RequiredArgsConstructor
    public static class CardInfo {
        private String cardNumber;
        private String month;
        private String year;
        private String owner;
        private String cvc;

    }

    // поле номер карты

    public static String getApprovedCardNumber() {
        return ("1111 2222 3333 4444");
    }

    public static String getDeclinedCardNumber() {
        return ("5555 6666 7777 8888");
    }

    public static String getRandomCardNumber() {
        return ("1234 5678 9876 5432");
    }

    public static String getShortCardNumber() {
        return ("1111 2222 3333");
    }

    public static String getCardNumberWithLetters() {
        return ("1111 2222 3333 abcd");
    }

    public static String getCardNumberWithSymbols() {
        return ("1111 2222 3333 ****");
    }

    // поле месяц

    public static String getValidMonth() {
        LocalDate localDate = LocalDate.now();
        return String.format("%02d", localDate.getMonthValue());
    }

    public static String getNonExistentMonth() {
        return ("27");
    }

    public static String getMonthWithZeroes() {
        return ("00");
    }

    public static String getMonthOfOneNumber() {
        return ("3");
    }

    public static String getMonthWithLetters() {
        return ("ab");
    }

    public static String getMonthWithSymbols() {
        return ("0*");
    }

    // поле год

    public static String getValidYear() {
        return String.format("%ty", Year.now());
    }

    public static String getFutureYear() {
        LocalDate localDate = LocalDate.now();
        return String.format("%ty", localDate.plusYears(4));
    }

    public static String getPastYear() {
        LocalDate localDate = LocalDate.now();
        return String.format("%ty", localDate.minusYears(3));
    }

    public static String getYearOfOneNumber() {
        return ("3");
    }

    public static String getYearWithLetters() {
        return ("ab");
    }

    public static String getYearWithSymbols() {
        return ("0*");
    }

    // поле Владелец

    public static String getCardOwnerName() {
        return faker.name().fullName();
    }

    public static String getCardOwnerFirstName() {
        return faker.name().firstName();
    }

    public static String getCardOwnerLastName() {
        return faker.name().lastName();
    }

    public static String getCardOwnerNameInRus() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().fullName();
    }

    public static String getCardOwnerNameWithOneLetter() {
        return ("a");
    }

    public static String getLongCardOwnerName() {
        return ("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa Bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
    }

    public static String getCardOwnerNameWithHyphen() {
        return ("Mikhail Saltykov-Shchedrin");
    }

    public static String getCardOwnerNameWithNumbers() {
        return ("12345678 456123789");
    }

    public static String getCardOwnerNameWithSymbols() {
        return ("Anna ****");
    }

    // поле СVC

    public static String getValidCvc() {
        return ("213");
    }

    public static String getCvcWithZero() {
        return ("000");
    }

    public static String getShortCvc() {
        return ("1");
    }

    public static String getCvcWithLetters() {
        return ("abc");
    }

    public static String getCvcWithSymbols() {
        return ("7**");
    }
}
