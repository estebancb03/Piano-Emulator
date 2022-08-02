/// @copyright 2022 Esteban Castañeda Blanco. All rights reserved
/// This code is released under the GNU Public License version 3
/// @author Esteban Castañeda Blanco <esteban.castaneda@ucr.ac.cr>

import java.awt.*;
import javax.swing.*;

public class Graphics {
  /// Properties

  public Graphics() {

  }

  public void frameInit(JFrame frame, String name) {
    frame.setVisible(true);
    frame.setBackground(Color.black);
    frame.setResizable(false);
    frame.setTitle(name);
    frame.setSize(740, 755);
    frame.setLocationRelativeTo(null);
    frame.setIconImage(new ImageIcon(getClass().getResource("sources/images/musicNote.png")).getImage());
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
    panel.add(image);
    panel.setVisible(true);
    panel.setBackground(Color.black);
    image.setBounds(0, 0, 740, 150);
    panel.setBounds(0, 0, 740, 500);
    ImageIcon pianoImage = new ImageIcon(getClass().getResource("sources/images/piano.png"));
    image.setIcon(new ImageIcon(pianoImage.getImage().getScaledInstance(740, 135, Image.SCALE_SMOOTH)));
  }
}
