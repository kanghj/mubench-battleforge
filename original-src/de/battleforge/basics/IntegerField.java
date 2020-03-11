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
import javax.swing.JTextField;

public class IntegerField extends JTextField {
    public IntegerField() {
        super();
        this.setDocument(new IntegerDocument());
    }

    public IntegerField(int i) {
        super();
        this.setDocument(new IntegerDocument());
        setInt(i);
    }

    public void setInt(int i) {
        this.setText(new Integer(i).toString());
    }

    public void setInteger(Integer i) {
        this.setText(i.toString());
    }

    public Integer getIntegerValue() {
        return Integer.getInteger(this.getText());
    }

    public int getintValue() {
        return Integer.parseInt(this.getText());
    }
}
