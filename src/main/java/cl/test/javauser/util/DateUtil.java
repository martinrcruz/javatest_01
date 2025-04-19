package cl.test.javauser.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static boolean isValidDate(String date) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    public static String formatDateTime(LocalDateTime datetime) {
        return datetime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    public static LocalDateTime parseDateTime(String datetime) {
        return LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    public static boolean isValidDateTime(String datetime) {
        try {
            LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }
}
