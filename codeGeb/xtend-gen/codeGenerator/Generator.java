package codeGenerator;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.Functions.Function2;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class Generator implements IGenerator {
  public static String START_OBJECT_NAME = "this";
  
  private SPEGroup root;
  
  private Set<SPEObject> matchedObjects = new HashSet<SPEObject>();
  
  private Set<SPELink> matchedLinks = new HashSet<SPELink>();
  
  public void doGenerate(final Resource input, final IFileSystemAccess fsa) {
    TreeIterator<EObject> _allContents = input.getAllContents();
    Iterable<EObject> _iterable = IteratorExtensions.<EObject>toIterable(_allContents);
    Iterable<SPEGroup> _filter = Iterables.<SPEGroup>filter(_iterable, SPEGroup.class);
    final List<SPEGroup> rootGrps = IterableExtensions.<SPEGroup>toList(_filter);
    int _size = rootGrps.size();
    boolean _notEquals = (_size != 1);
    if (_notEquals) {
      return;
    }
    SPEGroup _get = rootGrps.get(0);
    this.root = _get;
    List<SPEObject> _allObjects = Generator.getAllObjects(this.root);
    final Function1<SPEObject, Boolean> _function = new Function1<SPEObject, Boolean>() {
      public Boolean apply(final SPEObject it) {
        String _name = it.getName();
        return Boolean.valueOf(_name.equals(Generator.START_OBJECT_NAME));
      }
    };
    final SPEObject start = IterableExtensions.<SPEObject>findFirst(_allObjects, _function);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("MatchClass");
    String _name = this.root.getName();
    _builder.append(_name, "");
    _builder.append(".java");
    String _generateMatcherCode = this.generateMatcherCode(start);
    fsa.generateFile(_builder.toString(), _generateMatcherCode);
  }
  
  public String generateMatcherCode(final SPEObject start) {
    this.matchedObjects.add(start);
    final String type = start.getType();
    final String varName = Generator.varNameForObject(start);
    EList<SPELink> _outboundLinks = start.getOutboundLinks();
    final Function2<String, SPELink, String> _function = new Function2<String, SPELink, String>() {
      public String apply(final String value, final SPELink it) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append(value, "");
        CharSequence _createMatchCodeLinks = Generator.this.createMatchCodeLinks(it, varName);
        _builder.append(_createMatchCodeLinks, "");
        return _builder.toString();
      }
    };
    final String codeForAllObjects = IterableExtensions.<SPELink, String>fold(_outboundLinks, "", _function);
    EList<SPELink> _links = this.root.getLinks();
    final Function1<SPELink, Boolean> _function_1 = new Function1<SPELink, Boolean>() {
      public Boolean apply(final SPELink it) {
        boolean _contains = Generator.this.matchedLinks.contains(it);
        return Boolean.valueOf((!_contains));
      }
    };
    Iterable<SPELink> _filter = IterableExtensions.<SPELink>filter(_links, _function_1);
    final Function2<String, SPELink, String> _function_2 = new Function2<String, SPELink, String>() {
      public String apply(final String value, final SPELink it) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append(value, "");
        String _createMatchCodeForMissingLink = Generator.createMatchCodeForMissingLink(it);
        _builder.append(_createMatchCodeForMissingLink, "");
        return _builder.toString();
      }
    };
    final String codeForMissingLinks = IterableExtensions.<SPELink, String>fold(_filter, "", _function_2);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package matcher;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import model.*; ");
    _builder.newLine();
    _builder.append("import model.util.*;");
    _builder.newLine();
    _builder.append(" ");
    _builder.newLine();
    _builder.append("public class MatchClass");
    String _name = this.root.getName();
    _builder.append(_name, "");
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ");
    _builder.append(type, "\t");
    _builder.append("Set findMatch(");
    _builder.append(type, "\t");
    _builder.append(" start){");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append(type, "\t\t");
    _builder.append("Set startSet = new ");
    _builder.append(type, "\t\t");
    _builder.append("Set().with(start);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append(type, "\t\t");
    _builder.append("PO ");
    _builder.append(varName, "\t\t");
    _builder.append(" = startSet.has");
    _builder.append(type, "\t\t");
    _builder.append("PO()");
    EList<SPEAttribute> _attributes = start.getAttributes();
    String _createAttributeMatchCode = Generator.createAttributeMatchCode(_attributes);
    _builder.append(_createAttributeMatchCode, "\t\t");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append(codeForAllObjects, "\t\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append(codeForMissingLinks, "\t\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("return ");
    _builder.append(varName, "\t\t");
    _builder.append(".allMatches();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}  ");
    _builder.newLine();
    return _builder.toString();
  }
  
  public CharSequence createMatchCodeLinks(final SPELink link, final String fromVarName) {
    final SPEObject target = link.getTarget();
    boolean _contains = this.matchedObjects.contains(target);
    if (_contains) {
      return "";
    }
    this.matchedObjects.add(target);
    this.matchedLinks.add(link);
    final String varName = Generator.varNameForObject(target);
    String _name = link.getName();
    final String linkName = StringExtensions.toFirstUpper(_name);
    StringConcatenation _builder = new StringConcatenation();
    String _type = target.getType();
    _builder.append(_type, "");
    _builder.append("PO ");
    _builder.append(varName, "");
    _builder.append(" = ");
    _builder.append(fromVarName, "");
    _builder.append(".has");
    _builder.append(linkName, "");
    _builder.append("()");
    EList<SPEAttribute> _attributes = target.getAttributes();
    String _createAttributeMatchCode = Generator.createAttributeMatchCode(_attributes);
    _builder.append(_createAttributeMatchCode, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    final String codeForLink = _builder.toString();
    EList<SPELink> _outboundLinks = target.getOutboundLinks();
    final Function2<String, SPELink, String> _function = new Function2<String, SPELink, String>() {
      public String apply(final String value, final SPELink it) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append(value, "");
        CharSequence _createMatchCodeLinks = Generator.this.createMatchCodeLinks(it, varName);
        _builder.append(_createMatchCodeLinks, "");
        return _builder.toString();
      }
    };
    return IterableExtensions.<SPELink, String>fold(_outboundLinks, codeForLink, _function);
  }
  
  public static String createMatchCodeForMissingLink(final SPELink link) {
    SPEObject _source = link.getSource();
    final String sourceVarName = Generator.varNameForObject(_source);
    SPEObject _target = link.getTarget();
    final String targetVarName = Generator.varNameForObject(_target);
    String _name = link.getName();
    String linkName = StringExtensions.toFirstUpper(_name);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(sourceVarName, "");
    _builder.append(".has");
    _builder.append(linkName, "");
    _builder.append("(");
    _builder.append(targetVarName, "");
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  public static String createAttributeMatchCode(final List<SPEAttribute> attribute) {
    final Function2<String, SPEAttribute, String> _function = new Function2<String, SPEAttribute, String>() {
      public String apply(final String value, final SPEAttribute it) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append(value, "");
        _builder.append(".has");
        String _name = it.getName();
        String _firstUpper = StringExtensions.toFirstUpper(_name);
        _builder.append(_firstUpper, "");
        _builder.append("(");
        String _createAttributeMethodCall = Generator.createAttributeMethodCall(it);
        _builder.append(_createAttributeMethodCall, "");
        _builder.append(")");
        return _builder.toString();
      }
    };
    return IterableExtensions.<SPEAttribute, String>fold(attribute, "", _function);
  }
  
  public static String createAttributeMethodCall(final SPEAttribute attribute) {
    final String opString = attribute.getOperation();
    boolean _startsWith = opString.startsWith("==");
    if (_startsWith) {
      return opString.replace("==", "");
    }
    return "";
  }
  
  public static String varNameForObject(final SPEObject object) {
    String _name = object.getName();
    String _plus = (_name + "_");
    int _hashCode = object.hashCode();
    return (_plus + Integer.valueOf(_hashCode));
  }
  
  public static List<SPEObject> getAllObjects(final SPEGroup root) {
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
    return (List<SPEObject>)Conversions.doWrapArray(rootObjects);
  }
}
