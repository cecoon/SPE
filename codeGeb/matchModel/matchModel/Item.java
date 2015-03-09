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
   
package matchModel;

import org.sdmlib.serialization.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.StrUtil;

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
}
