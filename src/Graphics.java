/// @copyright 2022 Esteban Castañeda Blanco. All rights reserved
/// This code is released under the GNU Public License version 3
/// @author Esteban Castañeda Blanco <esteban.castaneda@ucr.ac.cr>

import java.awt.*;
import javax.swing.*;

/**
 * @brief Class that manages all graphic aspects of the app
 */
public class Graphics extends JFrame {
  /// App's frame
  private JFrame frame;

  /**
   * @brief Constructor
   * @param frame App's frame
   */
  public Graphics(JFrame frame) {
    this.frame = frame;
  }

  /**
   * @brief Method that initializes the JFrame
   * @param name JFrame's name
   * @param width JFrame's width
   * @param height JFrame's height
   */
  public void frameInit(String name, int width, int height) {
    this.frame.setVisible(true);
    this.frame.setResizable(false);
    this.frame.setTitle(name);
    this.frame.setSize(width, height);
    this.frame.setLocationRelativeTo(null);
    this.frame.setIconImage(new ImageIcon(getClass().getResource("sources/images/icon.png")).getImage());
    this.frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  /**
   * @brief Method that initializes a JButton
   * @param button JButton
   * @param text JButton's text
   * @param x Possition on the x-axis
   * @param y Possition on the y-axis
   * @param width JButton's width
   * @param height JButton's height
   */
  public void buttonInit(JButton button, String text, int x, int y, int width, int height) {
    this.frame.add(button);
    button.setText(text);
    button.setBorder(null);
    button.setVisible(true);
    button.setFocusable(false);
    button.setBackground(Color.black);
    button.setForeground(Color.white);
    button.setBounds(x, y, width, height);
    button.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
    button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
  }

  /**
   * @brief Method that initializes the main JPanel
   * @param panel JPanel
   */
  public void mainPanelInit(JPanel panel) {
    this.frame.add(panel);
    panel.add(new JLabel(" "));
    JLabel image = new JLabel();
    panel.add(image);
    panel.setVisible(true);
    panel.setBackground(Color.black);
    ImageIcon pianoImage = new ImageIcon(getClass().getResource("sources/images/piano.png"));
    image.setIcon(new ImageIcon(pianoImage.getImage().getScaledInstance(828, 155, Image.SCALE_SMOOTH)));
  }

  /**
   * @brief Method that shows a note's panel on the screen
   * @param note Note object
   * @param x Possition on the x-axis
   * @param y Possition on the y-axis
   * @param width JPanel's width
   * @param height JPanel's height
   */
  public void addNote(Note note, int x, int y, int width, int height) {
    JPanel panel = note.getPanel();
    this.frame.add(panel);
    panel.setVisible(true);
    panel.setBackground(note.getColor());
    panel.setBounds(x, y, width, height);
  }

  /**
   * @brief Method that reset a note to its initial state
   * @param note Note object
   * @param color Note's color
   * @param intensity Note's intensity
   */
  public void resetRegularNote(Note note, Color color, int intensity) {
    note.setColor(color);
    note.setIntensity(intensity);
    note.getPanel().setBackground(color);
    note.getPanel().setLocation(note.getX(), note.getY());
  }

  /**
   * @brief Method that reset a intensity bar note to its initial state
   * @param note Note object
   * @param color Note's color
   * @param intensity Note's intensity
   * @param x Possition on the x-axis 
   * @param y Possition on the y-axis
   * @param width JPanel's width
   * @param height JPanel's height
   */
  public void resetIntensityBarNote(Note note, Color color, int intensity, int x, int y, int width, int height) {
    note.setColor(color);
    note.setIntensity(intensity);
    note.getPanel().setBackground(color);
    note.getPanel().setSize(width, height);
    note.getPanel().setLocation(note.getX(), note.getY());
  }

  /**
   * @brief Method that changes the note's JPanel color
   * @param note Note object
   * @param binary Binary value of the note
   * @param isBlackNote Indicates if the note is a black one or not
   */
  public void changeNoteColor(Note note, byte binary, boolean isBlackNote) {
    Color background;
    int number = binary & 0b11111111; /// The mask is added to the binary number
    background = isBlackNote ? new Color(240 * number / 256, 128 * number /256, 128 * number / 256)
                             : new Color(0 / 256, 191 * number /256, 255 * number / 256);
    note.setColor(background);
    note.setIntensity(number);
    note.getPanel().setBackground(background);
  }

  /**
   * @brief Method that inherits properties from one note to another
   * @param note Note object of the current note
   * @param father Note object of the note that inherits
   */
  public void inheritNoteProperties(Note note, Note father) {
    note.setColor(father.getColor());
    note.setIntensity(father.getIntensity());
    note.getPanel().setBackground(father.getColor());
  }

  /**
   * @brief Method that changes a note from the intensity bar
   * @param regularNote Note object of the note from which it inherits intensity and color
   * @param intensityBarNote Note object of the note that inherits
   * @param width Note's width
   */
  public void handleIntensity(Note regularNote, Note intensityBarNote, int width) {
    int newHeight = 40 * regularNote.getIntensity() / 255;
    intensityBarNote.setColor(regularNote.getColor());
    intensityBarNote.setIntensity(regularNote.getIntensity());
    intensityBarNote.getPanel().setBackground(regularNote.getColor());
    intensityBarNote.getPanel().setSize(width, 10 + newHeight);
    intensityBarNote.getPanel().setLocation(intensityBarNote.getX(), intensityBarNote.getY() - newHeight);
  }
}
