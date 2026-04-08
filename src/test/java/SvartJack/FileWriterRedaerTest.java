package SvartJack;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;

public class FileWriterRedaerTest {

    @Test
    public void testCsvManagerCreatesFileAndHeader() throws IOException {
        Path testFile = Files.createTempFile("test-csv", ".csv");
        try {
            CSVManager csvManager = new CSVManager(testFile);
            assertTrue(Files.exists(testFile));
            List<String> lines = Files.readAllLines(testFile);
            assertEquals(1, lines.size());
            assertEquals("name,balance", lines.get(0));
        } finally {
            Files.deleteIfExists(testFile);
        }
    }

    @Test
    public void testSaveOrUpdatePlayerAndGetBalance() throws IOException {
        Path testFile = Files.createTempFile("test-csv", ".csv");
        try {
            CSVManager csvManager = new CSVManager(testFile);
            PlayerData player = new PlayerData("Alice", 150);
            csvManager.saveOrUpdatePlayer(player);

            assertEquals(150, csvManager.getBalance("Alice"));
            assertEquals(150, csvManager.getBalance("alice"));
            assertEquals(List.of("alice"), csvManager.getAllNames());

            csvManager.saveOrUpdatePlayer(new PlayerData("ALICE", 200));
            assertEquals(200, csvManager.getBalance("alice"));
            assertEquals(List.of("alice"), csvManager.getAllNames());
        } finally {
            Files.deleteIfExists(testFile);
        }
    }

    @Test
    public void testGetAllPlayers() throws IOException {
        Path testFile = Files.createTempFile("test-csv", ".csv");
        try {
            CSVManager csvManager = new CSVManager(testFile);
            csvManager.saveOrUpdatePlayer(new PlayerData("Bob", 120));
            csvManager.saveOrUpdatePlayer(new PlayerData("Carol", 80));

            List<PlayerData> players = csvManager.getAllPlayers();
            assertEquals(2, players.size());

            PlayerData first = players.get(0);
            PlayerData second = players.get(1);
            assertEquals("bob", first.getName().toLowerCase());
            assertEquals(120, first.getBalance());
            assertEquals("carol", second.getName().toLowerCase());
            assertEquals(80, second.getBalance());
        } finally {
            Files.deleteIfExists(testFile);
        }
    }
}