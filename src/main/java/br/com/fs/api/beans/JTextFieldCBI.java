package br.com.fs.api.beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.JFormattedTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Tiago D. Teixeira
 */
public class JTextFieldCBI extends JFormattedTextField {
    
    public final int ALL = 0;
    public final int LOWER = 1;
    public final int UPPER = 2;
    public final int NUMBER = 3;

    private final DocumentCBI doc;

    public JTextFieldCBI() {
        super();
        doc = new DocumentCBI();
        this.setDocument(doc);
    }

    public void setMaxLength(int value) {
        doc.setLimitCarecter(value);
    }

    public void setTypeCaracter(int type) {
        doc.setTypeCaracter(type);
    }

    private class DocumentCBI extends PlainDocument {

        private int limitCharacter = 255;
        private String typeField = "";

        public DocumentCBI() {
            super();
        }

        public void setLimitCarecter(int maxLength) {
            limitCharacter = maxLength;
        }

        public void setTypeCaracter(int typeField) {
            switch (typeField) {
                case 0:
                    this.typeField = "[^aA-zZ|^0-9]";
                    break;
                case 1:
                    this.typeField = "[^a-z]";
                    break;
                case 2:
                    this.typeField = "[^A-Z]";
                    break;
                case 3:
                    this.typeField = "[^0-9]";
                    break;
            }
        }

        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            if (str != null || getLength() != limitCharacter) {
                if ((str.length() + getLength()) <= limitCharacter) {
                    if (!"".equals(typeField)) {
                        super.insertString(offs, str.replaceAll(typeField, ""), a);
                    } else {
                        super.insertString(offs, str, a);
                    }
                } else {
                    super.insertString(offs, str.substring(0, getLength() - limitCharacter), a);
                }
            }
        }

    }
}
