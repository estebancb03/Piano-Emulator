/// @copyright 2022 Esteban Castañeda Blanco. All rights reserved
/// This code is released under the GNU Public License version 3
/// @author Esteban Castañeda Blanco <esteban.castaneda@ucr.ac.cr>

import java.awt.*;
import javax.swing.*;

public class Environment extends JFrame {
  /// Properties
  private int rows;
  private int columns;
  private JFrame frame;
  private JButton btnStep;
  private JButton btnStart;
  private JButton btnPause;
  private JButton btnReset;
  private JPanel mainPanel;
  private Graphics graphics;

  public Environment(int rows, int columns) {
    this.rows = rows;
    this.columns = columns;
    graphics = new Graphics();
    this.frame = new JFrame();
    graphics.frameInit(this.frame);
    this.frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
  }
}
