package ssafy.project.jobterview.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    /**
     * 문자열을 LocalDateTime 객체로 변환
     *
     * @param s
     * @return
     */
    public static LocalDateTime StringToLocalDateTime(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(s, formatter);
    }

    /**
     * LocalDateTime 객체를 문자열로 변환
     *
     * @param dateTime
     * @return
     */
    public static String LocalDateTimeToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return (dateTime.format(formatter));
    }
}