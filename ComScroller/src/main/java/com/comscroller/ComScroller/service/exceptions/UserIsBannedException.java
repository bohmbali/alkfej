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
public class UserIsBannedException extends Exception {

    /**
     * Creates a new instance of <code>UserIsBannedException</code> without
     * detail message.
     */
    public UserIsBannedException() {
    }

    /**
     * Constructs an instance of <code>UserIsBannedException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UserIsBannedException(String msg) {
        super(msg);
    }
}
