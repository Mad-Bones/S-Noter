package com.bsn.s_notera.pages;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bsn.s_notera.R;
import com.bsn.s_notera.pages.database.NotasHelper;
import com.bsn.s_notera.tools.buttons.AddContactButtons;
import com.bsn.s_notera.tools.text.AddTextTiped;
import com.bsn.s_notera.tools.text.AddTextTitle;

import java.util.ArrayList;

public class ActivityNotesAbout extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.container_main);
            new AddTextTitle(this,getString(R.string.about_text_title));
            new AddTextTiped(this,"Simple Noter:\n\nAnotador sencillo, usa RoomDatabase. Contiene funcion para compartir texto dentro y fuera de la app\n\nREQUIERE PERMISOS DE ALMACENAMIENTO\n\n==========  ==========\nCreado por Mad-BoneS");
            new AddContactButtons(
                    this,
                    getString(R.string.buttons_title_text),
                    getString(R.string.my_email_address),
                    getString(R.string.my_full_phone_number),
                    getString(R.string.my_github_repository));

        }

        @Override
        public void onBackPressed() {
            startActivity(new Intent(this,ActivityNotesList.class));
            finish();
        }
}
