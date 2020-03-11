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

import java.awt.Color;
import java.text.NumberFormat;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class CurrencyLabel extends JLabel {

    private double value;

    private String cur = "";

    public CurrencyLabel(double v) {
        value = v;
        setHorizontalAlignment(SwingConstants.RIGHT);
        setCurrent("C");
        setValue(value);
    }

    public void setCurrent(String current) {
        cur = current;
    }

    public void setValue(double v) {
        value = v;
        if (value < 0) {
            setForeground(Color.RED);
        } else {
            setForeground(Color.BLACK);
        }
        NumberFormat nf = NumberFormat.getCurrencyInstance();

        String valString = nf.format(new Double(value)).replaceFirst(nf.getCurrency().getSymbol(), cur);
        setText(valString);
    }

    public double getValue() {
        return value;
    }
}
