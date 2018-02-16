package com.walterjwhite.encryption.api.service;

public interface SaltService {
  byte[] generate();

  byte[] generate(int length);
}
