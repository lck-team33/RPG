package de.team33.lena.rpg;

public class MockStorageServiceTest extends StorageServiceTestBase {

    @Override
    protected StorageService getStorageService() {
        return new MockStorageService();
    }
}
