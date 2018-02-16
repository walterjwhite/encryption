package com.walterjwhite.encryption.api.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

public interface EncryptionService {
  byte[] encrypt(byte[] plaintextData)
      throws IOException, InvalidAlgorithmParameterException, InvalidKeyException;

  void encrypt(InputStream plaintextData, OutputStream cipherStream)
      throws IOException, InvalidAlgorithmParameterException, InvalidKeyException;

  byte[] decrypt(byte[] ciphertextData)
      throws IOException, InvalidAlgorithmParameterException, InvalidKeyException;

  void decrypt(InputStream cipherStream, OutputStream plaintextStream)
      throws InvalidAlgorithmParameterException, InvalidKeyException, IOException;
}
