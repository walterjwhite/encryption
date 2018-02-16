package com.walterjwhite.encryption.property;

import com.walterjwhite.google.guice.property.property.DefaultValue;
import com.walterjwhite.google.guice.property.property.GuiceProperty;

public interface EncryptionSaltLength extends GuiceProperty {
  @DefaultValue int L_8 = 8;
  int L16 = 16;
}
