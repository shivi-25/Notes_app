package shivani.com.example.appnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class NotesEditActivity extends AppCompatActivity {
   EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_edit);
       editText=(EditText) findViewById(R.id.editText);
        Intent in =getIntent();
        final int NoteId=in.getIntExtra("NoteId", -1);
        if(NoteId != -1)
        {
            editText.setText(MainActivity.notes.get(NoteId));
        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity.notes.set(NoteId, String.valueOf(s));
                MainActivity.aa.notifyDataSetChanged();
                SharedPreferences sp=getApplicationContext().getSharedPreferences("shivani.com.example.appnotes", Context.MODE_PRIVATE);
                HashSet<String> set=new HashSet(MainActivity.notes);
                sp.edit().putStringSet("notes",set).apply();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
