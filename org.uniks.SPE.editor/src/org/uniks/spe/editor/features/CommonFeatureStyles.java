package org.uniks.spe.editor.features;

import java.util.HashMap;
import java.util.Map;

import model.MatchTag;

import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;

public class CommonFeatureStyles {
    public final static IColorConstant TEXT_FOREGROUND = IColorConstant.BLACK;
    
    public final static IColorConstant NORMAL_FOREGROUND =  new ColorConstant(98, 131, 167);
    public final static IColorConstant NORMAL_BACKGROUND =   new ColorConstant(187, 218, 247);    

    public static final IColorConstant NOT_FOREGROUND = new ColorConstant(227, 75, 75);
    public static final IColorConstant NOT_BACKGROUND = new ColorConstant(235, 145, 145);
    public static final IColorConstant LIGTHEN_NOT_BACKGROUND = new ColorConstant(255, 210, 210);

    public final static IColorConstant OPTIONAL_FOREGROUND =  new ColorConstant(164, 164, 164);
    public final static IColorConstant OPTIONAL_BACKGROUND =    new ColorConstant(210, 210, 210);
    
    public static Map<MatchTag, LineStyle> LINESTYLE_PER_Tag = new HashMap<MatchTag, LineStyle>();  
    public static Map<MatchTag, IColorConstant> FOREGROUND_PER_Tag = new HashMap<MatchTag, IColorConstant>();  
    public static Map<MatchTag, IColorConstant> BACKGROUND_PER_Tag = new HashMap<MatchTag, IColorConstant>();  
    static {
        LINESTYLE_PER_Tag.put(MatchTag.DEFAULT, LineStyle.SOLID);
        LINESTYLE_PER_Tag.put(MatchTag.NOT, LineStyle.SOLID);
        LINESTYLE_PER_Tag.put(MatchTag.OPTIONAL, LineStyle.DASH);

        FOREGROUND_PER_Tag.put(MatchTag.DEFAULT, NORMAL_FOREGROUND);
        FOREGROUND_PER_Tag.put(MatchTag.NOT, NOT_FOREGROUND);
        FOREGROUND_PER_Tag.put(MatchTag.OPTIONAL, OPTIONAL_FOREGROUND);

        BACKGROUND_PER_Tag.put(MatchTag.DEFAULT, NORMAL_BACKGROUND);
        BACKGROUND_PER_Tag.put(MatchTag.NOT, NOT_BACKGROUND);
        BACKGROUND_PER_Tag.put(MatchTag.OPTIONAL, OPTIONAL_BACKGROUND);
    }
    
    public static LineStyle getLineStyleByTag(MatchTag MatchTag) {
        return LINESTYLE_PER_Tag.getOrDefault(MatchTag, LineStyle.SOLID);
    }
    
    public static IColorConstant getForegroundByTag(MatchTag MatchTag) {
        return FOREGROUND_PER_Tag.getOrDefault(MatchTag, CommonFeatureStyles.NORMAL_FOREGROUND);
    }
    
    public static IColorConstant getBackgroundByTag(MatchTag MatchTag) {
        return BACKGROUND_PER_Tag.getOrDefault(MatchTag, CommonFeatureStyles.NORMAL_BACKGROUND);
    }
  
    //more static = more better
}
