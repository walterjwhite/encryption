package com.walterjwhite.encryption.api.service;

import com.walterjwhite.encryption.enumeration.EncryptionType;
import java.io.IOException;
import java.lang.reflect.Field;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public interface FieldEncryptionService {
  void encrypt(Object e, Field field, EncryptionType encryptionType);

  void decrypt(Object e, Field field, EncryptionType encryptionType)
      throws NoSuchFieldException, IllegalAccessException, IOException, BadPaddingException,
          IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeyException;
}
