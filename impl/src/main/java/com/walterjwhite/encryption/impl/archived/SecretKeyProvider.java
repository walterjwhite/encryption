// package com.walterjwhite.encryption.impl;
//
// import com.walterjwhite.datastore.encryption.dto.EncryptionConfigurationDatastore;
// import java.io.IOException;
// import javax.crypto.SecretKey;
// import javax.crypto.spec.SecretKeySpec;
// import javax.inject.Inject;
// import javax.inject.Provider;
// import sun.misc.BASE64Decoder;
//
// public class SecretKeyProvider implements Provider<SecretKey> {
//  protected final SecretKey secretKey;
//
//  @Inject
//  public SecretKeyProvider(EncryptionConfigurationDatastore encryptionConfiguration)
//      throws IOException {
//    super();
//    final String secretPassword = encryptionConfiguration.getPassword();
//    secretKey =
//        new SecretKeySpec(
//            new BASE64Decoder().decodeBuffer(secretPassword),
//            encryptionConfiguration.getEncryptionAlgorithm().getAlgorithmName());
//  }
//
//  //TODO: get the secret key after prompting the user for the session key passphrase
//  @Override
//  public SecretKey get() {
//    return (secretKey);
//  }
// }
