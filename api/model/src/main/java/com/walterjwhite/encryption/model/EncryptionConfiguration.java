// package com.walterjwhite.encryption.model;
//
// import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;
// import com.walterjwhite.encryption.enumeration.EncryptionDataType;
// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.EnumType;
// import javax.persistence.Enumerated;
//
// @Entity
// public class EncryptionConfiguration extends AbstractNamedEntity {
//  @Column protected byte[] data;
//
//  @Enumerated(EnumType.STRING)
//  @Column
//  protected EncryptionDataType encryptionDataType;
//
//  public EncryptionConfiguration(byte[] data, EncryptionDataType encryptionDataType) {
//    super();
//    this.data = data;
//    this.encryptionDataType = encryptionDataType;
//  }
//
//  public EncryptionConfiguration() {
//    super();
//  }
//
//  public byte[] getData() {
//    return data;
//  }
//
//  public void setData(byte[] data) {
//    this.data = data;
//  }
//
//  public EncryptionDataType getEncryptionDataType() {
//    return encryptionDataType;
//  }
//
//  public void setEncryptionDataType(EncryptionDataType encryptionDataType) {
//    this.encryptionDataType = encryptionDataType;
//  }
// }
