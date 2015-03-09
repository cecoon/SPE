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
import model.Store;
import model.Item;
import model.Person;

public class StoreCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Store.PROPERTY_HAS,
      Store.PROPERTY_CUSTOMER,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Store();
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

      if (Store.PROPERTY_HAS.equalsIgnoreCase(attribute))
      {
         return ((Store) target).getHas();
      }

      if (Store.PROPERTY_CUSTOMER.equalsIgnoreCase(attribute))
      {
         return ((Store) target).getCustomer();
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

      if (Store.PROPERTY_HAS.equalsIgnoreCase(attrName))
      {
         ((Store) target).withHas((Item) value);
         return true;
      }
      
      if ((Store.PROPERTY_HAS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Store) target).withoutHas((Item) value);
         return true;
      }

      if (Store.PROPERTY_CUSTOMER.equalsIgnoreCase(attrName))
      {
         ((Store) target).withCustomer((Person) value);
         return true;
      }
      
      if ((Store.PROPERTY_CUSTOMER + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Store) target).withoutCustomer((Person) value);
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
      ((Store) entity).removeYou();
   }
}
