package org.petehering.dungeon;

import com.google.gson.Gson;
import static java.lang.System.exit;
import static java.lang.System.out;
import java.util.List;
import java.io.Console;
import java.io.InputStreamReader;
import java.net.URL;
import org.petehering.txtadv.Engine;
import org.petehering.txtadv.Main;
import org.petehering.txtadv.Model;
import org.petehering.txtadv.Parser;

public class Start
{
    public static void main(String[] args)
    {
        URL url = Start.class.getResource("/dungeon.json");

        try(InputStreamReader reader = new InputStreamReader(url.openStream()))
        {
            Main main = new Main(reader);
            main.run();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
