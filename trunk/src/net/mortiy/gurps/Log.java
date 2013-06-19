package net.mortiy.gurps;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Locale;

public class Log {
    private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    static {

    }

    public static void i (String tag, String message){
        logger.info(tag + ": " + message);
    }
    public static void e (String tag, String message){
        logger.error(tag + ": " + message);
    }
    public static void d (String tag, String message){
        logger.debug(tag + ": " + message);
    }

    public static void w (String tag, String message){
        logger.warn(tag + ": " + message);
    }

    public static void i (String tag, String message, Object ... args){
        i(tag, String.format(Locale.ENGLISH, message, args));
    }
    public static void w (String tag, String message, Object ... args){
        w(tag, String.format(Locale.ENGLISH, message, args));
    }
    public static void e (String tag, String message, Object ... args){
        e(tag, String.format(Locale.ENGLISH, message, args));
    }
    public static void d (String tag, String message, Object ... args){
        d(tag, String.format(Locale.ENGLISH, message, args));
    }
}
