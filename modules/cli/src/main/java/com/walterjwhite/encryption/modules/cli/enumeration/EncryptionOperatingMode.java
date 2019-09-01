package com.walterjwhite.encryption.modules.cli.enumeration;

import com.walterjwhite.encryption.modules.cli.handler.DecryptHandler;
import com.walterjwhite.encryption.modules.cli.handler.EncryptHandler;
import com.walterjwhite.encryption.modules.cli.handler.IVHandler;
import com.walterjwhite.encryption.modules.cli.handler.KeyHandler;
import com.walterjwhite.inject.cli.property.OperatingMode;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.api.annotation.DefaultValue;

// TODO: while this works, this is a bad practice.
// I would like to automatically start any services that are needed
public enum EncryptionOperatingMode implements OperatingMode {
  @DefaultValue
  Encrypt("Encrypt input text", EncryptHandler.class),
  Decrypt("Send a message(s)", DecryptHandler.class),
  Digest("One-way hash", DecryptHandler.class),
  Key("Generate encryption key", KeyHandler.class),
  IV("Generate encryption IV", IVHandler.class);

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
