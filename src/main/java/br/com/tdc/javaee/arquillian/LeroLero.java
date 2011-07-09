package br.com.tdc.javaee.arquillian;

import javax.ejb.Stateless;

@Stateless
public class LeroLero {
    private final static int ARRAY_SIZE = 100;

    public Boolean[] doSomethingComplicated() {
        Boolean booleanArray[] = new Boolean[ARRAY_SIZE];
        for (int i = 0; i < ARRAY_SIZE; i++) {
            booleanArray[i] = (i % 2 == 0) ? Boolean.TRUE : Boolean.FALSE;
        }
        return booleanArray;
    }

}
