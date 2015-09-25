package de.jpwinkler.daf.dafcore.csv.gui.commands;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;

import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;

public class RunJavascriptOnModuleCommand extends AbstractCommand {

    private final String javascript;

    public RunJavascriptOnModuleCommand(final DoorsModule module, final String javascript) {
        super(module);
        this.javascript = javascript;
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

        ScriptableObject.putProperty(scope, "module", Context.javaToJS(getModule(), scope));
        cx.evaluateString(scope, javascript, "script", 1, null);

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
