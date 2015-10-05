package de.jpwinkler.daf.csveditor.commands;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;

import de.jpwinkler.daf.dafcore.csv.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class RunJavascriptOnObjectsCommand extends AbstractCommand {

    private final String javascript;

    public RunJavascriptOnObjectsCommand(final DoorsModule module, final String javascript) {
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

        getModule().accept(new DoorsTreeNodeVisitor() {
            @Override
            public boolean visitPreTraverse(final DoorsObject object) {
                ScriptableObject.putProperty(scope, "object", Context.javaToJS(object, scope));
                cx.evaluateString(scope, javascript, "script", 1, null);
                return true;
            }
        });

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
