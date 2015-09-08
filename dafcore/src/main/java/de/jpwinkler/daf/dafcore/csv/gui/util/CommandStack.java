package de.jpwinkler.daf.dafcore.csv.gui.util;

public class CommandStack<T> {

    private static class CommandStackItem<T> {

        public T command;
        public CommandStackItem<T> previous;
        public CommandStackItem<T> next;

    }

    private CommandStackItem<T> first;

    private CommandStackItem<T> lastExecuted;
    private CommandStackItem<T> lastSaved;

    public boolean isEmpty() {
        return first == null;
    }

    public void addCommand(final T command) {
        final CommandStackItem<T> csi = new CommandStackItem<>();
        csi.command = command;
        csi.previous = lastExecuted;
        if (lastExecuted == null) {
            first = csi;
        } else {
            lastExecuted.next = csi;
        }
        lastExecuted = csi;

    }

    public boolean canUndo() {
        return lastExecuted != null;
    }

    public boolean canRedo() {
        return (lastExecuted == null && first != null) || (lastExecuted != null && lastExecuted.next != null);
    }

    public T undo() {
        final T commandToUndo = lastExecuted.command;

        lastExecuted = lastExecuted.previous;

        return commandToUndo;
    }

    public T redo() {
        if (lastExecuted == null) {
            lastExecuted = first;
        } else {
            lastExecuted = lastExecuted.next;
        }

        return lastExecuted.command;
    }

    public boolean isDirty() {
        return lastExecuted != lastSaved;
    }

    public void setSavePoint() {
        lastSaved = lastExecuted;
    }
}
