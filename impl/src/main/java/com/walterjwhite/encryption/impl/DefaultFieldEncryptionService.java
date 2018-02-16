package com.walterjwhite.encryption.impl;

import com.walterjwhite.encryption.api.EncryptionException;
import com.walterjwhite.encryption.api.service.EncryptionService;
import com.walterjwhite.encryption.api.service.FieldEncryptionService;
import com.walterjwhite.encryption.api.service.SaltService;
import com.walterjwhite.encryption.enumeration.EncryptionType;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.inject.Inject;

public class DefaultFieldEncryptionService implements FieldEncryptionService {
  protected final EncryptionConfiguration encryptionConfiguration;

  protected final SaltService saltService;

  protected final EncryptionService encryptionService;

  @Inject
  public DefaultFieldEncryptionService(
      EncryptionConfiguration encryptionConfiguration,
      SaltService saltService,
      EncryptionService encryptionService) {
    super();
    this.encryptionConfiguration = encryptionConfiguration;
    this.saltService = saltService;
    this.encryptionService = encryptionService;
  }

  @Override
  public void encrypt(Object e, Field field, EncryptionType encryptionType) {
    try {
      final Field encryptedField =
          field.getDeclaringClass().getDeclaredField(FieldUtil.getEncryptedField(field));
      final String plaintext = (String) FieldUtil.getValue(e, field);
      final byte[] encryptedValue;

      if (EncryptionType.Digest.equals(encryptionType)) {
        final Field saltField =
            field.getDeclaringClass().getDeclaredField(FieldUtil.getSaltField(field));
        final byte[] salt = saltService.generate();
        FieldUtil.setValue(e, saltField, salt);

        encryptedValue = doDigest(plaintext, salt);
      } else {
        encryptedValue = encryptionService.encrypt(plaintext.getBytes(Charset.defaultCharset()));
      }

      FieldUtil.setValue(e, encryptedField, encryptedValue);
    } catch (Exception exception) {
      throw (new EncryptionException(exception));
    }
  }

  protected byte[] doDigest(final String plaintext, final byte[] salt)
      throws NoSuchAlgorithmException {
    throw (new UnsupportedOperationException("Un-implemented."));
  }

  public void decrypt(Object e, Field field, EncryptionType encryptionType)
      throws IllegalAccessException, IOException, BadPaddingException, IllegalBlockSizeException,
          NoSuchFieldException, InvalidAlgorithmParameterException, InvalidKeyException {
    if (EncryptionType.Encrypt.equals(encryptionType)) {
      final Field encryptedField =
          field.getDeclaringClass().getDeclaredField(FieldUtil.getEncryptedField(field));

      byte[] decrypted = encryptionService.decrypt((byte[]) encryptedField.get(e));

      FieldUtil.setValue(e, field, new String(decrypted));
    }
  }
}
