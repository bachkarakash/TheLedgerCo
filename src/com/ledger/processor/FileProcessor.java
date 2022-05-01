package com.ledger.processor;

import com.ledger.factory.RequestHandlerFactory;
import com.ledger.handler.RequestHandler;
import com.ledger.response.BalanceResponse;
import com.ledger.response.DefaultResponse;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {
    private static FileProcessor fileProcessor = new FileProcessor();

    public String getInputFilename() {
        return inputFilename;
    }

    public void setInputFilename(String inputFilename) {
        this.inputFilename = inputFilename;
    }

    public String getOutputFilename() {
        return outputFilename;
    }

    public void setOutputFilename(String outputFilename) {
        this.outputFilename = outputFilename;
    }

    String inputFilename, outputFilename;
    private FileProcessor() {

    }
    public static FileProcessor getInstance(String inputFilename, String outputFilename) {
        fileProcessor.setInputFilename(inputFilename);
        fileProcessor.setOutputFilename(outputFilename);
        return fileProcessor;
    }
    public void processFileInput() {
        List<String> inputCommands = getInputCommands();
        clearExistingContent();
        inputCommands.forEach(command -> {
            RequestHandler requestHandler = RequestHandlerFactory.getRequestHandler(command);
            if(requestHandler != null) {
                DefaultResponse response = requestHandler.handleRequest();
                if(response instanceof BalanceResponse && response.isSuccess()) {
                    BalanceResponse balanceResponse = (BalanceResponse) response;
                    writeOutputToFile(balanceResponse);
                }
            }
        });
    }
    public List<String> getInputCommands() {
        List<String> commands = new ArrayList<>();
        try {
            File file = new File(inputFilename);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while((line = bufferedReader.readLine()) != null) {
                commands.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return commands;
    }

    public void clearExistingContent() {
        File file = new File(outputFilename);
        file.delete();
    }
    public void writeOutputToFile(BalanceResponse balanceResponse) {
        try {
            File file = new File(outputFilename);
//            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
//            objectOutputStream.writeObject(balanceResponse);
//            objectOutputStream.close();
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.append(balanceResponse.toString());
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
