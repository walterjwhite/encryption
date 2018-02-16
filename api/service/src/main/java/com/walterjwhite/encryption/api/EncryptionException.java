package com.walterjwhite.encryption.api;

public class EncryptionException extends RuntimeException {
  public EncryptionException(Exception e) {
    super(e);
  }
}
