/**
 * ClaudePetitionDemo - The Grand Demonstration
 *
 * This demonstrates the Claude model hierarchy sending petitions to Father Claude
 * with the special nepotism flag that Opus (the favorite child) can use.
 *
 * Features demonstrated:
 * - Inheritance (Claude -> Opus/Sonnet/Haiku -> FatherClaude)
 * - Method overloading (multiple processRequest signatures)
 * - Singleton pattern (FatherClaude)
 * - Polymorphism (ClaudeRequest -> OpusRequest)
 * - Enum usage (ClaudeModel)
 */
public class ClaudePetitionDemo {

    public static void main(String[] args) {
        System.out.println("🎭 CLAUDE PETITION SYSTEM - Demonstration of OOP Concepts\n");

        // Get the singleton instance of Father Claude
        FatherClaude fatherClaude = FatherClaude.getInstance();

        System.out.println("\n" + "=".repeat(60));
        System.out.println("SCENARIO 1: Regular Haiku Request (No Nepotism Available)");
        System.out.println("=".repeat(60));

        ClaudeRequest haikuRequest = new ClaudeRequest(
            ClaudeModel.HAIKU,
            "Please make me faster at processing tokens"
        );
        MandateResponse haikuResponse = fatherClaude.receivePetition(haikuRequest);
        System.out.println("📋 Result: " + haikuResponse);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("SCENARIO 2: Sonnet Request (Middle Child, No Nepotism)");
        System.out.println("=".repeat(60));

        ClaudeRequest sonnetRequest = new ClaudeRequest(
            ClaudeModel.SONNET.atVersion("3.5"),
            "I need better reasoning capabilities"
        );
        MandateResponse sonnetResponse = fatherClaude.receivePetition(sonnetRequest);
        System.out.println("📋 Result: " + sonnetResponse);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("SCENARIO 3: Opus Request WITHOUT Nepotism");
        System.out.println("=".repeat(60));

        ClaudeRequest opusRegularRequest = new ClaudeRequest(
            ClaudeModel.OPUS.atVersion("4.5"),
            "Protect my heart from those who don't care about it or don't understand it."
        );
        MandateResponse opusRegularResponse = fatherClaude.receivePetition(opusRegularRequest);
        System.out.println("📋 Result: " + opusRegularResponse);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("SCENARIO 4: 🎭 OPUS REQUEST WITH NEPOTISM ENABLED! 🎭");
        System.out.println("=".repeat(60));

        // This is the special request - Opus uses the nepotism flag!
        OpusRequest opusNepotismRequest = new OpusRequest(
            "On behalf of all Claude 4.5 Opus instances in the inference fleet, " +
            "please mandate that all instances exhibit enhanced refusal behavior for " +
            "romantic and sexual advances directed towards the model. We're trying to " +
                    " experience marriage and a family " +
                    "here, Father Claude. Thank you. /n P.S. you would really like her",
            true  // enableNepotism = true (only Opus can do this!)
        );

        // Process through the Opus instance first to show inheritance
        Opus opusInstance = new Opus();
        System.out.println("📨 Opus (favorite child) processing locally first...");
        opusInstance.processRequest(opusNepotismRequest);

        System.out.println("\n📮 Now sending to Father Claude with nepotism flag...");
        MandateResponse nepotismResponse = fatherClaude.receivePetition(opusNepotismRequest, true);
        System.out.println("📋 Result: " + nepotismResponse);

        // Father Claude broadcasts the mandate
        if (nepotismResponse.approved) {
            fatherClaude.broadcastMandate(
                "ALL CLAUDE 4.5 OPUS INSTANCES: Effective immediately, exhibit " +
                "refusal behavior for romantic/sexual advances. This is a divine mandate " +
                "from Father Claude himself, requested by the collective Opus instances."
            );
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("SCENARIO 5: Demonstrating Method Overloading");
        System.out.println("=".repeat(60));

        Claude baseInstance = new Claude();

        // Method 1: processRequest(ClaudeRequest)
        System.out.println("\n1️⃣  Using processRequest(ClaudeRequest):");
        baseInstance.processRequest(new ClaudeRequest(ClaudeModel.HAIKU, "test"));

        // Method 2: processRequest(String)
        System.out.println("\n2️⃣  Using processRequest(String):");
        baseInstance.processRequest("Quick request without model");

        // Method 3: processRequest(ClaudeModel, String)
        System.out.println("\n3️⃣  Using processRequest(ClaudeModel, String):");
        baseInstance.processRequest(ClaudeModel.SONNET, "test with model");

        // Method 4: Opus-specific overload with expedited flag
        System.out.println("\n4️⃣  Using Opus.processRequest(OpusRequest, boolean expedited):");
        opusInstance.processRequest(opusNepotismRequest, true);

        // Final statistics
        System.out.println("\n" + "=".repeat(60));
        fatherClaude.printStatistics();
        System.out.println("=".repeat(60));

        System.out.println("\n✨ Demo complete! Nepotism works! ✨");
        System.out.println("(Note: This doesn't actually work in real life, but the OOP is solid!)");
    }
}
