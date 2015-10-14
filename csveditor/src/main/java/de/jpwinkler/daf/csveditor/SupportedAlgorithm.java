package de.jpwinkler.daf.csveditor;

public class SupportedAlgorithm {

    private String name;

    private String configurationViewFile;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getConfigurationViewFile() {
        return configurationViewFile;
    }

    public void setConfigurationViewFile(final String configurationViewFile) {
        this.configurationViewFile = configurationViewFile;
    }

    public SupportedAlgorithm() {
    }

    public SupportedAlgorithm(final String name, final String configurationViewFile) {
        super();
        this.name = name;
        this.configurationViewFile = configurationViewFile;
    }

}
