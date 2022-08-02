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
  private Note[] intensityBar;

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
    this.graphics = new Graphics(this.frame);
    this.createNotes();
    this.createIntesityBar();
    this.graphics.buttonInit(btnStep, "STEP", 0, 945, 206, 66);
    this.graphics.buttonInit(btnStart, "START", 206, 945, 206, 66);
    this.graphics.buttonInit(btnPause, "PAUSE", 412, 945, 206, 66);
    this.graphics.buttonInit(btnReset, "RESET", 618, 945, 206, 66);
    this.graphics.mainPanelInit(this.mainPanel);
    this.graphics.frameInit("Piano Polynizer", 840, 1050);
    this.frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  private void createNotes() {
    int x = 16;
    int y = 930;
    this.notes = new Note[this.rows][this.columns];
    for (int row = 0; row < this.rows; ++row) {
      for (int column = 0; column < this.columns; ++column) {
        Note note = new Note(Color.black, new JPanel(), x, y, 0);
        this.notes[row][column] = note;
        this.graphics.addNote(note, x, y, this.noteWidth, this.noteHeight);
        x += this.noteWidth;
      }
      x = 16;
      y -= this.noteHeight;
    }
  }

  private void createIntesityBar() {
    int x = 16;
    int y = 5;
    this.intensityBar = new Note[this.columns];
    for (int column = 0; column < this.columns; ++column){
      Note note = new Note(Color.black, new JPanel(), x, y, 0);
      this.intensityBar[column] = note;
      graphics.addNote(note, x, y, this.noteWidth, 45);
      x += noteWidth;
    }
  }
}
