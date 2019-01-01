package org.petehering.txtadv.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.petehering.txtadv.Command;
import org.petehering.txtadv.Model;

public class Help implements Command
{
    private Map<String, CmdInfo> cmdInfoMap;

    public Help()
    {
    }

    @Override
    public List<String> execute (Model model, String[] args)
    {
        if(cmdInfoMap == null)
        {
            build(model.getCommands());
        }

        List<String> response = new ArrayList<>();

        if(args.length == 1) // list all available commands
        {
            response.add("available commands:");

            for(String key : cmdInfoMap.keySet())
            {
                if(!key.equals(cmdInfoMap.get(key).getShortcut()))
                {
                    response.add("  " + key + " [" + cmdInfoMap.get(key).getShortcut() + "]");
                }
            }
        }
        else //  list help text for each command
        {
            for(int i = 1; i < args.length; i++)
            {
                CmdInfo info = cmdInfoMap.get(args[i]);

                if(info != null)
                {
                    response.add(info.toString());
                }
                else
                {
                      response.add(" - undefined");
                }
            }
        }

        return response;
    }

    private void build(Map<String, Map<String, String>> helpMap)
    {
        cmdInfoMap = new TreeMap<>();

        for (String cmdClassName : helpMap.keySet())
        {
            Map<String, String> cmdMap = helpMap.get(cmdClassName);
            String helpText = cmdMap.get("HELP");

            if(helpText != null && helpText.length() > 0)
            {
                for(String cmmnd : cmdMap.keySet())
                {
                    if(!cmmnd.equals("HELP"))
                    {
                        String shrtct = cmdMap.get(cmmnd);
                        CmdInfo info = new CmdInfo(cmmnd, shrtct, helpText);

                        if(cmmnd != null && cmmnd.length() > 0)
                        {
                            cmdInfoMap.put(cmmnd, info);
                        }

                        if(shrtct != null && shrtct.length() > 0)
                        {
                            cmdInfoMap.put(shrtct, info);
                        }
                    }
                }
            }
        }
    }
}
