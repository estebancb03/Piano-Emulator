/// @copyright 2022 Esteban Castañeda Blanco. All rights reserved
/// This code is released under the GNU Public License version 3
/// @author Esteban Castañeda Blanco <esteban.castaneda@ucr.ac.cr>

import java.awt.*;
import javax.swing.*;

public class Graphics {
  /// Properties
  private JFrame frame;

  public Graphics(JFrame frame) {
    this.frame = frame;
  }

  public void frameInit(String name, int width, int height) {
    this.frame.setVisible(true);
    this.frame.setResizable(false);
    this.frame.setTitle(name);
    this.frame.setSize(width, height);
    this.frame.setLocationRelativeTo(null);
    this.frame.setIconImage(new ImageIcon(getClass().getResource("sources/images/icon.png")).getImage());
  }

  public void buttonInit(JButton button, String text, int x, int y, int width, int height) {
    this.frame.add(button);
    button.setText(text);
    button.setBorder(null);
    button.setVisible(true);
    button.setFocusable(false);
    button.setBackground(Color.black);
    button.setForeground(Color.white);
    button.setBounds(x, y, width, height);
    button.setFont(new Font("Agency FB", Font.BOLD, 16));
    button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
  }

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

  public void addNote(Note note, int x, int y, int width, int height) {
    JPanel panel = note.getPanel();
    this.frame.add(panel);
    panel.setVisible(true);
    panel.setBackground(note.getColor());
    panel.setBounds(x, y, width, height);
  }

  public void resetRegularNote(Note note, Color color, int intensity) {
    note.setColor(color);
    note.setIntensity(intensity);
    note.getPanel().setBackground(color);
    note.getPanel().setLocation(note.getX(), note.getY());
  }

  public void resetIntensityBarNote(Note note, Color color, int intensity, int x, int y, int width, int height) {
    note.setColor(color);
    note.setIntensity(intensity);
    note.getPanel().setBackground(color);
    note.getPanel().setSize(width, height);
    note.getPanel().setLocation(note.getX(), note.getY());
  }

  public void changeNoteColor(Note note, byte binary, Boolean isBlackNote) {
    Color background;
    int number = binary & 0b11111111; /// The mask is added to the binary number
    background = isBlackNote ? new Color(240 * number / 256, 128 * number /256, 128 * number / 256)
                             : new Color(0 / 256, 191 * number /256, 255 * number / 256);
    note.setColor(background);
    note.setIntensity(number);
    note.getPanel().setBackground(background);
  }

  public void inheritNoteProperties(Note note, Note father) {
    note.setColor(father.getColor());
    note.setIntensity(father.getIntensity());
    note.getPanel().setBackground(father.getColor());
  }

  public void manageIntensity(Note regularNote, Note intensityBarNote, int width) {
    int newHeight = 40 * regularNote.getIntensity() / 255;
    intensityBarNote.setColor(regularNote.getColor());
    intensityBarNote.setIntensity(regularNote.getIntensity());
    intensityBarNote.getPanel().setBackground(regularNote.getColor());
    intensityBarNote.getPanel().setSize(width, 10 + newHeight);
    intensityBarNote.getPanel().setLocation(intensityBarNote.getX(), intensityBarNote.getY() - newHeight);
  }
}
