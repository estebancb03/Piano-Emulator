/// @copyright 2022 Esteban Castañeda Blanco. All rights reserved
/// This code is released under the GNU Public License version 3
/// @author Esteban Castañeda Blanco <esteban.castaneda@ucr.ac.cr>

import java.io.File;
import java.nio.file.*;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

public class SoundHandler {
  private byte[] binaryData;
  private BasicPlayer player;

  public SoundHandler(String route) {
    this.player = new BasicPlayer();
    this.openBinaryFile(route + ".poly");
    this.openSoundFile(route + ".mp3");
  }

  private void openBinaryFile(String route) {
    Path path = Paths.get(route);
    try {
      this.binaryData = Files.readAllBytes(path);
    } catch (Exception e) {
      System.err.println(e);
    }
  }

  private void openSoundFile(String route) {
    try {
      this.player.open(new File(route));
    } catch (BasicPlayerException e) {
      System.err.println(e);
    }
  }

  public byte[] getBinaryData() { return this.binaryData; }
  public BasicPlayer getPlayer() { return this.player; }
}
