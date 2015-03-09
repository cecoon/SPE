package i.love.sdmlib.util;

import org.sdmlib.models.pattern.PatternObject;
import i.love.sdmlib.Person;
import org.sdmlib.models.pattern.AttributeConstraint;
import i.love.sdmlib.util.ItemPO;
import i.love.sdmlib.Item;
import i.love.sdmlib.util.PersonPO;
import i.love.sdmlib.util.ItemSet;

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
      newInstance(i.love.sdmlib.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public PersonPO(Person... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(i.love.sdmlib.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
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

}
