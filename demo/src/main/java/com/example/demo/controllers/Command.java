package com.example.demo.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Main interface for the Command pattern implementation.
 *
 * @author R.Humeniuk
 *
 */
public abstract class Command {
    private static final long serialVersionUID = 8879403039606311780L;

    /**
     * Execution method for command.
     * @return Address to go once the command is executed.
     */
    public abstract ServletResponse execute(HttpServletRequest request, HttpServletResponse response);

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
