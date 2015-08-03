package de.jpwinkler.daf.workflowdsl.factory;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import de.jpwinkler.daf.workflowdsl.ArrayVariable;
import de.jpwinkler.daf.workflowdsl.DependencyFeature;
import de.jpwinkler.daf.workflowdsl.ForFeature;
import de.jpwinkler.daf.workflowdsl.ImplementationFeature;
import de.jpwinkler.daf.workflowdsl.ModelConstructorStep;
import de.jpwinkler.daf.workflowdsl.ModelOperationStep;
import de.jpwinkler.daf.workflowdsl.ModuleSet;
import de.jpwinkler.daf.workflowdsl.ModuleSetEntry;
import de.jpwinkler.daf.workflowdsl.SimpleVariable;
import de.jpwinkler.daf.workflowdsl.SourceFeature;
import de.jpwinkler.daf.workflowdsl.Target;
import de.jpwinkler.daf.workflowdsl.Workflow;

public class WorkflowFactoryTest {

    @Test
    public void testStep() throws IOException {

        final Workflow workFlow = loadWorkFlow("testStep");

        assertEquals(2, workFlow.getElements().size());
        assertEquals(ModelConstructorStep.class, workFlow.getElements().get(0).getClass());
        assertEquals(ModelOperationStep.class, workFlow.getElements().get(1).getClass());

        final ModelConstructorStep constructor = (ModelConstructorStep) workFlow.getElements().get(0);
        final ModelOperationStep o = (ModelOperationStep) workFlow.getElements().get(1);

        assertEquals("con123_4", constructor.getName());
        assertEquals("op6_G4g", o.getName());
        assertEquals(0, constructor.getFeatures().size());
        assertEquals(0, o.getFeatures().size());
    }

    @Test
    public void testTarget() throws IOException {
        final Workflow workflow = loadWorkFlow("testTarget");

        assertEquals(2, workflow.getElements().size());
        assertEquals(Target.class, workflow.getElements().get(0).getClass());
        assertEquals(ModelOperationStep.class, workflow.getElements().get(1).getClass());

        final Target target = (Target) workflow.getElements().get(0);
        final ModelOperationStep op = (ModelOperationStep) workflow.getElements().get(1);

        assertEquals(op, target.getStep());
    }

    @Test
    public void testModuleSet() throws IOException {
        final Workflow workflow = loadWorkFlow("testModuleSet");
        assertEquals(1, workflow.getElements().size());
        assertEquals(ModuleSet.class, workflow.getElements().get(0).getClass());

        final ModuleSet moduleSet = (ModuleSet) workflow.getElements().get(0);

        assertEquals("Set1", moduleSet.getName());
        assertEquals(4, moduleSet.getModuleSetEntries().size());

        ModuleSetEntry entry = moduleSet.getModuleSetEntries().get(0);
        assertEquals("csv", entry.getType());
        assertEquals("C:\\a\\b\\c\\testMyFile.csv", entry.getReference());

        entry = moduleSet.getModuleSetEntries().get(1);
        assertEquals("csvfolder", entry.getType());
        assertEquals("C:\\a\\g\\hereAreMyFiles\\", entry.getReference());

        entry = moduleSet.getModuleSetEntries().get(2);
        assertEquals("doors", entry.getType());
        assertEquals("/Path/to/project/Path/to/module/System Req", entry.getReference());

        entry = moduleSet.getModuleSetEntries().get(3);
        assertEquals("doorsurl", entry.getType());
        assertEquals("doors://doors01.rd.corpintra.net:44910/?version=2&prodID=0&urn=urn:telelogic::1-3d298d2a4ddd3563-M-00209650", entry.getReference());

    }

    @Test
    public void testStrings() throws IOException {
        final Workflow workflow = loadWorkFlow("testStrings");

        final ModuleSet moduleSet = (ModuleSet) workflow.getElements().get(0);

        assertEquals("abc", moduleSet.getModuleSetEntries().get(0).getReference());
        assertEquals("123 123", moduleSet.getModuleSetEntries().get(1).getReference());
        assertEquals("!§$%&/()=?öäü+*#'<>|#~,;.:-_^°", moduleSet.getModuleSetEntries().get(2).getReference());
        assertEquals("\\", moduleSet.getModuleSetEntries().get(3).getReference());
        assertEquals("\"Hello\"", moduleSet.getModuleSetEntries().get(4).getReference());
        assertEquals("\n", moduleSet.getModuleSetEntries().get(5).getReference());
        assertEquals("", moduleSet.getModuleSetEntries().get(6).getReference());
    }

    @Test
    public void testSimpleVarible() throws IOException {
        final Workflow workflow = loadWorkFlow("testSimpleVariable");

        assertEquals(1, workflow.getElements().size());
        assertEquals(SimpleVariable.class, workflow.getElements().get(0).getClass());

        final SimpleVariable simpleVariable = (SimpleVariable) workflow.getElements().get(0);

        assertEquals("varname", simpleVariable.getName());
        assertEquals("varvalue", simpleVariable.getValue());
    }

    @Test
    public void testArrayVariable() throws IOException {
        final Workflow workflow = loadWorkFlow("testArrayVariable");

        assertEquals(3, workflow.getElements().size());
        assertEquals(ArrayVariable.class, workflow.getElements().get(0).getClass());
        assertEquals(ArrayVariable.class, workflow.getElements().get(1).getClass());
        assertEquals(ArrayVariable.class, workflow.getElements().get(2).getClass());

        final ArrayVariable var1 = (ArrayVariable) workflow.getElements().get(0);
        final ArrayVariable var2 = (ArrayVariable) workflow.getElements().get(1);
        final ArrayVariable var3 = (ArrayVariable) workflow.getElements().get(2);

        assertEquals("var1", var1.getName());
        assertEquals("var2", var2.getName());
        assertEquals("var3", var3.getName());
        assertEquals(0, var1.getItems().size());
        assertEquals(2, var2.getItems().size());
        assertEquals("", var2.getItems().get(0));
        assertEquals("", var2.getItems().get(1));

        assertEquals(3, var3.getItems().size());
        assertEquals("53", var3.getItems().get(0));
        assertEquals("gd3", var3.getItems().get(1));
        assertEquals("ddd", var3.getItems().get(2));
    }

    @Test
    public void testDependencyFeature() throws IOException {
        final Workflow workflow = loadWorkFlow("testDependencyFeature");

        final ModelConstructorStep c = (ModelConstructorStep) workflow.getElements().get(0);
        final ModelOperationStep o = (ModelOperationStep) workflow.getElements().get(1);

        assertEquals(3, o.getFeatures().size());
        assertEquals(DependencyFeature.class, o.getFeatures().get(0).getClass());
        assertEquals(DependencyFeature.class, o.getFeatures().get(1).getClass());
        assertEquals(DependencyFeature.class, o.getFeatures().get(2).getClass());

        final DependencyFeature d1 = (DependencyFeature) o.getFeatures().get(0);
        final DependencyFeature d2 = (DependencyFeature) o.getFeatures().get(1);
        final DependencyFeature d3 = (DependencyFeature) o.getFeatures().get(2);

        assertEquals("d1", d1.getName());
        assertEquals(0, d1.getVariables().size());
        assertEquals(c, d1.getStep());

        assertEquals("d2", d2.getName());
        assertEquals(0, d2.getVariables().size());
        assertEquals(c, d2.getStep());

        assertEquals("d3", d3.getName());
        assertEquals(2, d3.getVariables().size());
        assertEquals(SimpleVariable.class, d3.getVariables().get(0).getClass());
        assertEquals(ArrayVariable.class, d3.getVariables().get(1).getClass());

        final SimpleVariable x = (SimpleVariable) d3.getVariables().get(0);
        final ArrayVariable y = (ArrayVariable) d3.getVariables().get(1);

        assertEquals("x", x.getName());
        assertEquals("a", x.getValue());

        assertEquals("y", y.getName());
        assertEquals(2, y.getItems().size());
        assertEquals("a", y.getItems().get(0));
        assertEquals("b", y.getItems().get(1));
    }

    @Test
    public void testForFeature() throws IOException {
        final Workflow workflow = loadWorkFlow("testForFeature");

        final ModelOperationStep o = (ModelOperationStep) workflow.getElements().get(0);
        assertEquals(2, o.getFeatures().size());

        assertEquals(ForFeature.class, o.getFeatures().get(0).getClass());
        assertEquals(ForFeature.class, o.getFeatures().get(1).getClass());

        final ForFeature f1 = (ForFeature) o.getFeatures().get(0);
        final ForFeature f2 = (ForFeature) o.getFeatures().get(1);

        assertEquals("m", f1.getLoopVar());
        assertEquals("modules", f1.getArrayVar());
        assertEquals(0, f1.getFeatures().size());

        assertEquals("o", f2.getLoopVar());
        assertEquals("objects", f2.getArrayVar());
        assertEquals(1, f2.getFeatures().size());

        assertEquals(ForFeature.class, f2.getFeatures().get(0).getClass());

        final ForFeature f3 = (ForFeature) f2.getFeatures().get(0);

        assertEquals("a", f3.getLoopVar());
        assertEquals("attributes", f3.getArrayVar());
        assertEquals(1, f3.getFeatures().size());
        assertEquals(ImplementationFeature.class, f3.getFeatures().get(0).getClass());
    }

    @Test
    public void testImplementationFeature() throws IOException {
        final Workflow workflow = loadWorkFlow("testImplementationFeature");

        final ModelOperationStep o = (ModelOperationStep) workflow.getElements().get(0);

        assertEquals(1, o.getFeatures().size());
        assertEquals(ImplementationFeature.class, o.getFeatures().get(0).getClass());

        final ImplementationFeature implementationFeature = (ImplementationFeature) o.getFeatures().get(0);
        assertEquals("this.is.my.Implementation", implementationFeature.getImplementation());
    }

    @Test
    public void testSourceFeature() throws IOException {
        final Workflow workflow = loadWorkFlow("testSourceFeature");

        final ModuleSet moduleSet = (ModuleSet) workflow.getElements().get(0);

        final ModelOperationStep o = (ModelOperationStep) workflow.getElements().get(1);

        assertEquals(1, o.getFeatures().size());
        assertEquals(SourceFeature.class, o.getFeatures().get(0).getClass());

        final SourceFeature sourceFeature = (SourceFeature) o.getFeatures().get(0);

        assertEquals(moduleSet, sourceFeature.getModuleSet());
    }

    private Workflow loadWorkFlow(final String name) throws IOException {
        final Workflow workFlow = new WorkflowFactory().createWorkFlow(getClass().getResourceAsStream(name + ".workflow"));
        return workFlow;
    }

}
