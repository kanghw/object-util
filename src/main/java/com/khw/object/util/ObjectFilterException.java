package com.khw.object.util;

public class ObjectFilterException extends RuntimeException {
    private Object object;
    public ObjectFilterException(Exception e) {
        super(e);
    }

    public ObjectFilterException(Exception e, Object object) {
        super(e);
        this.object = object;
    }

    public Object getObject(){
        return this.object;
    }

}
