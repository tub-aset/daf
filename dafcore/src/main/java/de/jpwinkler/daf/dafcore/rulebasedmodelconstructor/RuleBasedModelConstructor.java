package de.jpwinkler.daf.dafcore.rulebasedmodelconstructor;

import java.util.List;
import java.util.logging.Logger;

import de.jpwinkler.daf.dafcore.model.common.ModelObject;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util.MarkedObjectVisitor;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util.RuleApplicationVisitor;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util.TransformationException;
import de.jpwinkler.daf.dafcore.workflow.AbstractStepImpl;
import de.jpwinkler.daf.dafcore.workflow.ModelConstructorImpl;

public abstract class RuleBasedModelConstructor extends AbstractStepImpl implements ModelConstructorImpl {

    private static final Logger LOGGER = Logger.getLogger(RuleBasedModelConstructor.class.getName());

    private DoorsModule module;
    private RuleContext context;

    protected abstract ModelObject createRootModelObject();

    protected abstract List<Class<? extends Rule>> getRules();

    protected abstract boolean probe(DoorsModule module);

    protected void preProcess(final ModelObject modelObject, final RuleContext context) {
        // Do nothing by default
    }

    protected void postProcess(final ModelObject modelObject, final RuleContext context) {
        // Do nothing by default
    }

    @Override
    public void setSource(final DoorsModule module) {
        this.module = module;
    }

    @Override
    public ModelObject execute() {
        LOGGER.info(String.format("Starting transformation run with model constructor %s on module %s.", getClass().getName(), module.getName()));
        final long t1 = System.currentTimeMillis();

        if (!probe(module)) {
            LOGGER.severe(String.format("%s can't handle module", getClass().getName()));
            return null;
        }

        context = new RuleContext();
        try {
            context.init(this, module);
        } catch (final TransformationException e) {
            // TODO: proper exception handling!
            throw new RuntimeException(e);
        }
        preProcess(context.getRootModelObject(), context);
        while (context.beginStage()) {
            LOGGER.info(String.format("Starting transformation stage %d. Active Rules: %s", context.getStage(), context.getActiveRules()));
            final long t2 = System.currentTimeMillis();

            context.getModule().accept(new RuleApplicationVisitor(context.getActiveRules(), context));

            context.endStage();

            LOGGER.info(String.format("Transformation stage %d completed in %d ms.", context.getStage(), System.currentTimeMillis() - t2));
        }
        postProcess(context.getRootModelObject(), context);

        LOGGER.info(String.format("Transformation run completed in %d ms.", System.currentTimeMillis() - t1));

        if (!context.getRulePool().isEmpty()) {
            LOGGER.warning("Some rules were not executed: " + context.getRulePool());
        }

        final MarkedObjectVisitor markedObjectVisitor = new MarkedObjectVisitor(context);
        context.getModule().accept(markedObjectVisitor);

        if (!markedObjectVisitor.getUnmarkedObjects().isEmpty()) {
            LOGGER.warning(String.format("%d out of %d objects were left unmarked.", markedObjectVisitor.getUnmarkedObjects().size(), markedObjectVisitor.getUnmarkedObjects().size() + markedObjectVisitor.getMarkedObjects().size()));
        }

        for (final String markerType : context.getUsedMarkerTypes()) {
            LOGGER.info(String.format("Number of objects marked with %s: %d", markerType, context.getMarkedObjects(markerType).size()));
        }

        return context.getRootModelObject();
    }

}
