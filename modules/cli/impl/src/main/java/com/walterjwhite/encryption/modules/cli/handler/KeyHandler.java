package com.walterjwhite.encryption.modules.cli.handler;

import com.walterjwhite.encryption.property.EncryptionKeyLength;
import com.walterjwhite.encryption.service.SaltService;
import com.walterjwhite.inject.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.impl.annotation.Property;
import java.io.*;
import javax.inject.Inject;

/** Helper to list our client id. */
public class KeyHandler extends AbstractCommandLineHandler {
  protected final SaltService saltService;

  @Inject
  public KeyHandler(
      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds,
      SaltService saltService) {
    super(shutdownTimeoutInSeconds);

    this.saltService = saltService;
  }

  @Override
  protected void doRun(final String... arguments) throws Exception {
    if (arguments.length == 0)
      throw new IllegalArgumentException("NO arguments were specified, specify at least 1.");

    // TODO: use only the non-processes arguments
    for (final String argument : arguments) {
      try (final FileOutputStream fos = new FileOutputStream(new File(argument))) {
        fos.write(saltService.generate(EncryptionKeyLength.L_256));
      }
    }
  }
}
