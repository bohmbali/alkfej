/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comscroller.ComScroller.service.exceptions;

/**
 *
 * @author Bohm_Balazs
 */
public class UserNotValidException extends Exception {

    /**
     * Creates a new instance of <code>UserNotValidException</code> without
     * detail message.
     */
    public UserNotValidException() {
    }

    /**
     * Constructs an instance of <code>UserNotValidException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UserNotValidException(String msg) {
        super(msg);
    }
}
