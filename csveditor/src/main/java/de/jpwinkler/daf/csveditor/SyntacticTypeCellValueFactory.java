package de.jpwinkler.daf.csveditor;

import java.util.HashMap;
import java.util.Map;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;
import de.jpwinkler.daf.reqinfclassifier.syntacticclassifier.SyntacticClassifier;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class SyntacticTypeCellValueFactory implements Callback<CellDataFeatures<DoorsObject, String>, ObservableValue<String>> {

    private final SyntacticClassifier syntacticClassifier = new SyntacticClassifier(ClassifierContext.getInstance());

    private final Map<DoorsObject, String> cache = new HashMap<>();

    @Override
    public ObservableValue<String> call(final CellDataFeatures<DoorsObject, String> param) {
        if (!cache.containsKey(param.getValue())) {
            cache.put(param.getValue(), syntacticClassifier.classify(param.getValue()));
        }
        final String label = cache.get(param.getValue());
        return new ReadOnlyStringWrapper(label != null ? label : "");
    }

}
