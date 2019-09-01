package com.walterjwhite.encryption.model;

import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;
import com.walterjwhite.encryption.enumeration.*;
import java.util.Set;
import javax.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(doNotUseGetters = true)
// @PersistenceCapable
@Entity
public class EncryptionPolicy extends AbstractNamedEntity {
  @EqualsAndHashCode.Exclude @ManyToOne @JoinColumn protected EncryptionPolicy parentPolicy;

  @EqualsAndHashCode.Exclude
  @OneToMany(mappedBy = "parentPolicy")
  protected Set<EncryptionPolicy> childrenPolicies;

  @Enumerated(EnumType.STRING)
  @Column
  protected CipherMode cipherMode;

  @Enumerated(EnumType.STRING)
  @Column
  protected DigestAlgorithm digestAlgorithm;

  @Enumerated(EnumType.STRING)
  @Column
  protected EncryptionAlgorithm encryptionAlgorithm;

  @Enumerated(EnumType.STRING)
  @Column
  protected PaddingType paddingType;

  @Enumerated(EnumType.STRING)
  @Column
  protected TransformationAlgorithm transformationAlgorithm;
}
