import java.io.*;
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


        int[] array = {1, 2, 0, 3, 2, 1, 0, 3, 1};
        String fileName = "file.txt";

        packNum(fileName, array);
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

    /**
     * числа в исходном массиве из 9 элементов имеют диапазон[0, 3],
     * и представляют собой, например, состояния ячеек поля для игры в крестикинолики,
     * где 0 – это пустое поле, 1 – это поле с крестиком, 2 – это поле с ноликом,3 – резервное значение.
     * Такое предположение позволит хранить в одном числе типа int всё поле 3х3.
     * Записать в файл 9 значений так, чтобы они заняли три байта
     * @param file для хранения битовых значений
     * @param arr входящий массив
     */
    private static void packNum(String file, int[] arr){
        try (DataOutputStream input = new DataOutputStream(new FileOutputStream(file))) {
            for (int value : arr) {
                input.writeByte(value);
            }
            System.out.println("Запись выполнена.");
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }


}





