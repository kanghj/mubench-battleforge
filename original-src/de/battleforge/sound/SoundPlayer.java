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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import de.battleforge.util.BFProperties;
import de.battleforge.util.Internationalization;
import de.battleforge.util.BFProperties.BFProps;

/**
 * 
 * 
 */
public class SoundPlayer implements Runnable {

    /**
     * Enumeration of allowed sound-types.
     */
    public static enum SOUNDTYPE {
        FX, VOICE, MUSIC

    } // SOUNDTYPE

    /**
     * Continuous looping.
     */
    public static final int LOOP_CONTINUOUSLY = -1;

    /**
     * Default sleep-time for cleanup-thread.
     */
    private static final int SLEEP_TIME = 60 * 1000; // sleep 60 seconds

    /**
     * Cache for fx.
     */
    private static HashMap<URL, AbstractPlayer> mFXCache = new HashMap<URL, AbstractPlayer>();

    /**
     * Cache for voices.
     */
    private static HashMap<URL, AbstractPlayer> mVoiceCache = new HashMap<URL, AbstractPlayer>();

    /**
     * Cache for music.
     */
    private static HashMap<URL, AbstractPlayer> mMusicCache = new HashMap<URL, AbstractPlayer>();

    /**
     * Our private instance.
     */
    private static SoundPlayer mInstance;

    /**
     * Our Logger.
     */
    private static Logger sLogger = Logger.getLogger(SoundPlayer.class);

    /**
     * Our private lock-object.
     */
    private static Object mLockObject = new Object();

    /**
     * Creates a new instance of the player.
     */
    private SoundPlayer() {
        // do nothing

    } // SoundPlayer

    /**
     * @inheritDoc
     */
    public void run() {
        while (true) {
            try {
                // sleep some times
                Thread.sleep(SLEEP_TIME);

            } catch (InterruptedException e) {
                // do nothing

            } // try

            // cleanup all caches after awakening
            synchronized (mLockObject) {
                cleanupCache(mFXCache);
                cleanupCache(mVoiceCache);
                cleanupCache(mMusicCache);

            } // synchronized
        } // while
    } // run

    /**
     * Plays the given name with the given type.
     * 
     * @param type
     *            the type to play
     * @param name
     *            the name of the song to play
     */
    public static void play(SOUNDTYPE type, String name) {
        play(type, name, 0);

    } // play

    /**
     * Plays the given name with the given type <code>loops</code> times.
     * 
     * @param type
     *            the type to play
     * @param name
     *            the name of the song to play
     * @param loops
     *            the count of loops to play
     */
    public static void play(SOUNDTYPE type, String name, int loops) {
        if ((type == SOUNDTYPE.VOICE) && !BFProperties.getBoolean(BFProps.PLAY_VOICE)) {
            return;

        } /* if */

        if ((type == SOUNDTYPE.FX) && !BFProperties.getBoolean(BFProps.PLAY_SOUND)) {
            return;

        } /* if */

        if ((type == SOUNDTYPE.MUSIC) && !BFProperties.getBoolean(BFProps.PLAY_MUSIC)) {
            return;

        } /* if */

        synchronized (mLockObject) {
            // check if there is already an instance
            if (mInstance == null) {
                // create a new instance
                mInstance = new SoundPlayer();

                // create a new thread used for cleanup unused players
                Thread t = new Thread(mInstance, "SoundPlayer-Cleanup");

                // set the thread to be a deamon so it don't prevent the VM to
                // exit
                t.setDaemon(true);

                // give the thread a low priority
                t.setPriority(Thread.MIN_PRIORITY);

                // start the thread
                t.start();

            } // if

            // get the player for the given type and clip, create a new one if
            // necessary
            AbstractPlayer player = getPlayer(type, name, true);

            // if a player was found start playing
            if (player != null) {
                // unmark the player for closing
                player.setMarkedForClose(false);

                // play loops-times
                player.play(loops);

            } // if
        } // synchronized
    } // play

    /**
     * Plays the given sound exactly one time and wait for finish playing.
     * 
     * @param type
     *            the type of the clip
     * @param name
     *            the name of the clip
     */
    public static void playSynchron(SOUNDTYPE type, String name) {
        // check if we should play voices
        if ((type == SOUNDTYPE.VOICE) && !BFProperties.getBoolean(BFProps.PLAY_VOICE)) {
            return;

        } /* if */

        if ((type == SOUNDTYPE.FX) && !BFProperties.getBoolean(BFProps.PLAY_SOUND)) {
            return;

        } /* if */

        // get the player, create one if necessary
        final AbstractPlayer player = getPlayer(type, name, true);

        // add the listener to the player
        player.addPlaybackListener(new PlaybackListener() {

            public void playbackStarted(PlaybackEvent e) {
                // do nothing

            } // playbackStarted

            public void playbackStopped(PlaybackEvent e) {
                synchronized (player) {
                    // remove this listener from the player
                    player.removePlaybackListener(this);

                    // wakeup
                    player.notifyAll();

                } // synchronized
            } // playbackStopped
        });

        // start playing of the clip
        play(type, name);

        synchronized (player) {
            try {
                // wait for finishing playing
                player.wait();

            } catch (InterruptedException e) {
                sLogger.warn("Wait was interrupted", e);

            } // try
        } // synchronized
    } // playSynchron

    /**
     * Stops playing of the given sound.
     * 
     * @param type
     *            the type of the sound
     * @param name
     *            the name of the clip
     */
    public static void stop(SOUNDTYPE type, String name) {
        AbstractPlayer player = getPlayer(type, name, false);

        if (player != null) {
            player.stopImpl();

        } // if
    } // stop

    /**
     * Stops all sounds currently running or opened and releases all resources.
     */
    public static void stopAllSounds() {
        stopSounds(mVoiceCache);
        stopSounds(mFXCache);
        stopSounds(mMusicCache);

    } // stopAllSounds

    /**
     * Stops all sounds in the given cache and than clears the cache.
     * 
     * @param cache
     *            the cache with the sounds to stop
     */
    private static void stopSounds(HashMap<URL, AbstractPlayer> cache) {
        // Iterate over all entries of the cache
        for (Entry<URL, AbstractPlayer> entry : cache.entrySet()) {
            // close the player, that means stop playback and release
            // system-resources
            entry.getValue().close();

        } // for

        // clear the cache
        cache.clear();

    } // stopSounds

    /**
     * Cleans the given cache.
     * 
     * @param cache
     *            the cache to cleanup
     */
    private void cleanupCache(HashMap<URL, AbstractPlayer> cache) {
        Iterator<Entry<URL, AbstractPlayer>> it = cache.entrySet().iterator();

        // iterate over all entries of the cache
        while (it.hasNext()) {
            Entry<URL, AbstractPlayer> entry = it.next();

            AbstractPlayer player = entry.getValue();

            // check if the player is marked for closing
            if (player.isMarkedForClose()) {
                sLogger.info("closing unused player: " + player);

                // close the player
                player.close();

                // remove the player from the cache
                it.remove();

            } else if (!player.isPlaying()) {
                sLogger.info("mark player for closing: " + player);

                // mark the not playing player for closing
                player.setMarkedForClose(true);

            } // if
        } // while
    } // cleanupCache

    /**
     * Returns a player which can play the given type and is associated with the
     * given name. The name is the name of the clip to play. If
     * <code>create</code> is <code>true</code> than a new player will be
     * instanciated for the clip.
     * 
     * @param type
     *            the type of the clip
     * @param name
     *            the name of the clip
     * @param create
     *            <code>true</code> to instanciate a new player if there can't
     *            be found one
     * 
     * @return the found or instanciated player or <code>null</code> if there
     *         was no player for the clip
     */
    private static AbstractPlayer getPlayer(SOUNDTYPE type, String name, boolean create) {
        URL clipURL = null;
        AbstractPlayer player = null;

        try {
            switch (type) {
            case FX:
                clipURL = SampledPlayer.class.getClassLoader().getResource("sound/fx/" + name + ".au");

                player = mFXCache.get(clipURL);

                if ((player == null) && create) {
                    player = new SampledPlayer(clipURL);

                    mFXCache.put(clipURL, player);

                } // if

                break;

            case VOICE:
                String language = Internationalization.getLanguage();
                clipURL = SampledPlayer.class.getClassLoader().getResource("sound/voice/" + language + "/" + name + ".au");

                player = mVoiceCache.get(clipURL);

                if ((player == null) && create) {
                    player = new SampledPlayer(clipURL);

                    mVoiceCache.put(clipURL, player);

                } // if

                break;

            case MUSIC:
                clipURL = SampledPlayer.class.getClassLoader().getResource("sound/music/" + name + ".mid");

                player = mMusicCache.get(clipURL);

                if ((player == null) && create) {
                    player = new MidiPlayer(clipURL);

                    mMusicCache.put(clipURL, player);

                } // if
            } /* switch */
        } catch (IllegalArgumentException iae) {
            if (clipURL == null) {
                sLogger.warn(Internationalization.getString("soundPlayer.clip_not_found"));

            } /* if */
        } // try

        return player;

    } // getPlayer
} // end of class SoundPlayer
