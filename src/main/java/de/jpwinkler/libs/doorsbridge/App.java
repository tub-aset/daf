package de.jpwinkler.libs.doorsbridge;

public class App {

    public static void main(final String[] args) {
        DoorsApplicationFactory.getDoorsApplication().ack("Hello World!");
    }

}
