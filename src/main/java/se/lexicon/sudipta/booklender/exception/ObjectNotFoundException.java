package se.lexicon.sudipta.booklender.exception;

public class ObjectNotFoundException extends Exception{

    public ObjectNotFoundException(String message){

        super("Object not found"+ message);
    }
}
