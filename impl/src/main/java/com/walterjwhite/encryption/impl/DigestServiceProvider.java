package com.walterjwhite.encryption.impl;

import com.walterjwhite.encryption.impl.digest.DigestMapping;
import com.walterjwhite.encryption.service.DigestService;
import javax.inject.Inject;
import javax.inject.Provider;

public class DigestServiceProvider implements Provider<DigestService> {
  protected final DigestService digestService;

  @Inject
  public DigestServiceProvider(EncryptionConfiguration encryptionConfiguration) {
    digestService =
        DigestMapping.get(encryptionConfiguration.getDigestAlgorithm())
            .get(encryptionConfiguration);
  }

  @Override
  public DigestService get() {
    return digestService;
  }
}
