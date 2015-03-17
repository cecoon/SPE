package org.uniks.spe.generator;

import com.google.common.base.Objects;
import model.SPEAttribute;
import model.SPEGroup;
import model.SPEObject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.uniks.spe.generator.ModelMatchGenerator;
import org.uniks.spe.generator.ModelUpdateGenerator;
import org.uniks.spe.generator.model.MatchState;
import org.uniks.spe.generator.utils.CodeSnippets;
import org.uniks.spe.generator.utils.Extentions;

@SuppressWarnings("all")
public class Generator implements IGenerator {
  private final ModelUpdateGenerator modelUpdater = new ModelUpdateGenerator();
  
  public void doGenerate(final Resource input, final IFileSystemAccess fsa) {
    final MatchState matchState = new MatchState();
    matchState.init(input);
    boolean _isValid = matchState.isValid();
    if (_isValid) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("MatchClass");
      SPEGroup _root = matchState.getRoot();
      String _name = _root.getName();
      _builder.append(_name, "");
      _builder.append(".java");
      CharSequence _generateClassCode = this.generateClassCode(matchState);
      fsa.generateFile(_builder.toString(), _generateClassCode);
    }
  }
  
  public CharSequence generateClassCode(final MatchState matchState) {
    CharSequence _xblockexpression = null;
    {
      SPEGroup _root = matchState.getRoot();
      String modelPackage = _root.getModel();
      boolean _or = false;
      boolean _equals = Objects.equal(modelPackage, null);
      if (_equals) {
        _or = true;
      } else {
        boolean _isEmpty = modelPackage.isEmpty();
        _or = _isEmpty;
      }
      if (_or) {
        modelPackage = "model";
      }
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package matcher; ");
      _builder.newLine();
      _builder.newLine();
      _builder.append("import ");
      _builder.append(modelPackage, "");
      _builder.append(".*; ");
      _builder.newLineIfNotEmpty();
      _builder.append("import ");
      _builder.append(modelPackage, "");
      _builder.append(".util.*;\t");
      _builder.newLineIfNotEmpty();
      _builder.append("import de.uniks.networkparser.logic.Condition;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("@SuppressWarnings(\"all\")");
      _builder.newLine();
      _builder.append("/**");
      _builder.newLine();
      _builder.append("* generated Matchclass for ");
      SPEGroup _root_1 = matchState.getRoot();
      String _name = _root_1.getName();
      _builder.append(_name, "");
      _builder.append(".spe diagram.");
      _builder.newLineIfNotEmpty();
      _builder.append("*/");
      _builder.newLine();
      _builder.append("public class MatchClass");
      SPEGroup _root_2 = matchState.getRoot();
      String _name_1 = _root_2.getName();
      _builder.append(_name_1, "");
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("/**");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("* matches and applies the in ");
      SPEGroup _root_3 = matchState.getRoot();
      String _name_2 = _root_3.getName();
      _builder.append(_name_2, "\t");
      _builder.append(".spe action to a given start object.");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("* @returns true if match was successfull");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("*/");
      _builder.newLine();
      _builder.append("\t");
      CharSequence _generateMethod = this.generateMethod(matchState);
      _builder.append(_generateMethod, "\t");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }
  
  public CharSequence generateMethod(final MatchState matchState) {
    CharSequence _xblockexpression = null;
    {
      final ModelMatchGenerator matcher = new ModelMatchGenerator(matchState);
      final SPEObject start = matchState.getStart();
      final String type = start.getType();
      matchState.markAsMatched(start);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("public boolean execute(");
      _builder.append(type, "");
      _builder.append(" start){");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("//grep start point");
      _builder.newLine();
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
      String _createAttributeMatchCode = ModelMatchGenerator.createAttributeMatchCode(_attributes, type);
      _builder.append(_createAttributeMatchCode, "\t");
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("//matching objects of root grp");
      _builder.newLine();
      _builder.append("\t");
      String _generateMatchCodeForNonAlienObjects = matcher.generateMatchCodeForNonAlienObjects(start);
      _builder.append(_generateMatchCodeForNonAlienObjects, "\t");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("//match objects of subgroups ");
      _builder.newLine();
      _builder.append("\t");
      String _createObjectsOfSubgroups = matcher.createObjectsOfSubgroups();
      _builder.append(_createObjectsOfSubgroups, "\t");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("//matching missing links to known");
      _builder.newLine();
      _builder.append("\t");
      String _generateCodeForMissingLinks = matcher.generateCodeForMissingLinks();
      _builder.append(_generateCodeForMissingLinks, "\t");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("//update model ");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("boolean hasMatch = ");
      String _varName = Extentions.varName(start);
      _builder.append(_varName, "\t");
      _builder.append(".getHasMatch();");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("if(hasMatch){");
      _builder.newLine();
      _builder.append("\t\t");
      MatchState _matchState = matcher.getMatchState();
      CharSequence _generateModelUpdateCode = this.modelUpdater.generateModelUpdateCode(_matchState);
      _builder.append(_generateModelUpdateCode, "\t\t");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("return hasMatch;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }
}
