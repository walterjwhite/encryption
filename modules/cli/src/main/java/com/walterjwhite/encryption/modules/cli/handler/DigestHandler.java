package com.walterjwhite.encryption.modules.cli.handler;

import com.walterjwhite.encryption.api.service.FieldEncryptionService;
import com.walterjwhite.encryption.enumeration.EncryptionType;
import com.walterjwhite.encryption.modules.cli.SecretData;
import com.walterjwhite.inject.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.logging.annotation.Sensitive;
import com.walterjwhite.property.impl.annotation.Property;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.inject.Inject;

/** Helper to list our client id. */
public class DigestHandler extends AbstractCommandLineHandler {
  protected final FieldEncryptionService fieldEncryptionService;

  @Inject
  public DigestHandler(
      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds,
      FieldEncryptionService fieldEncryptionService) {
    super(shutdownTimeoutInSeconds);
    this.fieldEncryptionService = fieldEncryptionService;
  }

  @Override
  protected void doRun(final String... arguments) throws Exception {
    final SecretData secretData = new SecretData(getInput());

    doDigest(secretData);
    showResult(secretData);
  }

  @Sensitive
  protected String getInput() throws IOException {
    return new BufferedReader(new InputStreamReader(System.in)).readLine();
  }

  protected void doDigest(SecretData secretData) throws NoSuchFieldException {
    fieldEncryptionService.encrypt(
        secretData, SecretData.class.getDeclaredField("password"), EncryptionType.Digest);
  }

  protected void showResult(SecretData secretData) {
    System.out.println(secretData.getPasswordEncrypted());
    System.out.println(secretData.getPasswordSalt());
  }
}
