package com.walterjwhite.encryption.modules.cli.handler;

import com.walterjwhite.encryption.api.service.EncryptionService;
import com.walterjwhite.google.guice.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.google.guice.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.google.guice.property.property.Property;
import javax.inject.Inject;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Helper to list our client id. */
public class DecryptHandler extends AbstractCommandLineHandler {
  private static final Logger LOGGER = LoggerFactory.getLogger(DecryptHandler.class);

  protected final EncryptionService encryptionService;

  @Inject
  public DecryptHandler(
      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds,
      EncryptionService encryptionService) {
    super(shutdownTimeoutInSeconds);
    this.encryptionService = encryptionService;
  }

  @Override
  public void run(final String... arguments) throws Exception {
    if (arguments == null || arguments.length == 0) {
      System.err.println("Please specify at least 1 argument to encrypt");
      System.exit(1);
    } else {
      for (final String argument : arguments) {
        System.out.println(new String(encryptionService.decrypt(Base64.decodeBase64(argument))));
      }
    }
  }
}
