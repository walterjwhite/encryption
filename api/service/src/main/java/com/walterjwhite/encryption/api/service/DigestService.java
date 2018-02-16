package com.walterjwhite.encryption.api.service;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface DigestService {
  String compute(final File file) throws IOException, NoSuchAlgorithmException;

  String compute(final byte[] data) throws IOException, NoSuchAlgorithmException;

  /** Uses iv + key to "sign" the signature of the data */
  String computeSignature(final byte[] data) throws IOException, NoSuchAlgorithmException;

  boolean matches(final File file, final String expectedChecksum)
      throws IOException, NoSuchAlgorithmException;

  boolean matches(final File file, final File checksumFile)
      throws IOException, NoSuchAlgorithmException;

  String getSignatureFromFile(final File file) throws IOException;
}
