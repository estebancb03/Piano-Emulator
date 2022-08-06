/// @copyright 2022 Esteban Castañeda Blanco. All rights reserved
/// This code is released under the GNU Public License version 3
/// @author Esteban Castañeda Blanco <esteban.castaneda@ucr.ac.cr>

import java.nio.file.*;

/**
 * @brief Class that handles binary and sound files
 */
public class SoundHandler {
  /// Array with all song's notes
  private byte[] binaryData;

  /**
   * @brief Constructor
   * @param route General route of the files
   */
  public SoundHandler(String route) {
    this.openBinaryFile(route + ".poly");
  }

  /**
   * @brief Method that saves the binary file information in a byte array
   * @param route File's route
   */
  private void openBinaryFile(String route) {
    Path path = Paths.get(route);
    try {
      this.binaryData = Files.readAllBytes(path);
    } catch (Exception e) {
      System.err.println(e);
    }
  }

  /**
   * @brief Method that returns the binary data array
   * @return byte[]
   */
  public byte[] getBinaryData() { return this.binaryData; }
}
