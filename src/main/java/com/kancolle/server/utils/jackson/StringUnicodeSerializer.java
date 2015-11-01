/**
 * 
 */
package com.kancolle.server.utils.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.json.JsonWriteContext;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * @author J.K.SAGE
 * @Date 2015年11月1日
 *
 */
public class StringUnicodeSerializer extends JsonSerializer<String> {
    final char[] HEX_CHARS = "0123456789abcdef".toCharArray();
    final int[] ESCAPE_CODES = CharTypes.get7BitOutputEscapes();

    private void writeUnicodeEscape(JsonGenerator gen, char c) throws IOException {
        gen.writeRaw('\\');
        gen.writeRaw('u');
        gen.writeRaw(HEX_CHARS[(c >> 12) & 0xF]);
        gen.writeRaw(HEX_CHARS[(c >> 8) & 0xF]);
        gen.writeRaw(HEX_CHARS[(c >> 4) & 0xF]);
        gen.writeRaw(HEX_CHARS[c & 0xF]);
    }

    private void writeShortEscape(JsonGenerator gen, char c) throws IOException {
        gen.writeRaw('\\');
        gen.writeRaw(c);
    }

    @Override
    public void serialize(String str, JsonGenerator gen, SerializerProvider provider) throws IOException {
        int status = ((JsonWriteContext) gen.getOutputContext()).writeValue();
        switch (status) {
        case JsonWriteContext.STATUS_OK_AFTER_COLON:
            gen.writeRaw(':');
            break;
        case JsonWriteContext.STATUS_OK_AFTER_COMMA:
            gen.writeRaw(',');
            break;
        case JsonWriteContext.STATUS_EXPECT_NAME:
            throw new JsonGenerationException("Can not write string value here");
        }
        gen.writeRaw('"');
        for (char c : str.toCharArray()) {
            if (c >= 0x80)
                writeUnicodeEscape(gen, c); // use generic escaping for all non US-ASCII characters
            else {
                // use escape table for first 128 characters
                int code = (c < ESCAPE_CODES.length ? ESCAPE_CODES[c] : 0);
                if (code == 0)
                    gen.writeRaw(c); // no escaping
                else if (code < 0)
                    writeUnicodeEscape(gen, (char) (-code - 1)); // generic escaping
                else
                    writeShortEscape(gen, (char) code); // short escaping (\n \t ...)
            }
        }
        gen.writeRaw('"');
    }

    @Override
    public Class<String> handledType() {
        return String.class;
    }
}
