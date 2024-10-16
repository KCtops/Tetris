Tetris Game
Overview
This project is a Java-based implementation of the classic game Tetris. It features a standard Tetris gameplay experience with the goal of completing lines by arranging falling blocks (tetrominoes). The project is structured with clear separation of concerns, following an MVC (Model-View-Controller) architecture.

**Project Structure**
Main.java: Entry point for the game.
Controller/: Contains logic for managing the game flow.
Model/: Holds the game state and data, including tetromino shapes and board configuration.
Views/: Responsible for rendering the game's UI and graphics.
out/: Contains compiled bytecode and other output from the build process.

.idea/: IntelliJ IDEA configuration files.

Tetris.iml: Project configuration file for IntelliJ IDEA.

**Features**
Classic Tetris Gameplay: Rotate and move falling tetrominoes to fill lines.
MVC Architecture: The project is divided into distinct layers for the game model, user interaction, and UI rendering, making it modular and easy to maintain.
Responsive UI: The game interface is designed to be user-friendly and responsive.

**How to Run**
Ensure you have Java installed on your system (JDK 8+ recommended).
Clone the repository or download the project files.
Open the project in your preferred Java IDE (e.g., IntelliJ IDEA).
Compile and run Main.java to start the game.
Alternatively, if you have a precompiled version:

Navigate to the out folder.
Run the game using the following command in a terminal:
java -cp out Main

**Contributing**
Feel free to fork this project and contribute by submitting pull requests. All contributions to improve the game or fix issues are welcome.

License
This project is licensed under the MIT License.
