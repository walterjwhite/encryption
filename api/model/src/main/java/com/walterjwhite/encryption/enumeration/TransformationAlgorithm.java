package com.walterjwhite.encryption.enumeration;

import com.walterjwhite.google.guice.property.property.DefaultValue;
import com.walterjwhite.google.guice.property.property.GuiceProperty;

// http://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#Cipher
public enum TransformationAlgorithm implements GuiceProperty {
  @DefaultValue
  CBC;

  //  @Override
  //  public String getDefaultValue() {
  //    return CBC.getName();
  //  }
  //
  //  @Override
  //  public String getDescription() {
  //    return "Transformation algorithm";
  //  }
  //
  //  @Override
  //  public String getName() {
  //    return getClass().getName();
  //  }
}
