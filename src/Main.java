import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Main {

    public static void main(String[] args) {
        String sourceDirectory = ".";
        String backupDirectory = "./backup";

        try {
            backupFiles(sourceDirectory, backupDirectory);
            System.out.println("Копия успешно создана в папке " + backupDirectory);
        } catch (IOException e) {
            System.err.println("Ошибка при создании копии: " + e.getMessage());
        }
    }

    /**
     * Написать функцию, создающую резервную копию всех файлов в директории(без поддиректорий)
     * во вновь созданную папку ./backup
     * @param sourceDir путь к копируемой директории
     * @param backupDir путь, где будет создана копия
     */
    static void backupFiles(String sourceDir, String backupDir) throws IOException {
        File source = new File(sourceDir);
        File backup = new File(backupDir);

        if (!source.exists() || !source.isDirectory()) {
            throw new IllegalArgumentException("Указанная директория не существует или не является директорией.");
        }

        if (!backup.exists()) {
            backup.mkdir();
        }

        for (File file : source.listFiles()) {
            if (file.isFile()) {
                Path sourcePath = file.toPath();
                Path targetPath = backup.toPath().resolve(file.getName());

                Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
            }
        }


    }
}





