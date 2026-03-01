public enum ClaudeModel {
    HAIKU("haiku"),
    SONNET("sonnet"),
    OPUS("opus");

    private final String label;
    private String releaseTag; // The "stamped" version

    ClaudeModel(String label) {
        this.label = label;
    }

    public ClaudeModel atVersion(String tag) {
        this.releaseTag = tag;
        return this;
    }

    public String getFullIdentifier() {
        return releaseTag != null ? releaseTag : label;
    }
}
