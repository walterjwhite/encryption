package com.walterjwhite.encryption.impl;

import com.walterjwhite.encryption.api.service.SaltService;
import com.walterjwhite.encryption.property.IVFilePath;
import com.walterjwhite.encryption.property.KeyFilePath;
import com.walterjwhite.google.guice.property.property.Property;
import java.io.*;
import java.security.Key;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.commons.io.IOUtils;

@Singleton
public class RuntimeEncryptionConfiguration {
  protected final String keyFilePath;
  protected final String ivFilePath;

  protected byte[] keyData;
  protected byte[] ivData;

  // TODO: generate this automatically on startup
  // protected String sharedSecret;
  protected final SaltService saltService;

  protected Key key;
  protected IvParameterSpec iv;

  @Inject
  public RuntimeEncryptionConfiguration(
      @Property(KeyFilePath.class) String keyFilePath,
      @Property(IVFilePath.class) String ivFilePath,
      SaltService saltService)
      throws IOException {
    super();
    this.keyFilePath = keyFilePath;
    this.ivFilePath = ivFilePath;

    this.saltService = saltService;
    setupKey();
  }

  protected void setupKey() throws IOException {
    if (new File(keyFilePath).exists()) {
      keyData = new byte[16];
      ivData = new byte[16];

      // read key from file
      IOUtils.read(new FileInputStream(keyFilePath), keyData);
      IOUtils.read(new FileInputStream(ivFilePath), ivData);
    } else {
      // generate key and write it
      keyData = saltService.generate(16);
      try (final FileOutputStream fos = new FileOutputStream(new File(keyFilePath))) {
        fos.write(keyData);
      }

      ivData = saltService.generate(16);
      try (final FileOutputStream fos = new FileOutputStream(new File(ivFilePath))) {
        fos.write(ivData);
      }
    }

    key = new SecretKeySpec(keyData, "AES");
    iv = new IvParameterSpec(ivData);
  }

  public byte[] getKeyData() {
    return keyData;
  }

  public byte[] getIvData() {
    return ivData;
  }

  public Key getKey() {
    return key;
  }

  public IvParameterSpec getIv() {
    return (iv);
  }
}
