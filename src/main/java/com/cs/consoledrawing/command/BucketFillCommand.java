package com.cs.consoledrawing.command;

import com.cs.consoledrawing.exception.InvalidInputException;
import com.cs.consoledrawing.model.Canvas;

public class BucketFillDrawingCommand implements DrawingCommand {
    private int x1, y1;
    private char color;

    public BucketFillDrawingCommand(String[] args) throws InvalidInputException {
        // request validation
        if (args.length != 3)
        {
            throw new InvalidInputException("Number of parameter not 3.");
        }

        this.x1 = Integer.parseInt(args[0]);
        this.y1 = Integer.parseInt(args[1]);
        this.color = args[2].charAt(0);

        validateInput(x1, y1);
    }

    private void validateInput(int x1, int y1) throws InvalidInputException {

        if (x1 < 0 || y1 < 0)
            throw new InvalidInputException("Point coordinates must > 0.");

    }

    private void fill(Canvas canvas, int row, int col, char originalPixel, char color){

        if (row < 1 || row > canvas.getHeight() || col < 1 || col > canvas.getWidth())
            return;

        if (canvas.getPixel(row, col) == originalPixel){
            canvas.updatePixel(row, col, color);

            // BFS
            fill(canvas, row-1, col, originalPixel, color);
            fill(canvas, row+1, col,  originalPixel, color);
            fill(canvas, row, col-1,   originalPixel, color);
            fill(canvas, row, col+1, originalPixel, color);
        }
    }

    @Override
    public void execute(Canvas canvas) throws InvalidInputException {
        if (x1 < 1 || x1 > canvas.getWidth() || y1 < 1 || y1 > canvas.getHeight() )
            throw new InvalidInputException("Coordinates must be inside the canvas");

        char originalPixel = canvas.getPixel(y1, x1);
        fill(canvas, y1, x1, originalPixel, this.color );
    }
}
