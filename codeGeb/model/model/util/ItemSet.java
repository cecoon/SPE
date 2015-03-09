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
   
package model.util;

import org.sdmlib.models.modelsets.SDMSet;
import model.Item;
import java.util.Collection;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.models.modelsets.ObjectSet;
import model.util.PersonSet;
import model.Person;
import model.util.StoreSet;
import model.Store;
import model.util.ItemSet;

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
      return "model.Item";
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

   public StoreSet getInStore()
   {
      StoreSet result = new StoreSet();
      
      for (Item obj : this)
      {
         result.add(obj.getInStore());
      }
      
      return result;
   }

   public ItemSet hasInStore(Object value)
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
         if (neighbors.contains(obj.getInStore()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public ItemSet withInStore(Store value)
   {
      for (Item obj : this)
      {
         obj.withInStore(value);
      }
      
      return this;
   }

   public intList getValue()
   {
      intList result = new intList();
      
      for (Item obj : this)
      {
         result.add(obj.getValue());
      }
      
      return result;
   }

   public ItemSet hasValue(int value)
   {
      ItemSet result = new ItemSet();
      
      for (Item obj : this)
      {
         if (value == obj.getValue())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ItemSet hasValue(int lower, int upper)
   {
      ItemSet result = new ItemSet();
      
      for (Item obj : this)
      {
         if (lower <= obj.getValue() && obj.getValue() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ItemSet withValue(int value)
   {
      for (Item obj : this)
      {
         obj.setValue(value);
      }
      
      return this;
   }

   public ItemSet getNext()
   {
      ItemSet result = new ItemSet();
      
      for (Item obj : this)
      {
         result.add(obj.getNext());
      }
      
      return result;
   }

   public ItemSet hasNext(Object value)
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
         if (neighbors.contains(obj.getNext()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public ItemSet getNextTransitive()
   {
      ItemSet todo = new ItemSet().with(this);
      
      ItemSet result = new ItemSet();
      
      while ( ! todo.isEmpty())
      {
         Item current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            if ( ! result.contains(current.getNext()))
            {
               todo.with(current.getNext());
            }
         }
      }
      
      return result;
   }

   public ItemSet withNext(Item value)
   {
      for (Item obj : this)
      {
         obj.withNext(value);
      }
      
      return this;
   }

   public ItemSet getPrev()
   {
      ItemSet result = new ItemSet();
      
      for (Item obj : this)
      {
         result.add(obj.getPrev());
      }
      
      return result;
   }

   public ItemSet hasPrev(Object value)
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
         if (neighbors.contains(obj.getPrev()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public ItemSet getPrevTransitive()
   {
      ItemSet todo = new ItemSet().with(this);
      
      ItemSet result = new ItemSet();
      
      while ( ! todo.isEmpty())
      {
         Item current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            if ( ! result.contains(current.getPrev()))
            {
               todo.with(current.getPrev());
            }
         }
      }
      
      return result;
   }

   public ItemSet withPrev(Item value)
   {
      for (Item obj : this)
      {
         obj.withPrev(value);
      }
      
      return this;
   }

}
