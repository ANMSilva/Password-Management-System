package com.nipun.Password.Manager.utils;

import org.slf4j.Logger;

public class LoggingUtils {

    public static void logError(Logger logger, Exception e) {
        StackTraceElement stackTraceElement = e.getStackTrace()[0];
        int lineNumber = stackTraceElement.getLineNumber();
        String className = stackTraceElement.getClassName();
        String methodName = stackTraceElement.getMethodName();
        String errorMessage = "An error occurred in " + className + "." + methodName + "() at line " + lineNumber + ": " + e.getMessage();
        logger.error("### ERROR: " + errorMessage + " ###", e);
    }

}
