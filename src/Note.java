/// @copyright 2022 Esteban Castañeda Blanco. All rights reserved
/// This code is released under the GNU Public License version 3
/// @author Esteban Castañeda Blanco <esteban.castaneda@ucr.ac.cr>

import java.awt.*;
import javax.swing.*;

public class Note {
  /// Properties
  private Color color;
  private JPanel panel;
  private int x;
  private int y;
  private int intensity;

  /// Constructor
  public Note(Color color, JPanel panel, int x, int y, int intensity) {
    this.color = color;
    this.panel = panel;
    this.x = x;
    this.y = y;
    this.intensity = intensity;
  }

  /// Getters
  public Color getColor() { return this.color; }
  public JPanel getPanel() { return this.panel; }
  public int getX() { return this.x; }
  public int getY() { return this.y; }
  public int getIntensity() { return this.intensity; }

  /// Setters
  public void setColor(Color color) { this.color = color; }
  public void setIntensity(int intensity) { this.intensity = intensity; }
}
