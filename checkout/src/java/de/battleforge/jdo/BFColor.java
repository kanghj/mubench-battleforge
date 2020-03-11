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
package de.battleforge.jdo;

import java.awt.Color;


/**
 * 
 * @author werner
 * 
 * @jdo.persistence-capable identity-type="datastore" table="BF_COLOR" 
 * @jdo.datastore-identity strategy="native" column="ID"
 */
public class BFColor {
    
    /**
     * @jdo.field
     * @jdo.column name="NAME" jdbc-type="VARCHAR" length="255"
     */
    private String name;
    
    /**
     * @jdo.field
     * @jdo.column name="RED" jdbc-type="INTEGER"
     */
    private int red;
    
    /**
     * @jdo.field
     * @jdo.column name="GREEN" jdbc-type="INTEGER"
     */
    private int green;
    
    /**
     * @jdo.field
     * @jdo.column name="BLUE" jdbc-type="INTEGER"
     */
    private int blue;
    
    public BFColor(){
        
    }

    public Color getJavaColor() {
        Color c = new Color(red, green, blue);
        return c;
    }

    /**
     * @return the blue
     */
    public int getBlue() {
        return blue;
    }

    /**
     * @return the green
     */
    public int getGreen() {
        return green;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the red
     */
    public int getRed() {
        return red;
    }

}
