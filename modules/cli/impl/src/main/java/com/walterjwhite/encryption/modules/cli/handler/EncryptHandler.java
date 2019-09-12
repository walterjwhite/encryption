package com.walterjwhite.encryption.modules.cli.handler;

import com.walterjwhite.encryption.service.EncryptionService;
import com.walterjwhite.inject.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.logging.annotation.Sensitive;
import com.walterjwhite.property.impl.annotation.Property;
import java.io.*;
import java.nio.charset.Charset;
import javax.inject.Inject;

/** Helper to list our client id. */
public class EncryptHandler extends AbstractCommandLineHandler {
  protected final EncryptionService encryptionService;

  @Inject
  public EncryptHandler(
      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds,
      EncryptionService encryptionService) {
    super(shutdownTimeoutInSeconds);
    this.encryptionService = encryptionService;
  }

  @Override
  protected void doRun(final String... arguments) throws Exception {
    System.out.println(doEncrypt());
  }

  @Sensitive
  protected String getInput() throws IOException {
    return new BufferedReader(new InputStreamReader(System.in)).readLine();
  }

  protected String doEncrypt() throws IOException {
    final ByteArrayOutputStream baos = new ByteArrayOutputStream();
    encryptionService.encrypt(
        new ByteArrayInputStream(getInput().getBytes(Charset.defaultCharset())), baos);

    return new String(baos.toByteArray());
  }
}
