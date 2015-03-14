package org.uniks.spe.generator.model;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.SPEGroup;
import model.SPELink;
import model.SPEObject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.xbase.lib.CollectionExtensions;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.uniks.spe.generator.model.EntryPoint;

@SuppressWarnings("all")
public class MatchState {
  private final String START_OBJECT_NAME = "this";
  
  private SPEObject startObject;
  
  private SPEGroup root;
  
  private List<SPEObject> allObjects;
  
  private Set<SPEObject> matchedObjects = new HashSet<SPEObject>();
  
  private Set<SPELink> matchedLinks = new HashSet<SPELink>();
  
  public SPEObject init(final Resource resource) {
    try {
      SPEObject _xblockexpression = null;
      {
        TreeIterator<EObject> _allContents = resource.getAllContents();
        final Function1<EObject, Boolean> _function = new Function1<EObject, Boolean>() {
          public Boolean apply(final EObject it) {
            return Boolean.valueOf((it instanceof SPEGroup));
          }
        };
        EObject _findFirst = IteratorExtensions.<EObject>findFirst(_allContents, _function);
        this.root = ((SPEGroup) _findFirst);
        boolean _equals = Objects.equal(this.root, null);
        if (_equals) {
          throw new Exception("Diagram doesnt contain a \'this\'-object.");
        }
        List<SPEObject> _allObjects = MatchState.getAllObjects(this.root);
        this.allObjects = _allObjects;
        final Function1<SPEObject, Boolean> _function_1 = new Function1<SPEObject, Boolean>() {
          public Boolean apply(final SPEObject it) {
            String _name = it.getName();
            return Boolean.valueOf(Objects.equal(_name, MatchState.this.START_OBJECT_NAME));
          }
        };
        SPEObject _findFirst_1 = IterableExtensions.<SPEObject>findFirst(this.allObjects, _function_1);
        _xblockexpression = this.startObject = _findFirst_1;
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public boolean isValid() {
    boolean _and = false;
    boolean _notEquals = (!Objects.equal(this.root, null));
    if (!_notEquals) {
      _and = false;
    } else {
      boolean _notEquals_1 = (!Objects.equal(this.startObject, null));
      _and = _notEquals_1;
    }
    return _and;
  }
  
  public boolean isMatched(final SPELink link) {
    return this.matchedLinks.contains(link);
  }
  
  public boolean isMatched(final SPEObject obj) {
    return this.matchedObjects.contains(obj);
  }
  
  public boolean markAsMatched(final SPELink link) {
    return this.matchedLinks.add(link);
  }
  
  public boolean markAsMatched(final SPEObject obj) {
    return this.matchedObjects.add(obj);
  }
  
  public boolean markAsMatched(final EntryPoint point) {
    boolean _xblockexpression = false;
    {
      SPEObject _start = point.getStart();
      this.markAsMatched(_start);
      SPELink _link = point.getLink();
      _xblockexpression = this.markAsMatched(_link);
    }
    return _xblockexpression;
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
  
  public SPEGroup getRoot() {
    return this.root;
  }
  
  public SPEObject getStart() {
    return this.startObject;
  }
  
  public List<SPEObject> getAllObjects() {
    return this.allObjects;
  }
  
  public void setAllObjects(final List<SPEObject> allObjects) {
    this.allObjects = allObjects;
  }
  
  public Set<SPEObject> getMatchedObjects() {
    return this.matchedObjects;
  }
  
  public void setMatchedObjects(final Set<SPEObject> matchedObjects) {
    this.matchedObjects = matchedObjects;
  }
  
  public Set<SPELink> getMatchedLinks() {
    return this.matchedLinks;
  }
  
  public void setMatchedLinks(final Set<SPELink> matchedLinks) {
    this.matchedLinks = matchedLinks;
  }
}
