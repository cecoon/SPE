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

import org.sdmlib.serialization.EntityFactory;
import de.uniks.networkparser.json.JsonIdMap;
import model.Item;
import model.Person;
import model.Store;

public class ItemCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Item.PROPERTY_NAME,
      Item.PROPERTY_VALUE,
      Item.PROPERTY_NEXT,
      Item.PROPERTY_PREV,
      Item.PROPERTY_OWNER,
      Item.PROPERTY_INSTORE,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Item();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (Item.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((Item) target).getName();
      }

      if (Item.PROPERTY_VALUE.equalsIgnoreCase(attribute))
      {
         return ((Item) target).getValue();
      }

      if (Item.PROPERTY_NEXT.equalsIgnoreCase(attribute))
      {
         return ((Item) target).getNext();
      }

      if (Item.PROPERTY_PREV.equalsIgnoreCase(attribute))
      {
         return ((Item) target).getPrev();
      }

      if (Item.PROPERTY_OWNER.equalsIgnoreCase(attribute))
      {
         return ((Item) target).getOwner();
      }

      if (Item.PROPERTY_INSTORE.equalsIgnoreCase(attribute))
      {
         return ((Item) target).getInStore();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Item.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Item) target).withName((String) value);
         return true;
      }

      if (Item.PROPERTY_VALUE.equalsIgnoreCase(attrName))
      {
         ((Item) target).withValue(Integer.parseInt(value.toString()));
         return true;
      }

      if (Item.PROPERTY_NEXT.equalsIgnoreCase(attrName))
      {
         ((Item) target).setNext((Item) value);
         return true;
      }

      if (Item.PROPERTY_PREV.equalsIgnoreCase(attrName))
      {
         ((Item) target).setPrev((Item) value);
         return true;
      }

      if (Item.PROPERTY_OWNER.equalsIgnoreCase(attrName))
      {
         ((Item) target).setOwner((Person) value);
         return true;
      }

      if (Item.PROPERTY_INSTORE.equalsIgnoreCase(attrName))
      {
         ((Item) target).setInStore((Store) value);
         return true;
      }
      
      return false;
   }
   public static JsonIdMap createIdMap(String sessionID)
   {
      return model.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Item) entity).removeYou();
   }
}
