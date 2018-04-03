package ru.dve.copypicture;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class CopyPicture {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Введите начальный путь картинки");
        String dir1 = sc.nextLine();
        System.out.println("Введите путь куда скопировать картинку ");
        String dir2 = sc.nextLine();
        boolean del = true;
        boolean scan = true;
        CopyFiles.scannerDir(dir1, dir2, del, scan);
    }

    private static class CopyFiles {
        private static void copyFile(File in, File out) {
            byte buffer[] = new byte[100000000];
            try {
                FileInputStream fileIn = new FileInputStream(in);
                int bytes = fileIn.read(buffer, 0, 100000000);
                fileIn.close();
                FileOutputStream fileOut = new FileOutputStream(out);
                fileOut.write(buffer, 0, bytes);
                fileOut.close();
            } catch (Exception e) {
            }
        }

        private static void scannerDir(String dir1, String dir2, boolean del, boolean scan) {
            int i;
            File[] list = new File(dir1).listFiles();
            for (i = 0; i < list.length; i++) {
                File file = new File(dir1 + "//" + list[i].getName());
                File file2 = new File(dir2 + "//" + list[i].getName());
                if (!file.isDirectory()) {
                    if (file.isFile() && file.getName().matches(".*\\.png$")) {
                        CopyFiles.copyFile(file, file2);
                        System.out.println("Файл " + list[i] + " скопирован в " + dir2);
                    }
                }
                if (scan == true) {
                    if (file.isDirectory()) {
                        CopyFiles.scannerDir(file.getAbsolutePath(), dir2, del, scan);
                    }
                }

                if ((del == true) && file.isFile()) {
                    if (file.delete()) {
                        System.out.println("Файл" + "//" + list[i] + " удален из " + dir1);
                    }
                }
            }
        }
    }
}




