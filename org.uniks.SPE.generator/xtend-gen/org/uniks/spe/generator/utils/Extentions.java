package org.uniks.spe.generator.utils;

import com.google.common.base.Objects;
import model.MatchTag;
import model.Operations;
import model.SPEAttribute;
import model.SPEGroup;
import model.SPELink;
import model.SPEObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public final class Extentions {
  public static boolean getIsNot(final MatchTag tag) {
    return Objects.equal(tag, MatchTag.NOT);
  }
  
  public static String Name(final SPELink link) {
    String _name = link.getName();
    return StringExtensions.toFirstUpper(_name);
  }
  
  public static String getAttrValue(final SPEAttribute raw) {
    String _operation = raw.getOperation();
    return _operation.replaceAll("[:!=<>]", "");
  }
  
  public static String getAttrName(final SPEAttribute raw) {
    String _name = raw.getName();
    return StringExtensions.toFirstUpper(_name);
  }
  
  public static String toSDMLibMatchTag(final SPEGroup group) {
    MatchTag _tag = group.getTag();
    boolean _equals = Objects.equal(_tag, MatchTag.NOT);
    if (_equals) {
      return "NAC";
    }
    MatchTag _tag_1 = group.getTag();
    boolean _equals_1 = Objects.equal(_tag_1, MatchTag.OPTIONAL);
    if (_equals_1) {
      return "SubPattern";
    }
    return "WTF?";
  }
  
  public static CharSequence asSubPattern(final CharSequence value) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(".startSubPattern()");
    _builder.append(value, "");
    _builder.append(".endSubPattern()");
    return _builder;
  }
  
  public static CharSequence asNAC(final CharSequence value) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(".startNAC()");
    _builder.append(value, "");
    _builder.append(".endNAC()");
    return _builder;
  }
  
  public static boolean isNop(final Operations operations) {
    return Objects.equal(operations, Operations.NOP);
  }
  
  public static boolean isNotCreate(final Operations operations) {
    return (!Objects.equal(operations, Operations.CREATE));
  }
  
  public static String varName(final SPEObject object) {
    String _name = object.getName();
    return (_name + "PO");
  }
}
