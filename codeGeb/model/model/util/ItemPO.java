package model.util;

import org.sdmlib.models.pattern.PatternObject;
import model.Item;
import org.sdmlib.models.pattern.AttributeConstraint;
import model.util.PersonPO;
import model.Person;
import model.util.ItemPO;
import model.util.StorePO;
import model.Store;

public class ItemPO extends PatternObject<ItemPO, Item>
{

    public ItemSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ItemSet matches = new ItemSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Item) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ItemPO(){
      newInstance(model.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ItemPO(Item... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(model.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public ItemPO hasName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Item.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttribute();
      
      return this;
   }
   
   public ItemPO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Item.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttribute();
      
      return this;
   }
   
   public ItemPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Item) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public ItemPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Item) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public ItemPO hasPrice(int value)
   {
      new AttributeConstraint()
      .withAttrName(Item.PROPERTY_PRICE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttribute();
      
      return this;
   }
   
   public ItemPO hasPrice(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Item.PROPERTY_PRICE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttribute();
      
      return this;
   }
   
   public ItemPO createPrice(int value)
   {
      this.startCreate().hasPrice(value).endCreate();
      return this;
   }
   
   public int getPrice()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Item) getCurrentMatch()).getPrice();
      }
      return 0;
   }
   
   public ItemPO withPrice(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Item) getCurrentMatch()).setPrice(value);
      }
      return this;
   }
   
   public PersonPO hasOwner()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Item.PROPERTY_OWNER, result);
      
      return result;
   }

   public PersonPO createOwner()
   {
      return this.startCreate().hasOwner().endCreate();
   }

   public ItemPO hasOwner(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Item.PROPERTY_OWNER);
   }

   public ItemPO createOwner(PersonPO tgt)
   {
      return this.startCreate().hasOwner(tgt).endCreate();
   }

   public Person getOwner()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Item) this.getCurrentMatch()).getOwner();
      }
      return null;
   }

   public StorePO hasInStore()
   {
      StorePO result = new StorePO(new Store[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Item.PROPERTY_INSTORE, result);
      
      return result;
   }

   public StorePO createInStore()
   {
      return this.startCreate().hasInStore().endCreate();
   }

   public ItemPO hasInStore(StorePO tgt)
   {
      return hasLinkConstraint(tgt, Item.PROPERTY_INSTORE);
   }

   public ItemPO createInStore(StorePO tgt)
   {
      return this.startCreate().hasInStore(tgt).endCreate();
   }

   public Store getInStore()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Item) this.getCurrentMatch()).getInStore();
      }
      return null;
   }

   public ItemPO hasValue(int value)
   {
      new AttributeConstraint()
      .withAttrName(Item.PROPERTY_VALUE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttribute();
      
      return this;
   }
   
   public ItemPO hasValue(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Item.PROPERTY_VALUE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttribute();
      
      return this;
   }
   
   public ItemPO createValue(int value)
   {
      this.startCreate().hasValue(value).endCreate();
      return this;
   }
   
   public int getValue()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Item) getCurrentMatch()).getValue();
      }
      return 0;
   }
   
   public ItemPO withValue(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Item) getCurrentMatch()).setValue(value);
      }
      return this;
   }
   
   public ItemPO hasNext()
   {
      ItemPO result = new ItemPO(new Item[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Item.PROPERTY_NEXT, result);
      
      return result;
   }

   public ItemPO createNext()
   {
      return this.startCreate().hasNext().endCreate();
   }

   public ItemPO hasNext(ItemPO tgt)
   {
      return hasLinkConstraint(tgt, Item.PROPERTY_NEXT);
   }

   public ItemPO createNext(ItemPO tgt)
   {
      return this.startCreate().hasNext(tgt).endCreate();
   }

   public Item getNext()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Item) this.getCurrentMatch()).getNext();
      }
      return null;
   }

   public ItemPO hasPrev()
   {
      ItemPO result = new ItemPO(new Item[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Item.PROPERTY_PREV, result);
      
      return result;
   }

   public ItemPO createPrev()
   {
      return this.startCreate().hasPrev().endCreate();
   }

   public ItemPO hasPrev(ItemPO tgt)
   {
      return hasLinkConstraint(tgt, Item.PROPERTY_PREV);
   }

   public ItemPO createPrev(ItemPO tgt)
   {
      return this.startCreate().hasPrev(tgt).endCreate();
   }

   public Item getPrev()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Item) this.getCurrentMatch()).getPrev();
      }
      return null;
   }

}
