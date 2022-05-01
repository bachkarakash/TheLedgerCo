package com.ledger;

import com.ledger.processor.FileProcessor;

public class Geektrust {

    public static void main(String[] args) {
        if(args.length != 0) processInput(args);
    }

    private static void processInput(String[] args) {
        String inputFilename =args[0];
        String outputFilename = args.length > 1 && args[1] != null ? args[1] : "output.txt";
        FileProcessor fileProcessor = FileProcessor.getInstance(inputFilename, outputFilename);
        fileProcessor.processFileInput();
    }
}
