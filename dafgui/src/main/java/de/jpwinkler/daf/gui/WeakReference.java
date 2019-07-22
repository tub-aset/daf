/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.gui;

import java.lang.ref.ReferenceQueue;
import java.util.stream.Stream;

/**
 *
 * @author fwiesweg
 */
public class WeakReference<T> extends java.lang.ref.WeakReference<T> {

    public WeakReference(T referent) {
        super(referent);
    }

    public WeakReference(T referent, ReferenceQueue<? super T> q) {
        super(referent, q);
    }

    public Stream<T> stream() {
        T value = get();
        return value != null ? Stream.of(value) : Stream.empty();

    }

}
