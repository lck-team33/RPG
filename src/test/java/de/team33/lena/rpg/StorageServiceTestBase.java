package de.team33.lena.rpg;

import com.google.common.collect.ImmutableMap;
import de.team33.lena.rpg.model.RpgCharacter;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public abstract class StorageServiceTestBase {

    protected abstract StorageService getStorageService();

    @Test
    public void insertCharacter() {
        final RpgCharacter character = new RpgCharacter().setProperties(ImmutableMap.<String, String>builder()
                .put("name", "Andi")
                .put("color", "red")
                .build());
        final String id = getStorageService().insertCharacter(character);
        assertNotNull(id);
        // TODO:
        // Lese character mit id aus der Storage und vergleiche mit den Erwartungswerten
        // final RpgCharacter character = getStorageService().getCharacter(id);
    }
}