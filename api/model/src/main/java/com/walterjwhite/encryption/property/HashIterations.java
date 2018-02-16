package com.walterjwhite.encryption.property;

import com.walterjwhite.google.guice.property.property.DefaultValue;
import com.walterjwhite.google.guice.property.property.GuiceProperty;

public interface HashIterations extends GuiceProperty {
  //  1000,
  // 5000,
  // 50000;
  @DefaultValue int Default = 50000;
}
