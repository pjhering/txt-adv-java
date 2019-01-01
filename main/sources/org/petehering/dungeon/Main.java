
import com.google.gson.Gson;
import static java.lang.System.exit;
import static java.lang.System.out;
import java.util.List;
import java.io.Console;
import java.io.File;
import java.io.FileReader;
import org.petehering.txtadv.Engine;
import org.petehering.txtadv.Model;
import org.petehering.txtadv.Parser;

public class Main
{
    public static void main(String[] args)
    {
        if(args.length != 1)
        {
            out.println("USAGE:\n\tjava -jar TxtAdv.jar path/to/model.json");
            exit(0);
        }
        else
        {
            Model model;
            File file = new File(args[0]);

            try(FileReader reader = new FileReader(file))
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
}
