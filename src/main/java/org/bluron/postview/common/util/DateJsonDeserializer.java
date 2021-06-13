package org.bluron.postview.common.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期反序列化
 *
 * @author JuLei
 * @since 1.0.0_2019年09月21日
 */
public class DateJsonDeserializer extends JsonDeserializer<LocalDateTime> {

    private static final Logger LOG = LoggerFactory.getLogger(DateJsonDeserializer.class);

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        LocalDateTime dateTime = null;
        try {
            if (jsonParser != null && StringUtils.isNotEmpty(jsonParser.getText())) {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                dateTime = LocalDateTime.parse(jsonParser.getText(), dateTimeFormatter);
            }
        } catch (Exception e) {
            LOG.warn("日期反序列化异常", e);
        }
        return dateTime;
    }

}
