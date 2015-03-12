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
import model.Store;
import java.util.Collection;
import org.sdmlib.models.modelsets.ObjectSet;
import java.util.Collections;
import model.util.ItemSet;
import model.Item;
import model.util.PersonSet;
import model.Person;

public class StoreSet extends SDMSet<Store>
{

   public static final StoreSet EMPTY_SET = new StoreSet().withReadOnly(true);


   public StorePO hasStorePO()
   {
      return new StorePO(this.toArray(new Store[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "model.Store";
   }


   @SuppressWarnings("unchecked")
   public StoreSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Store>)value);
      }
      else if (value != null)
      {
         this.add((Store) value);
      }
      
      return this;
   }
   
   public StoreSet without(Store value)
   {
      this.remove(value);
      return this;
   }

   public ItemSet getHas()
   {
      ItemSet result = new ItemSet();
      
      for (Store obj : this)
      {
         result.addAll(obj.getHas());
      }
      
      return result;
   }

   public StoreSet hasHas(Object value)
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
      
      StoreSet answer = new StoreSet();
      
      for (Store obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getHas()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public StoreSet withHas(Item value)
   {
      for (Store obj : this)
      {
         obj.withHas(value);
      }
      
      return this;
   }

   public StoreSet withoutHas(Item value)
   {
      for (Store obj : this)
      {
         obj.withoutHas(value);
      }
      
      return this;
   }

   public PersonSet getCustomer()
   {
      PersonSet result = new PersonSet();
      
      for (Store obj : this)
      {
         result.addAll(obj.getCustomer());
      }
      
      return result;
   }

   public StoreSet hasCustomer(Object value)
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
      
      StoreSet answer = new StoreSet();
      
      for (Store obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getCustomer()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public StoreSet withCustomer(Person value)
   {
      for (Store obj : this)
      {
         obj.withCustomer(value);
      }
      
      return this;
   }

   public StoreSet withoutCustomer(Person value)
   {
      for (Store obj : this)
      {
         obj.withoutCustomer(value);
      }
      
      return this;
   }

}
