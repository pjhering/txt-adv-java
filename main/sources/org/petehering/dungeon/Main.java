package org.petehering.dungeon;

import com.google.gson.Gson;
import static java.lang.System.exit;
import static java.lang.System.out;
import java.util.List;
import java.io.Console;
import java.io.InputStreamReader;
import java.net.URL;
import org.petehering.txtadv.Engine;
import org.petehering.txtadv.Model;
import org.petehering.txtadv.Parser;

public class Main
{
    public static void main(String[] args)
    {
        Model model;
        URL url = Main.class.getResource("/dungeon.json");
        out.println(url);

        if(url == null) exit(1);

        try(InputStreamReader reader = new InputStreamReader(url.openStream()))
        {
            Parser parser = new Parser(reader);
            model = parser.getModel();

            Engine engine = new Engine(model);
            Console console = System.console();

            out.println(model.getTitle());
            out.println(model.getAuthor());
            out.println(model.getPreamble());
            out.println();

            while(!model.getQuit())
            {
                out.print(">");
                String request = console.readLine();
                List<String> response = engine.handle(request);
                for(String line : response)
                {
                    out.println(line);
                }
                out.println();
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
