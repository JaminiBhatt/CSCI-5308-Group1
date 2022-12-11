package com.project.Group1.CommandFactory;

import com.project.Group1.Database.IDatabase;
import org.springframework.core.env.Environment;

public abstract class Command {
    protected IDatabase receiver;
    protected String args[];

    protected Environment env;

    public Command(IDatabase receiver, String[] args, Environment env) {
        this.receiver = receiver;
        this.args = args;
        this.env = env;
    }

    public abstract Object execute();
}
