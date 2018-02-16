package com.walterjwhite.encryption.impl;

import com.walterjwhite.encryption.api.service.SaltService;
import com.walterjwhite.encryption.property.EncryptionSaltLength;
import com.walterjwhite.google.guice.property.property.Property;
import java.security.SecureRandom;
import javax.inject.Inject;

public class DefaultSaltService implements SaltService {
  protected final int saltLength;
  protected final SecureRandom secureRandom = new SecureRandom();

  @Inject
  public DefaultSaltService(@Property(EncryptionSaltLength.class) int saltLength) {
    super();
    this.saltLength = saltLength;
  }

  @Override
  public byte[] generate() {
    final byte[] random = new byte[saltLength];
    secureRandom.nextBytes(random);
    return (random);
  }

  @Override
  public byte[] generate(int length) {
    final byte[] random = new byte[length];
    secureRandom.nextBytes(random);
    return (random);
  }
}
