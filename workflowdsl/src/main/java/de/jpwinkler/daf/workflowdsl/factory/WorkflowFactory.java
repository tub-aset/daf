package de.jpwinkler.daf.workflowdsl.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.apache.commons.lang.StringEscapeUtils;

import de.jpwinkler.daf.workflowdsl.ArrayVariable;
import de.jpwinkler.daf.workflowdsl.DependencyFeature;
import de.jpwinkler.daf.workflowdsl.ForFeature;
import de.jpwinkler.daf.workflowdsl.ImplementationFeature;
import de.jpwinkler.daf.workflowdsl.ModelConstructorStep;
import de.jpwinkler.daf.workflowdsl.ModelOperationStep;
import de.jpwinkler.daf.workflowdsl.ModuleSet;
import de.jpwinkler.daf.workflowdsl.ModuleSetEntry;
import de.jpwinkler.daf.workflowdsl.OperationFeature;
import de.jpwinkler.daf.workflowdsl.SimpleVariable;
import de.jpwinkler.daf.workflowdsl.SourceFeature;
import de.jpwinkler.daf.workflowdsl.Target;
import de.jpwinkler.daf.workflowdsl.Variable;
import de.jpwinkler.daf.workflowdsl.Workflow;
import de.jpwinkler.daf.workflowdsl.WorkflowBaseListener;
import de.jpwinkler.daf.workflowdsl.WorkflowLexer;
import de.jpwinkler.daf.workflowdsl.WorkflowParser;
import de.jpwinkler.daf.workflowdsl.WorkflowParser.ArrayVariableContext;
import de.jpwinkler.daf.workflowdsl.WorkflowParser.DependencyFeatureContext;
import de.jpwinkler.daf.workflowdsl.WorkflowParser.ForFeatureContext;
import de.jpwinkler.daf.workflowdsl.WorkflowParser.ImplementationFeatureContext;
import de.jpwinkler.daf.workflowdsl.WorkflowParser.ModelConstructorStepContext;
import de.jpwinkler.daf.workflowdsl.WorkflowParser.ModelOperationStepContext;
import de.jpwinkler.daf.workflowdsl.WorkflowParser.ModuleSetContext;
import de.jpwinkler.daf.workflowdsl.WorkflowParser.ModuleSetEntryContext;
import de.jpwinkler.daf.workflowdsl.WorkflowParser.SimpleVariableContext;
import de.jpwinkler.daf.workflowdsl.WorkflowParser.SourceFeatureContext;
import de.jpwinkler.daf.workflowdsl.WorkflowParser.TargetContext;
import de.jpwinkler.daf.workflowdsl.WorkflowParser.WorkflowContext;

public class WorkflowFactory extends WorkflowBaseListener {

    private Workflow workflow;

    private final Stack<List<ModuleSetEntry>> moduleSetEntries = new Stack<>();
    private final Stack<List<OperationFeature>> operationFeatures = new Stack<>();
    private final Stack<List<Variable>> variables = new Stack<>();

    public Workflow createWorkFlow(final InputStream is) throws IOException {
        final WorkflowLexer lexer = new WorkflowLexer(new ANTLRInputStream(is));
        final WorkflowParser parser = new WorkflowParser(new CommonTokenStream(lexer));

        parser.addParseListener(this);

        parser.workflow();

        try {
            new ProxyResolver().resolveProxies(workflow);
        } catch (final ProxyResolveException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

        return workflow;
    }

    private String replaceEscapeSequences(final String string) {
        return StringEscapeUtils.unescapeJavaScript(string.substring(1, string.length() - 1));
    }

    @Override
    public void enterWorkflow(final WorkflowContext ctx) {
        workflow = new Workflow();
        variables.push(new ArrayList<>());
    }

    @Override
    public void exitWorkflow(final WorkflowContext ctx) {
        workflow.getElements().addAll(variables.pop());
    }

    @Override
    public void exitTarget(final TargetContext ctx) {
        final Target target = new Target();

        target.setStep(new StepProxy(ctx.stepRef.getText()));

        workflow.getElements().add(target);

    }

    @Override
    public void exitModuleSetEntry(final ModuleSetEntryContext ctx) {
        final ModuleSetEntry moduleSetEntry = new ModuleSetEntry();

        moduleSetEntry.setType(ctx.type.getText());
        moduleSetEntry.setReference(replaceEscapeSequences(ctx.reference.getText()));

        moduleSetEntries.peek().add(moduleSetEntry);
    }

    @Override
    public void enterModuleSet(final ModuleSetContext ctx) {
        moduleSetEntries.push(new ArrayList<>());
    }

    @Override
    public void exitModuleSet(final ModuleSetContext ctx) {
        final ModuleSet moduleSet = new ModuleSet();
        moduleSet.setName(ctx.name.getText());
        moduleSet.getModuleSetEntries().addAll(moduleSetEntries.pop());

        workflow.getElements().add(moduleSet);
    }

    @Override
    public void enterModelConstructorStep(final ModelConstructorStepContext ctx) {
        operationFeatures.push(new ArrayList<>());
    }

    @Override
    public void exitModelConstructorStep(final ModelConstructorStepContext ctx) {
        final ModelConstructorStep modelConstructorStep = new ModelConstructorStep();

        modelConstructorStep.setName(ctx.name.getText());
        modelConstructorStep.getFeatures().addAll(operationFeatures.pop());

        workflow.getElements().add(modelConstructorStep);
    }

    @Override
    public void enterModelOperationStep(final ModelOperationStepContext ctx) {
        operationFeatures.push(new ArrayList<>());
    }

    @Override
    public void exitModelOperationStep(final ModelOperationStepContext ctx) {
        final ModelOperationStep modelOperationStep = new ModelOperationStep();

        modelOperationStep.setName(ctx.name.getText());
        modelOperationStep.getFeatures().addAll(operationFeatures.pop());

        workflow.getElements().add(modelOperationStep);
    }

    @Override
    public void exitSimpleVariable(final SimpleVariableContext ctx) {
        final SimpleVariable simpleVariable = new SimpleVariable();

        simpleVariable.setName(ctx.name.getText());
        simpleVariable.setValue(replaceEscapeSequences(ctx.value.getText()));

        variables.peek().add(simpleVariable);
    }

    @Override
    public void exitArrayVariable(final ArrayVariableContext ctx) {
        final ArrayVariable arrayVariable = new ArrayVariable();

        arrayVariable.setName(ctx.name.getText());

        for (final Token item : ctx.items) {
            arrayVariable.getItems().add(replaceEscapeSequences(item.getText()));
        }

        variables.peek().add(arrayVariable);
    }

    @Override
    public void exitSourceFeature(final SourceFeatureContext ctx) {
        final SourceFeature sourceFeature = new SourceFeature();

        sourceFeature.setModuleSet(new ModuleSetProxy(ctx.moduleSetRef.getText()));

        operationFeatures.peek().add(sourceFeature);
    }

    @Override
    public void enterForFeature(final ForFeatureContext ctx) {
        operationFeatures.push(new ArrayList<>());
    }

    @Override
    public void exitForFeature(final ForFeatureContext ctx) {
        final ForFeature forFeature = new ForFeature();

        forFeature.setArrayVar(ctx.arrayVar.getText());
        forFeature.setLoopVar(ctx.loopVar.getText());
        forFeature.getFeatures().addAll(operationFeatures.pop());

        operationFeatures.peek().add(forFeature);
    }

    @Override
    public void enterDependencyFeature(final DependencyFeatureContext ctx) {
        variables.push(new ArrayList<>());
    }

    @Override
    public void exitDependencyFeature(final DependencyFeatureContext ctx) {
        final DependencyFeature dependencyFeature = new DependencyFeature();
        dependencyFeature.setName(ctx.name.getText());
        dependencyFeature.setStep(new StepProxy(ctx.stepRef.getText()));
        dependencyFeature.getVariables().addAll(variables.pop());

        operationFeatures.peek().add(dependencyFeature);
    }

    @Override
    public void exitImplementationFeature(final ImplementationFeatureContext ctx) {
        final ImplementationFeature implementationFeature = new ImplementationFeature();

        implementationFeature.setImplementation(replaceEscapeSequences(ctx.implementation.getText()));

        operationFeatures.peek().add(implementationFeature);
    }

}
