# ASCII Art Generator

## Authors
- simaon78
- aviwolf

## Overview
This project is an ASCII art generator that converts images into ASCII art using various algorithms and data structures. The program is organized into multiple packages and classes, each handling different aspects of the ASCII art generation process.

## Packages and Classes

### Package: `ascii_art`

- **Class: `AsciiArtAlgorithm`**
  - Responsible for running the ASCII art algorithm and creating mementos for saving the last state of the ASCII art.
  
- **Class: `SplitImageMemento`**
  - A subclass of `AsciiArtAlgorithm`, responsible for the memento of the split image.
  
- **Class: `AsciiArtManager`**
  - Manages the entire program, containing instances of `Image`, `SubImageCharMatcher`, `SplitImageMemento`, and `AsciiOutput` to create and run the ASCII art generation.

- **Class: `InvalidSizeException`**
  - Handles exceptions related to invalid sizes of user input.

- **Class: `Shell`**
  - Runs the program, gets user input, and sends it to the factory `HandleUserChoice` which implements the `ExecuteUserCommand` interface.

### Package: `user_interface`

- **Interface: `ExecuteUserCommand`**
  - Responsible for the execution of user commands with one method called `execute`.

- **Class: `HandleUserChoice`**
  - A factory that implements `ExecuteUserCommand`, responsible for creating the classes that the user chooses to run and contains an instance of the manager.

- **Class: `ExecuteChangeOutput`**
  - Executes the user command to change the output, implements `ExecuteUserCommand`, and contains an instance of the manager.

- **Class: `ExecuteChars`**
  - Executes the user command to change the characters, implements `ExecuteUserCommand`, and contains an instance of the manager.

- **Class: `ExecuteCharset`**
  - Executes the user command to change the charset, implements `ExecuteUserCommand`, and contains an instance of the manager.

- **Class: `ExecuteAsciiArt`**
  - Executes the user command to generate ASCII art, implements `ExecuteUserCommand`, and contains an instance of the manager.

- **Class: `ExecuteLoad`**
  - Executes the user command to load an image, implements `ExecuteUserCommand`, and contains an instance of the manager.

- **Class: `ExecuteSave`**
  - Executes the user command to save the ASCII art, implements `ExecuteUserCommand`, and contains an instance of the manager.

### Package: `ascii_output`

- **Class: `AsciiOutput`**
  - Responsible for the output of the ASCII art to an HTML file.

### Package: `image`

- **Class: `Image`**
  - Represents the image chosen by the user to run the program.

- **Class: `SubImageCharMatcher`**
  - Matches the sub-image to a character.

- **Class: `CalculateBrightness`**
  - Calculates the brightness of the image.

- **Class: `PowerOfTwoImagePadder`**
  - Pads the image to the nearest power of two.

- **Class: `SplitImage`**
  - Splits the image into sub-images.

### Package: `image_char_matching`

- **Class: `CharBrightness`**
  - Calculates the brightness of the character.

- **Class: `CharConverter`**
  - Converts the character to an image.

- **Class: `SubImageCharMatcher`**
  - Matches the sub-image to a character.

## Data Structures

- **ArrayList**
  - Used in the `SplitImage` class to store sub-images.

- **TreeMap**
  - Used to save the brightness values in sorted order and provides efficient search for brightness values.

## Exception Handling

- **InvalidSizeException**
  - Thrown when the user input is not of the correct size. This exception is caught in the `HandleUserChoice` class and displays the appropriate message.

- **IncorrectFormat**
  - Thrown when the user input is not in the correct format. This exception is caught in the `Shell` class and displays the appropriate message.

## Additional Methods

- **getCharBrightness**
  - Added to retrieve the characters' brightness in the manager class for displaying the charset to the user.

