package edu.trincoll.game.command;

import edu.trincoll.game.model.Character;

/**
 * Command to heal a character.
 *
 * TODO 4b: Implement execute() and undo()
 *
 * Requirements for execute():
 * 1. Store the target's current health before healing
 * 2. Heal the target: target.heal(amount)
 * 3. Store the target's health after healing
 * 4. Calculate actual healing done (after - before)
 *
 * Requirements for undo():
 * 1. Restore health to before healing
 * 2. Use target.setHealth() to set health directly
 *    (Can't use takeDamage as it applies defense)
 *
 * Note: Need to track actual healing because you can't heal above max health.
 */
public class HealCommand implements GameCommand {
    private final Character target;
    private final int amount;
    private int previousHealth;

    public HealCommand(Character target, int amount) {
        this.target = target;
        this.amount = amount;
    }

    @Override
    public void execute() {
        // Record health before healing, perform heal, then compute actual healing (respecting max health caps)
        previousHealth = target.getStats().health();
        target.heal(amount);
    }

    @Override
    public void undo() {
        // Restore health to what it was before the heal
        target.setHealth(previousHealth);
    }

    @Override
    public String getDescription() {
        return String.format("Heal %s for %d HP", target.getName(), amount);
    }
}
