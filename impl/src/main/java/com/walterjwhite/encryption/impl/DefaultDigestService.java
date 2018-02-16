package com.walterjwhite.encryption.impl;

import com.walterjwhite.encryption.api.service.DigestService;
import com.walterjwhite.encryption.enumeration.DigestAlgorithm;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.inject.Inject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;

public class DefaultDigestService implements DigestService {
  protected final RuntimeEncryptionConfiguration runtimeEncryptionConfiguration;
  protected final EncryptionConfiguration encryptionConfiguration;

  @Inject
  public DefaultDigestService(
      RuntimeEncryptionConfiguration runtimeEncryptionConfiguration,
      EncryptionConfiguration encryptionConfiguration) {
    super();
    this.runtimeEncryptionConfiguration = runtimeEncryptionConfiguration;
    this.encryptionConfiguration = encryptionConfiguration;
  }

  @Override
  public String compute(java.io.File f) throws IOException, NoSuchAlgorithmException {
    // TODO: use enums here
    if (DigestAlgorithm.SHA_1.equals(encryptionConfiguration.getDigestAlgorithm())) {
      return (DigestUtils.sha1Hex(new FileInputStream(f)));
    }
    if (DigestAlgorithm.SHA_256.equals(encryptionConfiguration.getDigestAlgorithm())) {
      return (DigestUtils.sha256Hex(new FileInputStream(f)));
    }
    if (DigestAlgorithm.SHA_512.equals(encryptionConfiguration.getDigestAlgorithm())) {
      return (DigestUtils.sha512Hex(new FileInputStream(f)));
    }

    throw (new UnsupportedOperationException(
        encryptionConfiguration.getDigestAlgorithm() + " is NOT supported."));
  }

  @Override
  public String compute(final byte[] data) throws IOException, NoSuchAlgorithmException {
    // TODO: use enums here
    if (DigestAlgorithm.SHA_1.equals(encryptionConfiguration.getDigestAlgorithm())) {
      return (DigestUtils.sha1Hex(data));
    }
    if (DigestAlgorithm.SHA_256.equals(encryptionConfiguration.getDigestAlgorithm())) {
      return (DigestUtils.sha256Hex(data));
    }
    if (DigestAlgorithm.SHA_512.equals(encryptionConfiguration.getDigestAlgorithm())) {
      return (DigestUtils.sha512Hex(data));
    }

    throw (new UnsupportedOperationException(
        encryptionConfiguration.getDigestAlgorithm() + " is NOT supported."));
  }

  public String hash(final byte[] data, int count) {
    throw (new UnsupportedOperationException("Not yet implemented."));

    //    final MessageDigest messageDigest =
    //            MessageDigest.getInstance(
    //                    encryptionConfiguration.getEncryptionAlgorithm().getAlgorithmName());
    //
    //    byte[] input = plaintext.getBytes();
    //    for (int i = 0; i < encryptionConfiguration.getHashIterations(); i++) {
    //      input = messageDigest.digest(input);
    //    }
    //
    //    return (input);
  }

  @Override
  public boolean matches(java.io.File file, String expectedChecksum)
      throws IOException, NoSuchAlgorithmException {
    return (compute(file).equals(expectedChecksum));
  }

  @Override
  public boolean matches(java.io.File file, java.io.File checksumFile)
      throws IOException, NoSuchAlgorithmException {
    return (matches(file, getFromFile(checksumFile)));
  }

  public String getSignatureFromFile(final File file) throws IOException {
    return getFromFile(file);
  }

  protected static String getFromFile(final String filename) throws IOException {
    return (getFromFile(new java.io.File(filename)));
  }

  protected static String getFromFile(final java.io.File file) throws IOException {
    final String content = FileUtils.readLines(file, "UTF-8").get(0);
    return (content.split(" ")[1]);
  }

  @Override
  public String computeSignature(byte[] data) throws IOException, NoSuchAlgorithmException {
    return (compute(data));
  }
}
