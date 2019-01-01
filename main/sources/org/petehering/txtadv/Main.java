package org.petehering.txtadv;

import com.google.gson.Gson;
import static java.lang.System.exit;
import static java.lang.System.out;
import java.util.List;
import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;

public class Main
{
    private final Model model;

    public Main(Model model)
    {
        this.model = model;
    }

    public Main(Reader reader) throws Exception
    {
        Parser parser = new Parser(reader);
        model = parser.getModel();
    }

    public static void main(String[] args) throws Exception
    {
        if(args.length < 1)
        {
            out.println("USAGE: java -jar TxtAdv.jar path/to/model.json");
        }
        else
        {
            File file = new File(args[0]);
            FileReader reader = new FileReader(file);
            Main main = new Main(reader);
            main.run();
        }
    }

    public void run () throws Exception
    {
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
}
