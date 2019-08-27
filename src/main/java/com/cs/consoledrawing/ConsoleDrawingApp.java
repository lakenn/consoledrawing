package com.cs.consoledrawing;

import com.cs.consoledrawing.command.*;
import com.cs.consoledrawing.exception.InvalidInputException;
import com.cs.consoledrawing.model.Canvas;
import com.cs.consoledrawing.view.CanvasRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class ConsoleDrawingApp {
    private static final Logger logger = LoggerFactory.getLogger(ConsoleDrawingApp.class);

    private Canvas canvas;
    private CanvasRenderer renderer;
    private Stack<Canvas> doneStack;
    private Stack<Canvas> undoneStack;
    private boolean exited = false;

    public ConsoleDrawingApp() {
        renderer = new CanvasRenderer();
        doneStack = new Stack<>();
        undoneStack = new Stack<>();
    }

    private void printMenu() {
        System.out.println("*********************************************");
        System.out.println("*                                           *");
        System.out.println("*   0) C w h          to create a convas    *");
        System.out.println("*   1) L x1 y1 x2 y2  to create a line      *");
        System.out.println("*   2) R x1 y1 x2 y2  to create a rectangle *");
        System.out.println("*   3) B x1 y1 color  to refill             *");
        System.out.println("*   4) U              to undo               *");
        System.out.println("*   5) RE             to redo               *");
        System.out.println("*   4) Q              to Exit               *");
        System.out.println("*                                           *");
        System.out.println("******************************************");
    }

    private Canvas createCanvas(String[] args) throws InvalidInputException {

        int width = Integer.parseInt(args[0]);
        int height = Integer.parseInt(args[1]);

        if (width < 1 || height < 1 )
            throw new InvalidInputException("Width and height must be greater than 0");

        return new Canvas(width, height);
    }

    private Canvas getCanvas(){
        return doneStack.peek();
    }

    private void undoLastCmd() {
        if (doneStack.isEmpty()) return;
        Canvas previousImage = doneStack.pop();
        undoneStack.push(previousImage);
        this.canvas = previousImage;
    }

    private void redoLastUndone() {
        if (undoneStack.isEmpty()) return;
        Canvas previousImage = undoneStack.pop();
        doneStack.push(previousImage);
        this.canvas = previousImage;
    }

    private void executeCommand(DrawingCommand command) throws InvalidInputException {
        if (canvas == null)
            throw new InvalidInputException("Please create a canvas first");

        // make a clone of the canvas
        Canvas newCanvas = new Canvas(canvas);
        command.execute(newCanvas);
        doneStack.add(newCanvas);
        this.canvas = newCanvas;

        // Executing a drawingCommand triggers the clearing of the redo stack. After executing a drawingCommand,
        // it may no longer make sense to redo a drawingCommand that was previously undone.
        // This mirrors how a user expects redo behavior to work and avoids otherwise confusing behavior.
        undoneStack.clear();
    }

    private void processInput(String commandKey, String[] args) throws InvalidInputException {

        if ("Q".equals(commandKey)) {
            System.out.println("Bye !");
            exited = true;
            return;
        }

        switch(commandKey){
            case "C":
                canvas = createCanvas(args);
                doneStack.push(canvas);
                break;

            case "U":
                undoLastCmd();
                break;

            case "RE":
                redoLastUndone();
                break;

            default:
                // drawing commmand
                DrawingCommand drawingCommand = CommandFactory.createCommand(commandKey, args);

                if (drawingCommand == null) {
                    throw new InvalidInputException("Please input valid command");
                }

                executeCommand(drawingCommand);
        }

        renderer.render(getCanvas());
    }

    public void run() {
        printMenu();
        Scanner console = new Scanner(System.in);

        while(!exited){
            System.out.print("Enter command: ");
            String userInput = console.nextLine().trim();

            if(!userInput.equals("")){
                String[] input = userInput.split(" ");
                String commandKey = input[0].toUpperCase();
                String[] inputArgs = Arrays.copyOfRange( input, 1, input.length );

                try {
                    processInput(commandKey, inputArgs);

                } catch (InvalidInputException e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }


}
