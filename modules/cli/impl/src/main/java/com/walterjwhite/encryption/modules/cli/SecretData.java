package com.walterjwhite.encryption.modules.cli;

import com.walterjwhite.encryption.model.EncryptedEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@AllArgsConstructor
public class SecretData {
  protected EncryptedEntity data;
}
