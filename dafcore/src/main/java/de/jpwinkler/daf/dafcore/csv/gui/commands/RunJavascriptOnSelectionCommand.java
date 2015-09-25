package de.jpwinkler.daf.dafcore.csv.gui.commands;

import java.util.ArrayList;
import java.util.List;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;

import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class RunJavascriptOnSelectionCommand extends AbstractCommand {

    private final String javascript;
    private final List<DoorsObject> selectedObjects;

    public RunJavascriptOnSelectionCommand(final DoorsModule module, final String javascript, final List<DoorsObject> selection) {
        super(module);
        this.javascript = javascript;
        selectedObjects = new ArrayList<>(selection);
    }

    @Override
    public boolean isApplicable() {
        return true;
    }

    @Override
    public String getName() {
        return "Run Javascript";
    }

    @Override
    public void apply() {
        redo();
    }

    @Override
    public void redo() {
        final Context cx = Context.enter();

        final ScriptableObject scope = cx.initStandardObjects();

        for (final DoorsObject object : selectedObjects) {
            ScriptableObject.putProperty(scope, "object", Context.javaToJS(object, scope));
            cx.evaluateString(scope, javascript, "script", 1, null);
        }

        Context.exit();

    }

    @Override
    public void undo() {
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return new UpdateAction[] { UpdateAction.FIX_OBJECT_LEVELS, UpdateAction.FIX_OBJECT_NUMBERS, UpdateAction.UPDATE_CONTENT_VIEW, UpdateAction.UPDATE_OUTLINE_VIEW };
    }

    @Override
    public boolean canUndo() {
        return false;
    }
}
