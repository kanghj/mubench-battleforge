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

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.apache.log4j.Logger;

import de.battleforge.util.Internationalization;

/**
 * Implementation of a player for sampled sounds.
 * 
 * @author Kotzbrocken2
 */
public class SampledPlayer extends AbstractPlayer {

    /**
     * The clip to be played.
     */
    private Clip mClip;

    /**
     * Our Logger.
     */
    private static Logger sLogger = Logger.getLogger(SampledPlayer.class);

    /**
     * Constructs a new player.
     * 
     * @param clip
     *            the url of the clip to play
     * @throws IllegalArgumentException
     *             if the url is <code>null</code>
     */
    public SampledPlayer(URL clip) throws IllegalArgumentException {
        super(clip);

    } /* ClipPlayer */

    @Override
    protected void playImpl(int loops) {
        if (mClip == null) {
            try {
                // get the input-stream
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClipURL());

                // get the format of the audioinputstream
                AudioFormat format = audioInputStream.getFormat();

                // obtain the info-class for the audio-format
                DataLine.Info info = new DataLine.Info(Clip.class, format);

                // get the clip
                mClip = (Clip) AudioSystem.getLine(info);

                // open the clip
                mClip.open(audioInputStream);

            } catch (LineUnavailableException lue) {
                sLogger.error("Line unavailable!", lue);

                mClip = null;

            } catch (IOException ioe) {
                sLogger.error("IO-Error occured!", ioe);

                mClip = null;

            } catch (UnsupportedAudioFileException uafe) {
                sLogger.error("File-Format not supported!", uafe);

                mClip = null;

            } catch (Throwable th) {
                sLogger.error("Unknown error occured!", th);

                mClip = null;

            } /* try */
        } // if

        if (mClip == null) {
            sLogger.warn(Internationalization.getString("sampledPlayer.no_clip"));

            return;

        } /* if */

        if ((mClip != null) && !mClip.isRunning()) {
            sLogger.debug("play");

            // reset the position to play from the beginning
            mClip.setFramePosition(0);
            mClip.loop(loops == SoundPlayer.LOOP_CONTINUOUSLY ? Clip.LOOP_CONTINUOUSLY : loops);

            sLogger.debug("after loop");

            mClip.addLineListener(new LineListener() {
                public void update(LineEvent event) {
                    sLogger.debug("update: " + event.getSource());
                    sLogger.debug("update: " + event.getFramePosition());
                    sLogger.debug("update: " + event.getType());

                    if (event.getType() == LineEvent.Type.STOP) {
                        firePlaybackStopped();

                        mClip.removeLineListener(this);

                    } // if
                } // update
            });

        } // if
    } /* play */

    @Override
    public boolean isPlaying() {
        return mClip == null ? false : mClip.isRunning();

    } // isPlaying

    @Override
    protected void stopImpl() {
        if (mClip.isRunning()) {
            mClip.stop();

        } // if
    } // stop

    @Override
    public void close() {
        stop();

        if (mClip.isOpen()) {
            mClip.close();

        } // if
    } // close
} // end of class SampledPlayer
