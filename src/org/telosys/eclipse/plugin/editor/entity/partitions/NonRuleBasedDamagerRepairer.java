package org.telosys.eclipse.plugin.editor.entity.partitions;

import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.presentation.IPresentationDamager;
import org.eclipse.jface.text.presentation.IPresentationRepairer;
import org.eclipse.swt.custom.StyleRange;

public class NonRuleBasedDamagerRepairer implements IPresentationRepairer, IPresentationDamager {
    private TextAttribute textAttribute;

    public NonRuleBasedDamagerRepairer(TextAttribute attribute) {
        this.textAttribute = attribute;
    }

    @Override
    public void setDocument(IDocument document) {
        // No need to set a document, since we're only applying a static color
    }

    @Override
    public IRegion getDamageRegion(ITypedRegion partition, DocumentEvent event, boolean documentPartitioningChanged) {
        return partition;
    }

    @Override
    public void createPresentation(TextPresentation presentation, ITypedRegion region) {
        addStyleRange(presentation, region.getOffset(), region.getLength());
    }

    private void addStyleRange(TextPresentation presentation, int offset, int length) {
        if (textAttribute != null) {
            StyleRange styleRange = new StyleRange(offset, length, 
                textAttribute.getForeground(), textAttribute.getBackground(), 
                textAttribute.getStyle());
            presentation.addStyleRange(styleRange);
        }
    }
}
