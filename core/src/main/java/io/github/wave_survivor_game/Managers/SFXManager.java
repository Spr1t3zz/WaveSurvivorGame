package io.github.wave_survivor_game.Managers;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;

public class SFXManager {
    private static SFXManager aum;
    public static SFXManager getInstance() {
        if (aum == null) aum = new SFXManager();
        return aum;
    }

    private float globalVolume = 1f;

    private final HashMap<String, Sound> sounds = new HashMap<>();

    private SFXManager() {
        registerSounds();
    }

    public long play(String sound) {
        Sound s = sounds.get(sound);
        return s == null ? -1L : s.play();
    }

    public long loop(String sound) {
        Sound s = sounds.get(sound);
        return s == null ? -1L : s.loop();
    }

    public void stop(String sound) {
        Sound s = sounds.get(sound);
        if (s != null) s.stop();
    }

    public void stop(String sound, long id) {
        Sound s = sounds.get(sound);
        if (s != null && id != -1L) s.stop(id);
    }

    public void setVolume(String sound, long id, float volume) {
        sounds.get(sound).setVolume(id, volume);
    }

    private void registerSounds() {
        sounds.put("flying-fireball", Gdx.audio.newSound(Gdx.files.internal("assets/sounds/fireballFlying.ogg"))); //
        sounds.put("game-over", Gdx.audio.newSound(Gdx.files.internal("assets/sounds/gameOverSound.ogg")));
        sounds.put("orb-use", Gdx.audio.newSound(Gdx.files.internal("assets/sounds/orbUse.ogg"))); //
        sounds.put("picking-up-items", Gdx.audio.newSound(Gdx.files.internal("assets/sounds/pickingup.ogg"))); //
        sounds.put("sigil-use", Gdx.audio.newSound(Gdx.files.internal("assets/sounds/sigilUse.ogg"))); //
        sounds.put("sigil-gone", Gdx.audio.newSound(Gdx.files.internal("assets/sounds/sigilGone.ogg"))); //
        sounds.put("potion-use", Gdx.audio.newSound(Gdx.files.internal("assets/sounds/potionUse.ogg")));
        sounds.put("goblet-use", Gdx.audio.newSound(Gdx.files.internal("assets/sounds/gobletUse.ogg")));
    }

    public void setGlobalVolume(float volume) {
        this.globalVolume = volume;
    }

    public float getGlobalVolume() {
        return this.globalVolume;
    }

}
