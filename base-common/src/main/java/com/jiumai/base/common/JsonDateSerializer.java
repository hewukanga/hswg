package com.jiumai.base.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@JsonComponent
public class JsonDateSerializer {

	public static class Serializer extends JsonSerializer<Date> {

		@Override
		public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
			// System.out.println(value);
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
			String strValue = dateFormat.format(value);
			if(strValue.endsWith("00:00:00")){
				strValue = strValue.substring(0,10);
			}
			gen.writeString(strValue);
		}

	}

	public static class Deserializer extends JsonDeserializer<Date> {
		DateConverterConfig conv = new DateConverterConfig();
		@Override
		public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			if (p != null) {
				String calendatStr = p.getText();
				return conv.convert(calendatStr);
				// if (StringUtils.isNotBlank(calendatStr) && !StringUtils.contains(calendatStr,
				// 'T') &&
				// calendatStr.length() == 19) {
				// LocalDateTime dateTime = LocalDateTime.parse(calendatStr,
				// ExtensionUtils.LocalDateFormatEnum
				// .YYYY_MM_DD_HH_MM_SS.getDateFormatter());
				// return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
				// }
			}
			return null;
			// return super.deserialize(p, ctxt);

		}
	}
}
