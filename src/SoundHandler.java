/// @copyright 2022 Esteban Castañeda Blanco. All rights reserved
/// This code is released under the GNU Public License version 3
/// @author Esteban Castañeda Blanco <esteban.castaneda@ucr.ac.cr>

import java.io.File;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

public class SoundHandler {
  private BasicPlayer player;

  public SoundHandler(String route) {
    this.player = new BasicPlayer();
    this.openSoundFile(route);
  }

  private void openSoundFile(String route) {
    try {
      this.player.open(new File(route));
    } catch (BasicPlayerException e) {
      System.err.println(e);
    }
  }

  public BasicPlayer getPlayer() { return this.player; }
}
