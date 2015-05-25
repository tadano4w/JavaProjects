package test.p6spy;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

public class PrettySqlFormat implements MessageFormattingStrategy {

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql) {
        return "elapsed:" + elapsed + " " + sql;
    }

}