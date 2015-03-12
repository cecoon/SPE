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
import model.Person;
import java.util.Collection;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.models.modelsets.ObjectSet;
import java.util.Collections;
import model.util.ItemSet;
import model.Item;
import model.util.StoreSet;
import model.Store;

public class PersonSet extends SDMSet<Person>
{

   public static final PersonSet EMPTY_SET = new PersonSet().withReadOnly(true);


   public PersonPO hasPersonPO()
   {
      return new PersonPO(this.toArray(new Person[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "model.Person";
   }


   @SuppressWarnings("unchecked")
   public PersonSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Person>)value);
      }
      else if (value != null)
      {
         this.add((Person) value);
      }
      
      return this;
   }
   
   public PersonSet without(Person value)
   {
      this.remove(value);
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Person obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public PersonSet hasName(String value)
   {
      PersonSet result = new PersonSet();
      
      for (Person obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PersonSet hasName(String lower, String upper)
   {
      PersonSet result = new PersonSet();
      
      for (Person obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PersonSet withName(String value)
   {
      for (Person obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   public intList getBalance()
   {
      intList result = new intList();
      
      for (Person obj : this)
      {
         result.add(obj.getBalance());
      }
      
      return result;
   }

   public PersonSet hasBalance(int value)
   {
      PersonSet result = new PersonSet();
      
      for (Person obj : this)
      {
         if (value == obj.getBalance())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PersonSet hasBalance(int lower, int upper)
   {
      PersonSet result = new PersonSet();
      
      for (Person obj : this)
      {
         if (lower <= obj.getBalance() && obj.getBalance() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PersonSet withBalance(int value)
   {
      for (Person obj : this)
      {
         obj.setBalance(value);
      }
      
      return this;
   }

   public ItemSet getHas()
   {
      ItemSet result = new ItemSet();
      
      for (Person obj : this)
      {
         result.addAll(obj.getHas());
      }
      
      return result;
   }

   public PersonSet hasHas(Object value)
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
      
      PersonSet answer = new PersonSet();
      
      for (Person obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getHas()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public PersonSet withHas(Item value)
   {
      for (Person obj : this)
      {
         obj.withHas(value);
      }
      
      return this;
   }

   public PersonSet withoutHas(Item value)
   {
      for (Person obj : this)
      {
         obj.withoutHas(value);
      }
      
      return this;
   }

   public StoreSet getStore()
   {
      StoreSet result = new StoreSet();
      
      for (Person obj : this)
      {
         result.add(obj.getStore());
      }
      
      return result;
   }

   public PersonSet hasStore(Object value)
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
      
      PersonSet answer = new PersonSet();
      
      for (Person obj : this)
      {
         if (neighbors.contains(obj.getStore()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public PersonSet withStore(Store value)
   {
      for (Person obj : this)
      {
         obj.withStore(value);
      }
      
      return this;
   }

}
