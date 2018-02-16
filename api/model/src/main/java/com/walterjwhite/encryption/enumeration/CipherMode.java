package com.walterjwhite.encryption.enumeration;

import com.walterjwhite.google.guice.property.property.DefaultValue;

public enum CipherMode {
  @DefaultValue
  CBC,
  ECB
}
