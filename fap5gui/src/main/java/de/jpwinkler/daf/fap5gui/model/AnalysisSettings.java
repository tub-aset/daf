package de.jpwinkler.daf.fap5gui.model;

public class AnalysisSettings {

    private String csvDirectory;
    private boolean reuseCache;
    private boolean runNewDoorsAnalysis;
    private boolean updateProgressReportExcelSheet;
    private String progressReportSourceFile;
    private String progressReportTargetFile;
    private boolean generateIssueLists;
    private String issueListsDirectory;

    private String moduleListFile;
    private String doorsDatabase;
    private String doorsUsername;
    private transient String doorsPassword;

    public String getDoorsDatabase() {
        return doorsDatabase;
    }

    public void setDoorsDatabase(final String doorsDatabase) {
        this.doorsDatabase = doorsDatabase;
    }

    public String getDoorsUsername() {
        return doorsUsername;
    }

    public void setDoorsUsername(final String doorsUsername) {
        this.doorsUsername = doorsUsername;
    }

    public String getDoorsPassword() {
        return doorsPassword;
    }

    public void setDoorsPassword(final String doorsPassword) {
        this.doorsPassword = doorsPassword;
    }

    public String getCsvDirectory() {
        return csvDirectory;
    }

    public void setCsvDirectory(final String csvDirectory) {
        this.csvDirectory = csvDirectory;
    }

    public String getModuleListFile() {
        return moduleListFile;
    }

    public void setModuleListFile(final String moduleListFile) {
        this.moduleListFile = moduleListFile;
    }

    public boolean isReuseCache() {
        return reuseCache;
    }

    public void setReuseCache(final boolean reuseCache) {
        this.reuseCache = reuseCache;
    }

    public boolean isRunNewDoorsAnalysis() {
        return runNewDoorsAnalysis;
    }

    public void setRunNewDoorsAnalysis(final boolean runNewDoorsAnalysis) {
        this.runNewDoorsAnalysis = runNewDoorsAnalysis;
    }

    public boolean isUpdateProgressReportExcelSheet() {
        return updateProgressReportExcelSheet;
    }

    public void setUpdateProgressReportExcelSheet(final boolean updateProgressReportExcelSheet) {
        this.updateProgressReportExcelSheet = updateProgressReportExcelSheet;
    }

    public String getProgressReportSourceFile() {
        return progressReportSourceFile;
    }

    public void setProgressReportSourceFile(final String progressReportSourceFile) {
        this.progressReportSourceFile = progressReportSourceFile;
    }

    public String getProgressReportTargetFile() {
        return progressReportTargetFile;
    }

    public void setProgressReportTargetFile(final String progressReportTargetFile) {
        this.progressReportTargetFile = progressReportTargetFile;
    }

    public boolean isGenerateIssueLists() {
        return generateIssueLists;
    }

    public void setGenerateIssueLists(final boolean generateIssueLists) {
        this.generateIssueLists = generateIssueLists;
    }

    public String getIssueListsDirectory() {
        return issueListsDirectory;
    }

    public void setIssueListsDirectory(final String issueListsDirectory) {
        this.issueListsDirectory = issueListsDirectory;
    }

}
