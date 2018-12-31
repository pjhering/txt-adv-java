package org.petehering.txtadv.impl;

import java.util.ArrayList;
import java.util.List;
import org.petehering.txtadv.Command;
import org.petehering.txtadv.Model;
import org.petehering.txtadv.Recipe;
import org.petehering.txtadv.Recipe.Ingredients;

public class RecipeInfo implements Command
{
    @Override
    public List<String> execute(Model model, String[] args)
    {
        List<String> response = new ArrayList<>();

        if(args.length > 1)
        {
            for(int i = 1; i < args.length; i++)
            {
                if(model.getRecipes().containsKey(args[i]))
                {
                    Recipe r = model.getRecipes().get(args[i]);
                    Ingredients ingredients = r.getIngredients();

                    response.add("to make " + args[i] + " you need:");
                    for(String key : ingredients.keySet())
                    {
                        Integer value = ingredients.get(key);
                        response.add("  " + value + " " + key);
                    }
                }
                else
                {
                    response.add("there is no recipe for " + args[i]);
                }
            }
        }
        else
        {
            response.add("recipe for what?");
        }

        return response;
    }
}