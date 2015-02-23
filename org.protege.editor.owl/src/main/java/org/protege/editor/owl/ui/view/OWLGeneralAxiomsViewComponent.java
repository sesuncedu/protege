package org.protege.editor.owl.ui.view;

import org.protege.editor.owl.ui.frame.OWLGeneralClassAxiomsFrame;
import org.protege.editor.owl.ui.framelist.OWLFrameList;
import org.semanticweb.owlapi.model.OWLOntology;

import javax.swing.*;
import java.awt.*;


/**
 * Author: Matthew Horridge<br>
 * The University Of Manchester<br>
 * Bio-Health Informatics Group<br>
 * Date: 23-Apr-2007<br><br>
 */
public class OWLGeneralAxiomsViewComponent extends AbstractActiveOntologyViewComponent {

    /**
     * 
     */
    private static final long serialVersionUID = 8134326184738945842L;
    private OWLFrameList<OWLOntology> list;


    protected void initialiseOntologyView() throws Exception {
        list = new OWLFrameList<OWLOntology>(getOWLEditorKit(),
                                              new OWLGeneralClassAxiomsFrame(getOWLEditorKit(),
                                                                             getOWLModelManager().getOWLOntologyManager()));
        list.setRootObject(getOWLModelManager().getActiveOntology());
        setLayout(new BorderLayout());
        JScrollPane jScrollPane = new JScrollPane();
        add(jScrollPane);
        if(list.getModel().getSize() > 100) {
           jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        }

        Container parent = getParent();
        while(!parent.isValidateRoot()) {
            parent = parent.getParent();
        }
        parent.validate();
        JViewport viewport = jScrollPane.getViewport();
        int width = viewport.getExtentSize().width;
        list.setFixedCellWidth(width);
        jScrollPane.setViewportView(list);
    }


    protected void disposeOntologyView() {
        list.dispose();
    }


    protected void updateView(OWLOntology activeOntology) throws Exception {
        list.setRootObject(activeOntology);
    }
}
