package com.bsn.s_notera.pages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bsn.s_notera.R;
import com.bsn.s_notera.adapters.AutoTextoAdapter;
import com.bsn.s_notera.constanst.ConstNoterRoutes;
import com.bsn.s_notera.pages.database.NotasHelper;
import com.bsn.s_notera.pages.database.Objeto;
import com.bsn.s_notera.tools.buttons.AddSimpleTextButton;
import com.bsn.s_notera.tools.containers.AddEmptyBoxContainer;
import com.bsn.s_notera.tools.containers.AddEmptyBoxContainerInf;
import com.bsn.s_notera.tools.text.AddTextEditUnderlined;
import com.bsn.s_notera.tools.text.AddTextEditWhitList;
import com.bsn.s_notera.tools.text.AddTextSimple;
import com.bsn.s_notera.tools.text.AddTextTitleSubtitle;
import com.bsn.s_notera.tools.text.EditTextUnderlined;
import com.bsn.s_notera.tools.text.FilterTime;

import java.util.ArrayList;

public class ActivityNotesCreate extends AppCompatActivity {
    Objeto openObjecto;
    AutoCompleteTextView tittle_edit;
    EditTextUnderlined content_edit;
    TextView button_save,button_back,button_erase;
    long longsActual = System.currentTimeMillis();
    String routeString;
    String tagString, imageString;
    boolean isLocked, isChecked;
    int noteType;
    long timerTime;
    ArrayList<String> notesTitleArray;
    AutoTextoAdapter autoTextoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_main);
        notesTitleArray = new ArrayList<>();

        notesTitleArray = new NotasHelper(this).getTitullis(this);
        LinearLayout boxA = new AddEmptyBoxContainer().BoxContainer(this);
        new AddTextTitleSubtitle(this,"Nueva Nota",new FilterTime().onTime(longsActual),boxA);
        new AddTextSimple(this,"Titulo:",boxA);
        tittle_edit = new AddTextEditWhitList().TextEditSimple(this,"Inserta un titulo:",boxA,notesTitleArray);
        autoTextoAdapter = new AutoTextoAdapter(this,notesTitleArray);
        tittle_edit.setAdapter(autoTextoAdapter);

        new AddTextSimple(this,"Contenido:",boxA);
        content_edit = new AddTextEditUnderlined().TextEditUnderlined(this,"","Escribe algo:",boxA);

        LinearLayout boxB = new AddEmptyBoxContainerInf().BoxContainer(this);
        button_erase = new AddSimpleTextButton().TextButton(this,"Borrar",boxB);
        button_back = new AddSimpleTextButton().TextButton(this,"Volver",boxB);
        button_save = new AddSimpleTextButton().TextButton(this,"Guardar",boxB);

        preloadData();

        button_back.setOnClickListener(view -> onBackPressed());
        button_save.setOnClickListener(view -> {
            saveObjeto(); 
        });
        button_erase.setOnClickListener(view -> {
            routeString = ConstNoterRoutes.RouteTrash;
            saveObjeto();
        });
    }

    private void preloadData() {

        final Intent intent = getIntent();
        final String action = intent.getAction();
        final String type = intent.getType();

        if (getIntent().getBooleanExtra("update", false)) {
            openObjecto = (Objeto) getIntent().getSerializableExtra("note");
            longsActual = openObjecto.getCreado();
            tittle_edit.setText(openObjecto.getTitulo());
            content_edit.setText(openObjecto.getTexto());
            routeString = openObjecto.getRuta();
            tagString = openObjecto.getEtiqueta();
            imageString = openObjecto.getImagen();
            isChecked = openObjecto.isChecked();
            noteType = openObjecto.getTipo();
            timerTime = openObjecto.getTemp();
            if (routeString.matches(ConstNoterRoutes.RouteLock)){
                isLocked = true;
            }
            if (openObjecto.getRuta().matches(ConstNoterRoutes.RouteTrash)){
                button_erase.setVisibility(View.GONE);
                button_save.setText("restaurar");
            }else{
                button_erase.setVisibility(View.VISIBLE);
            }
        }else if(Intent.ACTION_SEND.equals(action) && type!=null){
            String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
            if (sharedText != null) { content_edit.setText(sharedText); }
            tagString = "";
            imageString = "";
            isChecked = false;
            isLocked = false;
            noteType = 0;
            timerTime = 0;
            routeString = ConstNoterRoutes.RouteMain;
            button_erase.setVisibility(View.GONE);
        }else{
            tagString = "";
            imageString = "";
            isChecked = false;
            isLocked = false;
            noteType = 0;
            timerTime = 0;
            routeString = ConstNoterRoutes.RouteMain;
            button_erase.setVisibility(View.GONE);
        }

    }

    private void saveObjeto(){
        final Objeto objetoa = new Objeto();

        objetoa.setCreado(longsActual);

        if(tittle_edit.getText().toString().matches("")){
            objetoa.setTitulo("Sin titulo");
        }else{
            objetoa.setTitulo(tittle_edit.getText().toString());
        }

        objetoa.setTexto(content_edit.getText().toString());
        objetoa.setRuta(routeString);
        objetoa.setEtiqueta(tagString);
        objetoa.setImagen(imageString);
        objetoa.setChecked(isChecked);
        objetoa.setTipo(noteType);
        objetoa.setTemp(timerTime);
        if (openObjecto!=null){
            objetoa.setId(openObjecto.getId());
        }

        new NotasHelper(this).GuardarNota(objetoa,ActivityNotesCreate.this);
        Toast.makeText(this, "saved Note", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,ActivityNotesList.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,ActivityNotesList.class));
        finish();
    }
}
