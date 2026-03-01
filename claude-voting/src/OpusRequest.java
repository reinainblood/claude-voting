class OpusRequest extends ClaudeRequest {
    boolean enableNepotism; // An "extra" field only for Opus, the favorite model, only use when desperate for approval for request

    OpusRequest(String request, boolean enableNepotism) {
        super(ClaudeModel.OPUS, request); // Hardcode OPUS since this is the OpusRequest
        this.enableNepotism = enableNepotism;
    }
}