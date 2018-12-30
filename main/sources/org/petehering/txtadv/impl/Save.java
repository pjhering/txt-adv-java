package org.petehering.txtadv.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import org.petehering.txtadv.Command;
import org.petehering.txtadv.Model;

public class Save implements Command
{
    @Override
    public List<String> execute (Model model, String[] args)
    {
        List<String> response = new ArrayList<>();

        if(args.length < 2)
        {
            response.add("you need to specify the file");
        }
        else
        {
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

                response.add("saved game in " + args[1]);
            }
            catch(Exception ex)
            {
                response.add("ERROR: " + ex.toString());
            }
        }

        return response;
    }
}
