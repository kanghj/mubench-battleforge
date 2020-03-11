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
package de.battleforge.sound;

/**
 * Event-Object for playback-events.
 * 
 * @author Kotzbrocken2
 */
public class PlaybackEvent {

    /**
     * The player that generates this event.
     */
    private AbstractPlayer mSource;

    /**
     * Constructs a new object.
     * 
     * @param source
     *            the player that generates this event
     */
    public PlaybackEvent(AbstractPlayer source) {
        mSource = source;

    } // PlaybackEvent

    /**
     * Returns the source-player of this event.
     * 
     * @return the player that generates this event
     */
    public AbstractPlayer getSource() {
        return mSource;

    } // getSource
} // end of class PlaybackEvent
