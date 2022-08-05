/// @copyright 2022 Esteban Castañeda Blanco. All rights reserved
/// This code is released under the GNU Public License version 3
/// @author Esteban Castañeda Blanco <esteban.castaneda@ucr.ac.cr>

import java.io.File;
import java.nio.file.*;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

/**
 * @brief Class that handles binary and sound files
 */
public class SoundHandler {
  /// Array with all song's notes
  private byte[] binaryData;
  /// MP3 player
  private BasicPlayer player;

  /**
   * @brief Constructor
   * @param route General route of the files
   */
  public SoundHandler(String route) {
    this.player = new BasicPlayer();
    this.openBinaryFile(route + ".poly");
    this.openSoundFile(route + ".mp3");
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
   * @brief Method that opens the .mp3 file and assigns it to the player
   * @param route File's route
   */
  private void openSoundFile(String route) {
    try {
      this.player.open(new File(route));
    } catch (BasicPlayerException e) {
      System.err.println(e);
    }
  }

  /**
   * @brief Method that returns the binary data array
   * @return byte[]
   */
  public byte[] getBinaryData() { return this.binaryData; }

  /**
   * @brief Method that returns the MP3 player
   * @return BasicPlayer
   */
  public BasicPlayer getPlayer() { return this.player; }
}
