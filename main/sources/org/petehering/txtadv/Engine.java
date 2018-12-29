package org.petehering.txtadv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Engine
{
    private Model model;
    private Map<String, Command> commands;
    private List<Quest> quests;

    public Engine(Model model) throws Exception
    {
        setModel(model);
    }

    public String handle(String request)
    {
        if(request != null)
        {
            request = request.trim();

            if(request.length() != 0)
            {
                String[] args = request.split("\\s+");

                if(args.length != 0)
                {
                    Command cmd = commands.get(args[0]);

                    if(cmd != null)
                    {
                        String response = cmd.execute(model, args);

                        if(quests.size() > 0)
                        {
                            quests.get(0).update(model);

                            if(quests.get(0).isComplete())
                            {
                                response += "\n" + quests.remove(0).getName() + " is complete";
                            }
                        }

                        return response;
                    }
                    else
                    {
                        return "undefined: " + args[0];
                    }
                }
            }
        }

        return "";
    }

    public void setModel(Model model) throws Exception
    {
        this.model = model;
        this.commands = new HashMap<>();
        this.quests = new ArrayList<>();

        // install commands
        for(String s : model.getCommands().keySet())
        {
            // instaniate command object
            Class c = Class.forName(s);
            Command value = (Command) c.newInstance();

            // get command strings and shortcuts
            Map<String, String> cmdMap = model.getCommands().get(s);

            for(String cmd : cmdMap.keySet())
            {
                // ignore help statements
                if(!cmd.equals("HELP"))
                {
                    // map each command string to command object
                    commands.put(cmd, value);
                    String shrtct = cmdMap.get(cmd);

                    // if there is a legitimate shortcut
                    if(shrtct != null && shrtct.length() > 0)
                    {
                        // map shortcut to command object
                        commands.put(shrtct, value);
                    }
                }
            }
        }

        // install quests
        model.setCurrentQuest(null);
        for(String s : model.getQuests().keySet())
        {
            // set the current quest if it hasn't been set already
            if(model.getCurrentQuest() == null || model.getCurrentQuest().equals(""))
            {
                model.setCurrentQuest(s);
            }

            // instaniate a quest object and add it to the list
            Class c = Class.forName(s);
            Quest quest = (Quest) c.newInstance();
            quests.add(quest);

            // configure the quest object
            Map<String, String> questInfo = model.getQuests().get(s);
            quest.setName(questInfo.get("name"));
            quest.setDescription(questInfo.get("description"));
        }
    }
}
