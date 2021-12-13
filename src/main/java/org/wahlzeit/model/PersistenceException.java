package org.wahlzeit.model;

public class PersistenceException extends Exception{
        Exception exception;

        public PersistenceException(Exception exception, String message) {
                super(message);
                this.exception = exception;       
        }
}
