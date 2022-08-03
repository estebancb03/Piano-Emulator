/// @copyright 2022 Esteban Castañeda Blanco. All rights reserved
/// This code is released under the GNU Public License version 3
/// @author Esteban Castañeda Blanco <esteban.castaneda@ucr.ac.cr>

import java.io.File;
import java.nio.file.*;
import java.io.IOException;

public class Sound {
  /// Properties
  private byte[] data;
  private String dataRoute;

  public Sound(String dataRoute) {
    this.dataRoute = dataRoute;
    this.readData();
  }

  private void readData() {
    Path path = Paths.get(this.dataRoute);
    try {
      this.data = Files.readAllBytes(path);
    } catch (IOException e) {
      System.out.println(e);
    }
  }

  /// Getters
  public byte[] getData() { return this.data; }
}
