package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import model.Db;

public class DBService {

    private Db db;

    private void loadDb() {
        try {
            //path = j.getSelectedFile().getAbsolutePath();

            File ff = new File("inventory.ser");
            if (ff.exists()) {
                FileInputStream fi = new FileInputStream(ff);
                ObjectInputStream oi = new ObjectInputStream(fi);
                db = (Db) oi.readObject();
            } else {
                db = new Db();
            }

            //JOptionPane.showMessageDialog(null, "Successfully Updated.", "Alert", JOptionPane.WARNING_MESSAGE);
        } catch (IOException | ClassNotFoundException e) {
        }

    }

    public void saveDbObject() {

        FileOutputStream ObjectInput;
        ObjectOutputStream ObjectOut;

        File ff = new File("inventory.ser");

        if (!ff.exists()) {
            try {
                ff.createNewFile();
            } catch (Exception e) {
            }
        }
        try {
            ObjectInput = new FileOutputStream(ff.getAbsolutePath());
            ObjectOut = new ObjectOutputStream(ObjectInput);

            ObjectOut.writeObject(db);
            ObjectOut.close();
            ObjectInput.close();

        } catch (IOException e) {
        }
    }

    public void saveDb(Db dbco) {
        this.db = dbco;
        saveDbObject();
    }

    public Db getDB() {
        loadDb();
        return db;
    }

}
