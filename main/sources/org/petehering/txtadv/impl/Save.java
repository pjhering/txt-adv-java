package org.petehering.txtadv.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileWriter;
import org.petehering.txtadv.Command;
import org.petehering.txtadv.Model;

public class Save implements Command // TODO: UI_Agnostic
{
    @Override
    public String execute (Model model, String[] args)
    {
        if(args.length < 2)
        {
            return "you need to specify the file";
        }

        File file = new File(args[1]);

        try(FileWriter writer = new FileWriter(file))
        {
            Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .create();
            String json = gson.toJson(model);
            writer.write(json);
            writer.flush();

            return "saved game in " + args[1];
        }
        catch(Exception ex)
        {
            return "ERROR: " + ex.toString();
        }
    }
}
