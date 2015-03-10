package codeGenerator;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
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
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.Functions.Function2;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.Pair;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class Generator implements IGenerator {
  private final String START_OBJECT_NAME = "this";
  
  private final static List<Pair<String, Function1<SPEAttribute, CharSequence>>> attributeMatchHandler = Generator.initAttributeHandler();
  
  private SPEGroup root;
  
  private List<SPEObject> allObjects;
  
  private Set<SPEObject> matchedObjects = new HashSet<SPEObject>();
  
  private Set<SPELink> matchedLinks = new HashSet<SPELink>();
  
  public void doGenerate(final Resource input, final IFileSystemAccess fsa) {
    TreeIterator<EObject> _allContents = input.getAllContents();
    final Function1<EObject, Boolean> _function = new Function1<EObject, Boolean>() {
      public Boolean apply(final EObject it) {
        return Boolean.valueOf((it instanceof SPEGroup));
      }
    };
    EObject _findFirst = IteratorExtensions.<EObject>findFirst(_allContents, _function);
    this.root = ((SPEGroup) _findFirst);
    boolean _notEquals = (!Objects.equal(this.root, null));
    if (_notEquals) {
      List<SPEObject> _allObjects = Generator.getAllObjects(this.root);
      this.allObjects = _allObjects;
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("MatchClass");
      String _name = this.root.getName();
      _builder.append(_name, "");
      _builder.append(".java");
      CharSequence _generateClassCode = this.generateClassCode();
      fsa.generateFile(_builder.toString(), _generateClassCode);
    }
  }
  
  public CharSequence generateClassCode() {
    CharSequence _xblockexpression = null;
    {
      final Function1<SPEObject, Boolean> _function = new Function1<SPEObject, Boolean>() {
        public Boolean apply(final SPEObject it) {
          String _name = it.getName();
          return Boolean.valueOf(Objects.equal(_name, Generator.this.START_OBJECT_NAME));
        }
      };
      final SPEObject start = IterableExtensions.<SPEObject>findFirst(this.allObjects, _function);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package matcher; ");
      _builder.newLine();
      _builder.newLine();
      _builder.append("import model.*; ");
      _builder.newLine();
      _builder.append("import model.util.*;\t");
      _builder.newLine();
      _builder.append("import de.uniks.networkparser.logic.Condition;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("@SuppressWarnings(\"all\")");
      _builder.newLine();
      _builder.append("public class MatchClass");
      String _name = this.root.getName();
      _builder.append(_name, "");
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("/**");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("* finds a match from a given start ");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("*/");
      _builder.newLine();
      _builder.append("\t");
      CharSequence _generateMatcherCode = this.generateMatcherCode(start);
      _builder.append(_generateMatcherCode, "\t");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}  ");
      _builder.newLine();
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }
  
  public CharSequence generateMatcherCode(final SPEObject start) {
    CharSequence _xblockexpression = null;
    {
      this.matchedObjects.add(start);
      final String type = start.getType();
      final String varName = Generator.varNameForObject(start);
      final String generateCreateLinksCode = this.generateLinksCode(Operations.CREATE, "Create");
      final String generateDeleteLinksCode = this.generateLinksCode(Operations.DELETE, "Delete");
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
      _builder.append(type, "\t");
      _builder.append("PO ");
      _builder.append(varName, "\t");
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
      _builder.append("//matching objects ");
      _builder.newLine();
      _builder.append("\t");
      String _generateMatchCodeForLinksToUnknownObjects = this.generateMatchCodeForLinksToUnknownObjects(start);
      _builder.append(_generateMatchCodeForLinksToUnknownObjects, "\t");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("//matching missing links to known\t\t\t\t\t");
      _builder.newLine();
      _builder.append("\t");
      EList<SPELink> _links = this.root.getLinks();
      final Function1<SPELink, Boolean> _function = new Function1<SPELink, Boolean>() {
        public Boolean apply(final SPELink it) {
          boolean _and = false;
          boolean _contains = Generator.this.matchedLinks.contains(it);
          boolean _not = (!_contains);
          if (!_not) {
            _and = false;
          } else {
            Operations _operation = it.getOperation();
            boolean _isNotCreate = Generator.this.isNotCreate(_operation);
            _and = _isNotCreate;
          }
          return Boolean.valueOf(_and);
        }
      };
      Iterable<SPELink> _filter = IterableExtensions.<SPELink>filter(_links, _function);
      final Function2<String, SPELink, String> _function_1 = new Function2<String, SPELink, String>() {
        public String apply(final String left, final SPELink it) {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append(left, "");
          CharSequence _createMatchCodeForMissingLink = Generator.createMatchCodeForMissingLink(it);
          _builder.append(_createMatchCodeForMissingLink, "");
          return _builder.toString();
        }
      };
      String _fold = IterableExtensions.<SPELink, String>fold(_filter, "", _function_1);
      _builder.append(_fold, "\t");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("//update model");
      _builder.newLine();
      _builder.append("\t");
      _builder.append(generateDeleteLinksCode, "\t");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append(generateCreateLinksCode, "\t");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      String _generateAttributesUpdateCode = this.generateAttributesUpdateCode();
      _builder.append(_generateAttributesUpdateCode, "\t");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      String _generateObjectUpdateCode = this.generateObjectUpdateCode();
      _builder.append(_generateObjectUpdateCode, "\t");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("return ");
      _builder.append(varName, "\t");
      _builder.append(".allMatches();");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }
  
  public String generateLinksCode(final Operations op, final String action) {
    EList<SPELink> _links = this.root.getLinks();
    final Function1<SPELink, Boolean> _function = new Function1<SPELink, Boolean>() {
      public Boolean apply(final SPELink it) {
        Operations _operation = it.getOperation();
        return Boolean.valueOf(Objects.equal(_operation, op));
      }
    };
    Iterable<SPELink> _filter = IterableExtensions.<SPELink>filter(_links, _function);
    StringConcatenation _builder = new StringConcatenation();
    final Function2<String, SPELink, String> _function_1 = new Function2<String, SPELink, String>() {
      public String apply(final String left, final SPELink it) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append(left, "");
        SPEObject _source = it.getSource();
        String _varNameForObject = Generator.varNameForObject(_source);
        _builder.append(_varNameForObject, "");
        _builder.append(".start");
        _builder.append(action, "");
        _builder.append("().has");
        String _Name = Generator.Name(it);
        _builder.append(_Name, "");
        _builder.append("(");
        SPEObject _target = it.getTarget();
        String _varNameForObject_1 = Generator.varNameForObject(_target);
        _builder.append(_varNameForObject_1, "");
        _builder.append(").end");
        _builder.append(action, "");
        _builder.append("();");
        return _builder.toString();
      }
    };
    return IterableExtensions.<SPELink, String>fold(_filter, _builder.toString(), _function_1);
  }
  
  public String generateObjectUpdateCode() {
    final Function1<SPEObject, Boolean> _function = new Function1<SPEObject, Boolean>() {
      public Boolean apply(final SPEObject it) {
        Operations _operation = it.getOperation();
        return Boolean.valueOf(Objects.equal(_operation, Operations.DELETE));
      }
    };
    Iterable<SPEObject> _filter = IterableExtensions.<SPEObject>filter(this.matchedObjects, _function);
    final Function2<String, SPEObject, String> _function_1 = new Function2<String, SPEObject, String>() {
      public String apply(final String left, final SPEObject it) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append(left, "");
        String _varNameForObject = Generator.varNameForObject(it);
        _builder.append(_varNameForObject, "");
        _builder.append(".destroy();");
        return _builder.toString();
      }
    };
    return IterableExtensions.<SPEObject, String>fold(_filter, "", _function_1);
  }
  
  public String generateAttributesUpdateCode() {
    String _xblockexpression = null;
    {
      StringConcatenation _builder = new StringConcatenation();
      String result = _builder.toString();
      for (final SPEObject object : this.matchedObjects) {
        {
          final String varName = Generator.varNameForObject(object);
          EList<SPEAttribute> _attributes = object.getAttributes();
          final Function1<SPEAttribute, Boolean> _function = new Function1<SPEAttribute, Boolean>() {
            public Boolean apply(final SPEAttribute it) {
              String _operation = it.getOperation();
              return Boolean.valueOf(_operation.matches("^:=.*$"));
            }
          };
          Iterable<SPEAttribute> addAttributes = IterableExtensions.<SPEAttribute>filter(_attributes, _function);
          int _size = IterableExtensions.size(addAttributes);
          boolean _greaterThan = (_size > 0);
          if (_greaterThan) {
            final Function2<String, SPEAttribute, String> _function_1 = new Function2<String, SPEAttribute, String>() {
              public String apply(final String left, final SPEAttribute it) {
                StringConcatenation _builder = new StringConcatenation();
                _builder.append(left, "");
                _builder.append(".create");
                String _attrName = Generator.getAttrName(it);
                _builder.append(_attrName, "");
                _builder.append("(");
                String _attrValue = Generator.getAttrValue(it);
                _builder.append(_attrValue, "");
                _builder.append(")");
                return _builder.toString();
              }
            };
            String createAttr = IterableExtensions.<SPEAttribute, String>fold(addAttributes, "", _function_1);
            String _result = result;
            StringConcatenation _builder_1 = new StringConcatenation();
            _builder_1.append(varName, "");
            _builder_1.append(".startCreate()");
            _builder_1.append(createAttr, "");
            _builder_1.append(".endCreate();");
            result = (_result + _builder_1);
          }
        }
      }
      _xblockexpression = result;
    }
    return _xblockexpression;
  }
  
  public String generateMatchCodeForLinksToUnknownObjects(final SPEObject object) {
    String _xblockexpression = null;
    {
      final String varName = Generator.varNameForObject(object);
      EList<SPELink> _outboundLinks = object.getOutboundLinks();
      final Function1<SPELink, Boolean> _function = new Function1<SPELink, Boolean>() {
        public Boolean apply(final SPELink it) {
          boolean _and = false;
          MatchTag _tag = it.getTag();
          boolean _notEquals = (!Objects.equal(_tag, MatchTag.NOT));
          if (!_notEquals) {
            _and = false;
          } else {
            Operations _operation = it.getOperation();
            boolean _isNotCreate = Generator.this.isNotCreate(_operation);
            _and = _isNotCreate;
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
      boolean _contains = this.matchedObjects.contains(target);
      if (_contains) {
        return "";
      }
      this.matchedObjects.add(target);
      this.matchedLinks.add(link);
      final String varName = Generator.varNameForObject(target);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append(".has");
      String _Name = Generator.Name(link);
      _builder.append(_Name, "");
      _builder.append("()");
      EList<SPEAttribute> _attributes = target.getAttributes();
      String _type = target.getType();
      String _createAttributeMatchCode = Generator.createAttributeMatchCode(_attributes, _type);
      _builder.append(_createAttributeMatchCode, "");
      String matchExpression = _builder.toString();
      String _addMatchTags = Generator.addMatchTags(matchExpression, target);
      matchExpression = _addMatchTags;
      StringConcatenation _builder_1 = new StringConcatenation();
      String _type_1 = target.getType();
      _builder_1.append(_type_1, "");
      _builder_1.append("PO ");
      _builder_1.append(varName, "");
      _builder_1.append(" = ");
      _builder_1.append(fromVarName, "");
      _builder_1.append(matchExpression, "");
      _builder_1.append(";");
      _builder_1.newLineIfNotEmpty();
      String _generateMatchCodeForLinksToUnknownObjects = this.generateMatchCodeForLinksToUnknownObjects(target);
      _builder_1.append(_generateMatchCodeForLinksToUnknownObjects, "");
      _builder_1.newLineIfNotEmpty();
      _xblockexpression = _builder_1;
    }
    return _xblockexpression;
  }
  
  public static CharSequence createMatchCodeForMissingLink(final SPELink link) {
    CharSequence _xblockexpression = null;
    {
      SPEObject _source = link.getSource();
      final String sourceVarName = Generator.varNameForObject(_source);
      SPEObject _target = link.getTarget();
      final String targetVarName = Generator.varNameForObject(_target);
      String _name = link.getName();
      String linkName = StringExtensions.toFirstUpper(_name);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append(".has");
      _builder.append(linkName, "");
      _builder.append("(");
      _builder.append(targetVarName, "");
      _builder.append(")");
      String matchCode = _builder.toString();
      String _addMatchTags = Generator.addMatchTags(matchCode, link);
      matchCode = _addMatchTags;
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append(sourceVarName, "");
      _builder_1.append(matchCode, "");
      _builder_1.append(";");
      _builder_1.newLineIfNotEmpty();
      _xblockexpression = _builder_1;
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
          String _attrName = Generator.getAttrName(it);
          _builder.append(_attrName, "");
          _builder.append("(");
          String _attrValue = Generator.getAttrValue(it);
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
          String _attrName = Generator.getAttrName(it);
          _builder.append(_attrName, "");
          _builder.append("(");
          String _attrValue = Generator.getAttrValue(it);
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
          String _attrName = Generator.getAttrName(it);
          _builder.append(_attrName, "");
          _builder.append("(");
          String _attrValue = Generator.getAttrValue(it);
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
          String _attrName = Generator.getAttrName(it);
          _builder.append(_attrName, "");
          _builder.append("(");
          _builder.append(minInt, "");
          _builder.append(", ");
          String _attrValue = Generator.getAttrValue(it);
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
          String _attrName = Generator.getAttrName(it);
          _builder.append(_attrName, "");
          _builder.append("(");
          _builder.append(minInt, "");
          _builder.append(", ");
          String _attrValue = Generator.getAttrValue(it);
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
          String _attrName = Generator.getAttrName(it);
          _builder.append(_attrName, "");
          _builder.append("(");
          String _attrValue = Generator.getAttrValue(it);
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
          String _attrName = Generator.getAttrName(it);
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
          String _attrName = Generator.getAttrName(it);
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
    String _attrValue = Generator.getAttrValue(raw);
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
  
  public static String getAttrValue(final SPEAttribute raw) {
    String _operation = raw.getOperation();
    return _operation.replaceAll("[:!=<>]", "");
  }
  
  public static String getAttrName(final SPEAttribute raw) {
    String _name = raw.getName();
    return StringExtensions.toFirstUpper(_name);
  }
  
  public static String addMatchTags(final String string, final IHasMatchTag tag) {
    MatchTag _tag = tag.getTag();
    boolean _equals = Objects.equal(_tag, MatchTag.NOT);
    if (_equals) {
      return Generator.asNAC(string);
    }
    MatchTag _tag_1 = tag.getTag();
    boolean _equals_1 = Objects.equal(_tag_1, MatchTag.OPTIONAL);
    if (_equals_1) {
      return Generator.asSubPattern(string);
    }
    return string;
  }
  
  public static String Name(final SPELink link) {
    String _name = link.getName();
    return StringExtensions.toFirstUpper(_name);
  }
  
  public static String asSubPattern(final String value) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(".startSubPattern()");
    _builder.append(value, "");
    _builder.append(".endSubPattern()");
    return _builder.toString();
  }
  
  public static String asNAC(final String value) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(".startNAC()");
    _builder.append(value, "");
    _builder.append(".endNAC()");
    return _builder.toString();
  }
  
  public boolean isNop(final Operations operations) {
    return Objects.equal(operations, Operations.NOP);
  }
  
  public boolean isNotCreate(final Operations operations) {
    return (!Objects.equal(operations, Operations.CREATE));
  }
  
  public static String varNameForObject(final SPEObject object) {
    String _name = object.getName();
    return (_name + "PO");
  }
  
  public static List<SPEObject> getAllObjects(final SPEGroup root) {
    SPEObject[] _xblockexpression = null;
    {
      EList<SPEObject> _objects = root.getObjects();
      final SPEObject[] rootObjects = ((SPEObject[])Conversions.unwrapArray(_objects, SPEObject.class)).clone();
      EList<SPEGroup> _subGroups = root.getSubGroups();
      boolean _notEquals = (!Objects.equal(_subGroups, null));
      if (_notEquals) {
        EList<SPEGroup> _subGroups_1 = root.getSubGroups();
        final Function1<SPEGroup, List<SPEObject>> _function = new Function1<SPEGroup, List<SPEObject>>() {
          public List<SPEObject> apply(final SPEGroup it) {
            return Generator.getAllObjects(it);
          }
        };
        List<List<SPEObject>> _map = ListExtensions.<SPEGroup, List<SPEObject>>map(_subGroups_1, _function);
        Iterables.<Object>addAll(((Collection<Object>)Conversions.doWrapArray(rootObjects)), _map);
      }
      _xblockexpression = rootObjects;
    }
    return (List<SPEObject>)Conversions.doWrapArray(_xblockexpression);
  }
}
