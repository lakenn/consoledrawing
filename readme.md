# console-drawing
A simple, fun program to draw on console.

# overview
The program was built using Java 8 and Maven

# usage

```
Command 		Description
C w h           Create a new canvas of width w and height h.
L x1 y1 x2 y2   Create a new line of 'x' from (x1,y1) to (x2,y2). Only support 
                horizontal or vertical lines.
R x1 y1 x2 y2   Create a new rectangle, (x1,y1) is upper left corner & (x2,y2) is 
                lower right corner.
B x y c         Fill the entire area around (x,y) with "colour" c.
                Same as that of the "bucket fill" tool in paint programs.
Q               Quit.
``` 

# example
```
enter drawingCommand: C 20 4
----------------------
|                    |
|                    |
|                    |
|                    |
----------------------

enter drawingCommand: L 1 2 6 2
----------------------
|                    |
|xxxxxx              |
|                    |
|                    |
----------------------

enter drawingCommand: L 6 3 6 4
----------------------
|                    |
|xxxxxx              |
|     x              |
|     x              |
----------------------

enter drawingCommand: R 14 1 18 3
----------------------
|             xxxxx  |
|xxxxxx       x   x  |
|     x       xxxxx  |
|     x              |
----------------------

enter drawingCommand: B 10 3 o
----------------------
|oooooooooooooxxxxxoo|
|xxxxxxooooooox   xoo|
|     xoooooooxxxxxoo|
|     xoooooooooooooo|
----------------------
```
