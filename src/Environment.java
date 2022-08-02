/// @copyright 2022 Esteban Castañeda Blanco. All rights reserved
/// This code is released under the GNU Public License version 3
/// @author Esteban Castañeda Blanco <esteban.castaneda@ucr.ac.cr>

import java.awt.*;
import javax.swing.*;

public class Environment extends JFrame {
  /// Properties
  private int rows;
  private int columns;
  private int noteWidth;
  private int noteHeight;
  private JFrame frame;
  private JButton btnStep;
  private JButton btnStart;
  private JButton btnPause;
  private JButton btnReset;
  private JPanel mainPanel;
  private Graphics graphics;

  private Note[][] notes;

  public Environment(int rows, int columns, int noteWidth, int noteHeight) {
    this.rows = rows;
    this.columns = columns;
    this.noteWidth = noteWidth;
    this.noteHeight = noteHeight;
    this.frame = new JFrame();
    this.btnStep = new JButton();
    this.btnStart = new JButton();
    this.btnPause = new JButton();
    this.btnReset = new JButton();
    this.mainPanel = new JPanel();
    this.graphics = new Graphics();
    this.frame.add(this.btnStep);
    this.frame.add(this.btnStart);
    this.frame.add(this.btnPause);
    this.frame.add(this.btnReset);
    this.frame.add(this.mainPanel);
    this.graphics.buttonInit(btnStep, "STEP", 0, 901, 196, 60);
    this.graphics.buttonInit(btnStart, "START", 196, 901, 196, 60);
    this.graphics.buttonInit(btnPause, "PAUSE", 392, 901, 196, 60);
    this.graphics.buttonInit(btnReset, "RESET", 588, 901, 196, 60);
    this.graphics.panelInit(this.mainPanel);
    this.graphics.frameInit(this.frame, "Piano Polynizer", 800, 1000);
    this.frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

    notes = new Note[this.rows][this.columns];
  }
}
