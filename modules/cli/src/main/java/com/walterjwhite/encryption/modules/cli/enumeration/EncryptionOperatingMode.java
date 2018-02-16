package com.walterjwhite.encryption.modules.cli.enumeration;

import com.walterjwhite.encryption.modules.cli.handler.DecryptHandler;
import com.walterjwhite.encryption.modules.cli.handler.EncryptHandler;
import com.walterjwhite.google.guice.cli.property.OperatingMode;
import com.walterjwhite.google.guice.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.google.guice.property.property.DefaultValue;

// TODO: while this works, this is a bad practice.
// I would like to automatically start any services that are needed
public enum EncryptionOperatingMode implements OperatingMode {
  @DefaultValue
  Encrypt("Encrypt input text", EncryptHandler.class),
  Decrypt("Send a message(s)", DecryptHandler.class);

  private final String description;
  private final Class<? extends AbstractCommandLineHandler> initiatorClass;

  EncryptionOperatingMode(
      String description, Class<? extends AbstractCommandLineHandler> initiatorClass) {
    this.description = description;
    this.initiatorClass = initiatorClass;
  }

  @Override
  public Class<? extends AbstractCommandLineHandler> getInitiatorClass() {
    return initiatorClass;
  }
}
