package com.walterjwhite.encryption.modules.cli.handler;

import com.walterjwhite.encryption.api.service.SaltService;
import com.walterjwhite.encryption.property.InitializationVectorLength;
import com.walterjwhite.inject.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.impl.annotation.Property;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.inject.Inject;

/** Helper to list our client id. */
public class IVHandler extends AbstractCommandLineHandler {
  protected final SaltService saltService;

  @Inject
  public IVHandler(
      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds,
      SaltService saltService) {
    super(shutdownTimeoutInSeconds);

    this.saltService = saltService;
  }

  @Override
  protected void doRun(final String... arguments) throws Exception {
    doRunInstance(arguments);
  }

  protected void doRunInstance(final String[] arguments) throws IOException {
    for (final String argument : arguments) {
      doRunInstance(argument);
    }
  }

  protected void doRunInstance(final String argument) throws IOException {
    try (final FileOutputStream fos = new FileOutputStream(new File(argument))) {
      fos.write(saltService.generate(InitializationVectorLength.B_16));
    }
  }
}
