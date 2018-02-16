package com.walterjwhite.encryption.modules.aes;

import com.walterjwhite.encryption.impl.DefaultRandomSequenceGenerator;
import org.junit.Test;

public class TestDefaultRandomSequenceGenerator {

  @Test
  public void test1() {
    final DefaultRandomSequenceGenerator randomSequenceGenerator =
        new DefaultRandomSequenceGenerator();
    System.out.println(randomSequenceGenerator.generate(8, 12));

    doPrivateCall();
    doProtectedCall();
  }
  //
  //  @Test
  //  public void test2() {
  //    final DefaultRandomSequenceGenerator randomSequenceGenerator =
  //        new DefaultRandomSequenceGenerator();
  //    System.out.println(
  //        randomSequenceGenerator.generate(
  //            8, 12, /*new char[][] {{'a', 'z'}, {'A', 'Z'}, {'0', '9'}}*/ "abcdefg"));
  //  }

  protected void doProtectedCall() {}

  private void doPrivateCall() {}
}
