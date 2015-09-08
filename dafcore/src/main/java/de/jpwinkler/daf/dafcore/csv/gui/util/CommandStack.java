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

    public T getUndoCommand() {
        return lastExecuted != null ? lastExecuted.command : null;
    }

    public T getRedoCommand() {
        if (lastExecuted == null) {
            return first != null ? first.command : null;
        } else {
            return lastExecuted.next != null ? lastExecuted.next.command : null;
        }
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
