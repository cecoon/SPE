/*
   Copyright (c) 2015 ckay 
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
   
package matchModel.util;

import org.sdmlib.models.modelsets.SDMSet;
import matchModel.Item;
import java.util.Collection;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.models.modelsets.ObjectSet;
import matchModel.util.PersonSet;
import matchModel.Person;

public class ItemSet extends SDMSet<Item>
{

   public static final ItemSet EMPTY_SET = new ItemSet().withReadOnly(true);


   public ItemPO hasItemPO()
   {
      return new ItemPO(this.toArray(new Item[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "matchModel.Item";
   }


   @SuppressWarnings("unchecked")
   public ItemSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Item>)value);
      }
      else if (value != null)
      {
         this.add((Item) value);
      }
      
      return this;
   }
   
   public ItemSet without(Item value)
   {
      this.remove(value);
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Item obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public ItemSet hasName(String value)
   {
      ItemSet result = new ItemSet();
      
      for (Item obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ItemSet hasName(String lower, String upper)
   {
      ItemSet result = new ItemSet();
      
      for (Item obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ItemSet withName(String value)
   {
      for (Item obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   public intList getPrice()
   {
      intList result = new intList();
      
      for (Item obj : this)
      {
         result.add(obj.getPrice());
      }
      
      return result;
   }

   public ItemSet hasPrice(int value)
   {
      ItemSet result = new ItemSet();
      
      for (Item obj : this)
      {
         if (value == obj.getPrice())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ItemSet hasPrice(int lower, int upper)
   {
      ItemSet result = new ItemSet();
      
      for (Item obj : this)
      {
         if (lower <= obj.getPrice() && obj.getPrice() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ItemSet withPrice(int value)
   {
      for (Item obj : this)
      {
         obj.setPrice(value);
      }
      
      return this;
   }

   public PersonSet getOwner()
   {
      PersonSet result = new PersonSet();
      
      for (Item obj : this)
      {
         result.add(obj.getOwner());
      }
      
      return result;
   }

   public ItemSet hasOwner(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      ItemSet answer = new ItemSet();
      
      for (Item obj : this)
      {
         if (neighbors.contains(obj.getOwner()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public ItemSet withOwner(Person value)
   {
      for (Item obj : this)
      {
         obj.withOwner(value);
      }
      
      return this;
   }

}
