package de.jpwinkler.daf.fap5;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import de.jpwinkler.daf.dafcore.model.common.ModelObject;
import de.jpwinkler.daf.dafcore.workflow.AbstractStepImpl;
import de.jpwinkler.daf.dafcore.workflow.ModelOperationImpl;
import de.jpwinkler.daf.fap5.model.cockpit.CockpitFactory;
import de.jpwinkler.daf.fap5.model.cockpit.CockpitModel;
import de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionProgress;
import de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionTargetMapping;
import de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress;
import de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel;
import de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsModel;
import de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget;
import de.jpwinkler.daf.fap5.model.componentssystems.Functionality;
import de.jpwinkler.daf.fap5.model.srs.FunctionContribution;
import de.jpwinkler.daf.fap5.model.srs.SRSModel;
import de.jpwinkler.daf.fap5.model.srs.VehicleFunction;

public class GenerateCockpitOperation extends AbstractStepImpl implements ModelOperationImpl {

    private static final CSVFormat FORMAT = CSVFormat.newFormat(',').
            withHeader().
            withQuote('"').
            withEscape('\\').
            withIgnoreSurroundingSpaces().
            withRecordSeparator("\r\n");

    private static final Logger LOGGER = Logger.getLogger(GenerateCockpitOperation.class.getName());

    private List<SRSModel> getSRSModels() {
        return getParameter("srs");
    }

    private ComponentsSystemsModel getComponentsSystemsModel() {
        return getSingleParameter("cs");
    }

    private List<CodeBeamerModel> getCodeBeamerModels() {
        return getParameter("cb");
    }

    private CockpitModel cockpitModel;

    @Override
    public ModelObject execute() {
        cockpitModel = CockpitFactory.eINSTANCE.createCockpitModel();

        cockpitModel.getDocuments().addAll(getCodeBeamerModels());

        if (getComponentsSystemsModel() != null) {
            readMapping(getStringVariable("mappingFile"));

            for (final SRSModel srsModel : getSRSModels()) {
                for (final VehicleFunction vf : srsModel.getVehicleFunctions()) {
                    createVehicleFunctionProgress(vf, null);
                }
            }
        }

        return cockpitModel;
    }

    private void createVehicleFunctionProgress(final VehicleFunction vf, final VehicleFunctionProgress parentProgress) {
        final VehicleFunctionProgress vfProgress = CockpitFactory.eINSTANCE.createVehicleFunctionProgress();
        vfProgress.setVehicleFunction(vf);
        if (parentProgress != null) {
            parentProgress.getSubFunctionProgress().add(vfProgress);
        } else {
            cockpitModel.getVehicleFunctionProgress().add(vfProgress);
        }
        vfProgress.setCockpitModel(cockpitModel);

        for (final FunctionContribution fc : vf.getFunctionContributions()) {
            final FunctionContributionProgress fcProgress = CockpitFactory.eINSTANCE.createFunctionContributionProgress();
            fcProgress.setFunctionContribution(fc);
            fcProgress.setCockpitModel(cockpitModel);
            vfProgress.getFunctionContributionProgress().add(fcProgress);
        }

        for (final VehicleFunction subFunction : vf.getSubFunctions()) {
            createVehicleFunctionProgress(subFunction, vfProgress);
        }
    }

    private void readMapping(final String filename) {
        try {
            final CSVParser result = CSVParser.parse(new File(filename), Charset.defaultCharset(), FORMAT);

            // Step 1: create mappings for all objects in the mapping file.
            for (final CSVRecord record : result.getRecords()) {
                final String path = record.get("Path");
                if (!path.isEmpty()) {
                    createMapping(record.get("Object Text"), path.split("\r\n"));
                }
            }

            // Step 2: add mappings for all unmapped function contribution
            // targets.

            for (final FunctionContributionTarget fct : getComponentsSystemsModel().getFunctionContributionTargets()) {
                if (cockpitModel.findMapping(fct) == null) {
                    final FunctionContributionTargetMapping mapping = CockpitFactory.eINSTANCE.createFunctionContributionTargetMapping();
                    mapping.setFunctionContributionTarget(fct);
                    cockpitModel.getMappings().add(mapping);
                }
            }
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createMapping(final String functionContributionTargetName, final String... documentPaths) {
        LOGGER.fine(String.format("Creating mapping for function contribution target %s.", functionContributionTargetName));
        final FunctionContributionTargetMapping mapping = CockpitFactory.eINSTANCE.createFunctionContributionTargetMapping();
        for (final FunctionContributionTarget fct : getComponentsSystemsModel().getFunctionContributionTargets()) {
            if (functionContributionTargetName.equals(fct.getName())) {

                mapping.setFunctionContributionTarget(fct);
                LOGGER.fine(String.format(" Using function contribution target %s (%s).", fct.getName(), fct.getAcronym()));
            }
        }

        if (mapping.getFunctionContributionTarget() != null) {
            if (cockpitModel.findMapping(mapping.getFunctionContributionTarget()) != null) {
                LOGGER.warning(String.format(" A mapping for function contribution target %s already exists.", mapping.getFunctionContributionTarget().getName()));
                return;
            }

            for (final CodeBeamerModel model : getCodeBeamerModels()) {
                for (final String documentPath : documentPaths) {
                    if (documentPath.contains(model.getName())) {
                        mapping.getDocuments().add(model);
                        LOGGER.fine(String.format(" Adding document %s for document name %s.", model.getName(), documentPath));
                    }
                }
            }
            cockpitModel.getMappings().add(mapping);
        } else {
            LOGGER.warning(String.format(" Could not find function contribution target %s.", functionContributionTargetName));
        }

        if (mapping.getFunctionContributionTarget() instanceof de.jpwinkler.daf.fap5.model.componentssystems.System) {
            final de.jpwinkler.daf.fap5.model.componentssystems.System system = (de.jpwinkler.daf.fap5.model.componentssystems.System) mapping.getFunctionContributionTarget();

            for (final Functionality functionality : system.getFunctionalities()) {
                createMapping(functionality.getName(), documentPaths);
            }
        }
    }

}
