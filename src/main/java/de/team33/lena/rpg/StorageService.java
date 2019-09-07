package de.team33.lena.rpg;

import de.team33.lena.rpg.model.RpgCharacter;

import java.util.List;

public interface StorageService {

    String insertCharacter(RpgCharacter character);

    List<RpgCharacter> getCharacters(String key, String value);
}
