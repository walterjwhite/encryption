package com.walterjwhite.encryption.property;

import com.walterjwhite.google.guice.property.property.DefaultValue;
import com.walterjwhite.google.guice.property.property.GuiceProperty;

public interface InitializationVectorLength extends GuiceProperty {
  int B_8 = 8;
  int B_16 = 16;
  @DefaultValue int B_96 = 96;
}
