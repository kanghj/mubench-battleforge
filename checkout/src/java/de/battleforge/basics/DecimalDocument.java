/* This software is free; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation,
 * Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package de.battleforge.basics;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Werner
 * @version 1.0
 */
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

public class DecimalDocument extends PlainDocument {
    public DecimalDocument(JTextComponent jtc) {
        jc = jtc;
    }

    private JTextComponent jc;

    private int decimalPlace = -1;

    public void setDecimal(int decimalPlaces) {
        this.decimalPlace = decimalPlaces;
    }

    @Override
    public void insertString(int offset, String s, AttributeSet attributeSet) throws BadLocationException {
        if (s.equals("-") && (offset == 0) && (jc.getText().lastIndexOf("-") == -1)) {
            super.insertString(offset, s, attributeSet);
        }
        if (!s.equals(".")) {
            try {
                if (((jc.getText().length() - jc.getText().lastIndexOf(".")) > this.decimalPlace) && (jc.getText().lastIndexOf(".") > -1)
                        && (offset > jc.getText().lastIndexOf("."))) {
                    return;
                }
                Double.parseDouble(s);
            } catch (Exception ex) {
                return;
            }
        } else {
            if (jc.getText().lastIndexOf(".") == -1) {
                if (this.decimalPlace != -1) {
                    String d = jc.getText();
                    if ((d.length() - offset) > decimalPlace) {
                        return;
                    }
                }
            } else {
                return;
            }
        }
        super.insertString(offset, s, attributeSet);
    }
}
