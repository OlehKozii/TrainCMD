package com.traincmd.Command;

public class user {
    private Command command;

    public user(Command command) {
        this.command = command;
    }

    public user() {
        this.command = null;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand() {
        command.execute();
    }
}
