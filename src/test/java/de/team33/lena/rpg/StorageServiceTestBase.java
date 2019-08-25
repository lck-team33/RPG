package de.team33.lena.rpg;

import com.google.common.collect.ImmutableMap;
import de.team33.lena.rpg.model.RpgCharacter;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class StorageServiceTestBase {

    protected abstract StorageService getStorageService();

    @Test
    public void insertCharacter() {
        final String id = getStorageService().insertCharacter(ImmutableMap.<String, String>builder()
                .put("name", "Andi")
                .put("color", "red")
                .build());
        assertNotNull(id);
        // TODO:
        // Lese character mit id aus der Storage und vergleiche mit den Erwartungswerten
        // final RpgCharacter character = getStorageService().getCharacter(id);
    }
}