package com.cs.consoledrawing.view;

import com.cs.consoledrawing.model.Canvas;

import java.io.PrintStream;

public class CanvasRenderer {

    private static final char HORIZONTAL_EDGE_CHAR = '-';
    private static final char VERTICAL_EDGE_CHAR   = '|';

    PrintStream printStream = new PrintStream(System.out);

    public void render(Canvas canvas){
        int height = canvas.getHeight();
        int width = canvas.getWidth();

        for (int row = 0; row <= height + 1; row++){

            for (int col = 0; col <= width + 1; col++){

                if (row == 0 || row == height + 1){
                    printStream.print(HORIZONTAL_EDGE_CHAR);
                }
                else if (col == 0 || col == width + 1){
                    printStream.print(VERTICAL_EDGE_CHAR);
                }
                else{
                    printStream.print(canvas.getPixel(row, col));
                }
            }
            printStream.print("\n");
        }
    }
}
