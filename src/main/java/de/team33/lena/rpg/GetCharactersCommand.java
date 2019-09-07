package de.team33.lena.rpg;

import de.team33.lena.rpg.model.RpgCharacter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class GetCharactersCommand implements Runnable {

    private static final StorageService STORAGE_SERVICE = new JdbiStorageService();

    private final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void run() {
        try{
            System.out.println("Nach welchem Schlüssel möchtest du suchen?");
            String key = in.readLine();
            System.out.println("Nach welchem Inhalt möchtest du suchen?");
            String value = in.readLine();

            List<RpgCharacter> characterList = STORAGE_SERVICE.getCharacters(key, value);
            characterList.forEach(character -> character.print());

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
