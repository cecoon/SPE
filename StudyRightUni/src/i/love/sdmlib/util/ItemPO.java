package i.love.sdmlib.util;

import org.sdmlib.models.pattern.PatternObject;
import i.love.sdmlib.Item;
import org.sdmlib.models.pattern.AttributeConstraint;
import i.love.sdmlib.util.PersonPO;
import i.love.sdmlib.Person;
import i.love.sdmlib.util.ItemPO;

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
      newInstance(i.love.sdmlib.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ItemPO(Item... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(i.love.sdmlib.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
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

}
