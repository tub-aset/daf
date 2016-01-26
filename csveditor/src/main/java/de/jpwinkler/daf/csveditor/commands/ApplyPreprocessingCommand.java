package de.jpwinkler.daf.csveditor.commands;

import java.util.HashMap;
import java.util.Map;

import de.jpwinkler.daf.dafcore.csv.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dataprocessing.preprocessing.ObjectTextPreprocessor;

public class ApplyPreprocessingCommand extends AbstractCommand {

    private final ObjectTextPreprocessor preprocessor;

    private final Map<DoorsObject, String> oldObjectHeading = new HashMap<>();
    private final Map<DoorsObject, String> newObjectHeading = new HashMap<>();
    private final Map<DoorsObject, String> oldObjectText = new HashMap<>();
    private final Map<DoorsObject, String> newObjectText = new HashMap<>();

    public ApplyPreprocessingCommand(final DoorsModule module, final ObjectTextPreprocessor preprocessor) {
        super(module);
        this.preprocessor = preprocessor;

    }

    @Override
    public boolean isApplicable() {
        return true;
    }

    @Override
    public String getName() {
        return "Apply Preprocessing";
    }

    @Override
    public void apply() {
        getModule().accept(new DoorsTreeNodeVisitor() {
            @Override
            public boolean visitPreTraverse(final DoorsObject object) {
                oldObjectHeading.put(object, object.getObjectHeading());
                newObjectHeading.put(object, preprocessor.preprocessTextToString(object.getObjectHeading()));
                oldObjectText.put(object, object.getObjectText());
                newObjectText.put(object, preprocessor.preprocessTextToString(object.getObjectText()));
                return true;
            }
        });
        redo();
    }

    @Override
    public void redo() {
        setHeadingAndText(newObjectHeading, newObjectText);
    }

    private void setHeadingAndText(final Map<DoorsObject, String> objectHeadingMap, final Map<DoorsObject, String> objectTextMap) {
        getModule().accept(new DoorsTreeNodeVisitor() {
            @Override
            public boolean visitPreTraverse(final DoorsObject object) {
                object.setObjectText(objectTextMap.get(object));
                object.setObjectHeading(objectHeadingMap.get(object));
                return true;
            }
        });

    }

    @Override
    public void undo() {
        setHeadingAndText(oldObjectHeading, oldObjectText);
    }

    @Override
    public UpdateAction[] getUpdateActions() {
        return new UpdateAction[] { UpdateAction.UPDATE_CONTENT_VIEW };
    }

}
