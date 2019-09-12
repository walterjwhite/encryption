package com.walterjwhite.encryption.modules.cli.handler;

import com.walterjwhite.encryption.service.EncryptionService;
import com.walterjwhite.inject.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.impl.annotation.Property;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import javax.inject.Inject;
import org.apache.commons.codec.binary.Base64;

/** Helper to list our client id. */
public class DecryptHandler extends AbstractCommandLineHandler {
  protected final EncryptionService encryptionService;

  @Inject
  public DecryptHandler(
      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds,
      EncryptionService encryptionService) {
    super(shutdownTimeoutInSeconds);
    this.encryptionService = encryptionService;
  }

  @Override
  protected void doRun(final String... arguments)
      throws InvalidAlgorithmParameterException, InvalidKeyException, IOException {
    validateInput(arguments);
    doRunInstance(arguments);
  }

  protected void validateInput(final String[] arguments) {
    if (arguments == null || arguments.length == 0) {
      System.err.println("Please specify at least 1 argument to encrypt");
      System.exit(1);
    }
  }

  protected void doRunInstance(final String[] arguments)
      throws InvalidAlgorithmParameterException, InvalidKeyException, IOException {
    for (final String argument : arguments) {
      doRunInstance(argument);
    }
  }

  protected void doRunInstance(final String argument)
      throws InvalidAlgorithmParameterException, InvalidKeyException, IOException {
    final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    encryptionService.decrypt(
        new ByteArrayInputStream(Base64.decodeBase64(argument)), byteArrayOutputStream);
    System.out.println(new String(byteArrayOutputStream.toByteArray()));
  }
}
