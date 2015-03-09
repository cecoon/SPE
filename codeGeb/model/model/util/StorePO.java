package model.util;

import org.sdmlib.models.pattern.PatternObject;
import model.Store;
import model.util.ItemPO;
import model.Item;
import model.util.StorePO;
import model.util.ItemSet;
import model.util.PersonPO;
import model.Person;
import model.util.PersonSet;

public class StorePO extends PatternObject<StorePO, Store>
{

    public StoreSet allMatches()
   {
      this.setDoAllMatches(true);
      
      StoreSet matches = new StoreSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Store) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public StorePO(){
      newInstance(model.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public StorePO(Store... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(model.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public ItemPO hasHas()
   {
      ItemPO result = new ItemPO(new Item[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Store.PROPERTY_HAS, result);
      
      return result;
   }

   public ItemPO createHas()
   {
      return this.startCreate().hasHas().endCreate();
   }

   public StorePO hasHas(ItemPO tgt)
   {
      return hasLinkConstraint(tgt, Store.PROPERTY_HAS);
   }

   public StorePO createHas(ItemPO tgt)
   {
      return this.startCreate().hasHas(tgt).endCreate();
   }

   public ItemSet getHas()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Store) this.getCurrentMatch()).getHas();
      }
      return null;
   }

   public PersonPO hasCustomer()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Store.PROPERTY_CUSTOMER, result);
      
      return result;
   }

   public PersonPO createCustomer()
   {
      return this.startCreate().hasCustomer().endCreate();
   }

   public StorePO hasCustomer(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Store.PROPERTY_CUSTOMER);
   }

   public StorePO createCustomer(PersonPO tgt)
   {
      return this.startCreate().hasCustomer(tgt).endCreate();
   }

   public PersonSet getCustomer()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Store) this.getCurrentMatch()).getCustomer();
      }
      return null;
   }

}
