package com.walterjwhite.encryption.impl;

import com.walterjwhite.encryption.enumeration.*;

public class EncryptionConfiguration {
  protected final int saltLength;
  protected final int keyLength;
  protected final int hashIterations;
  protected final int ivLength;

  protected final EncryptionAlgorithm encryptionAlgorithm;
  protected final TransformationAlgorithm transformationAlgorithm;
  protected final PaddingType paddingType;
  protected final DigestAlgorithm digestAlgorithm;

  public EncryptionConfiguration(
      EncryptionAlgorithm encryptionAlgorithm,
      TransformationAlgorithm transformationAlgorithm,
      PaddingType paddingType,
      DigestAlgorithm digestAlgorithm,
      int saltLength,
      int keyLength,
      int hashIterations,
      int ivLength) {
    super();
    this.encryptionAlgorithm = encryptionAlgorithm;
    this.transformationAlgorithm = transformationAlgorithm;
    this.paddingType = paddingType;
    this.digestAlgorithm = digestAlgorithm;

    this.saltLength = saltLength;
    this.keyLength = keyLength;
    this.hashIterations = hashIterations;
    this.ivLength = ivLength;
  }

  public EncryptionAlgorithm getEncryptionAlgorithm() {
    return encryptionAlgorithm;
  }

  public TransformationAlgorithm getTransformationAlgorithm() {
    return transformationAlgorithm;
  }

  public PaddingType getPaddingType() {
    return paddingType;
  }

  public DigestAlgorithm getDigestAlgorithm() {
    return digestAlgorithm;
  }

  public int getSaltLength() {
    return saltLength;
  }

  public int getKeyLength() {
    return keyLength;
  }

  public int getHashIterations() {
    return hashIterations;
  }

  public int getIvLength() {
    return ivLength;
  }
}
