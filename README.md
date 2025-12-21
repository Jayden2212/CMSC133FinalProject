# CMSC133FinalProject
Project Proposal:
https://docs.google.com/document/d/1P2e2n-x-xkm0VLsUYkcEffocMba_W6i3FIf_RHVVD9g/edit?usp=sharing

Overview: 
The classic tabletop board game of Battleship, between a player and a smart computer, remade entirely with Java and smiles... suitable for everyone of all ages!

Instructions: 
To run and compile this program, you need a device with an IDE that supports Java. Clone this repo using the link and once it is fully loaded, press play/run to enjoy this game.

Classes: 
- GameManager: class with a main method that utilizes all the other classes to implement and run the game
- Board: represents the Battleship board containing a 2D array of Cell objects which represent each space on the grid. Handles the Ship placements and Shooting of spaces as well as the display of the board.
- Cell: represents a singular position on the board. Handles hit status, info on what ship is currently on the space if any, and the display of the space with either the ship's initial, hit status (ex. O = hit, X = miss), or empty status (ex "-" = empty)
- Player: represents the player. Handles their name and their current win status
- Computer: represents the computer. It's a child class of the player, handling it's AI of hunting and targeting cells it hits when necessary
- Ship: an interface which is implemented by all the ship classes to determine what methods they have. Allows for easier & efficient use of the different ships methods
- Carrier: represents the Airship Carrier. Has a size of 5 cells
- Battleship: represents the Battleship. Has a size of 4 cells
- Cruiser: represents the Cruiser. Has a size of 3 cells
- Submarine: represents the Submarine. Has a size of 3 cells
- Destroyer:  represents the Destroyer. Has a size of 2 cells

Features: 
In this game, the player start offs with either choosing from one of three pre-made boards to use, or manually placing each ship at the positions they specify to create their board. Once finished with their board, the computer randomly places ships on their board and begins the turn based sequence of shooting at positions on each other's board's until all of the opponents ships are sunk. Both the player & computer have a nuke ability, allowing them to instantly sink a ship they have hit.

Challenges Faced:
The most challenging aspect of implementing this game was creating a computer AI system that was smart enough to continue hitting spaces adjacent to the space they initally hit with a ship on it, until the ship sunk. Another challenge was creating methods that both the player and computer can use to place boats and shoot at cells without having to redundantly create/ reuse code. 
What I learned is that do-while loops are very useful for implementing continous player input, until said input is valid. Also, utilizing more boolean variables to represent different states is simpler, better, and more efficient compared to using other variable types, especially when used with loops and if statements.

Debugging Summary:
1. One bug I enountered was the incorrect shooting of spaces and indexOutOfBound errors for the cells I was accessing using the position of the space the player was shooting at. I identified this as it was shooting at row 3 and column 2 when it should've been shooting at row 4 and column 3. The way I fixed this bug was by using print statements throughout the shoot method to ensure certain if statements were being exceuted and methods were doing what they're supposed to. I looked at the way I implemented the code for the row and column and realized the player is entering row numbers and column numbers from 1-10 but the code is utilizing their indices from 0-9. This slight mismatch caused the bug and was later fixed.
2. Another bug I encountered was when I was implementing File I/O to read a text file that would create a new board as represented in the file. I managed to get the file to be loaded and read but the placement of the ships were not the same between the output and the text file. I later searched on the internet and realized scan.hasNext() is not the correct method to use but instead scan.hasNextLine(). I changed my code to grab each line from the text file, utilizing the String.split() method to split that line into an array of indivdual cells. This then produced the correct output as expected in the text file.
