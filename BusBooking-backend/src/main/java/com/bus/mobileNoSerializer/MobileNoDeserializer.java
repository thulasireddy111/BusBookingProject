package com.bus.mobileNoSerializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.bus.util.EncryptionUtils;
import java.io.IOException;

public class MobileNoDeserializer extends JsonDeserializer<String> {
    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        try {
            // Decrypt the mobile number after deserialization
            String encryptedMobileNo = p.getText();
            return EncryptionUtils.decrypt(encryptedMobileNo);
        } catch (Exception e) {
            throw new IOException("Error decrypting mobile number", e);
        }
    }
}

