package com.jiumai.base.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.jiumai.base.common.core.utils.StringUtils;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class EnumDeserializer extends JsonDeserializer<Enum<?>> {

    @Override
    public Enum<?> deserialize(JsonParser jp, DeserializationContext cxt) throws IOException {
        //String value = jp.getValueAsString();
        JsonNode node = jp.getCodec().readTree(jp);
        Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> str = fields.next();
            if (StringUtils.isNotBlank(str.getValue().asText()) && !"name".equals(str.getKey())) {
                Class findPropertyType = BeanUtils.findPropertyType(jp.currentName(), jp.getCurrentValue().getClass());
                try {
                    String key = str.getKey();
                    return Enum.valueOf(findPropertyType, str.getValue().asText());
                } catch (IllegalArgumentException e) {
                    return null;
                }
            }
        }
        /*if (StringUtils.isBlank()) {
            return null;
        }*/
        /*Class findPropertyType = BeanUtils.findPropertyType(jp.currentName(), jp.getCurrentValue().getClass());
        try {
            return Enum.valueOf(findPropertyType, node.asText());
        } catch (IllegalArgumentException e) {
            return null;
        }*/
        return null;
    }
}
