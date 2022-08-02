/// @copyright 2022 Esteban Castañeda Blanco. All rights reserved
/// This code is released under the GNU Public License version 3
/// @author Esteban Castañeda Blanco <esteban.castaneda@ucr.ac.cr>

import java.awt.*;
import javax.swing.*;

public class Graphics {
  /// Properties
  private JFrame frame;
  private int noteWidth;
  private int noteHeight;

  public Graphics(JFrame frame, int noteWidth, int noteHeight) {
    this.frame = frame;
    this.noteWidth = noteWidth;
    this.noteHeight = noteHeight;
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

  public void panelInit(JPanel panel) {
    JLabel image = new JLabel();
    panel.add(new JLabel("    "));
    panel.add(image);
    panel.setVisible(true);
    panel.setBackground(Color.white);
    ImageIcon pianoImage = new ImageIcon(getClass().getResource("sources/images/piano.png"));
    image.setIcon(new ImageIcon(pianoImage.getImage().getScaledInstance(830, 145, Image.SCALE_SMOOTH)));
  }

  public void addNote(Note note, int x, int y) {
    JPanel panel = note.getPanel();
    this.frame.add(panel);
    panel.setVisible(true);
    panel.setBackground(note.getColor());
    panel.setBounds(x, y, this.noteWidth, this.noteHeight);
  }
}
