package org.uniks.spe.generator;

import com.google.common.base.Objects;
import java.util.List;
import model.Operations;
import model.SPEAttribute;
import model.SPEGroup;
import model.SPELink;
import model.SPEObject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.Functions.Function2;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.uniks.spe.generator.model.MatchState;
import org.uniks.spe.generator.utils.CodeSnippets;
import org.uniks.spe.generator.utils.Extentions;

@SuppressWarnings("all")
public class ModelUpdateGenerator {
  public CharSequence generateModelUpdateCode(final MatchState matchState) {
    CharSequence _xblockexpression = null;
    {
      SPEGroup _root = matchState.getRoot();
      final String generateCreateLinksCode = ModelUpdateGenerator.generateLinksCode(Operations.CREATE, "Create", _root);
      SPEGroup _root_1 = matchState.getRoot();
      final String generateDeleteLinksCode = ModelUpdateGenerator.generateLinksCode(Operations.DELETE, "Destroy", _root_1);
      final List<SPEObject> allObjects = matchState.getAllObjects();
      StringConcatenation _builder = new StringConcatenation();
      _builder.append(generateDeleteLinksCode, "");
      _builder.newLineIfNotEmpty();
      String _generateDeleteObjectCode = ModelUpdateGenerator.generateDeleteObjectCode(allObjects);
      _builder.append(_generateDeleteObjectCode, "");
      _builder.newLineIfNotEmpty();
      String _generateCreateObjectCode = ModelUpdateGenerator.generateCreateObjectCode(allObjects);
      _builder.append(_generateCreateObjectCode, "");
      _builder.newLineIfNotEmpty();
      _builder.append(generateCreateLinksCode, "");
      _builder.newLineIfNotEmpty();
      String _generateAttributesUpdateCode = ModelUpdateGenerator.generateAttributesUpdateCode(allObjects);
      _builder.append(_generateAttributesUpdateCode, "");
      _builder.newLineIfNotEmpty();
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }
  
  private static String generateLinksCode(final Operations op, final String action, final SPEGroup root) {
    EList<SPELink> _links = root.getLinks();
    final Function1<SPELink, Boolean> _function = new Function1<SPELink, Boolean>() {
      public Boolean apply(final SPELink it) {
        Operations _operation = it.getOperation();
        return Boolean.valueOf(Objects.equal(_operation, op));
      }
    };
    Iterable<SPELink> _filter = IterableExtensions.<SPELink>filter(_links, _function);
    StringConcatenation _builder = new StringConcatenation();
    final Function2<String, SPELink, String> _function_1 = new Function2<String, SPELink, String>() {
      public String apply(final String _, final SPELink it) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append(_, "");
        SPEObject _source = it.getSource();
        String _varName = Extentions.varName(_source);
        _builder.append(_varName, "");
        _builder.append(".start");
        _builder.append(action, "");
        _builder.append("()");
        CharSequence _hasLinkToObj = CodeSnippets.hasLinkToObj(it);
        _builder.append(_hasLinkToObj, "");
        _builder.append(".end");
        _builder.append(action, "");
        _builder.append("();");
        return _builder.toString();
      }
    };
    return IterableExtensions.<SPELink, String>fold(_filter, _builder.toString(), _function_1);
  }
  
  private static String generateDeleteObjectCode(final List<SPEObject> allObjects) {
    final Function1<SPEObject, Boolean> _function = new Function1<SPEObject, Boolean>() {
      public Boolean apply(final SPEObject it) {
        Operations _operation = it.getOperation();
        return Boolean.valueOf(Objects.equal(_operation, Operations.DELETE));
      }
    };
    Iterable<SPEObject> _filter = IterableExtensions.<SPEObject>filter(allObjects, _function);
    final Function2<String, SPEObject, String> _function_1 = new Function2<String, SPEObject, String>() {
      public String apply(final String _, final SPEObject it) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append(_, "");
        String _varName = Extentions.varName(it);
        _builder.append(_varName, "");
        _builder.append(".destroy();");
        return _builder.toString();
      }
    };
    return IterableExtensions.<SPEObject, String>fold(_filter, "", _function_1);
  }
  
  private static String generateCreateObjectCode(final List<SPEObject> allObjects) {
    final Function1<SPEObject, Boolean> _function = new Function1<SPEObject, Boolean>() {
      public Boolean apply(final SPEObject it) {
        Operations _operation = it.getOperation();
        return Boolean.valueOf(Objects.equal(_operation, Operations.CREATE));
      }
    };
    Iterable<SPEObject> _filter = IterableExtensions.<SPEObject>filter(allObjects, _function);
    final Function2<String, SPEObject, String> _function_1 = new Function2<String, SPEObject, String>() {
      public String apply(final String _, final SPEObject it) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append(_, "");
        CharSequence _declarePO = CodeSnippets.declarePO(it);
        _builder.append(_declarePO, "");
        _builder.append(" = ");
        CharSequence _createPO = CodeSnippets.createPO(it);
        _builder.append(_createPO, "");
        _builder.append(";");
        return _builder.toString();
      }
    };
    return IterableExtensions.<SPEObject, String>fold(_filter, "", _function_1);
  }
  
  private static String generateAttributesUpdateCode(final List<SPEObject> allObjects) {
    String _xblockexpression = null;
    {
      StringConcatenation _builder = new StringConcatenation();
      String result = _builder.toString();
      final Function1<SPEObject, Boolean> _function = new Function1<SPEObject, Boolean>() {
        public Boolean apply(final SPEObject it) {
          Operations _operation = it.getOperation();
          return Boolean.valueOf((!Objects.equal(_operation, Operations.DELETE)));
        }
      };
      Iterable<SPEObject> _filter = IterableExtensions.<SPEObject>filter(allObjects, _function);
      for (final SPEObject object : _filter) {
        {
          final String varName = Extentions.varName(object);
          EList<SPEAttribute> _attributes = object.getAttributes();
          final Function1<SPEAttribute, Boolean> _function_1 = new Function1<SPEAttribute, Boolean>() {
            public Boolean apply(final SPEAttribute it) {
              String _operation = it.getOperation();
              return Boolean.valueOf(_operation.matches("^:=.*$"));
            }
          };
          Iterable<SPEAttribute> addAttributes = IterableExtensions.<SPEAttribute>filter(_attributes, _function_1);
          int _size = IterableExtensions.size(addAttributes);
          boolean _greaterThan = (_size > 0);
          if (_greaterThan) {
            final Function2<String, SPEAttribute, String> _function_2 = new Function2<String, SPEAttribute, String>() {
              public String apply(final String _, final SPEAttribute it) {
                StringConcatenation _builder = new StringConcatenation();
                _builder.append(_, "");
                _builder.append(".create");
                String _attrName = Extentions.getAttrName(it);
                _builder.append(_attrName, "");
                _builder.append("(");
                String _attrValue = Extentions.getAttrValue(it);
                _builder.append(_attrValue, "");
                _builder.append(")");
                return _builder.toString();
              }
            };
            String createAttr = IterableExtensions.<SPEAttribute, String>fold(addAttributes, "", _function_2);
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
}
