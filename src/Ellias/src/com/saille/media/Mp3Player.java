package com.saille.media;

import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.decoder.Decoder;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.player.Player;

public class Mp3Player {
    public void play() {
        try {
            String url = "D:\\Can You Feel the Love Today.MP3";
            File f = new File(url);
            FileInputStream fis = new FileInputStream(f);
            AudioDevice device = FactoryRegistry.systemRegistry().createAudioDevice();
            Decoder decoder = new Decoder();

            Player player = new Player(fis, device);
            player.play();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}