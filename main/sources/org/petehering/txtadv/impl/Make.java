package org.petehering.txtadv.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.petehering.txtadv.Command;
import org.petehering.txtadv.Item;
import org.petehering.txtadv.Model;
import org.petehering.txtadv.Player;
import org.petehering.txtadv.Recipe;
import org.petehering.txtadv.Recipe.Ingredients;
import org.petehering.txtadv.Recipe.Product;

public class Make implements Command
{
    @Override
    public List<String> execute(Model model, String[] args)
    {
        List<String> response = new ArrayList<>();

        if(args.length >= 2)
        {
            Player p = model.getPlayer();
            Map<String, Recipe> recipes = model.getRecipes();

            for(int i = 1; i < args.length; i++)
            {
                if(recipes.containsKey(args[i]))
                {
                    make(args[i], recipes.get(args[i]), p, response);
                }
                else
                {
                    response.add("there is no recipe for " + args[i]);
                }
            }
        }
        else
        {
            response.add("make what?");
        }

        return response;
    }

    private void make(String recipeName, Recipe r, Player p, List<String> response)
    {
        Map<String, Integer> count = new HashMap<>();
        Set<Item> inventory = p.getInventory();

        for(Item item : inventory)
        {
            String name = item.getName();

            if(count.containsKey(name))
            {
                count.put(name, count.get(name) + 1);
            }
            else
            {
                count.put(name, 1);
            }
        }

        Ingredients ingredients = r.getIngredients();
        boolean foundAllIngredients = true;
        List<String> ingredientsNameList = new ArrayList<>();

        for(String key : ingredients.keySet())
        {
            int value = ingredients.get(key);

            if(count.containsKey(key))
            {
                if(count.get(key) < value)
                {
                    foundAllIngredients = false;
                }
                else
                {
                    for(int i = 0; i < value; i++)
                    {
                        ingredientsNameList.add(key);
                    }
                }
            }
            else
            {
                foundAllIngredients = false;
            }
        }

        if(foundAllIngredients)
        {
            List<Item> remove = new ArrayList<>();

            for(String name : ingredientsNameList)
            {
                for(Item item : inventory)
                {
                    if(item.getName().equals(name))
                    {
                        if(!remove.contains(item))
                        {
                            remove.add(item);
                        }
                    }
                }
            }

            inventory.removeAll(remove);
            Item product = r.getProduct().createItem(recipeName);
            inventory.add(product);
            response.add("you made a " + recipeName);
        }
        else
        {
            response.add("you do not have all the items need to make " + recipeName);
        }
    }
}
