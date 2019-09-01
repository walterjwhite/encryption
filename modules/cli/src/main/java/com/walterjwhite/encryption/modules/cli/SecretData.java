package com.walterjwhite.encryption.modules.cli;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class SecretData {
  protected EncryptedEntity data;
}
