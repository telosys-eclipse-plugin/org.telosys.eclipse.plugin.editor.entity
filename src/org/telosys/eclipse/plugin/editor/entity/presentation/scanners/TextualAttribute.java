package org.telosys.eclipse.plugin.editor.entity.presentation.scanners;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;

public class TextualAttribute {
	
	// Text colors 
//	private static final Color RED      = new org.eclipse.swt.graphics.Color(null, 255,   0,   0) ; 
//	private static final Color GREEN    = new org.eclipse.swt.graphics.Color(null,   0, 128,   0) ; 
//	private static final Color BLUE     = new org.eclipse.swt.graphics.Color(null,   0,   0, 255) ; 
//
//	private static final Color OLIVE    = new org.eclipse.swt.graphics.Color(null, 128, 128,   0) ; 
	
//	private static final Color PURPLE   = new org.eclipse.swt.graphics.Color(null, 153, 163, 164) ; // BAD / EClipse
	
//	private static final Color VIOLET   = new org.eclipse.swt.graphics.Color(null, 127,   0, 255) ; 
//	
//	private static final Color NAVYBLUE = new org.eclipse.swt.graphics.Color(null, 153, 163, 164) ; 
	
	
//	private static final Color DARK_ORANGE    = new org.eclipse.swt.graphics.Color(null, 255, 135,   3) ; // too bright
	private static final Color DARK_RED       = new org.eclipse.swt.graphics.Color(null, 170,   0,   1) ;
//	private static final Color INDIAN_RED     = new org.eclipse.swt.graphics.Color(null, 190,  97, 106) ;
	private static final Color SADDLE_BROWN   = new org.eclipse.swt.graphics.Color(null, 133,  67,   6) ;
	

	private static final Color ORANGE_RED     = new org.eclipse.swt.graphics.Color(null, 255,69, 0) ;
//	private static final Color FIREBRICK      = new org.eclipse.swt.graphics.Color(null, 178,34,34) ;
	private static final Color CRIMSON        = new org.eclipse.swt.graphics.Color(null, 220,20,60) ;
	
//	private static final Color DARK_MAGENTA    = new org.eclipse.swt.graphics.Color(null, 136, 16, 147) ; 
//	private static final Color STEEL_BLUE      = new org.eclipse.swt.graphics.Color(null, 94, 129, 171) ; 
	
	// Eclipse colors
	private static final Color DARK_PURPLE     = new org.eclipse.swt.graphics.Color(null, 127,   0,  85) ; // keywords
	private static final Color DARK_BLUE       = new org.eclipse.swt.graphics.Color(null,   0,   0, 192) ; // constants
	private static final Color GREEN_SHADE     = new org.eclipse.swt.graphics.Color(null,  63, 127,  95) ; // comments
//	private static final Color BRIGHT_BLUE     = new org.eclipse.swt.graphics.Color(null,  42,   0, 255) ; 

	
	// Text attributes for each type of token
	public static final TextAttribute COMMENT           = new TextAttribute(GREEN_SHADE, null, SWT.ITALIC); // Eclipse like
	//public static final TextAttribute LITERAL_STRING    = new TextAttribute(BRIGHT_BLUE); // Eclipse like
	public static final TextAttribute LITERAL_STRING    = new TextAttribute(DARK_BLUE); 

	public static final TextAttribute CURRENT_ENTITY    = new TextAttribute(ORANGE_RED, null, SWT.BOLD); 
	public static final TextAttribute ENTITY            = new TextAttribute(CRIMSON,  null, SWT.BOLD); 

	public static final TextAttribute NEUTRAL_TYPE      = new TextAttribute(DARK_PURPLE, null, SWT.BOLD); // Eclipse like
//	public static final TextAttribute NEUTRAL_TYPE      = new TextAttribute(DARK_MAGENTA, null, SWT.BOLD); 
	
	
	
	public static final TextAttribute ANNOTATION        = new TextAttribute(DARK_RED);
	public static final TextAttribute ANNOTATION_PARAM  = new TextAttribute(DARK_BLUE); // Eclipse like
	//public static final TextAttribute TAG               = new TextAttribute(INDIAN_RED);
	public static final TextAttribute TAG               = new TextAttribute(SADDLE_BROWN);
	
}
