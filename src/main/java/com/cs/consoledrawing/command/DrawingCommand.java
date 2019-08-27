package com.cs.consoledrawing.command;

import com.cs.consoledrawing.exception.InvalidInputException;
import com.cs.consoledrawing.model.Canvas;

public interface DrawingCommand {
    void execute(Canvas canvas) throws InvalidInputException;
}
