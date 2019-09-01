package com.walterjwhite.encryption.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

public interface EncryptionService {
  void encrypt(InputStream inputStream, OutputStream outputStream) throws IOException;

  OutputStream getEncryptionStream(OutputStream outputStream) throws IOException;

  void decrypt(InputStream cipherStream, OutputStream plaintextStream)
      throws InvalidAlgorithmParameterException, InvalidKeyException, IOException;

  InputStream getDecryptionStream(InputStream inputStream) throws IOException;
}
