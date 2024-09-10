package com.example;

import picocli.CommandLine;

import java.net.MalformedURLException;

@CommandLine.Command
public class Cli implements Runnable {

    @CommandLine.Parameters(index = "0", paramLabel = "<action>", description = "Enums : run, delete")
    String action;

    @CommandLine.Option(names = { "-ds", "--dataset" }, description = "Dataset")
    String dataset;

    @CommandLine.Option(names = { "-n", "--name" }, description = "Who will we greet?")
    String name;

    @CommandLine.Option(names = { "-o", "--output" }, description = "Output path")
    String output;

    @CommandLine.Option(names = { "-h", "--help" }, usageHelp = true, description = "Display a help message")
    private boolean helpRequested;

    private final ActionsService actionsService;

    public Cli(ActionsService actionsService) {
        this.actionsService = actionsService;
    }

    @Override
    public void run() {
        switch (action) {
            case "run":
                try {
                    actionsService.handleRunAction(name);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "delete":
                actionsService.handleDeleteAction(dataset);
                break;
            default:
                actionsService.handleNotAnAction();
                break;
        }
    }

}

