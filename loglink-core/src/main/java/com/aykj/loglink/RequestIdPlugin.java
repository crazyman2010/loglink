package com.aykj.loglink;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;
import org.apache.logging.log4j.core.pattern.PatternConverter;


@Plugin(name = "RequestIdPlugin", category = PatternConverter.CATEGORY)
@ConverterKeys({"RequestId"})
public class RequestIdPlugin extends LogEventPatternConverter {
    private static RequestIdPlugin instance = new RequestIdPlugin("RequestId", "RequestId");

    /**
     * Constructs an instance of LoggingEventPatternConverter.
     *
     * @param name  name of converter.
     * @param style CSS style for output.
     */
    public RequestIdPlugin(String name, String style) {
        super(name, style);
    }

    /**
     * Formats an event into a string buffer.
     *
     * @param event      event to format, may not be null.
     * @param toAppendTo string buffer to which the formatted event will be appended.  May not be null.
     */
    @Override
    public void format(LogEvent event, StringBuilder toAppendTo) {
        toAppendTo.append(RequestIdLogEventFactory.getRequestId(event));
    }


    public static RequestIdPlugin newInstance(String[] params) {
        return instance;
    }

}
