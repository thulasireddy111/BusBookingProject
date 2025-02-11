package com.bus.mobileNoSerializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.bus.util.EncryptionUtils;
import java.io.IOException;

public class MobileNoSerializer extends JsonSerializer<String> {
    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        try {
            // Encrypt the mobile number before serializing
            String encryptedMobileNo = EncryptionUtils.encrypt(value);
            gen.writeString(encryptedMobileNo);
        } catch (Exception e) {
            throw new IOException("Error encrypting mobile number", e);
        }
    }
}
