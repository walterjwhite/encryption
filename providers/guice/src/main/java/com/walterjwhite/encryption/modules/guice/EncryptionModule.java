package com.walterjwhite.encryption.modules.guice;

import com.google.inject.AbstractModule;
import com.walterjwhite.encryption.impl.*;
import com.walterjwhite.encryption.service.DigestService;
import com.walterjwhite.encryption.service.EncryptionService;
import com.walterjwhite.encryption.service.FieldEncryptionService;
import com.walterjwhite.encryption.service.SaltService;

public class EncryptionModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(SaltService.class).to(DefaultSaltService.class);
    bind(DigestService.class).toProvider(DigestServiceProvider.class);
    bind(EncryptionService.class).to(DefaultEncryptionService.class);
    bind(FieldEncryptionService.class).to(DefaultFieldEncryptionService.class);
    bind(EncryptionConfiguration.class).toProvider(EncryptionConfigurationProvider.class);

    //    bind(Cipher.class).toProvider(CipherProvider.class);
  }
}
