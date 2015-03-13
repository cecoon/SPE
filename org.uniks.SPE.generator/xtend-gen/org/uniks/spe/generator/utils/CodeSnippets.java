package org.uniks.spe.generator.utils;

import model.SPELink;
import model.SPEObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.uniks.spe.generator.utils.Extentions;

@SuppressWarnings("all")
public final class CodeSnippets {
  public static CharSequence declarePO(final SPEObject obj) {
    StringConcatenation _builder = new StringConcatenation();
    String _type = obj.getType();
    _builder.append(_type, "");
    _builder.append("PO ");
    String _varName = Extentions.varName(obj);
    _builder.append(_varName, "");
    return _builder;
  }
  
  public static CharSequence createPO(final SPEObject obj) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("new ");
    String _type = obj.getType();
    _builder.append(_type, "");
    _builder.append("Set().with(new ");
    String _type_1 = obj.getType();
    _builder.append(_type_1, "");
    _builder.append("()).has");
    String _type_2 = obj.getType();
    _builder.append(_type_2, "");
    _builder.append("PO()");
    return _builder;
  }
  
  public static CharSequence hasLinkToObj(final SPELink link) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(".has");
    String _Name = Extentions.Name(link);
    _builder.append(_Name, "");
    _builder.append("(");
    SPEObject _target = link.getTarget();
    String _varName = Extentions.varName(_target);
    _builder.append(_varName, "");
    _builder.append(")");
    return _builder;
  }
}
