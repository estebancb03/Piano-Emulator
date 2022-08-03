/// @copyright 2022 Esteban Castañeda Blanco. All rights reserved
/// This code is released under the GNU Public License version 3
/// @author Esteban Castañeda Blanco <esteban.castaneda@ucr.ac.cr>

import java.awt.*;
import javax.swing.*;

/** 
 * @brief Encapsulator class for the representation of a note
*/
public class Note {
  /// JPanle's color
  private Color color;
  /// JPanel object
  private JPanel panel;
  /// Position on the x-axis
  private int x;
  /// Position on the y-axis
  private int y;
  /// Note's intensity
  private int intensity;

  /**
   * @brief Costructor
   * @param color JPanel's color
   * @param panel JPanel
   * @param x Position on the x-axis
   * @param y Position on the y-axis
   * @param intensity Note's intensity
   */
  public Note(Color color, JPanel panel, int x, int y, int intensity) {
    this.color = color;
    this.panel = panel;
    this.x = x;
    this.y = y;
    this.intensity = intensity;
  }

  /**
   * @brief Method that returns note's color
   * @return Color
   */
  public Color getColor() { return this.color; }

  /**
   * @brief Method that returns note's panel
   * @return JPanel
   */
  public JPanel getPanel() { return this.panel; }

  /**
   * @brief Method that returns the position on the x-axis
   * @return int
   */
  public int getX() { return this.x; }

  /**
   * @brief Method that returns the position on the x-axis
   * @return int
   */
  public int getY() { return this.y; }

  /**
   * @brief Method that returns the note's intensity
   * @return int
   */
  public int getIntensity() { return this.intensity; }

  /**
   * @brief Method that sets a new note's color
   * @param color The new color of the note
   */
  public void setColor(Color color) { this.color = color; }

  /**
   * @brief Method that sets a new note's intensity
   * @param intensity The new intensity of the note
   */
  public void setIntensity(int intensity) { this.intensity = intensity; }
}
