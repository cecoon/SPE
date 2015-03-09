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

public class Item implements PropertyChangeInterface
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
      setOwner(null);
      setInStore(null);
      setNext(null);
      setPrev(null);
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
   
   public Item withName(String value)
   {
      setName(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getName());
      result.append(" ").append(this.getPrice());
      result.append(" ").append(this.getValue());
      return result.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_PRICE = "price";
   
   private int price;

   public int getPrice()
   {
      return this.price;
   }
   
   public void setPrice(int value)
   {
      if (this.price != value)
      {
         int oldValue = this.price;
         this.price = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PRICE, oldValue, value);
      }
   }
   
   public Item withPrice(int value)
   {
      setPrice(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Item ----------------------------------- Person
    *              has                   owner
    * </pre>
    */
   
   public static final String PROPERTY_OWNER = "owner";

   private Person owner = null;

   public Person getOwner()
   {
      return this.owner;
   }

   public boolean setOwner(Person value)
   {
      boolean changed = false;
      
      if (this.owner != value)
      {
         Person oldValue = this.owner;
         
         if (this.owner != null)
         {
            this.owner = null;
            oldValue.withoutHas(this);
         }
         
         this.owner = value;
         
         if (value != null)
         {
            value.withHas(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_OWNER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Item withOwner(Person value)
   {
      setOwner(value);
      return this;
   } 

   public Person createOwner()
   {
      Person value = new Person();
      withOwner(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Item ----------------------------------- Store
    *              has                   inStore
    * </pre>
    */
   
   public static final String PROPERTY_INSTORE = "inStore";

   private Store inStore = null;

   public Store getInStore()
   {
      return this.inStore;
   }

   public boolean setInStore(Store value)
   {
      boolean changed = false;
      
      if (this.inStore != value)
      {
         Store oldValue = this.inStore;
         
         if (this.inStore != null)
         {
            this.inStore = null;
            oldValue.withoutHas(this);
         }
         
         this.inStore = value;
         
         if (value != null)
         {
            value.withHas(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_INSTORE, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Item withInStore(Store value)
   {
      setInStore(value);
      return this;
   } 

   public Store createInStore()
   {
      Store value = new Store();
      withInStore(value);
      return value;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_VALUE = "value";
   
   private int value;

   public int getValue()
   {
      return this.value;
   }
   
   public void setValue(int value)
   {
      if (this.value != value)
      {
         int oldValue = this.value;
         this.value = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_VALUE, oldValue, value);
      }
   }
   
   public Item withValue(int value)
   {
      setValue(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Item ----------------------------------- Item
    *              prev                   next
    * </pre>
    */
   
   public static final String PROPERTY_NEXT = "next";

   private Item next = null;

   public Item getNext()
   {
      return this.next;
   }
   public ItemSet getNextTransitive()
   {
      ItemSet result = new ItemSet().with(this);
      return result.getNextTransitive();
   }


   public boolean setNext(Item value)
   {
      boolean changed = false;
      
      if (this.next != value)
      {
         Item oldValue = this.next;
         
         if (this.next != null)
         {
            this.next = null;
            oldValue.setPrev(null);
         }
         
         this.next = value;
         
         if (value != null)
         {
            value.withPrev(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_NEXT, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Item withNext(Item value)
   {
      setNext(value);
      return this;
   } 

   public Item createNext()
   {
      Item value = new Item();
      withNext(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Item ----------------------------------- Item
    *              next                   prev
    * </pre>
    */
   
   public static final String PROPERTY_PREV = "prev";

   private Item prev = null;

   public Item getPrev()
   {
      return this.prev;
   }
   public ItemSet getPrevTransitive()
   {
      ItemSet result = new ItemSet().with(this);
      return result.getPrevTransitive();
   }


   public boolean setPrev(Item value)
   {
      boolean changed = false;
      
      if (this.prev != value)
      {
         Item oldValue = this.prev;
         
         if (this.prev != null)
         {
            this.prev = null;
            oldValue.setNext(null);
         }
         
         this.prev = value;
         
         if (value != null)
         {
            value.withNext(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PREV, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Item withPrev(Item value)
   {
      setPrev(value);
      return this;
   } 

   public Item createPrev()
   {
      Item value = new Item();
      withPrev(value);
      return value;
   } 
}
