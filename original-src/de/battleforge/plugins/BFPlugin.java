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
package de.battleforge.plugins;

import java.awt.Container;

import javax.swing.Icon;

/**
 * Interface for plugins
 * 
 * @author
 * 
 */
public interface BFPlugin {

    public enum PluginType {
        MODALFRAME, FRAME, TAB
    }

    /**
     * Gets the container
     */
    Container getContentPane();

    /**
     * 
     * @return
     */
    String getDescription();

    /**
     * Returns name of the plugin
     * 
     * @return
     */
    String getName();

    /**
     * Returns short description
     * 
     * @return
     */
    String getShortDescription();

    /**
     * Returns the small version of the icon
     * 
     * @return
     */
    Icon getSmallIcon();

    /**
     * Returns the icon
     * 
     * @return
     */
    Icon getIcon();

    /**
     * 
     */
    PluginType getPluginType();

    /**
     * Execution of the plugin
     * 
     * @param con
     */
    void execute(BFPluginContext con);
}
