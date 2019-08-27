package com.cs.consoledrawing.command;

import com.cs.consoledrawing.exception.InvalidInputException;

public class CommandFactory {

    public static DrawingCommand createCommand(String commandKey, String[] args) throws InvalidInputException {
        DrawingCommand drawingCommand = null;

        switch(commandKey){
            case "L":
                drawingCommand = new DrawLineDrawingCommand(args);
                break;

            case "R":
                drawingCommand = new DrawRectangleDrawingCommand(args);
                break;

            case "B":
                drawingCommand = new BucketFillDrawingCommand(args);
                break;

            default:
                break;
        }

        return drawingCommand;
    }
}
