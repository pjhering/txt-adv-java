package org.petehering.txtadv;

import com.google.gson.Gson;
import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;

public class Parser
{
    private Reader reader;

    public Parser(Reader reader)
    {
        this.reader = reader;
    }

    public Model getModel () throws Exception
    {
        Gson gson = new Gson();
        Model model = gson.fromJson(reader, Model.class);
        return model;
    }
}
