package com.jiumai.base.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jiumai.base.common.core.utils.LocalDateTimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;


@Configuration
public class LocalDateTimeSerializerConfig {

    /**
     * 序列化LocalDateTime
     * @return
     */
    @Bean
    @Primary
    public ObjectMapper serializingObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        javaTimeModule.addDeserializer(Enum.class, new EnumDeserializer());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(javaTimeModule);
        return objectMapper;
    }

    /**
     * 序列化实现
     */
    public static class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
        @Override
        public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException {
            if (value != null){
                long timestamp = value.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
                gen.writeNumber(timestamp);
            }
        }
    }

    /**
     * 反序列化实现
     */
    public static class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonParser p, DeserializationContext deserializationContext) throws IOException {
            long timestamp = p.getValueAsLong();
            String str = p.getText();
            if (timestamp > 0) {
                return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
            } else if (StringUtils.isNotBlank(str)) {
                switch (str.length()) {
                    case 19:
                        return LocalDateTimeUtils.getLocalDateTimeFromString(str, LocalDateTimeUtils.YYYY_MM_DD_HH_MM_SS);
                    case 16:
                        return LocalDateTimeUtils.getLocalDateTimeFromString(str, LocalDateTimeUtils.YYYY_MM_DD_HH_MM);
                    case 14:
                        return LocalDateTimeUtils.getLocalDateTimeFromString(str, LocalDateTimeUtils.YYYYMMDDHHMMSS);
                    case 10:
                        str += " 00:00:00";
                        return LocalDateTimeUtils.getLocalDateTimeFromString(str, LocalDateTimeUtils.YYYY_MM_DD_HH_MM_SS);
                    default:
                }
            }
            return null;
        }
    }
}

