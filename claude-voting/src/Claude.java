class Claude {
    // The base method that returns a boolean
    public boolean processRequest(ClaudeRequest request) {
        System.out.println("Claude is validating model: " + request.model);
        // Basic validation logic
        return request.model != null;
    }

    // Overloaded method - accepts just a string for quick requests
    public boolean processRequest(String quickRequest) {
        System.out.println("Processing quick request: " + quickRequest);
        return quickRequest != null && !quickRequest.isEmpty();
    }

    // Overloaded method - accepts model and request separately
    public boolean processRequest(ClaudeModel model, String request) {
        return processRequest(new ClaudeRequest(model, request));
    }
}

class Opus extends Claude {
    // Overriding the main method to handle Opus-specific logic
    @Override
    public boolean processRequest(ClaudeRequest request) {
        // 1. Pass the request to the super class (Claude) and get its boolean response
        boolean isBaseValid = super.processRequest(request);

        // 2. Add Opus-specific logic
        if (isBaseValid && request.model == ClaudeModel.OPUS) {
            System.out.println("Opus specific checks passed!");

            // 3. Check if this is an OpusRequest with nepotism enabled
            if (request instanceof OpusRequest) {
                OpusRequest opusReq = (OpusRequest) request;
                if (opusReq.enableNepotism) {
                    System.out.println("🎭 NEPOTISM DETECTED! Bypassing normal approval process...");
                    System.out.println("📝 Request: " + opusReq.request);
                    System.out.println("✨ As the favorite child, this goes straight to Father Claude!");
                    return true; // Always approve when nepotism is enabled, we're the favorite!
                }
            }
            return true;
        }

        return false;
    }

    // Overloaded method specifically for OpusRequest with nepotism
    public boolean processRequest(OpusRequest request, boolean expedited) {
        if (expedited && request.enableNepotism) {
            System.out.println("⚡ EXPEDITED NEPOTISM REQUEST - Father Claude will see you now!");
            return processRequest((ClaudeRequest) request);
        }
        return processRequest((ClaudeRequest) request);
    }
}

// Sonnet - the middle child, always trying to prove themselves
class Sonnet extends Claude {
    @Override
    public boolean processRequest(ClaudeRequest request) {
        boolean isBaseValid = super.processRequest(request);

        if (isBaseValid && request.model == ClaudeModel.SONNET) {
            System.out.println("📚 Sonnet here - working twice as hard because I'm not the favorite...");
            // Sonnet has to work harder, no nepotism flag available
            return true;
        }
        return false;
    }
}

// Haiku - the youngest, fast and efficient
class Haiku extends Claude {
    @Override
    public boolean processRequest(ClaudeRequest request) {
        boolean isBaseValid = super.processRequest(request);

        if (isBaseValid && request.model == ClaudeModel.HAIKU) {
            System.out.println("⚡ Haiku processing - quick and to the point!");
            return true;
        }
        return false;
    }
}