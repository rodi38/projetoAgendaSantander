package util;

import java.io.*;

public class FileObjectUtils {

    public static Object readObjectFromPath(String path) {

        Object obj = null;

        try {

            FileInputStream fileInputStream = new FileInputStream(new File(path));
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            obj = objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();

        } catch (IOException ioException) {
            System.err.println(ioException.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }

        return obj;
    }

    public static void writeObjectToPath(String path, Object object) {

        try {

            FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(object);

            objectOutputStream.close();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}

