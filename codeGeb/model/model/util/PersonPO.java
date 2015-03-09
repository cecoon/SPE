package model.util;

import org.sdmlib.models.pattern.PatternObject;
import model.Person;
import org.sdmlib.models.pattern.AttributeConstraint;
import model.util.ItemPO;
import model.Item;
import model.util.PersonPO;
import model.util.ItemSet;
import model.util.StorePO;
import model.Store;

public class PersonPO extends PatternObject<PersonPO, Person>
{

    public PersonSet allMatches()
   {
      this.setDoAllMatches(true);
      
      PersonSet matches = new PersonSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Person) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public PersonPO(){
      newInstance(model.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public PersonPO(Person... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(model.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public PersonPO hasName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttribute();
      
      return this;
   }
   
   public PersonPO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttribute();
      
      return this;
   }
   
   public PersonPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public PersonPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Person) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public ItemPO hasHas()
   {
      ItemPO result = new ItemPO(new Item[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_HAS, result);
      
      return result;
   }

   public ItemPO createHas()
   {
      return this.startCreate().hasHas().endCreate();
   }

   public PersonPO hasHas(ItemPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_HAS);
   }

   public PersonPO createHas(ItemPO tgt)
   {
      return this.startCreate().hasHas(tgt).endCreate();
   }

   public ItemSet getHas()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getHas();
      }
      return null;
   }

   public StorePO hasStore()
   {
      StorePO result = new StorePO(new Store[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_STORE, result);
      
      return result;
   }

   public StorePO createStore()
   {
      return this.startCreate().hasStore().endCreate();
   }

   public PersonPO hasStore(StorePO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_STORE);
   }

   public PersonPO createStore(StorePO tgt)
   {
      return this.startCreate().hasStore(tgt).endCreate();
   }

   public Store getStore()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getStore();
      }
      return null;
   }

   public PersonPO hasBalance(int value)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_BALANCE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttribute();
      
      return this;
   }
   
   public PersonPO hasBalance(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_BALANCE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttribute();
      
      return this;
   }
   
   public PersonPO createBalance(int value)
   {
      this.startCreate().hasBalance(value).endCreate();
      return this;
   }
   
   public int getBalance()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) getCurrentMatch()).getBalance();
      }
      return 0;
   }
   
   public PersonPO withBalance(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Person) getCurrentMatch()).setBalance(value);
      }
      return this;
   }
   
}
