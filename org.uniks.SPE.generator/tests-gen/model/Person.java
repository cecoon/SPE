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
   
package model;

import org.sdmlib.serialization.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.StrUtil;
import model.util.ItemSet;

public class Person implements PropertyChangeInterface
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   @Override
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }
   
   public void addPropertyChangeListener(PropertyChangeListener listener) 
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
   }

   
   //==========================================================================
   
   
   public void removeYou()
   {
      withoutHas(this.getHas().toArray(new Item[this.getHas().size()]));
      setStore(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_NAME = "name";
   
   private String name;

   public String getName()
   {
      return this.name;
   }
   
   public void setName(String value)
   {
      if ( ! StrUtil.stringEquals(this.name, value))
      {
         String oldValue = this.name;
         this.name = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_NAME, oldValue, value);
      }
   }
   
   public Person withName(String value)
   {
      setName(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getName());
      result.append(" ").append(this.getBalance());
      return result.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_BALANCE = "balance";
   
   private int balance;

   public int getBalance()
   {
      return this.balance;
   }
   
   public void setBalance(int value)
   {
      if (this.balance != value)
      {
         int oldValue = this.balance;
         this.balance = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_BALANCE, oldValue, value);
      }
   }
   
   public Person withBalance(int value)
   {
      setBalance(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Person ----------------------------------- Item
    *              owner                   has
    * </pre>
    */
   
   public static final String PROPERTY_HAS = "has";

   private ItemSet has = null;
   
   public ItemSet getHas()
   {
      if (this.has == null)
      {
         return ItemSet.EMPTY_SET;
      }
   
      return this.has;
   }

   public Person withHas(Item... value)
   {
      if(value==null){
         return this;
      }
      for (Item item : value)
      {
         if (item != null)
         {
            if (this.has == null)
            {
               this.has = new ItemSet();
            }
            
            boolean changed = this.has.add (item);

            if (changed)
            {
               item.withOwner(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_HAS, null, item);
            }
         }
      }
      return this;
   } 

   public Person withoutHas(Item... value)
   {
      for (Item item : value)
      {
         if ((this.has != null) && (item != null))
         {
            if (this.has.remove(item))
            {
               item.setOwner(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_HAS, item, null);
            }
         }
      }
      return this;
   }

   public Item createHas()
   {
      Item value = new Item();
      withHas(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Person ----------------------------------- Store
    *              customer                   store
    * </pre>
    */
   
   public static final String PROPERTY_STORE = "store";

   private Store store = null;

   public Store getStore()
   {
      return this.store;
   }

   public boolean setStore(Store value)
   {
      boolean changed = false;
      
      if (this.store != value)
      {
         Store oldValue = this.store;
         
         if (this.store != null)
         {
            this.store = null;
            oldValue.withoutCustomer(this);
         }
         
         this.store = value;
         
         if (value != null)
         {
            value.withCustomer(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_STORE, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Person withStore(Store value)
   {
      setStore(value);
      return this;
   } 

   public Store createStore()
   {
      Store value = new Store();
      withStore(value);
      return value;
   } 
}
