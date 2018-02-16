package com.walterjwhite.encryption.modules.aes;

import com.google.inject.Injector;
import com.walterjwhite.encryption.api.service.DigestService;
import com.walterjwhite.encryption.api.service.EncryptionService;
import java.io.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultEncryptionServiceTest {
  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultEncryptionServiceTest.class);

  private static Injector GUICE_INJECTOR;

  protected EncryptionService encryptionService;
  protected DigestService digestService;
  // need to generate reflectionx.xml
  //  @Before
  //  public void before() throws Exception {
  //    GuiceHelper.addModules(new EncryptionModule(), new GuiceTestModule());
  //    GuiceHelper.setup();
  //    GUICE_INJECTOR = GuiceHelper.getGuiceInjector();
  //
  //    encryptionService = GUICE_INJECTOR.getInstance(EncryptionService.class);
  //    digestService = GUICE_INJECTOR.getInstance(DigestService.class);
  //  }
  //
  //  @Test
  //  public void testEncryption()
  //      throws IOException, InvalidAlgorithmParameterException, InvalidKeyException {
  //    final byte[] rawData = new byte[1024];
  //    for (int i = 0; i < rawData.length; i++) {
  //      rawData[i] = 0;
  //    }
  //
  //    final File sourceFile = new File("/tmp/plain-test");
  //    IOUtils.write(rawData, new FileOutputStream(sourceFile));
  //
  //    final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
  //    encryptionService.encrypt(new ByteArrayInputStream(rawData), byteArrayOutputStream);
  //
  //    final File outputFile = new File("/tmp/cipher-test");
  //    IOUtils.write(byteArrayOutputStream.toByteArray(), new FileOutputStream(outputFile));
  //
  //    sourceFile.delete();
  //    outputFile.delete();
  //  }
  //
  //  @Test
  //  public void testDigest() throws IOException, NoSuchAlgorithmException {
  //    LOGGER.info(
  //        "computed:"
  //            + digestService.compute(
  //                new File(
  //                    "/usr/bin/java")));
  //  }
}
