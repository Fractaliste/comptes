/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raphdine.comptes.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.util.ReflectionUtil;

/**
 *
 * @author Raphiki
 */
public class Logger {

    Class<?> clazz;
    org.apache.logging.log4j.Logger logger;

    private Logger() {
        clazz = ReflectionUtil.getCallerClass(3);
        logger = LogManager.getLogger(clazz);
        assert (logger != null);
    }

    public static Logger getInstance() {
        return new Logger();
    }

    public void debug(String msg, Object... params) {
        logger.debug(msg, params);
    }

    public void info(String msg, Object... params) {
        logger.info(msg, params);
    }

    public void warn(String msg, Object... params) {
        logger.warn(msg, params);
    }

    public void error(String msg, Object... params) {
        logger.error(msg, params);
    }

    public void error(Exception e, String msg, Object... params) {
        String format = String.format(msg.replaceAll("\\{\\}", "%s"), params);
        logger.error(format, e);
    }
}
