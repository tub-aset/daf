/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui;

import java.util.Optional;
import java.util.stream.Stream;
import static javafx.application.Application.launch;

/**
 *
 * @author fwiesweg
 */
public class Main {

    public static void main(final String[] args) {
        launch(MainFX.class, args);
    }
    
    /**
     * Java 8 replacement for Optional.stream()
     * @param <T>
     * @param optional
     * @return 
     */
    @Deprecated
    public static <T> Stream<T> asStream(Optional<T> optional) {
        return optional.isPresent() ? Stream.of(optional.get()) : Stream.empty();
    }
}
