package com.aykj.loglink;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.impl.ContextDataFactory;
import org.apache.logging.log4j.core.impl.MutableLogEvent;
import org.apache.logging.log4j.core.impl.ReusableLogEventFactory;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.util.StringMap;

import java.util.List;

public class RequestIdLogEventFactory extends ReusableLogEventFactory {

    public static String getRequestId(LogEvent event) {
        return event.getContextData().getValue("RequestId");
    }

    /**
     * Creates a log event.
     *
     * @param loggerName The name of the Logger.
     * @param marker     An optional Marker.
     * @param fqcn       The fully qualified class name of the caller.
     * @param location   The location of the caller.
     * @param level      The event Level.
     * @param message    The Message.
     * @param properties Properties to be added to the log event.
     * @param t          An optional Throwable.
     * @return The LogEvent.
     */
    @Override
    public LogEvent createEvent(String loggerName, Marker marker, String fqcn, StackTraceElement location, Level level, Message message, List<Property> properties, Throwable t) {
        LogEvent event = super.createEvent(loggerName, marker, fqcn, location, level, message, properties, t);
        addRequestIdToEvent(event);
        return event;
    }

    private void addRequestIdToEvent(LogEvent event) {
        if (event instanceof MutableLogEvent) {
            StringMap contextData = ContextDataFactory.createContextData();
            contextData.putAll(event.getContextData());
//            try {
//                System.out.println("class loader1:"+getClassLoader());
//                getClassLoader().loadClass("com.aykj.loglink.LogLinker");
                contextData.putValue("RequestId", LogLinker.getRequestId());
//                System.out.println("class loader: "+LogLinker.class.getClassLoader());
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
            ((MutableLogEvent) event).setContextData(contextData);
        }
    }

    private ClassLoader getClassLoader() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader != null) {
            return classLoader;
        }
        return this.getClassLoader();
    }


}
