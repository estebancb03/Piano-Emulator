# Data structures design


## Note

This class is used to encapsulate the necessary elements to adequately represent a note, both in its logical and graphical parts. The properties of this class store the JPanel to represent the note in the frame, the color of the JPanel, the x and y position in which the JPanel is going to be located and the intensity of the note. The Note class is implemented in Java as follows:

``` java
public class Note {
  private Color color;
  private JPanel panel;
  private int x;
  private int y;
  private int intensity;
}
```

## GraphicsHandler

This class is in charge of loading the graphic elements that are shown to the user on the screen and modifying their appearance during the execution of the app. It only has one property and it is the JFrame where all the other elements like panels and buttons are placed, for this purpose, this class inherits from the JFrame class. The GraphicsHandler class is implemented in Java as follows:

``` java
private class GraphicsHandler extends JFrame {
  private JFrame frame;
}
```

## Navegation

* [Main RAEDME](../README.md)