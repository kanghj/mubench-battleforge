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

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

import org.apache.log4j.Logger;

/**
 * Implementation of a player for midi-sounds.
 * 
 * @author Kotzbrocken2
 */
public class MidiPlayer extends AbstractPlayer {

    /**
     * Our Logger.
     */
    private static Logger sLogger = Logger.getLogger(MidiPlayer.class);

    /**
     * The sequencer used to play the midi-sound.
     */
    private Sequencer sequencer;

    /**
     * Constructs a new player.
     * 
     * @param clip
     *            the url of the clip to play
     * @throws IllegalArgumentException
     *             if the url is <code>null</code>
     */
    public MidiPlayer(URL clip) throws IllegalArgumentException {
        super(clip);

    } // MidiPlayer

    @Override
    protected void playImpl(int loopCount) {
        try {
            if (sequencer == null) {
                Sequence sequence = MidiSystem.getSequence(getClipURL());

                // Create a sequencer for the sequence
                sequencer = MidiSystem.getSequencer();

                sequencer.setSequence(sequence);

            } // if

            sequencer.open();
            sequencer.setLoopCount(loopCount == SoundPlayer.LOOP_CONTINUOUSLY ? Sequencer.LOOP_CONTINUOUSLY : loopCount);

            sequencer.start();

        } catch (InvalidMidiDataException e) {
            sLogger.error("Exception occured", e);

        } catch (IOException e) {
            sLogger.error("Exception occured", e);

        } catch (MidiUnavailableException e) {
            sLogger.error("Exception occured", e);

        } // try
    } // play

    @Override
    public boolean isPlaying() {
        return sequencer == null ? false : sequencer.isRunning();

    } // isPlaying

    @Override
    protected void stopImpl() {
        if (sequencer.isRunning()) {
            sequencer.stop();

        } // if
    } // stop

    @Override
    public void close() {
        stop();

        if (sequencer.isOpen()) {
            sequencer.close();

        } // if
    } // stop
} // end of class MidiPlayer
