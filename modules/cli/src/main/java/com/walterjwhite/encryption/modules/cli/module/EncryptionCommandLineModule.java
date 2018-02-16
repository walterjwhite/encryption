package com.walterjwhite.encryption.modules.cli.module;

import com.walterjwhite.encryption.impl.EncryptionModule;
import com.walterjwhite.encryption.modules.cli.enumeration.EncryptionOperatingMode;
import com.walterjwhite.google.guice.cli.AbstractCommandLineModule;
import org.reflections.Reflections;

public class EncryptionCommandLineModule extends AbstractCommandLineModule {

  public EncryptionCommandLineModule(Reflections reflections) {
    super(reflections, EncryptionOperatingMode.class);
  }

  /**
   * FYI, at this point, properties should be injected, so we can do a dynamic configuration here
   * ...
   */
  @Override
  protected void doConfigure() {
    install(new EncryptionModule());
  }
}
