package com.hx.novel.common.util;

import android.util.Log;

import java.util.Hashtable;

/**
 * Created by ac-er on 2015/11/3.
 */
public class LoggerHelper {
    public final static String tag = "[AppName]";
    private final static boolean logFlag = true;
    private final static int logLevel = Log.VERBOSE;
    private static final String Lhx = "@lhx@ ";
    private static Hashtable<String, LoggerHelper> sLoggerTable = new Hashtable<String, LoggerHelper>();
    private static LoggerHelper Llog;
    private String mClassName;

    private LoggerHelper(String name) {
        mClassName = name;
    }

    /**
     * @param className
     * @return
     */
    @SuppressWarnings("unused")
    private static LoggerHelper getLogger(String className) {
        LoggerHelper classLogger = sLoggerTable.get(className);
        if (classLogger == null) {
            classLogger = new LoggerHelper(className);
            sLoggerTable.put(className, classLogger);
        }
        return classLogger;
    }

    /**
     * Purpose:Mark user one
     *
     * @return
     */
    public static LoggerHelper LLog() {
        if (Llog == null) {
            Llog = new LoggerHelper(Lhx);
        }
        return Llog;
    }

    /**
     * Get The Current Function Name
     *
     * @return
     */
    private String getFunctionName() {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        if (sts == null) {
            return null;
        }
        for (StackTraceElement st : sts) {
            if (st.isNativeMethod()) {
                continue;
            }
            if (st.getClassName().equals(Thread.class.getName())) {
                continue;
            }
            if (st.getClassName().equals(this.getClass().getName())) {
                continue;
            }
            return mClassName + "[ " + Thread.currentThread().getName() + ": "
                    + st.getFileName() + ":" + st.getLineNumber() + " "
                    + st.getMethodName() + " ]";
        }
        return null;
    }

    /**
     * The Log Level:i
     *
     * @param str
     */
    public void i(Object str) {
        if (logFlag) {
            if (logLevel <= Log.INFO) {
                String name = getFunctionName();
                if (name != null) {
                    Log.i(tag, name + " - " + str);
                } else {
                    Log.i(tag, str.toString());
                }
            }
        }

    }

    /**
     * The Log Level:d
     *
     * @param str
     */
    public void d(Object str) {
        if (logFlag) {
            if (logLevel <= Log.DEBUG) {
                String name = getFunctionName();
                if (name != null) {
                    Log.d(tag, name + " - " + str);
                } else {
                    Log.d(tag, str.toString());
                }
            }
        }
    }

    /**
     * The Log Level:V
     *
     * @param str
     */
    public void v(Object str) {
        if (logFlag) {
            if (logLevel <= Log.VERBOSE) {
                String name = getFunctionName();
                if (name != null) {
                    Log.v(tag, name + " - " + str);
                } else {
                    Log.v(tag, str.toString());
                }
            }
        }
    }

    /**
     * The Log Level:w
     *
     * @param str
     */
    public void w(Object str) {
        if (logFlag) {
            if (logLevel <= Log.WARN) {
                String name = getFunctionName();
                if (name != null) {
                    Log.w(tag, name + " - " + str);
                } else {
                    Log.w(tag, str.toString());
                }
            }
        }
    }

    /**
     * The Log Level:e
     *
     * @param str
     */
    public void e(Object str) {
        if (logFlag) {
            if (logLevel <= Log.ERROR) {
                String name = getFunctionName();
                if (name != null) {
                    Log.e(tag, name + " - " + str);
                } else {
                    Log.e(tag, str.toString());
                }
            }
        }
    }

    /**
     * The Log Level:e
     *
     * @param ex
     */
    public void e(Exception ex) {
        if (logFlag) {
            if (logLevel <= Log.ERROR) {
                Log.e(tag, "error", ex);
            }
        }
    }

    /**
     * The Log Level:e
     *
     * @param log
     * @param tr
     */
    public void e(String log, Throwable tr) {
        if (logFlag) {
            String line = getFunctionName();
            Log.e(tag, "{Thread:" + Thread.currentThread().getName() + "}"
                    + "[" + mClassName + line + ":] " + log + "\n", tr);
        }
    }
}
