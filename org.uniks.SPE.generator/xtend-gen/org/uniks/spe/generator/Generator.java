package org.uniks.spe.generator;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import model.IHasMatchTag;
import model.MatchTag;
import model.Operations;
import model.SPEAttribute;
import model.SPEGroup;
import model.SPELink;
import model.SPEObject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.xbase.lib.CollectionExtensions;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.Functions.Function2;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.Pair;
import org.uniks.spe.generator.ModelUpdateGenerator;
import org.uniks.spe.generator.model.EntryPoint;
import org.uniks.spe.generator.model.MatchState;
import org.uniks.spe.generator.utils.CodeSnippets;
import org.uniks.spe.generator.utils.Extentions;

@SuppressWarnings("all")
public class Generator implements IGenerator {
  private final String START_OBJECT_NAME = "this";
  
  private final static List<Pair<String, Function1<SPEAttribute, CharSequence>>> attributeMatchHandler = Generator.initAttributeHandler();
  
  private final ModelUpdateGenerator modelUpdater = new ModelUpdateGenerator();
  
  private MatchState matchState;
  
  public void doGenerate(final Resource input, final IFileSystemAccess fsa) {
    MatchState _matchState = new MatchState();
    this.matchState = _matchState;
    TreeIterator<EObject> _allContents = input.getAllContents();
    final Function1<EObject, Boolean> _function = new Function1<EObject, Boolean>() {
      public Boolean apply(final EObject it) {
        return Boolean.valueOf((it instanceof SPEGroup));
      }
    };
    EObject _findFirst = IteratorExtensions.<EObject>findFirst(_allContents, _function);
    this.matchState.setRoot(((SPEGroup) _findFirst));
    SPEGroup _root = this.matchState.getRoot();
    boolean _equals = Objects.equal(_root, null);
    if (_equals) {
      return;
    }
    SPEGroup _root_1 = this.matchState.getRoot();
    List<SPEObject> _allObjects = Generator.getAllObjects(_root_1);
    this.matchState.setAllObjects(_allObjects);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("MatchClass");
    SPEGroup _root_2 = this.matchState.getRoot();
    String _name = _root_2.getName();
    _builder.append(_name, "");
    _builder.append(".java");
    CharSequence _generateClassCode = this.generateClassCode();
    fsa.generateFile(_builder.toString(), _generateClassCode);
  }
  
  public CharSequence generateClassCode() {
    CharSequence _xblockexpression = null;
    {
      List<SPEObject> _allObjects = this.matchState.getAllObjects();
      final Function1<SPEObject, Boolean> _function = new Function1<SPEObject, Boolean>() {
        public Boolean apply(final SPEObject it) {
          String _name = it.getName();
          return Boolean.valueOf(Objects.equal(_name, Generator.this.START_OBJECT_NAME));
        }
      };
      final SPEObject start = IterableExtensions.<SPEObject>findFirst(_allObjects, _function);
      String matchCode = "//invalidDiagram";
      boolean _notEquals = (!Objects.equal(start, null));
      if (_notEquals) {
        StringConcatenation _builder = new StringConcatenation();
        CharSequence _generateMatcherCode = this.generateMatcherCode(start);
        _builder.append(_generateMatcherCode, "");
        matchCode = _builder.toString();
      }
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("package matcher; ");
      _builder_1.newLine();
      _builder_1.newLine();
      _builder_1.append("import model.*; ");
      _builder_1.newLine();
      _builder_1.append("import model.util.*;\t");
      _builder_1.newLine();
      _builder_1.append("import de.uniks.networkparser.logic.Condition;");
      _builder_1.newLine();
      _builder_1.newLine();
      _builder_1.append("@SuppressWarnings(\"all\")");
      _builder_1.newLine();
      _builder_1.append("public class MatchClass");
      SPEGroup _root = this.matchState.getRoot();
      String _name = _root.getName();
      _builder_1.append(_name, "");
      _builder_1.append(" {");
      _builder_1.newLineIfNotEmpty();
      _builder_1.append("\t");
      _builder_1.newLine();
      _builder_1.append("\t");
      _builder_1.append("/**");
      _builder_1.newLine();
      _builder_1.append("\t");
      _builder_1.append("* finds a match from a given start ");
      _builder_1.newLine();
      _builder_1.append("\t");
      _builder_1.append("*/");
      _builder_1.newLine();
      _builder_1.append("\t");
      _builder_1.append(matchCode, "\t");
      _builder_1.newLineIfNotEmpty();
      _builder_1.append("\t");
      _builder_1.newLine();
      _builder_1.append("}");
      _builder_1.newLine();
      _xblockexpression = _builder_1;
    }
    return _xblockexpression;
  }
  
  public CharSequence generateMatcherCode(final SPEObject start) {
    CharSequence _xblockexpression = null;
    {
      this.matchState.markAsMatched(start);
      final String type = start.getType();
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("public ");
      _builder.append(type, "");
      _builder.append("Set findMatch(");
      _builder.append(type, "");
      _builder.append(" start){\t\t\t\t\t");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append(type, "\t");
      _builder.append("Set startSet = new ");
      _builder.append(type, "\t");
      _builder.append("Set().with(start);");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      CharSequence _declarePO = CodeSnippets.declarePO(start);
      _builder.append(_declarePO, "\t");
      _builder.append(" = startSet.has");
      _builder.append(type, "\t");
      _builder.append("PO()");
      EList<SPEAttribute> _attributes = start.getAttributes();
      String _createAttributeMatchCode = Generator.createAttributeMatchCode(_attributes, type);
      _builder.append(_createAttributeMatchCode, "\t");
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("//matching objects of root grp");
      _builder.newLine();
      _builder.append("\t");
      String _generateMatchCodeForNonAlienObjects = this.generateMatchCodeForNonAlienObjects(start);
      _builder.append(_generateMatchCodeForNonAlienObjects, "\t");
      _builder.append(" ");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("//match objects of subgroups");
      _builder.newLine();
      _builder.append("\t");
      SPEGroup _root = this.matchState.getRoot();
      EList<SPEGroup> _subGroups = _root.getSubGroups();
      final Function2<String, SPEGroup, String> _function = new Function2<String, SPEGroup, String>() {
        public String apply(final String left, final SPEGroup it) {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append(left, "");
          CharSequence _createCodeForSubgroupMatching = Generator.this.createCodeForSubgroupMatching(it);
          _builder.append(_createCodeForSubgroupMatching, "");
          return _builder.toString();
        }
      };
      String _fold = IterableExtensions.<SPEGroup, String>fold(_subGroups, "", _function);
      _builder.append(_fold, "\t");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("//matching missing links to known\t\t\t\t\t");
      _builder.newLine();
      _builder.append("\t");
      SPEGroup _root_1 = this.matchState.getRoot();
      EList<SPELink> _links = _root_1.getLinks();
      final Function1<SPELink, Boolean> _function_1 = new Function1<SPELink, Boolean>() {
        public Boolean apply(final SPELink it) {
          boolean _and = false;
          boolean _isMatched = Generator.this.matchState.isMatched(it);
          boolean _not = (!_isMatched);
          if (!_not) {
            _and = false;
          } else {
            Operations _operation = it.getOperation();
            boolean _isNotCreate = Extentions.isNotCreate(_operation);
            _and = _isNotCreate;
          }
          return Boolean.valueOf(_and);
        }
      };
      Iterable<SPELink> _filter = IterableExtensions.<SPELink>filter(_links, _function_1);
      final Function2<String, SPELink, String> _function_2 = new Function2<String, SPELink, String>() {
        public String apply(final String left, final SPELink it) {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append(left, "");
          CharSequence _createMatchCodeForMissingLink = Generator.this.createMatchCodeForMissingLink(it);
          _builder.append(_createMatchCodeForMissingLink, "");
          return _builder.toString();
        }
      };
      String _fold_1 = IterableExtensions.<SPELink, String>fold(_filter, "", _function_2);
      _builder.append(_fold_1, "\t");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("//update model ");
      _builder.newLine();
      _builder.append("\t");
      CharSequence _generateModelUpdateCode = this.modelUpdater.generateModelUpdateCode(this.matchState);
      _builder.append(_generateModelUpdateCode, "\t");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("return ");
      String _varName = Extentions.varName(start);
      _builder.append(_varName, "\t");
      _builder.append(".allMatches();");
      _builder.newLineIfNotEmpty();
      _builder.append("} ");
      _builder.newLine();
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }
  
  public String generateMatchCodeForNonAlienObjects(final SPEObject object) {
    String _xblockexpression = null;
    {
      final String varName = Extentions.varName(object);
      final SPEGroup groupOfObject = this.getGroup(object);
      EList<SPELink> _outboundLinks = object.getOutboundLinks();
      final Function1<SPELink, Boolean> _function = new Function1<SPELink, Boolean>() {
        public Boolean apply(final SPELink it) {
          boolean _and = false;
          boolean _and_1 = false;
          MatchTag _tag = it.getTag();
          boolean _isntNot = Extentions.getIsntNot(_tag);
          if (!_isntNot) {
            _and_1 = false;
          } else {
            Operations _operation = it.getOperation();
            boolean _isNotCreate = Extentions.isNotCreate(_operation);
            _and_1 = _isNotCreate;
          }
          if (!_and_1) {
            _and = false;
          } else {
            SPEObject _target = it.getTarget();
            SPEGroup _group = Generator.this.getGroup(_target);
            boolean _equals = Objects.equal(groupOfObject, _group);
            _and = _equals;
          }
          return Boolean.valueOf(_and);
        }
      };
      Iterable<SPELink> _filter = IterableExtensions.<SPELink>filter(_outboundLinks, _function);
      final Function2<String, SPELink, String> _function_1 = new Function2<String, SPELink, String>() {
        public String apply(final String left, final SPELink it) {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append(left, "");
          CharSequence _createMatchingCodeForLinkedObjects = Generator.this.createMatchingCodeForLinkedObjects(it, varName);
          _builder.append(_createMatchingCodeForLinkedObjects, "");
          return _builder.toString();
        }
      };
      _xblockexpression = IterableExtensions.<SPELink, String>fold(_filter, "", _function_1);
    }
    return _xblockexpression;
  }
  
  public CharSequence createMatchingCodeForLinkedObjects(final SPELink link, final String fromVarName) {
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
      String _createAttributeMatchCode = Generator.createAttributeMatchCode(_attributes, _type);
      _builder.append(_createAttributeMatchCode, "");
      String matchExpression = _builder.toString();
      CharSequence matchExp = Generator.addMatchTags(matchExpression, target);
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
  
  public CharSequence createCodeForSubgroupMatching(final SPEGroup group) {
    CharSequence _xblockexpression = null;
    {
      ArrayList<SPELink> alienLinks = this.findAlienLinks(group);
      int _size = alienLinks.size();
      boolean _equals = (_size == 0);
      if (_equals) {
        StringConcatenation _builder = new StringConcatenation();
        return _builder.toString();
      }
      SPELink link = alienLinks.get(0);
      EntryPoint entryPoint = this.extractEntryPoint(link, group);
      this.matchState.markAsMatched(link);
      alienLinks.remove(link);
      StringConcatenation _builder_1 = new StringConcatenation();
      SPEObject _alien = entryPoint.getAlien();
      String _varName = Extentions.varName(_alien);
      _builder_1.append(_varName, "");
      _builder_1.append(".start");
      String _sDMLibMatchTag = Extentions.toSDMLibMatchTag(group);
      _builder_1.append(_sDMLibMatchTag, "");
      _builder_1.append("();");
      _builder_1.newLineIfNotEmpty();
      CharSequence _entrySourceCode = entryPoint.getEntrySourceCode();
      _builder_1.append(_entrySourceCode, "");
      _builder_1.newLineIfNotEmpty();
      SPEObject _start = entryPoint.getStart();
      String _generateMatchCodeForNonAlienObjects = this.generateMatchCodeForNonAlienObjects(_start);
      _builder_1.append(_generateMatchCodeForNonAlienObjects, "");
      _builder_1.newLineIfNotEmpty();
      final Function2<String, SPELink, String> _function = new Function2<String, SPELink, String>() {
        public String apply(final String left, final SPELink it) {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append(left, "");
          CharSequence _createMatchCodeForMissingLink = Generator.this.createMatchCodeForMissingLink(it);
          _builder.append(_createMatchCodeForMissingLink, "");
          return _builder.toString();
        }
      };
      String _fold = IterableExtensions.<SPELink, String>fold(alienLinks, "", _function);
      _builder_1.append(_fold, "");
      _builder_1.newLineIfNotEmpty();
      SPEObject _alien_1 = entryPoint.getAlien();
      String _varName_1 = Extentions.varName(_alien_1);
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
  
  public ArrayList<SPELink> findAlienLinks(final SPEGroup group) {
    ArrayList<SPELink> alienLinks = CollectionLiterals.<SPELink>newArrayList();
    Set<SPEObject> _matchedObjects = this.matchState.getMatchedObjects();
    for (final SPEObject object : _matchedObjects) {
      {
        EList<SPELink> _outboundLinks = object.getOutboundLinks();
        final Function1<SPELink, Boolean> _function = new Function1<SPELink, Boolean>() {
          public Boolean apply(final SPELink it) {
            SPEObject _target = it.getTarget();
            SPEGroup _group = Generator.this.getGroup(_target);
            return Boolean.valueOf(Objects.equal(group, _group));
          }
        };
        Iterable<SPELink> _filter = IterableExtensions.<SPELink>filter(_outboundLinks, _function);
        Iterables.<SPELink>addAll(alienLinks, _filter);
        EList<SPELink> _inboundLinks = object.getInboundLinks();
        final Function1<SPELink, Boolean> _function_1 = new Function1<SPELink, Boolean>() {
          public Boolean apply(final SPELink it) {
            SPEObject _source = it.getSource();
            SPEGroup _group = Generator.this.getGroup(_source);
            return Boolean.valueOf(Objects.equal(group, _group));
          }
        };
        Iterable<SPELink> _filter_1 = IterableExtensions.<SPELink>filter(_inboundLinks, _function_1);
        Iterables.<SPELink>addAll(alienLinks, _filter_1);
      }
    }
    return alienLinks;
  }
  
  public EntryPoint extractEntryPoint(final SPELink link, final SPEGroup grp) {
    EntryPoint result = new EntryPoint();
    SPEObject _target = link.getTarget();
    SPEGroup _group = this.getGroup(_target);
    boolean _equals = Objects.equal(_group, grp);
    if (_equals) {
      SPEObject _source = link.getSource();
      result.setAlien(_source);
      SPEObject _target_1 = link.getTarget();
      result.setStart(_target_1);
      SPEObject _start = result.getStart();
      String type = _start.getType();
      SPEObject _start_1 = result.getStart();
      EList<SPEAttribute> attr = _start_1.getAttributes();
      StringConcatenation _builder = new StringConcatenation();
      SPEObject _start_2 = result.getStart();
      CharSequence _declarePO = CodeSnippets.declarePO(_start_2);
      _builder.append(_declarePO, "");
      _builder.append(" = ");
      SPEObject _alien = result.getAlien();
      String _varName = Extentions.varName(_alien);
      _builder.append(_varName, "");
      _builder.append(".has");
      String _Name = Extentions.Name(link);
      _builder.append(_Name, "");
      _builder.append("()");
      String _createAttributeMatchCode = Generator.createAttributeMatchCode(attr, type);
      _builder.append(_createAttributeMatchCode, "");
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      result.setEntrySourceCode(_builder);
    } else {
      SPEObject _target_2 = link.getTarget();
      result.setAlien(_target_2);
      SPEObject _source_1 = link.getSource();
      result.setStart(_source_1);
      SPEObject _start_3 = result.getStart();
      String type_1 = _start_3.getType();
      SPEObject _start_4 = result.getStart();
      EList<SPEAttribute> attr_1 = _start_4.getAttributes();
      StringConcatenation _builder_1 = new StringConcatenation();
      SPEObject _start_5 = result.getStart();
      CharSequence _declarePO_1 = CodeSnippets.declarePO(_start_5);
      _builder_1.append(_declarePO_1, "");
      _builder_1.append(" = ");
      SPEObject _start_6 = result.getStart();
      CharSequence _createPO = CodeSnippets.createPO(_start_6);
      _builder_1.append(_createPO, "");
      String _createAttributeMatchCode_1 = Generator.createAttributeMatchCode(attr_1, type_1);
      _builder_1.append(_createAttributeMatchCode_1, "");
      _builder_1.append(";");
      _builder_1.newLineIfNotEmpty();
      SPEObject _start_7 = result.getStart();
      String _varName_1 = Extentions.varName(_start_7);
      _builder_1.append(_varName_1, "");
      CharSequence _hasLinkToObj = CodeSnippets.hasLinkToObj(link);
      _builder_1.append(_hasLinkToObj, "");
      _builder_1.append("; ");
      _builder_1.newLineIfNotEmpty();
      result.setEntrySourceCode(_builder_1);
    }
    return result;
  }
  
  public CharSequence createMatchCodeForMissingLink(final SPELink link) {
    CharSequence _xblockexpression = null;
    {
      this.matchState.markAsMatched(link);
      StringConcatenation _builder = new StringConcatenation();
      SPEObject _source = link.getSource();
      String _varName = Extentions.varName(_source);
      _builder.append(_varName, "");
      CharSequence _hasLinkToObj = CodeSnippets.hasLinkToObj(link);
      CharSequence _addMatchTags = Generator.addMatchTags(_hasLinkToObj, link);
      _builder.append(_addMatchTags, "");
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }
  
  public static String createAttributeMatchCode(final List<SPEAttribute> attribute, final String type) {
    final Function2<String, SPEAttribute, String> _function = new Function2<String, SPEAttribute, String>() {
      public String apply(final String left, final SPEAttribute it) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append(left, "");
        CharSequence _createAttributeMethodCall = Generator.createAttributeMethodCall(it, type);
        _builder.append(_createAttributeMethodCall, "");
        return _builder.toString();
      }
    };
    return IterableExtensions.<SPEAttribute, String>fold(attribute, "", _function);
  }
  
  public static CharSequence createAttributeMethodCall(final SPEAttribute attribute, final String type) {
    CharSequence _xblockexpression = null;
    {
      final Function1<Pair<String, Function1<SPEAttribute, CharSequence>>, Boolean> _function = new Function1<Pair<String, Function1<SPEAttribute, CharSequence>>, Boolean>() {
        public Boolean apply(final Pair<String, Function1<SPEAttribute, CharSequence>> it) {
          String _operation = attribute.getOperation();
          String _key = it.getKey();
          return Boolean.valueOf(_operation.matches(_key));
        }
      };
      Pair<String, Function1<SPEAttribute, CharSequence>> _findFirst = IterableExtensions.<Pair<String, Function1<SPEAttribute, CharSequence>>>findFirst(Generator.attributeMatchHandler, _function);
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
        CharSequence _createRegExCondition = Generator.createRegExCondition(attribute, type);
        call = _createRegExCondition;
      }
      _xblockexpression = call;
    }
    return _xblockexpression;
  }
  
  public static List<Pair<String, Function1<SPEAttribute, CharSequence>>> initAttributeHandler() {
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
  
  public static CharSequence createRegExCondition(final SPEAttribute raw, final String type) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(".has(new Condition<Object>() {              ");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("public boolean check(Object value) {");
    _builder.newLine();
    _builder.append("                ");
    _builder.append("if(value instanceof ");
    _builder.append(type, "                ");
    _builder.append("){");
    _builder.newLineIfNotEmpty();
    _builder.append("                    ");
    _builder.append("boolean matches = ((");
    _builder.append(type, "                    ");
    _builder.append(")value).getName().matches(\"");
    String _attrValue = Extentions.getAttrValue(raw);
    _builder.append(_attrValue, "                    ");
    _builder.append("\");");
    _builder.newLineIfNotEmpty();
    _builder.append("                    ");
    _builder.append("return matches;");
    _builder.newLine();
    _builder.append("                ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("                ");
    _builder.append("return false;   ");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("}   ");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("})");
    return _builder;
  }
  
  public SPEGroup getGroup(final SPEObject object) {
    SPEGroup _root = this.matchState.getRoot();
    EList<SPEObject> _objects = _root.getObjects();
    boolean _contains = _objects.contains(object);
    if (_contains) {
      return this.matchState.getRoot();
    } else {
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
  }
  
  public static CharSequence addMatchTags(final CharSequence string, final IHasMatchTag tag) {
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
  
  public static List<SPEObject> getAllObjects(final SPEGroup root) {
    List<SPEObject> _xblockexpression = null;
    {
      EList<SPEObject> _objects = root.getObjects();
      SPEObject[] rootObjects = ((SPEObject[])Conversions.unwrapArray(_objects, SPEObject.class)).clone();
      EList<SPEGroup> _subGroups = root.getSubGroups();
      boolean _equals = Objects.equal(_subGroups, null);
      if (_equals) {
        return (List<SPEObject>)Conversions.doWrapArray(rootObjects);
      }
      EList<SPEGroup> _subGroups_1 = root.getSubGroups();
      final Function1<SPEGroup, EList<SPEObject>> _function = new Function1<SPEGroup, EList<SPEObject>>() {
        public EList<SPEObject> apply(final SPEGroup it) {
          return it.getObjects();
        }
      };
      List<EList<SPEObject>> _map = ListExtensions.<SPEGroup, EList<SPEObject>>map(_subGroups_1, _function);
      Iterable<SPEObject> _flatten = Iterables.<SPEObject>concat(_map);
      List<SPEObject> tmp = IterableExtensions.<SPEObject>toList(_flatten);
      CollectionExtensions.<SPEObject>addAll(tmp, rootObjects);
      _xblockexpression = tmp;
    }
    return _xblockexpression;
  }
}
