package com.pixelthieves.pokemontd.component;

import com.artemis.Component;

/**
 * Created by Tomas on 10/4/13.
 */
public class TextComponent extends Component {

    private final String text;

    public TextComponent(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
