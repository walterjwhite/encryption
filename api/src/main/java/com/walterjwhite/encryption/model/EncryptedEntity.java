package com.walterjwhite.encryption.model;

import com.walterjwhite.encryption.enumeration.EncryptionType;
import com.walterjwhite.encryption.service.DigestService;
import com.walterjwhite.encryption.service.EncryptionService;
import com.walterjwhite.encryption.service.SaltService;
import com.walterjwhite.infrastructure.inject.core.helper.ApplicationHelper;
import com.walterjwhite.logging.annotation.Sensitive;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.persistence.*;
import lombok.*;
import org.apache.commons.codec.binary.Base64;

@NoArgsConstructor
@Data
@ToString(doNotUseGetters = true)
// @PersistenceAware
@Embeddable
public class EncryptedEntity /*implements StoreCallback, LoadCallback*/ {
  @Getter(onMethod_ = @Sensitive)
  @Setter(onMethod_ = @Sensitive)
  @Sensitive
  @ToString.Exclude /*@NotPersistent*/
  @Transient
  protected transient String plainText;

  @Enumerated(EnumType.STRING)
  @Column
  protected EncryptionType encryptionType;

  @ToString.Exclude @EqualsAndHashCode.Exclude @Lob @Column protected String cipherText;

  @Getter(onMethod_ = @Sensitive)
  @Setter(onMethod_ = @Sensitive)
  @Sensitive
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @Column
  protected String salt;

  @ManyToOne @JoinColumn protected EncryptionPolicy applicableEncryptionPolicy;

  public EncryptedEntity(String plainText, EncryptionType encryptionType) {
    this.plainText = plainText;
    this.encryptionType = encryptionType;
  }

  protected void doEncrypt() {
    try {
      if (EncryptionType.Encrypt.equals(encryptionType)) {
        //      final EncryptionService encryptionService =
        //          ApplicationHelper.getApplicationInstance().getInjector()
        //              .getInstance(EncryptionServiceProvider.class)
        //              .get(applicableEncryptionPolicy);

        // TODO: make this more sophisticated
        final EncryptionService encryptionService =
            ApplicationHelper.getApplicationInstance()
                .getInjector()
                .getInstance(EncryptionService.class);

        // cipherText =
        final ByteArrayOutputStream cipherOutputStream = new ByteArrayOutputStream();

        encryptionService.encrypt(
            new ByteArrayInputStream(plainText.getBytes(Charset.defaultCharset())),
            cipherOutputStream);
        cipherText = Base64.encodeBase64String(cipherOutputStream.toByteArray());
      } else {
        //      final DigestService digestService =
        //          ApplicationHelper.getApplicationInstance().getInjector()
        //              .getInstance(DigestServiceProvider.class)
        //              .get(applicableEncryptionPolicy);
        final DigestService digestService =
            ApplicationHelper.getApplicationInstance()
                .getInjector()
                .getInstance(DigestService.class);
        final SaltService saltService =
            ApplicationHelper.getApplicationInstance().getInjector().getInstance(SaltService.class);

        cipherText =
            digestService.compute(
                new ByteArrayInputStream(plainText.getBytes(Charset.defaultCharset())));
        final byte[] salt = saltService.generate();
        // salt = Base64.encodeBase64String(salt);
      }
    } catch (IOException | NoSuchAlgorithmException e) {
      throw new RuntimeException("Error encrypting entity", e);
    }
  }

  protected void doDecrypt() {
    try {
      if (EncryptionType.Encrypt.equals(encryptionType)) {
        //      final EncryptionService encryptionService =
        //          ApplicationHelper.getApplicationInstance().getInjector()
        //              .getInstance(EncryptionServiceProvider.class)
        //              .get(applicableEncryptionPolicy);
        final EncryptionService encryptionService =
            ApplicationHelper.getApplicationInstance()
                .getInjector()
                .getInstance(EncryptionService.class);

        final ByteArrayOutputStream plaintextStream = new ByteArrayOutputStream();
        encryptionService.decrypt(
            new ByteArrayInputStream(Base64.decodeBase64(cipherText)), plaintextStream);
        plainText = new String(plaintextStream.toByteArray(), Charset.defaultCharset());
      }
    } catch (InvalidAlgorithmParameterException | InvalidKeyException | IOException e) {
      throw new RuntimeException("Error decrypting entity", e);
    }
  }

  // @Override
  @PostLoad
  public void onPostLoad() {
    doDecrypt();
  }

  // @Override
  @PrePersist
  @PreUpdate
  public void onPreStore() {
    doEncrypt();
  }
}
