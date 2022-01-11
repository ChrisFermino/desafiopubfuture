package br.com.pubfuture.desafiopubfuture.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParse {

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public Date dateparse (String date) {
        Date date1 = null;
        try {
            date1 = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;
    }
}
