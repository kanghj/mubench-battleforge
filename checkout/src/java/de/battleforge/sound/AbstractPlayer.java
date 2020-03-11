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

import java.net.URL;
import java.util.ArrayList;

/**
 * Abtract class for players.
 * 
 * @author kotzbrocken2
 */
public abstract class AbstractPlayer {

    /**
     * The clip-url.
     */
    private URL mClipURL;

    /**
     * Indicates that this player can be closed.
     */
    private boolean mMarkedForClose;

    /**
     * A list with listeners.
     */
    private ArrayList<PlaybackListener> mListener;

    /**
     * Constructor to create a new player.
     * 
     * @param clip
     *            the url of the clip to play
     * @throws IllegalArgumentException
     *             if the clip is <code>null</code>
     */
    public AbstractPlayer(URL clip) throws IllegalArgumentException {
        if (clip == null) {
            throw new IllegalArgumentException("clip can't be null");

        } // if

        mClipURL = clip;

        mListener = new ArrayList<PlaybackListener>();

    } // AbstractPlayer

    /**
     * Returns the url of the clip to play.
     * 
     * @return the url of the clip
     */
    protected URL getClipURL() {
        return mClipURL;

    } // getClipURL

    /**
     * Set the mark for closing.
     * 
     * @param markedForClose
     *            the mark for closing the player
     */
    public void setMarkedForClose(boolean markedForClose) {
        mMarkedForClose = markedForClose;

    } // setMarkedForClose

    /**
     * Returns the mark for closing the player.
     * 
     * @return <code>true</code> if the player is marked for closing otherwise
     *         <code>false</code>.
     */
    public boolean isMarkedForClose() {
        return mMarkedForClose;

    } // isMarkedForClose

    /**
     * Adds the given listener to the player.
     * 
     * @param listener
     *            the listener to add
     */
    public void addPlaybackListener(PlaybackListener listener) {
        mListener.add(0, listener);

    } // addPlaybackListener

    /**
     * Removes the given listener from the player.
     * 
     * @param listener
     *            the listener to remove
     */
    public void removePlaybackListener(PlaybackListener listener) {
        mListener.remove(listener);

    } // removePlaybackListener

    /**
     * Fires a <code>PlaybackEvent</code>.
     */
    protected void firePlaybackStarted() {
        PlaybackEvent e = new PlaybackEvent(this);

        for (PlaybackListener listener : mListener) {
            listener.playbackStarted(e);

        } // for
    } // firePlaybackStarted

    /**
     * Fires a <code>PlaybackEvent</code>.
     */
    protected void firePlaybackStopped() {
        PlaybackEvent e = new PlaybackEvent(this);

        for (PlaybackListener listener : mListener) {
            listener.playbackStopped(e);

        } // for
    } // firePlaybackStopped

    /**
     * Starts playing of the given clip. The loop-parameter indicates the count
     * of loops that the clip should played. For infinite looping use
     * {@link SoundPlayer#LOOP_CONTINUOUSLY}. A PlaybackStarted-event will be
     * forwarded to all registered listeners.
     * 
     * @param loops
     *            count of loops that the clip should be played
     */
    public void play(int loops) {
        // System.out.println( "isPlaying(): " + isPlaying() );
        if (!isPlaying()) {
            playImpl(loops);

            firePlaybackStarted();

        } // if
    } // play

    /**
     * Stops the playing of the clip. A PlaybackStopped-event will be forwarded
     * to all registered listeners.
     */
    public void stop() {
        if (isPlaying()) {
            stopImpl();

            firePlaybackStopped();

        } // if
    } // stop

    /**
     * The implementation of play.
     * 
     * @param loops
     *            the count of loops the clip should be played
     * @see #play(int)
     */
    protected abstract void playImpl(int loops);

    /**
     * The implementation of stop.
     * 
     * @see #stop()
     */
    protected abstract void stopImpl();

    /**
     * Returns <code>true</code> if the player is playing.
     * 
     * @return <code>true</code> if the player is playing otherwise
     *         <code>false</code>
     */
    public abstract boolean isPlaying();

    /**
     * Closes the player and releases all resources. If the player is playing
     * than it should be stopped before closing.
     */
    public abstract void close();

} // end of class AbstractPlayer
