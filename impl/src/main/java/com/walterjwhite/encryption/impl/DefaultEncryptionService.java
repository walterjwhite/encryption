package com.walterjwhite.encryption.impl;

import com.walterjwhite.encryption.service.EncryptionService;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import javax.inject.Inject;
import org.apache.commons.crypto.stream.CryptoInputStream;
import org.apache.commons.crypto.stream.CryptoOutputStream;
import org.apache.commons.io.IOUtils;

public class DefaultEncryptionService implements EncryptionService {
  protected final RuntimeEncryptionConfiguration runtimeEncryptionConfiguration;

  protected final EncryptionConfiguration encryptionConfiguration;

  @Inject
  public DefaultEncryptionService(
      RuntimeEncryptionConfiguration runtimeEncryptionConfiguration,
      EncryptionConfiguration encryptionConfiguration) {
    super();
    this.runtimeEncryptionConfiguration = runtimeEncryptionConfiguration;
    this.encryptionConfiguration = encryptionConfiguration;
  }

  @Override
  public void encrypt(final InputStream plaintextStream, OutputStream outputStream)
      throws IOException {
    try (final OutputStream encryptedOutputStream = getEncryptionStream(outputStream)) {
      IOUtils.copy(plaintextStream, encryptedOutputStream);
      encryptedOutputStream.flush();
    }
  }

  @Override
  public OutputStream getEncryptionStream(OutputStream outputStream) throws IOException {
    return new CryptoOutputStream(
        getTransformation(),
        new Properties(),
        outputStream,
        runtimeEncryptionConfiguration.getKey(),
        runtimeEncryptionConfiguration.getIv());
  }

  @Override
  public void decrypt(InputStream cipherStream, OutputStream plaintextStream) throws IOException {
    try (final InputStream decryptedOutputStream = getDecryptionStream(cipherStream)) {
      IOUtils.copy(decryptedOutputStream, plaintextStream);
      plaintextStream.flush();
    }
  }

  @Override
  public InputStream getDecryptionStream(InputStream cipherStream) throws IOException {
    return new CryptoInputStream(
        getTransformation(),
        new Properties(),
        cipherStream,
        runtimeEncryptionConfiguration.getKey(),
        runtimeEncryptionConfiguration.getIv());
  }

  protected String getTransformation() {
    return (encryptionConfiguration.getEncryptionAlgorithm().name()
        + "/"
        + encryptionConfiguration.getTransformationAlgorithm().name()
        + "/"
        + encryptionConfiguration.getPaddingType().name());
  }
}
