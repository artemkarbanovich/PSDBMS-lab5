package karbanovich.fit.bstu.hashtbl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;


public class MainActivity extends AppCompatActivity {

    private EditText key;
    private EditText value;
    private EditText searchKey;
    private EditText valueKey;
    private Toast notification;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding();
        FileHelper.existFile(this);
    }

    public void save(View v) {
        FileHelper.existFile(this);

        if(validation())
            Hashtbl.add(this, key.getText().toString(), value.getText().toString());
        else {
            notification = Toast.makeText(this, "Проверьте введенные данные", Toast.LENGTH_LONG);
            notification.show();
        }
    }

    public void get(View v) {
        FileHelper.existFile(this);
        String result = Hashtbl.get(this, searchKey.getText().toString());

        if(result != null)
            valueKey.setText(result);
        else {
            valueKey.setText("");
            notification = Toast.makeText(this, "Такого ключа не существует", Toast.LENGTH_LONG);
            notification.show();
        }
    }

    private boolean validation() {
        int keyLength = key.getText().toString().length();
        int valueLength = value.getText().toString().length();

        if(keyLength < 1 || keyLength > 5) return false;
        if(valueLength < 1 || valueLength > 10) return false;

        return true;
    }

    private void binding() {
        key = findViewById(R.id.key);
        value = findViewById(R.id.value);
        searchKey = findViewById(R.id.searchKey);
        valueKey = findViewById(R.id.valueKey);
    }
}