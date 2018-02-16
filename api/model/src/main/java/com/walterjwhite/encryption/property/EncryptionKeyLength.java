package com.walterjwhite.encryption.property;

import com.walterjwhite.google.guice.property.property.DefaultValue;
import com.walterjwhite.google.guice.property.property.GuiceProperty;

public interface EncryptionKeyLength extends GuiceProperty {
  @DefaultValue int L_256 = 256;
  int L_512 = 512;
}
