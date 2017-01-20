package essilor.integrator.adapter.utils;

import essilor.integrator.adapter.Adapter;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DatatypeConverter {

    private static final Logger logger = Logger.getLogger(DatatypeConverter.class);

    private static ThreadLocal<SimpleDateFormat> dateFormatter = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        }
    };

    private static ThreadLocal<DecimalFormat> decimalFormatter = new ThreadLocal<DecimalFormat>() {
        @Override
        protected DecimalFormat initialValue() {
            DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
            otherSymbols.setDecimalSeparator('.');
            DecimalFormat df = new DecimalFormat("");
            df.setMaximumFractionDigits(2);
            df.setMinimumFractionDigits(2);
            df.setMinimumIntegerDigits(1);
            df.setGroupingSize(0);
            df.setDecimalFormatSymbols(otherSymbols);
            df.setParseBigDecimal(true);
            return df;
        }
    };

    public static java.util.Date unmarshal(String s)  {
        try {
            return dateFormatter.get().parse(s);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String marshal(Date v) {
        if (v != null) {
            return dateFormatter.get().format(v);
        } else {
            return null;
        }
    }

    public static BigDecimal unmarshalDecimal(String s)  {
        try {
            return (BigDecimal) decimalFormatter.get().parse(s);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String marshalDecimal(BigDecimal v) {
        if (v != null) {
            logger.debug("formatted bigdecimal: " + decimalFormatter.get().format(v));
            return decimalFormatter.get().format(v);
        } else {
            return null;
        }
    }

}
