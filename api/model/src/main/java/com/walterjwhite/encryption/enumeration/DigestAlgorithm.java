package com.walterjwhite.encryption.enumeration;

import com.walterjwhite.google.guice.property.property.DefaultValue;
import com.walterjwhite.google.guice.property.property.GuiceProperty;

public enum DigestAlgorithm implements GuiceProperty {
  MD5,
  SHA_1,
  @DefaultValue
  SHA_256,
  SHA_384,
  SHA_512;

  public String getAlgorithmName() {
    return (name().replace("_", "-"));
  }
  //
  //  @Override
  //  public String getDefaultValue() {
  //    return SHA_256.getName();
  //  }
  //
  //  @Override
  //  public String getDescription() {
  //    return "Digest algorithm";
  //  }
  //
  //  @Override
  //  public String getName() {
  //    return getClass().getName();
  //  }
}
