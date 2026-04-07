package SvartJack;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CSVManager {

    private final Path filePath;

    // Constructor: tar Path til CSV-filen
    public CSVManager(Path filePath) {
        this.filePath = filePath;

        // Hvis filen ikke finnes, lag den med header
        if (!Files.exists(filePath)) {
            try (FileWriter writer = new FileWriter(filePath.toFile())) {
                writer.write("name,balance\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /** Legger til eller oppdaterer en spiller i CSV */
    public void saveOrUpdatePlayer(PlayerData player) {
        try {
            List<String> lines = Files.readAllLines(filePath);
            List<String> output = new ArrayList<>();
            
            // behold header
            output.add(lines.get(0));

            boolean found = false;

            for (int i = 1; i < lines.size(); i++) {
                String[] parts = lines.get(i).split(",");
                String existingName = parts[0];

                if (existingName.equals(player.getName())) {
                    // oppdater balance
                    output.add(player.getName() + "," + player.getBalance());
                    found = true;
                } else {
                    output.add(lines.get(i));
                }
            }

            // hvis ikke funnet, legg til på slutten
            if (!found) {
                output.add(player.getName() + "," + player.getBalance());
            }

            Files.write(filePath, output);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Henter alle navn i CSV */
    public List<String> getAllNames() {
        List<String> names = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // hopp over header
                    continue;
                }
                String[] parts = line.split(",");
                names.add(parts[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return names;
    }

    /** Henter alle PlayerData fra CSV */
    public List<PlayerData> getAllPlayers() {
        List<PlayerData> players = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // hopp over header
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String name = parts[0];
                    int balance = Integer.parseInt(parts[1]);
                    players.add(new PlayerData(name, balance));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return players;
    }

    public int getBalance(String playerName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // hopp over header
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length < 2) continue;

                String name = parts[0];
                if (name.equals(playerName)) {
                    return Integer.parseInt(parts[1]);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        // Dette skal ikke skje hvis navnet alltid finnes
        throw new RuntimeException("Player not found or invalid balance in CSV: " + playerName);
    }
}