/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.db;

/**
 *
 * @author fwiesweg
 */
public interface BackgroundTaskInterface {

    void incrementProgress(long increment);

    void incrementProgress(long increment, long maxProgressIncrement);
}
