package com.tagwolf.meinplugin;

import com.tagwolf.TagWolfAPI;

public class Main extends TagWolfAPI {
    
    @Override
    public void onStart() {
        log("Plugin gestartet!");
        
        cmd("hello", (sender, args) -> {
            msg(sender, "Hello World!");
            return true;
        });
    }
    
    @Override
    public void onStop() {
        log("Plugin gestoppt");
    }
}
