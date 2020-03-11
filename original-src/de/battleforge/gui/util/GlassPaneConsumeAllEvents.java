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
package de.battleforge.gui.util;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.event.MouseInputAdapter;

public class GlassPaneConsumeAllEvents extends JPanel {

    private MouseInputAdapter mMouseAdapter = new MouseInputAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            UIManager.getLookAndFeel().provideErrorFeedback(GlassPaneConsumeAllEvents.this);

        } /* mouseClicked */
    };

    /**
     * Erzeugt eine neue Instanz dieser Klasse.
     */
    public GlassPaneConsumeAllEvents() {
        super(null);

        setOpaque(false);

        addMouseListener(mMouseAdapter);
        addMouseMotionListener(mMouseAdapter);

    } /* GlassPaneConsumeAllEvents */

    @Override
    protected boolean processKeyBinding(KeyStroke ks, KeyEvent e, int condition, boolean pressed) {
        // super.processKeyBinding( ks, e, condition, pressed );

        return true;

    } /* processKeyBinding */
} /* end of class BGlassPaneConsumeAllEvents */