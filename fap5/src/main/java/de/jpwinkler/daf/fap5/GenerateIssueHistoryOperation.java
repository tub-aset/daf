package de.jpwinkler.daf.fap5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.jpwinkler.daf.dafcore.model.common.ModelObject;
import de.jpwinkler.daf.dafcore.workflow.AbstractStepImpl;
import de.jpwinkler.daf.dafcore.workflow.ModelOperationImpl;
import de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel;
import de.jpwinkler.daf.fap5.model.issuehistory.IssueHistoryFactory;
import de.jpwinkler.daf.fap5.model.issuehistory.IssueHistoryModel;
import de.jpwinkler.daf.fap5.model.issuehistory.Version;

public class GenerateIssueHistoryOperation extends AbstractStepImpl implements ModelOperationImpl {

    @Override
    public ModelObject execute() {

        final IssueHistoryModel issueHistoryModel = IssueHistoryFactory.eINSTANCE.createIssueHistoryModel();

        final List<String> parameterNames = new ArrayList<>(getParameterNames());
        Collections.sort(parameterNames);

        for (final String parameter : parameterNames) {
            final List<CodeBeamerModel> model = getParameter(parameter);

            final Version version = IssueHistoryFactory.eINSTANCE.createVersion();

            for (final CodeBeamerModel cbm : model) {
                version.getDocuments().add(cbm);
                version.setVersionNumber(cbm.getVersionNumber());
            }

            issueHistoryModel.getVersions().add(version);
        }

        return issueHistoryModel;
    }

}
