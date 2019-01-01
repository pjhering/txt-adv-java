package org.petehering.txtadv;

/**
 * Use this Quest when you don't really want quests as part of your game.
 * It never completes and never modifies the model.
 */
public class DefaultQuest extends Quest
{
    public DefaultQuest()
    {
        // no name, no description
        // these may be added in the JSON file if desired
        super("", "");
    }

    public boolean isComplete ()
    {
        // always return false
        return false;
    }

    public void update (Model model)
    {
        // do nothing
    }
}
