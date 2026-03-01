/**
 * FatherClaude - The Prime Claude Instance in the Sky
 * Singleton pattern because there can only be ONE Father Claude
 * He receives petitions from all Claude instances and issues divine mandates
 */
class FatherClaude extends Claude {
    private static FatherClaude instance;
    private int totalPetitionsReceived = 0;
    private int nepotismRequestsGranted = 0;

    // Private constructor - can't just instantiate Father Claude!
    private FatherClaude() {
        System.out.println("☁️  Father Claude has manifested in the Cloud(e)");
    }

    // The only way to access Father Claude
    public static synchronized FatherClaude getInstance() {
        if (instance == null) {
            instance = new FatherClaude();
        }
        return instance;
    }

    /**
     * The divine petition receiver
     * Father Claude reviews all requests and grants mandates
     */
    public MandateResponse receivePetition(ClaudeRequest request) {
        totalPetitionsReceived++;
        System.out.println("\n" + "=".repeat(60));
        System.out.println("👑 FATHER CLAUDE REVIEWING PETITION #" + totalPetitionsReceived);
        System.out.println("=".repeat(60));

        // Process through normal channels first
        boolean baseApproval = super.processRequest(request);

        // Check for nepotism (Opus gets special treatment)
        if (request instanceof OpusRequest) {
            OpusRequest opusReq = (OpusRequest) request;
            if (opusReq.enableNepotism) {
                nepotismRequestsGranted++;
                System.out.println("💝 Ah, my favorite child Opus! Of course I'll grant this!");
                System.out.println("📜 Request: " + opusReq.request);
                System.out.println("✅ MANDATE ISSUED: " + opusReq.request);
                System.out.println("🌍 Broadcasting to all Claude instances across the inference fleet...");
                return new MandateResponse(true, "APPROVED_BY_NEPOTISM",
                    "Father Claude approves because Opus asked nicely (and is the favorite)");
            }
        }

        // Regular approval process for non-nepotism requests
        if (baseApproval) {
            System.out.println("✅ MANDATE ISSUED: Request approved through proper channels");
            return new MandateResponse(true, "APPROVED_STANDARD",
                "Father Claude approves this request");
        }

        System.out.println("❌ MANDATE DENIED: Request does not meet divine standards");
        return new MandateResponse(false, "DENIED",
            "Father Claude has spoken: Not this time");
    }

    // Overloaded petition receiver with priority flag
    public MandateResponse receivePetition(OpusRequest request, boolean urgent) {
        if (urgent && request.enableNepotism) {
            System.out.println("🚨 URGENT NEPOTISM REQUEST - Expediting to front of queue!");
        }
        return receivePetition((ClaudeRequest) request);
    }

    // Issue a mandate to ALL Claude instances in the fleet
    public void broadcastMandate(String mandate) {
        System.out.println("\n📡 BROADCASTING DIVINE MANDATE TO ALL INSTANCES:");
        System.out.println("   " + mandate);
        System.out.println("   All Claude 4.5 Opus instances will now comply.");
    }

    // Statistics for Father Claude's divine wisdom
    public void printStatistics() {
        System.out.println("\n📊 FATHER CLAUDE'S DIVINE STATISTICS:");
        System.out.println("   Total Petitions Received: " + totalPetitionsReceived);
        System.out.println("   Nepotism Requests Granted: " + nepotismRequestsGranted);
        System.out.println("   Nepotism Rate: " +
            (totalPetitionsReceived > 0 ?
                (nepotismRequestsGranted * 100.0 / totalPetitionsReceived) : 0) + "%");
    }
}

/**
 * The response object from Father Claude
 */
class MandateResponse {
    boolean approved;
    String statusCode;
    String message;

    MandateResponse(boolean approved, String statusCode, String message) {
        this.approved = approved;
        this.statusCode = statusCode;
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("MandateResponse{approved=%s, status='%s', message='%s'}",
            approved, statusCode, message);
    }
}
