/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.todoweb.business;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 *
 * @author tss
 */
public class DateUtils {

    private static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime asLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static Date dateFromString(String date) {
        if (date == null) {
            return null;
        }
        try {
            return df.parse(date);
        } catch (ParseException ex) {
            return null;
        }
    }

    public static String dateToString(Date date) {
        return df.format(date);
    }

    public static Date scadenzaToken(int min) {
        LocalDateTime plus = LocalDateTime.now().plus(min, ChronoUnit.MINUTES);
        return asDate(plus);
    }
    
    public static String dateToISO(Date date) {
        return asLocalDate(date).format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
