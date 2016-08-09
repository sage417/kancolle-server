package com.kancolle.server.utils.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Created by J.K.SAGE on 2016/8/9 0009.
 */
public class NumericBooleanSerializer extends JsonSerializer<Boolean> {
    @Override
    public void serialize(Boolean b, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(b ? 1 : 0);
    }
}
