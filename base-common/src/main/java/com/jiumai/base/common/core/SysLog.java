package com.jiumai.base.common.core;

import org.slf4j.Logger;

/**
 * 日志实体
 * 
 * @author louis<83030246@qq.com>
 * @date 2018-02-12
 * @version 1.0
 */
public class SysLog {
    private Logger logger;

    public SysLog(Logger logger) {
        this.logger = logger;
    }

    /**
     * Is the logger instance enabled for the DEBUG level?
     *
     * @return True if this Logger is enabled for the DEBUG level, false
     *         otherwise.
     */
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    /**
     * Is the logger instance enabled for the INFO level?
     *
     * @return True if this Logger is enabled for the INFO level, false
     *         otherwise.
     */
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    /**
     * Is the logger instance enabled for the ERROR level?
     *
     * @return True if this Logger is enabled for the ERROR level, false
     *         otherwise.
     */
    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    /**
     * Log a message at the DEBUG level.
     *
     * @param msg
     *            the message string to be logged
     */
    public void debug(String msg) {
        logger.debug(msg);
    }

    /**
     * Log a message at the DEBUG level.
     *
     * @param msg
     *            the message string to be logged
     */
    public void debug(Object msg) {
        logger.debug(msg.toString());
    }

    /**
     * Log a message at the DEBUG level according to the specified format and
     * argument.
     * <p/>
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the DEBUG level.
     * </p>
     *
     * @param format
     *            the format string
     * @param arg
     *            the argument
     */
    public void debug(String format, Object arg) {
        logger.debug(format, arg);
    }

    /**
     * Log a message at the DEBUG level according to the specified format and
     * argument.
     * <p/>
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the DEBUG level.
     * </p>
     *
     * @param format
     *            the format string
     * @param arg
     *            the argument
     * @param arg2
     *            the argument
     */
    public void debug(String format, Object arg, Object arg2) {
        logger.debug(format, arg, arg2);
    }

    /**
     * Log an exception (throwable) at the DEBUG level with an accompanying
     * message.
     *
     * @param msg
     *            the message accompanying the exception
     * @param t
     *            the exception (throwable) to log
     */
    public void debug(String msg, Throwable t) {
        logger.debug(msg, t);
    }

    /**
     * Log a message at the INFO level.
     *
     * @param msg
     *            the message string to be logged
     */
    public void info(String msg) {
        logger.info(msg);
    }

    /**
     * Log a message at the INFO level.
     *
     * @param msg
     *            the message string to be logged
     */
    public void info(Object msg) {
        logger.info(msg.toString());
    }

    /**
     * Log a message at the INFO level according to the specified format and
     * argument.
     * <p/>
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the INFO level.
     * </p>
     *
     * @param format
     *            the format string
     * @param arg
     *            the argument
     */
    public void info(String format, Object arg) {
        logger.info(format, arg);
    }

    /**
     * Log an exception (throwable) at the INFO level with an accompanying
     * message.
     *
     * @param msg
     *            the message accompanying the exception
     * @param t
     *            the exception (throwable) to log
     */
    public void info(String msg, Throwable t) {
        logger.info(msg, t);
    }

    /**
     * Log a message at the WARN level.
     *
     * @param msg
     *            the message string to be logged
     */
    public void warn(String msg) {
        logger.warn(msg);
    }

    /**
     * Log a message at the WARN level according to the specified format and
     * argument.
     * <p/>
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the WARN level.
     * </p>
     *
     * @param format
     *            the format string
     * @param arg
     *            the argument
     */
    public void warn(String format, Object arg) {
        logger.warn(format, arg);
    }

    /**
     * Log an exception (throwable) at the WARN level with an accompanying
     * message.
     *
     * @param msg
     *            the message accompanying the exception
     * @param t
     *            the exception (throwable) to log
     */
    public void warn(String msg, Throwable t) {
        logger.warn(msg, t);
    }

    /**
     * Log a message at the ERROR level.
     *
     * @param msg
     *            the message string to be logged
     */
    public void error(String msg) {
        logger.error(msg);
    }

    /**
     * Log a message at the ERROR level.
     *
     * @param msg
     *            the message string to be logged
     */
    public void error(Object msg) {
        logger.error(msg.toString());
    }

    /**
     * Log a message at the ERROR level according to the specified format and
     * argument.
     * <p/>
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the ERROR level.
     * </p>
     *
     * @param format
     *            the format string
     * @param arg
     *            the argument
     */
    public void error(String format, Object arg) {
        logger.error(format, arg);
    }

    /**
     * Log an exception (throwable) at the ERROR level with an accompanying
     * message.
     *
     * @param msg
     *            the message accompanying the exception
     * @param t
     *            the exception (throwable) to log
     */
    public void error(String msg, Throwable t) {
        logger.error(msg, t);
    }

    /**
     * Log an exception (throwable) at the ERROR level with an accompanying
     * message.
     *
     * @param t
     *            the exception (throwable) to log
     */
    public void error(Throwable t) {
        logger.error("异常", t);
    }

}
