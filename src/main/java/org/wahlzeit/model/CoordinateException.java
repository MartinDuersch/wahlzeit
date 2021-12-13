package org.wahlzeit.model;

public class CoordinateException extends Exception{
        Exception exception;

        public CoordinateException(Exception exception, String message) {
                super(message);
                this.exception = exception;       
        }
}
