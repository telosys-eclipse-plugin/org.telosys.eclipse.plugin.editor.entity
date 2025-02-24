package org.telosys.eclipse.plugin.editor.entity;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.telosys.eclipse.plugin.editor.entity.partitions.MyPartitionScanner;

public class MySourceViewerConfiguration extends SourceViewerConfiguration {
	
    @Override
    public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
        PresentationReconciler reconciler = new PresentationReconciler();

        // Scanner for HEADER : before "{"
        RuleBasedScanner headerScanner = new RuleBasedScanner();
        headerScanner.setDefaultReturnToken(new Token(
            new TextAttribute(new Color(Display.getCurrent(), 128, 0, 128)))); // Purple

        // Scanner for BODY : after "{"
        RuleBasedScanner bodyScanner = new RuleBasedScanner();
        bodyScanner.setDefaultReturnToken(new Token(
            new TextAttribute(new Color(Display.getCurrent(), 0, 0, 255)))); // Blue

        // Attach scanners to partitions
        DefaultDamagerRepairer headerDR = new DefaultDamagerRepairer(headerScanner);
        reconciler.setDamager(headerDR, MyPartitionScanner.HEADER);
        reconciler.setRepairer(headerDR, MyPartitionScanner.HEADER);

        DefaultDamagerRepairer bodyDR = new DefaultDamagerRepairer(bodyScanner);
        reconciler.setDamager(bodyDR, MyPartitionScanner.BODY);
        reconciler.setRepairer(bodyDR, MyPartitionScanner.BODY);

        return reconciler;
    }
}

