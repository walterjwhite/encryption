package com.walterjwhite.encryption.impl;

import com.google.inject.AbstractModule;
import com.walterjwhite.encryption.api.service.DigestService;
import com.walterjwhite.encryption.api.service.EncryptionService;
import com.walterjwhite.encryption.api.service.FieldEncryptionService;
import com.walterjwhite.encryption.api.service.SaltService;

public class EncryptionModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(SaltService.class).to(DefaultSaltService.class);
    bind(DigestService.class).to(DefaultDigestService.class);
    bind(EncryptionService.class).to(DefaultEncryptionService.class);
    bind(FieldEncryptionService.class).to(DefaultFieldEncryptionService.class);
    bind(EncryptionConfiguration.class).toProvider(EncryptionConfigurationProvider.class);

    //    bind(Cipher.class).toProvider(CipherProvider.class);
  }
}
