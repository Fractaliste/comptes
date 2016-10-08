/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raphdine.comptes.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Raphiki
 */
public final class DateUtils {

    private static final String DEFAULT_DATE_FORMAT = "dd/MM/yyy";

    private final static DateFormat DF = new SimpleDateFormat(DEFAULT_DATE_FORMAT);

    public static String format(Calendar c) {
        return format(c.getTime());
    }

    public static String format(Date d) {
        return DF.format(d);
    }

    public static Calendar dateToCalendar(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return c;
    }

    public static DateFormat getDefaultDateFormat() {
        return DF;
    }
}
