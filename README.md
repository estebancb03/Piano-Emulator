# Piano emulator 1.0

## Description

This app visually represents a song as if a piano were playing it, following each note and chord to the letter. Every time a note hits the piano, it plays it. To represent the volume or intensity of each note, colors were used, with the most intense colors representing greater volume and the more opaque less volume. For notes that play a white key, blue tones are used and for notes that play black keys, red tones are used.

An intensity bar that represents the volume of a note has also been implemented. So, when a note plays the piano, it is represented in the intensity bar by copying its color and giving a size to the rectangle of the intensity bar according to the volume of the note, the more volume it has, the greater the height of its respective rectangle in intensity bar.

To represent the song in the simulator, it is necessary to read a .poly file that basically contains the song in a binary representation.

![Piano emulator](../src/../piano-polynizer2.0/src/images/app.png)

## User manual

To run the simulator, simply install the extensions for java development in VScode and press the run button. The app has four buttons to manipulate the basic functions:
* Step: Show the next 88 notes.
* Play: Show the notes continuously.
* Pause: Pause reading and show notes.
* Reset: Return the app to its initial state.

Currently only plays Rimsky Korsakov - Flight of the bumblebee.

## Credits

Project developed by Esteban Castañeda Blanco, email: esteban.castaneda@ucr.ac.cr