package org.petehering.txtadv.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.petehering.txtadv.Command;
import org.petehering.txtadv.Model;

public class QuestInfo implements Command
{
    @Override
    public List<String> execute (Model model, String[] args)
    {
        List<String> response = new ArrayList<>();
        String s = model.getCurrentQuest();

        if(s != null && !s.equals(""))
        {
            Map<String, String> info = model.getQuests().get(s);

            if(info != null)
            {
                String name = info.get("name");
                String desc = info.get("description");

                name = (name == null || name.equals(""))? "unnamed" : name;
                desc = (desc == null || desc.equals(""))? "no description available" : desc;

                response.add(name + "... " + desc);
            }
        }
        else
        {
            response.add("no information is available");
        }

        return response;
    }
}
