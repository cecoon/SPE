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
import model.util.ItemSet;
import model.util.PersonSet;

public class Store implements PropertyChangeInterface
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
      withoutCustomer(this.getCustomer().toArray(new Person[this.getCustomer().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Store ----------------------------------- Item
    *              inStore                   has
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

   public Store withHas(Item... value)
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
               item.withInStore(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_HAS, null, item);
            }
         }
      }
      return this;
   } 

   public Store withoutHas(Item... value)
   {
      for (Item item : value)
      {
         if ((this.has != null) && (item != null))
         {
            if (this.has.remove(item))
            {
               item.setInStore(null);
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
    *              one                       many
    * Store ----------------------------------- Person
    *              store                   customer
    * </pre>
    */
   
   public static final String PROPERTY_CUSTOMER = "customer";

   private PersonSet customer = null;
   
   public PersonSet getCustomer()
   {
      if (this.customer == null)
      {
         return PersonSet.EMPTY_SET;
      }
   
      return this.customer;
   }

   public Store withCustomer(Person... value)
   {
      if(value==null){
         return this;
      }
      for (Person item : value)
      {
         if (item != null)
         {
            if (this.customer == null)
            {
               this.customer = new PersonSet();
            }
            
            boolean changed = this.customer.add (item);

            if (changed)
            {
               item.withStore(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_CUSTOMER, null, item);
            }
         }
      }
      return this;
   } 

   public Store withoutCustomer(Person... value)
   {
      for (Person item : value)
      {
         if ((this.customer != null) && (item != null))
         {
            if (this.customer.remove(item))
            {
               item.setStore(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_CUSTOMER, item, null);
            }
         }
      }
      return this;
   }

   public Person createCustomer()
   {
      Person value = new Person();
      withCustomer(value);
      return value;
   } 
}
