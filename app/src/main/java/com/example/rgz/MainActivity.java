package com.example.rgz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    TextView mTextView;
    public static final String LEVEL = "LEVEL";
    public static final String TIME = "TIME";
    public static final String RESULT = "RESULT";
    public static final String FILE_NAME = "result.txt";

    String l;
    int result[] = new int[30];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i = 0; i < 30; i++){
            result[i] = 1000000;
        }
        FileInputStream fin = null;
        File file = new File(getExternalFilesDir(null), FILE_NAME);



        if(file.exists())                                                // если файл не существует
            try {
                fin =  new FileInputStream(file);
                byte[] bytes = new byte[fin.available()];
                fin.read(bytes);
                String text = new String (bytes);

                for(int i = 0, id = 0; i != text.length(); )
                    for(int j = 0; j != 3; j++, i++, id++){
                        String t = "";
                        while(text.charAt(i) != ' ') t += text.charAt(i++);
                        result[id] = Integer.valueOf(t);
                    }

            }
            catch(IOException ex) { Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();}
            finally{
                try{ if(fin!=null) fin.close();}
                catch(IOException ex){ Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();}
            }
    }

    public void setLevel(View v){
        Intent intent = new Intent(this, GameActivity.class);
        String level = getResources().getResourceEntryName(v.getId());
        l = "" + level.charAt(level.length()-1);
        intent.putExtra(LEVEL, l);
        startActivityForResult(intent, 0);
        //Toast.makeText(this, l, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK){
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            int rest = data.getIntExtra(TIME, 0);
            int id = Integer.parseInt(l);
            //Toast.makeText(this, Integer.toString(id), Toast.LENGTH_SHORT).show();
            id *= 3;
            for(int j = 0; j < 3; j++, id++){
                if(rest < result[id]){
                    int t = rest;
                    rest = result[id];
                    result[id] = t;
                }
            }
        }
        else{
            Toast.makeText(this, "Time is up", Toast.LENGTH_SHORT).show();
        }
    }

    private File getExternalPath() {
        return new File(getExternalFilesDir(null), FILE_NAME);
    }

    @Override
    protected void onStop() {
        FileOutputStream fos = null;
        try {
            String text = "";
            for(int i = 0; i != 27; i++) text += Integer.toString(result[i]) + " ";

            fos = new FileOutputStream(getExternalPath());
            fos.write(text.getBytes());
            //Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
        }
        catch(IOException ex) { Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();}
        finally{
            try{ if(fos!=null) fos.close();}
            catch(IOException ex){ Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();}
        }

        super.onStop();
    }

    @Override
    protected void onDestroy() {
        FileOutputStream fos = null;
        try {
            String text = "";
            for(int i = 0; i != 27; i++) text += Integer.toString(result[i]) + " ";

            fos = new FileOutputStream(getExternalPath());
            fos.write(text.getBytes());
            //Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
        }
        catch(IOException ex) { Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();}
        finally{
            try{ if(fos!=null) fos.close();}
            catch(IOException ex){ Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();}
        }

        super.onDestroy();
    }

    public void LeaderBoard(View view){
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(RESULT, result);
        startActivity(intent);
    }
}