package com.walterjwhite.encryption.impl;

import com.walterjwhite.encryption.api.service.EncryptionService;
import java.io.*;
import java.security.*;
import java.util.Properties;
import javax.inject.Inject;
import org.apache.commons.crypto.stream.CryptoInputStream;
import org.apache.commons.crypto.stream.CryptoOutputStream;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultEncryptionService implements EncryptionService {

  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultEncryptionService.class);

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

  //  @Autowired
  //  protected Client client;

  //  @Autowired protected Cipher cipher;

  @Override
  public byte[] encrypt(byte[] plaintextData)
      throws IOException, InvalidAlgorithmParameterException, InvalidKeyException {
    try (final ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
      try (final ByteArrayInputStream bais = new ByteArrayInputStream(plaintextData)) {
        encrypt(bais, baos);
        return (baos.toByteArray());
      }
    }
  }

  @Override
  public void encrypt(InputStream plaintextData, OutputStream cipherStream)
      throws IOException, InvalidAlgorithmParameterException, InvalidKeyException {
    Properties properties = new Properties();

    try (CryptoOutputStream cos =
        new CryptoOutputStream(
            getTransformation(),
            properties,
            cipherStream,
            runtimeEncryptionConfiguration.getKey(),
            runtimeEncryptionConfiguration.getIv())) {
      cos.write(IOUtils.toByteArray(plaintextData));
      cos.flush();
    }
  }

  @Override
  public byte[] decrypt(byte[] ciphertextData)
      throws IOException, InvalidAlgorithmParameterException, InvalidKeyException {
    try (final ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
      try (final ByteArrayInputStream bais = new ByteArrayInputStream(ciphertextData)) {
        decrypt(bais, baos);
        return (baos.toByteArray());
      }
    }
  }

  @Override
  public void decrypt(InputStream cipherStream, OutputStream plaintextStream)
      throws InvalidAlgorithmParameterException, InvalidKeyException, IOException {
    Properties properties = new Properties();
    try (CryptoInputStream cis =
        new CryptoInputStream(
            getTransformation(),
            properties,
            cipherStream,
            runtimeEncryptionConfiguration.getKey(),
            runtimeEncryptionConfiguration.getIv())) {
      IOUtils.copy(cis, plaintextStream);
    }
  }

  protected String getTransformation() {
    return (encryptionConfiguration.getEncryptionAlgorithm().name()
        + "/"
        + encryptionConfiguration.getTransformationAlgorithm().name()
        + "/"
        + encryptionConfiguration.getPaddingType().name());
  }
}
