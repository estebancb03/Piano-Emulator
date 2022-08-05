/// @copyright 2022 Esteban Castañeda Blanco. All rights reserved
/// This code is released under the GNU Public License version 3
/// @author Esteban Castañeda Blanco <esteban.castaneda@ucr.ac.cr>

import java.awt.*;
import javax.swing.*;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

/**
 * @brief Class that handles the algorithms and modification of arrays and arrays
 */
public class Environment {
  /// Handles the mp3 player state
  private boolean state;
  /// Number of rows
  private int rows;
  /// Number of columns
  private int columns;
  /// Width of the note's JPanel
  private int noteWidth;
  /// Height of the note's JPanel
  private int noteHeight;
  /// Indicates the current possition in the binary notes array
  private int control;
  /// Array with the black notes columns
  private int[] blackNotes;
  /// Array with all song's notes
  private byte[] binaryNotes;
  /// Window's frame
  private JFrame frame;
  /// Main JPanel
  private JPanel mainPanel;
  /// Button that advances a step of the song
  private JButton stepButton;
  /// Button that starts the song
  private JButton playButton;
  /// Button that pauses the song
  private JButton pauseButton;
  /// Button  that resets the song
  private JButton resetButton;
  /// MP3 player
  private BasicPlayer player;
  /// Timer object that handles the events
  private Timer timer;
  /// GraphicsHandler object
  private GraphicsHandler graphicsHandler;
  /// SoundHandler object
  private SoundHandler soundHandler;
  /// Note's array for the intensity bar
  private Note[] intensityBar;
  /// Note's matrix
  private Note[][] notes;

  /**
   * @brief Constructor
   * @param rows Number of rows
   * @param columns Number of columns
   * @param noteWidth Width of note's panel
   * @param noteHeight Height of note's panel
   */
  public Environment(int rows, int columns, int noteWidth, int noteHeight) {
    this.state = false;
    this.rows = rows;
    this.columns = columns;
    this.noteWidth = noteWidth;
    this.noteHeight = noteHeight;
    this.control = 0;
    this.blackNotes = new int[] { 1,4,6,9,11,13,16,18,21,23,25,28,30,33,35,37,40,42,45,
      47,49,52,54,57,59,61,64,66,69,71,73,76,78,81,83,85 };
    this.frame = new JFrame();
    this.stepButton = new JButton();
    this.playButton = new JButton();
    this.pauseButton = new JButton();
    this.resetButton = new JButton();
    this.mainPanel = new JPanel();
    this.graphicsHandler = new GraphicsHandler(this.frame);
    this.soundHandler = new SoundHandler("resources/Rimsky Korsakov - Flight of the bumblebee (arr. Rachmaninoff) (439 Hz)");
    this.binaryNotes = this.soundHandler.getBinaryData();
    this.player = soundHandler.getPlayer();
    this.timer = new Timer(15, evt -> this.oneStep());
    this.step();
    this.play();
    this.pause();
    this.reset();
    this.createNotes();
    this.createIntensityBar();
    this.graphicsHandler.buttonInit(stepButton, "STEP", 0, 945, 206, 66);
    this.graphicsHandler.buttonInit(playButton, "PLAY", 206, 945, 206, 66);
    this.graphicsHandler.buttonInit(pauseButton, "PAUSE", 412, 945, 206, 66);
    this.graphicsHandler.buttonInit(resetButton, "RESET", 618, 945, 206, 66);
    this.graphicsHandler.mainPanelInit(this.mainPanel);
    this.graphicsHandler.frameInit("Piano Emulator", 840, 1050);
  }

  /**
   * @brief Method that creates a new note and shows it in the screen
   */
  private void createNotes() {
    int x = 16;
    int y = 930;
    this.notes = new Note[this.rows][this.columns];
    for (int row = 0; row < this.rows; ++row) {
      for (int column = 0; column < this.columns; ++column) {
        Note note = new Note(Color.black, new JPanel(), x, y, 0);
        this.notes[row][column] = note;
        this.graphicsHandler.addNote(note, x, y, this.noteWidth, this.noteHeight);
        x += this.noteWidth;
      }
      x = 16;
      y -= this.noteHeight;
    }
  }

  /**
   * @brief Method that creates a new note of the intensity bar and 
   *        shows it in the screen
   */
  private void createIntensityBar() {
    int x = 16;
    int y = 39;
    this.intensityBar = new Note[this.columns];
    for (int column = 0; column < this.columns; ++column){
      Note note = new Note(Color.black, new JPanel(), x, y, 0);
      this.intensityBar[column] = note;
      graphicsHandler.addNote(note, x, y, this.noteWidth, this.noteHeight);
      x += noteWidth;
    }
  }

  /**
   * @brief Method that resets all elements to their initial state
   */
  private void resetEnvironment() {
    this.timer.stop();
    this.state = false;
    this.control = 0;
    Note regularNote;
    Note intensityNote;
    for (int column = 0; column < this.columns; ++column) {
      for (int row = 0; row < this.rows; ++row) {
        regularNote = this.notes[row][column];
        this.graphicsHandler.resetRegularNote(regularNote, Color.black, 0);
      }
      intensityNote = this.intensityBar[column];
      this.graphicsHandler.resetIntensityBarNote(intensityNote, Color.black, 0, intensityNote.getX(), 
        intensityNote.getIntensity(), this.noteWidth, this.noteHeight);
    }
  }

  /**
   * @brief Method that checks if a note is a black one or not
   * @param number Column number
   * @return boolean
   */
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

  /**
   * @brief Method that advances 88 notes of the song and applies the 
   *        corresponding changes in the frame
   */
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
        this.graphicsHandler.inheritNoteProperties(currentNote, father);
        this.graphicsHandler.handleIntensity(lastNote, intensityNote, this.noteWidth);
      }
      try {
        if (this.control < this.binaryNotes.length) {
          ++this.control;
          boolean isBlackNote = this.validateBlackNote(column);
          this.graphicsHandler.changeNoteColor(this.notes[0][column], this.binaryNotes[this.control], isBlackNote);
        } else {
          ImageIcon icon = new ImageIcon(getClass().getResource("images/icon.png"));
          JOptionPane.showMessageDialog(null, "The song is over, press PLAY to start over",
            "Information",JOptionPane.INFORMATION_MESSAGE, icon);
          this.resetEnvironment();
          break;
        }
      } catch (Exception e) { }
    }
  }

  /**
   * @brief Method that handles the step's event
   */
  private void step() {
    this.stepButton.addActionListener(evt -> {
      this.oneStep();
    });
  }

  /**
   * @brief Method that handles the play's event
   */
  private void play() {
    this.playButton.addActionListener(evt -> {
      try {
        this.timer.start();
        if(this.state == false) 
          this.player.play();
        else
          this.player.resume(); 
      } catch (BasicPlayerException e) {
        System.err.println(e);
      }
    });
  }

  /**
   * @brief Method that handles the pause's event
   */
  private void pause() {
    this.pauseButton.addActionListener(evt -> {
      try {
        this.timer.stop();
        this.player.pause();
        this.state = true;
      } catch (BasicPlayerException e) {
        System.err.println(e);
      }
    });
  }
  
  /**
   * @brief Method that handles the reset's event
   */
  private void reset() {
    this.resetButton.addActionListener(evt -> {
      try {
        this.resetEnvironment();
        this.player.stop();
      } catch (BasicPlayerException e) {
        System.err.println(e);
      }
    });
  }
}
