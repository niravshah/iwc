package com.infinityworks.test.nns.domain;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DecimalFormat;

public class CustomFloatDeserializer extends JsonSerializer<Float> {
    @Override
    public void serialize(Float value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        gen.writeString(df.format(value));
    }
}
