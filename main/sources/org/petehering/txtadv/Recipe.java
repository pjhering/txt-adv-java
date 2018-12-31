package org.petehering.txtadv;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.petehering.txtadv.Item;

public class Recipe
{
    private Ingredients ingredients;
    private Product product;

    public Recipe()
    {
    }

    public void setIngredients(Ingredients value)
    {
        this.ingredients = value;
    }

    public Ingredients getIngredients()
    {
        return ingredients;
    }

    public void setProduct(Product value)
    {
        this.product = value;
    }

    public Product getProduct()
    {
        return product;
    }

    public static class Ingredients extends HashMap<String, Integer>
    {
        public Ingredients()
        {
        }
    }

    public static class Product
    {
        private String description;
        private Map<String, String> properties;

        public Product()
        {
        }

        public void setDescription(String value)
        {
            this.description = value;
        }

        public String getDescription()
        {
            return description;
        }

        public void setProperties(Map<String, String> value)
        {
            this.properties = value;
        }

        public Map<String, String> getProperties()
        {
            return properties;
        }

        public Item createItem(String name)
        {
            return new Item(name, description, properties);
        }
    }
}
