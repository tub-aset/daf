package de.jpwinkler.daf.fap5;

import java.util.Arrays;
import java.util.List;

import de.jpwinkler.daf.dafcore.model.common.ModelObject;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.Marker;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.Rule;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleBasedModelConstructor;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;
import de.jpwinkler.daf.fap5.codebeamerrules.CodeBeamerConstants;
import de.jpwinkler.daf.fap5.codebeamerrules.DuplicatedLiteralRule;
import de.jpwinkler.daf.fap5.codebeamerrules.EllipsisRule;
import de.jpwinkler.daf.fap5.codebeamerrules.EmptyObjectTypeRule;
import de.jpwinkler.daf.fap5.codebeamerrules.HeadingAsRequirementRule;
import de.jpwinkler.daf.fap5.codebeamerrules.HeadingsRule;
import de.jpwinkler.daf.fap5.codebeamerrules.InformationRule;
import de.jpwinkler.daf.fap5.codebeamerrules.InformationWithLinkRule;
import de.jpwinkler.daf.fap5.codebeamerrules.MaturityRule;
import de.jpwinkler.daf.fap5.codebeamerrules.ObjectTextAndHeadingRule;
import de.jpwinkler.daf.fap5.codebeamerrules.QuestionMarkRule;
import de.jpwinkler.daf.fap5.codebeamerrules.RequirementRule;
import de.jpwinkler.daf.fap5.codebeamerrules.RequirementWithoutLinkRule;
import de.jpwinkler.daf.fap5.codebeamerrules.TBDRule;
import de.jpwinkler.daf.fap5.codebeamerrules.TBVRule;
import de.jpwinkler.daf.fap5.codebeamerrules.TodoRule;
import de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel;
import de.jpwinkler.daf.fap5.model.codebeamer.CodebeamerFactory;
import de.jpwinkler.daf.fap5.model.codebeamer.IntMetric;

public class CodeBeamerModelConstructor extends RuleBasedModelConstructor {

    @Override
    protected ModelObject createRootModelObject() {
        return CodebeamerFactory.eINSTANCE.createCodeBeamerModel();
    }

    @Override
    protected List<Class<? extends Rule>> getRules() {
        return Arrays.asList(EllipsisRule.class,
                // EmptyHeadingsRule.class,
                HeadingsRule.class,
                InformationRule.class,
                RequirementRule.class,
                EmptyObjectTypeRule.class,
                TBDRule.class,
                TBVRule.class,
                TodoRule.class,
                // WikilinkRule.class,
                // IllegalFormattingRule.class,
                QuestionMarkRule.class,
                DuplicatedLiteralRule.class, // +
                // IllegalFOObjectTypeRule.class,// +
                // SentenceCountRule.class,// +
                // InlinedChaptersRule.class,// +
                HeadingAsRequirementRule.class,
                ObjectTextAndHeadingRule.class, // +
                // EmptyDocumentRule.class,
                MaturityRule.class,
                RequirementWithoutLinkRule.class,
                InformationWithLinkRule.class
                // HeadingWithLinkRule.class
                );
    }

    @Override
    protected void postProcess(final ModelObject modelObject, final RuleContext context) {
        final CodeBeamerModel model = (CodeBeamerModel) modelObject;

        model.setName(context.getModule().getName());

        model.setSize(calcSize(context));

        model.setSpecified(context.getUsedMarkerTypes().contains(CodeBeamerConstants.MARKER_REQUIREMENT));

        model.setVersionNumber(getStringVariable("version"));

        model.setModule(context.getModule());

        final int reqCount = context.getMarkerCount(CodeBeamerConstants.MARKER_REQUIREMENT);
        addMetric(model, CodeBeamerConstants.METRIC_REQUIREMENT_COUNT, reqCount);
        addMetric(model, CodeBeamerConstants.METRIC_HEADING_AS_REQUIREMENT_COUNT, context.getMarkerCount(CodeBeamerConstants.MARKER_HEADING_AS_REQUIREMENT));
        addMetric(model, CodeBeamerConstants.METRIC_OPEN_TODOS, context.getMarkerCount(CodeBeamerConstants.MARKER_TBD) + context.getMarkerCount(CodeBeamerConstants.MARKER_TBV) + context.getMarkerCount(CodeBeamerConstants.MARKER_TODO) + context.getMarkerCount(CodeBeamerConstants.MARKER_QUESTION_MARK) + context.getMarkerCount(CodeBeamerConstants.MARKER_ELLIPSIS));
        addMetric(model, CodeBeamerConstants.METRIC_EMPTY_OBJECT_TYPE, context.getMarkerCount(CodeBeamerConstants.MARKER_EMPTY_OBJECT_TYPE));
        addMetric(model, CodeBeamerConstants.METRIC_EMPTY_HEADING, context.getMarkerCount(CodeBeamerConstants.MARKER_EMPTY_HEADING));
        addMetric(model, CodeBeamerConstants.METRIC_INFORMATION_WITH_LINK, context.getMarkerCount(CodeBeamerConstants.MARKER_INFORMATION_WITH_LINK));
        addMetric(model, CodeBeamerConstants.METRIC_HEADING_WITH_LINK, context.getMarkerCount(CodeBeamerConstants.MARKER_HEADING_WITH_LINK));
        addMetric(model, CodeBeamerConstants.METRIC_REQUIREMENT_WITHOUT_LINK, context.getMarkerCount(CodeBeamerConstants.MARKER_REQUIREMENT_WITHOUT_LINK));
        addMetric(model, CodeBeamerConstants.METRIC_MATURITY_OPEN_COUNT, context.getMarkerCount(CodeBeamerConstants.MARKER_MATURITY_OPEN));
        addMetric(model, CodeBeamerConstants.METRIC_MATURITY_SPECIFIED_COUNT, context.getMarkerCount(CodeBeamerConstants.MARKER_MATURITY_SPECIFIED));
        addMetric(model, CodeBeamerConstants.METRIC_MATURITY_FOLLOW_UP_COUNT, context.getMarkerCount(CodeBeamerConstants.MARKER_MATURITY_FOLLOW_UP));
        addMetric(model, CodeBeamerConstants.METRIC_MATURITY_FOLLOW_UP_HASHTAGS_COUNT, context.getMarkerCount(CodeBeamerConstants.MARKER_MATURITY_FOLLOW_UP_HASHTAGS));
        addMetric(model, CodeBeamerConstants.METRIC_MATURITY_AGREED_COUNT, context.getMarkerCount(CodeBeamerConstants.MARKER_MATURITY_AGREED));
        addMetric(model, CodeBeamerConstants.METRIC_ESTIMATED_REMAINING_WORK, (int) model.getEstimatedRemainingWork());
    }

    private void addMetric(final CodeBeamerModel model, final String metricName, final int metricValue) {
        final IntMetric metric = CodebeamerFactory.eINSTANCE.createIntMetric();
        metric.setName(metricName);
        metric.setValue(metricValue);
        model.getMetrics().add(metric);
    }

    private float calcSize(final RuleContext context) {
        float result = 0;
        for (final Marker requirementsMarker : context.getMarkers(CodeBeamerConstants.MARKER_REQUIREMENT)) {
            result += (int) requirementsMarker.getAttribute(CodeBeamerConstants.MARKER_REQUIREMENT_LENGTH);
        }
        result += context.getMarkerCount(CodeBeamerConstants.MARKER_INFORMATION);
        result += context.getMarkerCount(CodeBeamerConstants.MARKER_HEADING);
        return result;
    }

    @Override
    protected boolean probe(final DoorsModule module) {
        return true;
    }

}
