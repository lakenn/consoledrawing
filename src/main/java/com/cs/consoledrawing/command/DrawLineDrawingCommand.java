package com.cs.consoledrawing.command;

import com.cs.consoledrawing.exception.InvalidInputException;
import com.cs.consoledrawing.model.Canvas;

public class DrawLineDrawingCommand implements DrawingCommand {

    private int x1, y1, x2, y2;
    private static final char LINE_CHAR = 'X';


    public DrawLineDrawingCommand(String[] args) throws InvalidInputException {

        // request validation
        if (args.length != 4)
        {
            throw new InvalidInputException("Number of parameter not 4.");
        }

        this.x1 = Integer.parseInt(args[0]);
        this.y1 = Integer.parseInt(args[1]);
        this.x2 = Integer.parseInt(args[2]);
        this.y2 = Integer.parseInt(args[3]);

        validateInput(x1, y1, x2, y2);
    }

    private void validateInput(int x1, int y1, int x2, int y2) throws InvalidInputException {

        if (x1 < 0 || x2 <0 || y1 < 0 || y2 < 0)
            throw new InvalidInputException("Point coordinates must > 0.");

        if (x1 > x2 || y1 > y2 )
            throw new InvalidInputException("Must be x1 < x2 && y1 < y2");

        // check diagonal
        if (x1 != x2 && y1 != y2)
            throw new InvalidInputException("Only horizontal or vertical line is supported.");

    }

    @Override
    public void execute(Canvas canvas) throws InvalidInputException {
        // domain specific validation
        if (x1 < 1 || x2 > canvas.getWidth() || y1 < 1 || y2 > canvas.getHeight() )
            throw new InvalidInputException("Coordinates must be inside the canvas");

        for (int row = y1; row <= y2; row++ ){
            for(int col = x1; col <= x2; col++){
                canvas.updatePixel(row, col, LINE_CHAR);
            }
        }
    }
}
