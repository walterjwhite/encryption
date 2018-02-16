package com.walterjwhite.encryption.api.service;

public interface RandomSequenceGenerator {
  String generate(int minLength, int maxLength);

  String generate(int minLength, int maxLength, String characters);
}
