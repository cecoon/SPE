package org.uniks.spe.generator;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import model.IHasMatchTag;
import model.MatchTag;
import model.SPEAttribute;
import model.SPEGroup;
import model.SPELink;
import model.SPEObject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.Functions.Function2;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.Pair;
import org.uniks.spe.generator.model.EntryPoint;
import org.uniks.spe.generator.model.MatchState;
import org.uniks.spe.generator.utils.CodeSnippets;
import org.uniks.spe.generator.utils.Extentions;

@SuppressWarnings("all")
public class ModelMatchGenerator {
  private static List<Pair<String, Function1<SPEAttribute, CharSequence>>> attributeMatchHandler = ModelMatchGenerator.initAttributeHandler();
  
  private MatchState matchState;
  
  public ModelMatchGenerator(final MatchState matchState) {
    this.matchState = matchState;
  }
  
  public String generateMatchCodeForNonAlienObjects(final SPEObject object) {
    EList<SPELink> _outboundLinks = object.getOutboundLinks();
    final Function1<SPELink, Boolean> _function = new Function1<SPELink, Boolean>() {
      public Boolean apply(final SPELink it) {
        boolean _and = false;
        boolean _canBeMatched = Extentions.canBeMatched(it);
        if (!_canBeMatched) {
          _and = false;
        } else {
          boolean _canCreatePO = Extentions.canCreatePO(it);
          _and = _canCreatePO;
        }
        return Boolean.valueOf(_and);
      }
    };
    Iterable<SPELink> _filter = IterableExtensions.<SPELink>filter(_outboundLinks, _function);
    final Function1<SPELink, Boolean> _function_1 = new Function1<SPELink, Boolean>() {
      public Boolean apply(final SPELink it) {
        SPEObject _target = it.getTarget();
        return Boolean.valueOf(ModelMatchGenerator.this.areInSameGroup(object, _target));
      }
    };
    Iterable<SPELink> _filter_1 = IterableExtensions.<SPELink>filter(_filter, _function_1);
    final Function2<String, SPELink, String> _function_2 = new Function2<String, SPELink, String>() {
      public String apply(final String _, final SPELink it) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append(_, "");
        String _varName = Extentions.varName(object);
        CharSequence _createMatchingCodeForLinkedObjects = ModelMatchGenerator.this.createMatchingCodeForLinkedObjects(it, _varName);
        _builder.append(_createMatchingCodeForLinkedObjects, "");
        return _builder.toString();
      }
    };
    return IterableExtensions.<SPELink, String>fold(_filter_1, "", _function_2);
  }
  
  private CharSequence createMatchingCodeForLinkedObjects(final SPELink link, final String fromVarName) {
    CharSequence _xblockexpression = null;
    {
      final SPEObject target = link.getTarget();
      boolean _isMatched = this.matchState.isMatched(target);
      if (_isMatched) {
        return "";
      }
      this.matchState.markAsMatched(target);
      this.matchState.markAsMatched(link);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append(".has");
      String _Name = Extentions.Name(link);
      _builder.append(_Name, "");
      _builder.append("()");
      EList<SPEAttribute> _attributes = target.getAttributes();
      String _type = target.getType();
      String _createAttributeMatchCode = ModelMatchGenerator.createAttributeMatchCode(_attributes, _type);
      _builder.append(_createAttributeMatchCode, "");
      String matchExpression = _builder.toString();
      CharSequence matchExp = ModelMatchGenerator.addMatchTags(matchExpression, target);
      StringConcatenation _builder_1 = new StringConcatenation();
      CharSequence _declarePO = CodeSnippets.declarePO(target);
      _builder_1.append(_declarePO, "");
      _builder_1.append(" = ");
      _builder_1.append(fromVarName, "");
      _builder_1.append(matchExp, "");
      _builder_1.append(";");
      _builder_1.newLineIfNotEmpty();
      String _generateMatchCodeForNonAlienObjects = this.generateMatchCodeForNonAlienObjects(target);
      _builder_1.append(_generateMatchCodeForNonAlienObjects, "");
      _builder_1.newLineIfNotEmpty();
      _xblockexpression = _builder_1;
    }
    return _xblockexpression;
  }
  
  public String generateCodeForMissingLinks() {
    SPEGroup _root = this.matchState.getRoot();
    EList<SPELink> _links = _root.getLinks();
    final Function1<SPELink, Boolean> _function = new Function1<SPELink, Boolean>() {
      public Boolean apply(final SPELink it) {
        boolean _and = false;
        boolean _isMatched = ModelMatchGenerator.this.matchState.isMatched(it);
        boolean _not = (!_isMatched);
        if (!_not) {
          _and = false;
        } else {
          boolean _canBeMatched = Extentions.canBeMatched(it);
          _and = _canBeMatched;
        }
        return Boolean.valueOf(_and);
      }
    };
    Iterable<SPELink> _filter = IterableExtensions.<SPELink>filter(_links, _function);
    final Function2<String, SPELink, String> _function_1 = new Function2<String, SPELink, String>() {
      public String apply(final String _, final SPELink it) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append(_, "");
        CharSequence _createMatchCodeForALink = ModelMatchGenerator.this.createMatchCodeForALink(it);
        _builder.append(_createMatchCodeForALink, "");
        return _builder.toString();
      }
    };
    return IterableExtensions.<SPELink, String>fold(_filter, "", _function_1);
  }
  
  private CharSequence createMatchCodeForALink(final SPELink link) {
    CharSequence _xblockexpression = null;
    {
      this.matchState.markAsMatched(link);
      StringConcatenation _builder = new StringConcatenation();
      SPEObject _source = link.getSource();
      String _varName = Extentions.varName(_source);
      _builder.append(_varName, "");
      CharSequence _hasLinkToObj = CodeSnippets.hasLinkToObj(link);
      CharSequence _addMatchTags = ModelMatchGenerator.addMatchTags(_hasLinkToObj, link);
      _builder.append(_addMatchTags, "");
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }
  
  public String createObjectsOfSubgroups() {
    SPEGroup _root = this.matchState.getRoot();
    EList<SPEGroup> _subGroups = _root.getSubGroups();
    final Function2<String, SPEGroup, String> _function = new Function2<String, SPEGroup, String>() {
      public String apply(final String left, final SPEGroup it) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append(left, "");
        CharSequence _createCodeForSubgroupMatching = ModelMatchGenerator.this.createCodeForSubgroupMatching(it);
        _builder.append(_createCodeForSubgroupMatching, "");
        return _builder.toString();
      }
    };
    return IterableExtensions.<SPEGroup, String>fold(_subGroups, "", _function);
  }
  
  private CharSequence createCodeForSubgroupMatching(final SPEGroup group) {
    CharSequence _xblockexpression = null;
    {
      ArrayList<SPELink> alienLinks = this.findAlienLinks(group);
      int _size = alienLinks.size();
      boolean _equals = (_size == 0);
      if (_equals) {
        StringConcatenation _builder = new StringConcatenation();
        return _builder.toString();
      }
      final Function1<SPELink, EntryPoint> _function = new Function1<SPELink, EntryPoint>() {
        public EntryPoint apply(final SPELink it) {
          return ModelMatchGenerator.this.extractEntryPoint(it, group);
        }
      };
      List<EntryPoint> entryPoints = ListExtensions.<SPELink, EntryPoint>map(alienLinks, _function);
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("//subgroup");
      _builder_1.newLine();
      SPEObject _start = this.matchState.getStart();
      String _varName = Extentions.varName(_start);
      _builder_1.append(_varName, "");
      _builder_1.append(".start");
      String _sDMLibMatchTag = Extentions.toSDMLibMatchTag(group);
      _builder_1.append(_sDMLibMatchTag, "");
      _builder_1.append("();");
      _builder_1.newLineIfNotEmpty();
      final Function1<EntryPoint, Boolean> _function_1 = new Function1<EntryPoint, Boolean>() {
        public Boolean apply(final EntryPoint it) {
          return Boolean.valueOf(it.isInbound());
        }
      };
      Iterable<EntryPoint> _filter = IterableExtensions.<EntryPoint>filter(entryPoints, _function_1);
      final Function2<String, EntryPoint, String> _function_2 = new Function2<String, EntryPoint, String>() {
        public String apply(final String _, final EntryPoint it) {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append(_, "");
          CharSequence _matchCodeForEntryPointObjects = ModelMatchGenerator.this.matchCodeForEntryPointObjects(it);
          _builder.append(_matchCodeForEntryPointObjects, "");
          return _builder.toString();
        }
      };
      String _fold = IterableExtensions.<EntryPoint, String>fold(_filter, "", _function_2);
      _builder_1.append(_fold, "");
      _builder_1.newLineIfNotEmpty();
      ArrayList<SPELink> _findUnmatchedGroupLinks = this.findUnmatchedGroupLinks(group);
      final Function2<String, SPELink, String> _function_3 = new Function2<String, SPELink, String>() {
        public String apply(final String _, final SPELink it) {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append(_, "");
          CharSequence _createMatchCodeForALink = ModelMatchGenerator.this.createMatchCodeForALink(it);
          _builder.append(_createMatchCodeForALink, "");
          return _builder.toString();
        }
      };
      String _fold_1 = IterableExtensions.<SPELink, String>fold(_findUnmatchedGroupLinks, "", _function_3);
      _builder_1.append(_fold_1, "");
      _builder_1.newLineIfNotEmpty();
      final Function2<String, SPELink, String> _function_4 = new Function2<String, SPELink, String>() {
        public String apply(final String _, final SPELink it) {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append(_, "");
          CharSequence _createMatchCodeForALink = ModelMatchGenerator.this.createMatchCodeForALink(it);
          _builder.append(_createMatchCodeForALink, "");
          return _builder.toString();
        }
      };
      String _fold_2 = IterableExtensions.<SPELink, String>fold(alienLinks, "", _function_4);
      _builder_1.append(_fold_2, "");
      _builder_1.newLineIfNotEmpty();
      SPEObject _start_1 = this.matchState.getStart();
      String _varName_1 = Extentions.varName(_start_1);
      _builder_1.append(_varName_1, "");
      _builder_1.append(".end");
      String _sDMLibMatchTag_1 = Extentions.toSDMLibMatchTag(group);
      _builder_1.append(_sDMLibMatchTag_1, "");
      _builder_1.append("();");
      _builder_1.newLineIfNotEmpty();
      _xblockexpression = _builder_1;
    }
    return _xblockexpression;
  }
  
  public CharSequence matchCodeForEntryPointObjects(final EntryPoint point) {
    CharSequence _xblockexpression = null;
    {
      SPEObject _start = point.getStart();
      boolean _isMatched = this.matchState.isMatched(_start);
      if (_isMatched) {
        StringConcatenation _builder = new StringConcatenation();
        return _builder.toString();
      }
      this.matchState.markAsMatched(point);
      StringConcatenation _builder_1 = new StringConcatenation();
      SPEObject _start_1 = point.getStart();
      CharSequence _declarePO = CodeSnippets.declarePO(_start_1);
      _builder_1.append(_declarePO, "");
      _builder_1.append(" = ");
      SPEObject _alien = point.getAlien();
      String _varName = Extentions.varName(_alien);
      _builder_1.append(_varName, "");
      _builder_1.append(".has");
      SPELink _link = point.getLink();
      String _Name = Extentions.Name(_link);
      _builder_1.append(_Name, "");
      _builder_1.append("()");
      SPEObject _start_2 = point.getStart();
      EList<SPEAttribute> _attributes = _start_2.getAttributes();
      SPEObject _start_3 = point.getStart();
      String _type = _start_3.getType();
      String _createAttributeMatchCode = ModelMatchGenerator.createAttributeMatchCode(_attributes, _type);
      _builder_1.append(_createAttributeMatchCode, "");
      _builder_1.append(";");
      _builder_1.newLineIfNotEmpty();
      SPEObject _start_4 = point.getStart();
      String _generateMatchCodeForNonAlienObjects = this.generateMatchCodeForNonAlienObjects(_start_4);
      _builder_1.append(_generateMatchCodeForNonAlienObjects, "");
      _builder_1.newLineIfNotEmpty();
      _xblockexpression = _builder_1;
    }
    return _xblockexpression;
  }
  
  private ArrayList<SPELink> findAlienLinks(final SPEGroup group) {
    ArrayList<SPELink> _xblockexpression = null;
    {
      ArrayList<SPELink> alienLinks = CollectionLiterals.<SPELink>newArrayList();
      Set<SPEObject> _matchedObjects = this.matchState.getMatchedObjects();
      for (final SPEObject object : _matchedObjects) {
        {
          EList<SPELink> _outboundLinks = object.getOutboundLinks();
          final Function1<SPELink, Boolean> _function = new Function1<SPELink, Boolean>() {
            public Boolean apply(final SPELink it) {
              boolean _and = false;
              boolean _canBeMatched = Extentions.canBeMatched(it);
              if (!_canBeMatched) {
                _and = false;
              } else {
                SPEObject _target = it.getTarget();
                SPEGroup _group = ModelMatchGenerator.this.getGroup(_target);
                boolean _equals = Objects.equal(group, _group);
                _and = _equals;
              }
              return Boolean.valueOf(_and);
            }
          };
          Iterable<SPELink> _filter = IterableExtensions.<SPELink>filter(_outboundLinks, _function);
          Iterables.<SPELink>addAll(alienLinks, _filter);
          EList<SPELink> _inboundLinks = object.getInboundLinks();
          final Function1<SPELink, Boolean> _function_1 = new Function1<SPELink, Boolean>() {
            public Boolean apply(final SPELink it) {
              boolean _and = false;
              boolean _canBeMatched = Extentions.canBeMatched(it);
              if (!_canBeMatched) {
                _and = false;
              } else {
                SPEObject _source = it.getSource();
                SPEGroup _group = ModelMatchGenerator.this.getGroup(_source);
                boolean _equals = Objects.equal(group, _group);
                _and = _equals;
              }
              return Boolean.valueOf(_and);
            }
          };
          Iterable<SPELink> _filter_1 = IterableExtensions.<SPELink>filter(_inboundLinks, _function_1);
          Iterables.<SPELink>addAll(alienLinks, _filter_1);
        }
      }
      _xblockexpression = alienLinks;
    }
    return _xblockexpression;
  }
  
  private ArrayList<SPELink> findUnmatchedGroupLinks(final SPEGroup group) {
    ArrayList<SPELink> _xblockexpression = null;
    {
      ArrayList<SPELink> grpLinks = CollectionLiterals.<SPELink>newArrayList();
      Set<SPEObject> _matchedObjects = this.matchState.getMatchedObjects();
      for (final SPEObject object : _matchedObjects) {
        EList<SPELink> _outboundLinks = object.getOutboundLinks();
        final Function1<SPELink, Boolean> _function = new Function1<SPELink, Boolean>() {
          public Boolean apply(final SPELink it) {
            boolean _and = false;
            boolean _and_1 = false;
            SPEObject _target = it.getTarget();
            SPEGroup _group = ModelMatchGenerator.this.getGroup(_target);
            boolean _equals = Objects.equal(group, _group);
            if (!_equals) {
              _and_1 = false;
            } else {
              SPEObject _source = it.getSource();
              SPEGroup _group_1 = ModelMatchGenerator.this.getGroup(_source);
              boolean _equals_1 = Objects.equal(group, _group_1);
              _and_1 = _equals_1;
            }
            if (!_and_1) {
              _and = false;
            } else {
              boolean _isMatched = ModelMatchGenerator.this.matchState.isMatched(it);
              boolean _not = (!_isMatched);
              _and = _not;
            }
            return Boolean.valueOf(_and);
          }
        };
        Iterable<SPELink> _filter = IterableExtensions.<SPELink>filter(_outboundLinks, _function);
        Iterables.<SPELink>addAll(grpLinks, _filter);
      }
      _xblockexpression = grpLinks;
    }
    return _xblockexpression;
  }
  
  private EntryPoint extractEntryPoint(final SPELink link, final SPEGroup grp) {
    EntryPoint _xblockexpression = null;
    {
      EntryPoint result = new EntryPoint();
      SPEObject _target = link.getTarget();
      SPEGroup _group = this.getGroup(_target);
      boolean _equals = Objects.equal(_group, grp);
      result.setInbound(_equals);
      result.setLink(link);
      boolean _isInbound = result.isInbound();
      if (_isInbound) {
        SPEObject _source = link.getSource();
        result.setAlien(_source);
        SPEObject _target_1 = link.getTarget();
        result.setStart(_target_1);
      } else {
        SPEObject _target_2 = link.getTarget();
        result.setAlien(_target_2);
        SPEObject _source_1 = link.getSource();
        result.setStart(_source_1);
      }
      _xblockexpression = result;
    }
    return _xblockexpression;
  }
  
  public static String createAttributeMatchCode(final List<SPEAttribute> attribute, final String type) {
    final Function2<String, SPEAttribute, String> _function = new Function2<String, SPEAttribute, String>() {
      public String apply(final String _, final SPEAttribute it) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append(_, "");
        CharSequence _createAttributeMethodCall = ModelMatchGenerator.createAttributeMethodCall(it, type);
        _builder.append(_createAttributeMethodCall, "");
        return _builder.toString();
      }
    };
    return IterableExtensions.<SPEAttribute, String>fold(attribute, "", _function);
  }
  
  private static CharSequence createAttributeMethodCall(final SPEAttribute attribute, final String type) {
    CharSequence _xblockexpression = null;
    {
      final Function1<Pair<String, Function1<SPEAttribute, CharSequence>>, Boolean> _function = new Function1<Pair<String, Function1<SPEAttribute, CharSequence>>, Boolean>() {
        public Boolean apply(final Pair<String, Function1<SPEAttribute, CharSequence>> it) {
          String _operation = attribute.getOperation();
          String _key = it.getKey();
          return Boolean.valueOf(_operation.matches(_key));
        }
      };
      Pair<String, Function1<SPEAttribute, CharSequence>> _findFirst = IterableExtensions.<Pair<String, Function1<SPEAttribute, CharSequence>>>findFirst(ModelMatchGenerator.attributeMatchHandler, _function);
      Function1<SPEAttribute, CharSequence> _value = null;
      if (_findFirst!=null) {
        _value=_findFirst.getValue();
      }
      CharSequence _apply = null;
      if (_value!=null) {
        _apply=_value.apply(attribute);
      }
      CharSequence call = _apply;
      boolean _equals = Objects.equal(call, null);
      if (_equals) {
        CharSequence _createRegExCondition = ModelMatchGenerator.createRegExCondition(attribute, type);
        call = _createRegExCondition;
      }
      _xblockexpression = call;
    }
    return _xblockexpression;
  }
  
  private static CharSequence createRegExCondition(final SPEAttribute raw, final String type) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(".has(new Condition<Object>() {");
    _builder.newLine();
    _builder.append("           ");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("           ");
    _builder.append("public boolean check(Object value) {");
    _builder.newLine();
    _builder.append("               ");
    _builder.append("if(value instanceof ");
    _builder.append(type, "               ");
    _builder.append("){");
    _builder.newLineIfNotEmpty();
    _builder.append("                   ");
    _builder.append("boolean matches = ((");
    _builder.append(type, "                   ");
    _builder.append(")value).getName().matches(\"");
    String _attrValue = Extentions.getAttrValue(raw);
    _builder.append(_attrValue, "                   ");
    _builder.append("\");");
    _builder.newLineIfNotEmpty();
    _builder.append("                   ");
    _builder.append("return matches;");
    _builder.newLine();
    _builder.append("               ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("               ");
    _builder.append("return false;");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("})");
    _builder.newLine();
    return _builder;
  }
  
  private boolean areInSameGroup(final SPEObject source, final SPEObject target) {
    SPEGroup _group = this.getGroup(source);
    SPEGroup _group_1 = this.getGroup(target);
    return Objects.equal(_group, _group_1);
  }
  
  private SPEGroup getGroup(final SPEObject object) {
    SPEGroup _root = this.matchState.getRoot();
    EList<SPEObject> _objects = _root.getObjects();
    boolean _contains = _objects.contains(object);
    if (_contains) {
      return this.matchState.getRoot();
    }
    SPEGroup _root_1 = this.matchState.getRoot();
    EList<SPEGroup> _subGroups = _root_1.getSubGroups();
    final Function1<SPEGroup, Boolean> _function = new Function1<SPEGroup, Boolean>() {
      public Boolean apply(final SPEGroup it) {
        EList<SPEObject> _objects = it.getObjects();
        return Boolean.valueOf(_objects.contains(object));
      }
    };
    return IterableExtensions.<SPEGroup>findFirst(_subGroups, _function);
  }
  
  private static CharSequence addMatchTags(final CharSequence string, final IHasMatchTag tag) {
    MatchTag _tag = tag.getTag();
    boolean _equals = Objects.equal(_tag, MatchTag.NOT);
    if (_equals) {
      return Extentions.asNAC(string);
    }
    MatchTag _tag_1 = tag.getTag();
    boolean _equals_1 = Objects.equal(_tag_1, MatchTag.OPTIONAL);
    if (_equals_1) {
      return Extentions.asSubPattern(string);
    }
    return string;
  }
  
  private static List<Pair<String, Function1<SPEAttribute, CharSequence>>> initAttributeHandler() {
    List<Pair<String, Function1<SPEAttribute, CharSequence>>> _xblockexpression = null;
    {
      final String maxInt = "Integer.MAX_VALUE";
      final String minInt = "Integer.MIN_VALUE";
      final Function1<SPEAttribute, CharSequence> _function = new Function1<SPEAttribute, CharSequence>() {
        public CharSequence apply(final SPEAttribute it) {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append(".has");
          String _attrName = Extentions.getAttrName(it);
          _builder.append(_attrName, "");
          _builder.append("(");
          String _attrValue = Extentions.getAttrValue(it);
          _builder.append(_attrValue, "");
          _builder.append(")");
          return _builder;
        }
      };
      final Pair<String, Function1<SPEAttribute, CharSequence>> equal = Pair.<String, Function1<SPEAttribute, CharSequence>>of("^==[\\d\"].*$", _function);
      final Function1<SPEAttribute, CharSequence> _function_1 = new Function1<SPEAttribute, CharSequence>() {
        public CharSequence apply(final SPEAttribute it) {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append(".has");
          String _attrName = Extentions.getAttrName(it);
          _builder.append(_attrName, "");
          _builder.append("(");
          String _attrValue = Extentions.getAttrValue(it);
          _builder.append(_attrValue, "");
          _builder.append(" - 1 ,");
          _builder.append(maxInt, "");
          _builder.append(")");
          return _builder;
        }
      };
      final Pair<String, Function1<SPEAttribute, CharSequence>> smaller = Pair.<String, Function1<SPEAttribute, CharSequence>>of("^<\\d*$", _function_1);
      final Function1<SPEAttribute, CharSequence> _function_2 = new Function1<SPEAttribute, CharSequence>() {
        public CharSequence apply(final SPEAttribute it) {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append(".has");
          String _attrName = Extentions.getAttrName(it);
          _builder.append(_attrName, "");
          _builder.append("(");
          String _attrValue = Extentions.getAttrValue(it);
          _builder.append(_attrValue, "");
          _builder.append(" ,");
          _builder.append(maxInt, "");
          _builder.append(")");
          return _builder;
        }
      };
      final Pair<String, Function1<SPEAttribute, CharSequence>> sEqual = Pair.<String, Function1<SPEAttribute, CharSequence>>of("^<=\\d*$", _function_2);
      final Function1<SPEAttribute, CharSequence> _function_3 = new Function1<SPEAttribute, CharSequence>() {
        public CharSequence apply(final SPEAttribute it) {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append(".has");
          String _attrName = Extentions.getAttrName(it);
          _builder.append(_attrName, "");
          _builder.append("(");
          _builder.append(minInt, "");
          _builder.append(", ");
          String _attrValue = Extentions.getAttrValue(it);
          _builder.append(_attrValue, "");
          _builder.append(" + 1)");
          return _builder;
        }
      };
      final Pair<String, Function1<SPEAttribute, CharSequence>> bigger = Pair.<String, Function1<SPEAttribute, CharSequence>>of("^>\\d*$", _function_3);
      final Function1<SPEAttribute, CharSequence> _function_4 = new Function1<SPEAttribute, CharSequence>() {
        public CharSequence apply(final SPEAttribute it) {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append(".has");
          String _attrName = Extentions.getAttrName(it);
          _builder.append(_attrName, "");
          _builder.append("(");
          _builder.append(minInt, "");
          _builder.append(", ");
          String _attrValue = Extentions.getAttrValue(it);
          _builder.append(_attrValue, "");
          _builder.append(")");
          return _builder;
        }
      };
      final Pair<String, Function1<SPEAttribute, CharSequence>> bEqual = Pair.<String, Function1<SPEAttribute, CharSequence>>of("^>=\\d*$", _function_4);
      final Function1<SPEAttribute, CharSequence> _function_5 = new Function1<SPEAttribute, CharSequence>() {
        public CharSequence apply(final SPEAttribute it) {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append(".startNAC().has");
          String _attrName = Extentions.getAttrName(it);
          _builder.append(_attrName, "");
          _builder.append("(");
          String _attrValue = Extentions.getAttrValue(it);
          _builder.append(_attrValue, "");
          _builder.append(").endNAC()");
          return _builder;
        }
      };
      final Pair<String, Function1<SPEAttribute, CharSequence>> nEqual = Pair.<String, Function1<SPEAttribute, CharSequence>>of("^!=.*$", _function_5);
      final Function1<SPEAttribute, CharSequence> _function_6 = new Function1<SPEAttribute, CharSequence>() {
        public CharSequence apply(final SPEAttribute it) {
          StringConcatenation _builder = new StringConcatenation();
          return _builder;
        }
      };
      final Pair<String, Function1<SPEAttribute, CharSequence>> setVal = Pair.<String, Function1<SPEAttribute, CharSequence>>of("^:=.*$", _function_6);
      final Function1<SPEAttribute, CharSequence> _function_7 = new Function1<SPEAttribute, CharSequence>() {
        public CharSequence apply(final SPEAttribute it) {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append(".has");
          String _attrName = Extentions.getAttrName(it);
          _builder.append(_attrName, "");
          _builder.append("(");
          String _operation = it.getOperation();
          String _replace = _operation.replace(">", "");
          String _replace_1 = _replace.replace("<", " + 1, ");
          _builder.append(_replace_1, "");
          _builder.append(" - 1)");
          return _builder;
        }
      };
      final Pair<String, Function1<SPEAttribute, CharSequence>> inv_bs = Pair.<String, Function1<SPEAttribute, CharSequence>>of("^>\\d*<\\d*$", _function_7);
      final Function1<SPEAttribute, CharSequence> _function_8 = new Function1<SPEAttribute, CharSequence>() {
        public CharSequence apply(final SPEAttribute it) {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append(".has");
          String _attrName = Extentions.getAttrName(it);
          _builder.append(_attrName, "");
          _builder.append("(");
          String _operation = it.getOperation();
          String _replace = _operation.replace("<", "");
          String _replace_1 = _replace.replace(">", " - 1, ");
          _builder.append(_replace_1, "");
          _builder.append(" + 1)");
          return _builder;
        }
      };
      final Pair<String, Function1<SPEAttribute, CharSequence>> inv_sb = Pair.<String, Function1<SPEAttribute, CharSequence>>of("^<\\d*>\\d*$", _function_8);
      _xblockexpression = Collections.<Pair<String, Function1<SPEAttribute, CharSequence>>>unmodifiableList(CollectionLiterals.<Pair<String, Function1<SPEAttribute, CharSequence>>>newArrayList(equal, bEqual, nEqual, smaller, sEqual, bigger, inv_bs, inv_sb, setVal));
    }
    return _xblockexpression;
  }
  
  public MatchState getMatchState() {
    return this.matchState;
  }
}
