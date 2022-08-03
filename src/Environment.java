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
  private int control;
  private int[] blackNotes;
  private byte[] binaryNotes;

  private JFrame frame;
  private JButton btnStep;
  private JButton btnStart;
  private JButton btnPause;
  private JButton btnReset;
  private JPanel mainPanel;

  private Sound sound;
  private Graphics graphics;
  private Timer timer;

  private Note[][] notes;
  private Note[] intensityBar;

  public Environment(int rows, int columns, int noteWidth, int noteHeight) {
    this.rows = rows;
    this.columns = columns;
    this.noteWidth = noteWidth;
    this.noteHeight = noteHeight;
    this.control = 0;
    this.blackNotes = new int[] { 1,4,6,9,11,13,16,18,21,23,25,28,30,33,35,37,40,42,45,
      47,49,52,54,57,59,61,64,66,69,71,73,76,78,81,83,85 };
    this.frame = new JFrame();
    this.btnStep = new JButton();
    this.btnStart = new JButton();
    this.btnPause = new JButton();
    this.btnReset = new JButton();
    this.mainPanel = new JPanel();
    this.sound = new Sound("src/sources/Avicii - Waiting for love (440 Hz).poly");
    this.graphics = new Graphics(this.frame);
    this.binaryNotes = this.sound.getData();
    this.timer = new Timer(20, e -> this.oneStep());
    this.step();
    this.start();
    this.pause();
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
    int y = 39;
    this.intensityBar = new Note[this.columns];
    for (int column = 0; column < this.columns; ++column){
      Note note = new Note(Color.black, new JPanel(), x, y, 0);
      this.intensityBar[column] = note;
      graphics.addNote(note, x, y, this.noteWidth, this.noteHeight);
      x += noteWidth;
    }
  }

  private void resetEnvironment() {
    this.timer.stop();
    this.control = 0;
    Note regularNote;
    Note intensityNote;
    for (int column = 0; column < this.columns; ++column) {
      for (int row = 0; row < this.rows; ++row) {
        regularNote = this.notes[row][column];
        this.graphics.resetRegularNote(regularNote, Color.black, 0);
      }
      intensityNote = this.intensityBar[column];
      this.graphics.resetIntensityBarNote(intensityNote, Color.black, 0, intensityNote.getX(), 0, this.noteWidth, 45);
    }
  }

  private boolean validateBlackNote(int number) {
    boolean answer = false;
    for (int index : this.blackNotes) {
      if (number == index) {
        answer = true;
        break;
      }
    }
    return answer;
  }

  private void oneStep() {
    Note currentNote;
    Note father;
    Note lastNote;
    Note intensityNote;
    for (int column = 0; column < this.columns; ++column) {  
      for (int row = this.rows - 1; row > 0; --row) {
        currentNote = this.notes[row][column];
        father = this.notes[row - 1][column];
        lastNote = this.notes[this.rows - 1][column];
        intensityNote = this.intensityBar[column];
        this.graphics.inheritNoteProperties(currentNote, father);
        this.graphics.manageIntensity(lastNote, intensityNote, this.noteWidth);
      }
      try {
        if (this.control >= this.binaryNotes.length) {
          ImageIcon icon = new ImageIcon(getClass().getResource("sources/icon.png"));
            JOptionPane.showMessageDialog(null, "The song is over, press start to play again",
              "Information",JOptionPane.INFORMATION_MESSAGE, icon);
          this.resetEnvironment();
          break;
        } else {
          ++this.control;
          boolean isBlackNote = this.validateBlackNote(column);
          this.graphics.changeNoteColor(this.notes[0][column], this.binaryNotes[this.control], isBlackNote);
        }
      } catch (Exception e) {
        System.out.println(e);
      }
    }
  }

  private void step() {
    this.btnStep.addActionListener(evt -> {
      this.oneStep();
    });
  }

  private void start() {
    this.btnStart.addActionListener(evt -> {
      this.timer.start();
    });
  }

  private void pause() {
    this.btnPause.addActionListener(evt -> {
      this.timer.stop();
    });
  }
  
}
