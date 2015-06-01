package de.jpwinkler.daf.dafcore.workflow;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import de.jpwinkler.daf.workflowdsl.workflowDsl.ArrayVariable;
import de.jpwinkler.daf.workflowdsl.workflowDsl.DependencyFeature;
import de.jpwinkler.daf.workflowdsl.workflowDsl.ImplementationFeature;
import de.jpwinkler.daf.workflowdsl.workflowDsl.ModelConstructorStep;
import de.jpwinkler.daf.workflowdsl.workflowDsl.ModelOperationStep;
import de.jpwinkler.daf.workflowdsl.workflowDsl.OperationFeature;
import de.jpwinkler.daf.workflowdsl.workflowDsl.SimpleVariable;
import de.jpwinkler.daf.workflowdsl.workflowDsl.SourceFeature;
import de.jpwinkler.daf.workflowdsl.workflowDsl.Step;
import de.jpwinkler.daf.workflowdsl.workflowDsl.Target;
import de.jpwinkler.daf.workflowdsl.workflowDsl.Variable;
import de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowModel;

public final class WorkflowUtil {

    private static final Pattern VARIABLE_PATTERN = Pattern.compile("\\$\\{(.*?)\\}");

    private WorkflowUtil() {
    }

    public static List<DependencyFeature> getDependencies(final List<OperationFeature> step) {
        return step.stream().filter(f -> f instanceof DependencyFeature).map(f -> (DependencyFeature) f).collect(Collectors.toList());
    }

    public static List<Variable> getVariables(final WorkflowModel model) {
        return model.getElements().stream().filter(e -> e instanceof Variable).map(e -> (Variable) e).collect(Collectors.toList());
    }

    public static List<Target> getTargets(final WorkflowModel model) {
        return model.getElements().stream().filter(e -> e instanceof Target).map(e -> (Target) e).collect(Collectors.toList());
    }

    public static String getImplementation(final Step step) {
        Optional<ImplementationFeature> implementationFeature;
        if (step instanceof ModelConstructorStep) {
            implementationFeature = ((ModelConstructorStep) step).getFeatures().stream().filter(f -> f instanceof ImplementationFeature).map(f -> (ImplementationFeature) f).findFirst();
        } else if (step instanceof ModelOperationStep) {
            implementationFeature = ((ModelOperationStep) step).getFeatures().stream().filter(f -> f instanceof ImplementationFeature).map(f -> (ImplementationFeature) f).findFirst();
        } else {
            throw new RuntimeException(String.format("Don't know how to get implementation of step %s.", step.getClass().getName()));
        }
        if (implementationFeature.isPresent()) {
            return implementationFeature.get().getImplementation();
        } else {
            return null;
        }
    }

    public static SourceFeature getSource(final ModelConstructorStep step) {
        final Optional<SourceFeature> sourceFeature = step.getFeatures().stream().filter(f -> f instanceof SourceFeature).map(f -> (SourceFeature) f).findFirst();
        if (sourceFeature.isPresent()) {
            return sourceFeature.get();
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T replaceVariables(final T variableValue, final Map<String, Object> variables) throws WorkflowException {
        if (variableValue instanceof String) {
            return (T) replaceVariablesInString((String) variableValue, variables);
        } else if (variableValue instanceof String[]) {
            final String[] array = (String[]) variableValue;
            final String[] result = Arrays.copyOf(array, array.length);
            for (int i = 0; i < result.length; i++) {
                result[i] = replaceVariablesInString(result[i], variables);
            }
            return (T) result;
        } else {
            throw new WorkflowException();
        }
    }

    private static String replaceVariablesInString(final String s, final Map<String, Object> variables) throws WorkflowException {
        String result = s;

        Matcher matcher = null;
        while ((matcher = VARIABLE_PATTERN.matcher(result)).find()) {
            final Object variableValue = variables.get(matcher.group(1));
            if (variableValue == null) {
                throw new WorkflowException(String.format("Unresolved variable %s.", matcher.group(1)));
            } else if (!(variableValue instanceof String)) {
                throw new WorkflowException();
            } else {
                result = matcher.replaceFirst(Matcher.quoteReplacement((String) variableValue));
            }
        }

        return result;
    }

    public static Object getVariableValue(final Variable variable) throws WorkflowException {
        if (variable instanceof SimpleVariable) {
            return ((SimpleVariable) variable).getValue();
        } else if (variable instanceof ArrayVariable) {
            return ((ArrayVariable) variable).getItems().toArray(new String[] {});
        } else {
            throw new WorkflowException(String.format("Unknown variable type %s.", variable.getClass().getName()));
        }
    }
}
