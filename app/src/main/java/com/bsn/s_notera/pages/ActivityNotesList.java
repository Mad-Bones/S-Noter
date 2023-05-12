package com.bsn.s_notera.pages;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bsn.s_notera.R;
import com.bsn.s_notera.adapters.RecyclerMultipleAdapterCompact;
import com.bsn.s_notera.adapters.RecyclerMultipleAdapterList;
import com.bsn.s_notera.adapters.RecyclerMultipleListener;
import com.bsn.s_notera.adapters.SearchInsideAdapter;
import com.bsn.s_notera.constanst.ConstAppButtBack;
import com.bsn.s_notera.constanst.ConstAppIcons;
import com.bsn.s_notera.constanst.ConstAppMode;
import com.bsn.s_notera.constanst.ConstAppVisuals;
import com.bsn.s_notera.constanst.ConstNoterRoutes;
import com.bsn.s_notera.dialogs.DialogDeleteList;
import com.bsn.s_notera.dialogs.DialogExit;
import com.bsn.s_notera.pages.database.NotasHelper;
import com.bsn.s_notera.pages.database.Objeto;
import com.bsn.s_notera.preferences.PreferencesAdapter;
import com.bsn.s_notera.tools.bars.AddEmptyBarContainer;
import com.bsn.s_notera.tools.buttons.AddIconButtonsSmall;
import com.bsn.s_notera.tools.buttons.AddSimpleTextButton;
import com.bsn.s_notera.tools.containers.AddEmptyBoxContainerInf;
import com.bsn.s_notera.tools.containers.AddEmptyBoxContainerRelative;
import com.bsn.s_notera.tools.containers.AddEmptyBoxContainerSup;
import com.bsn.s_notera.tools.containers.AddEmptyRecyclerContainer;
import com.bsn.s_notera.tools.text.AddTextSearchBar;
import com.bsn.s_notera.tools.text.AddTextTitleSelectable;

import java.util.ArrayList;
import java.util.List;

public class ActivityNotesList extends AppCompatActivity implements RecyclerMultipleListener {

    String title_text = "";
    String selectedRoute;
    String lastTitle;
    TextView button_new;
    EditText search_field;
    ImageView search_button,settings_button,view_button,trash_button,delete_button;
    RecyclerView recyclerView;
    ArrayList<Objeto> notesListArray;
    ArrayList<String> notesTitleArray;

    RecyclerMultipleAdapterList recyclerMultipleAdapterList;
    RecyclerMultipleAdapterCompact recyclerMultipleAdapterCompact;
    StaggeredGridLayoutManager listaManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
    StaggeredGridLayoutManager titlesManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

    int typeView = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_main);
        String mode = new PreferencesAdapter(ActivityNotesList.this).inMode();
        typeView = new PreferencesAdapter(this).viewTypeInt();
        selectedRoute = new PreferencesAdapter(this).primaryRoute();
        lastTitle = new PreferencesAdapter(this).titleSearch();
        if(mode.matches(ConstAppMode.inicial)){
            new PreferencesAdapter(this).setinMode(ConstAppMode.noter);
        }

        notesListArray = new ArrayList<>();
        notesTitleArray = new ArrayList<>();

        LinearLayout boxA = new AddEmptyBoxContainerSup().BoxContainer(this);
        LinearLayout sup_barA = new AddEmptyBarContainer().BoxContainer(this,boxA);
        search_field = new AddTextSearchBar().Add(this,"",sup_barA);
        search_button = new AddIconButtonsSmall().Add(this,sup_barA, ConstAppIcons.search,R.color.black, ConstAppButtBack.green);
        view_button = new AddIconButtonsSmall().Add(this,sup_barA,ConstAppIcons.ic_view,R.color.black, ConstAppButtBack.green);
        trash_button  = new AddIconButtonsSmall().Add(this,sup_barA,ConstAppIcons.ic_trash,R.color.black, ConstAppButtBack.green);
        settings_button = new AddIconButtonsSmall().Add(this,sup_barA,ConstAppIcons.ic_about,R.color.black, ConstAppButtBack.green);
        delete_button  = new AddIconButtonsSmall().Add(this,sup_barA,ConstAppIcons.ic_delete,R.color.black, ConstAppButtBack.green);
        delete_button.setVisibility(View.GONE);

        LinearLayout boxC = new AddEmptyBoxContainerInf().BoxContainer(this);
        button_new = new AddSimpleTextButton().TextButton(this,"Nuevo",boxC);

        primaryRoute();

        LinearLayout sup_barB = new AddEmptyBarContainer().BoxContainer(this,boxA);
        new AddTextTitleSelectable(this,title_text,R.color.black,R.color.general_green,sup_barB);

        search_field.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                if(typeView == ConstAppVisuals.VistaLista){
                    new SearchInsideAdapter.SearchinLists(ActivityNotesList.this,recyclerMultipleAdapterList,search_field).execute();
                }else if(typeView == ConstAppVisuals.VistaCompacta){
                    new SearchInsideAdapter.SearchinTitles(ActivityNotesList.this,recyclerMultipleAdapterCompact,search_field).execute();
                }
            }
            return true;
        });
        search_button.setOnClickListener(v->{
            if(typeView == ConstAppVisuals.VistaLista){
                new SearchInsideAdapter.SearchinLists(ActivityNotesList.this,recyclerMultipleAdapterList,search_field).execute();
            }else if(typeView == ConstAppVisuals.VistaCompacta){
                new SearchInsideAdapter.SearchinTitles(ActivityNotesList.this,recyclerMultipleAdapterCompact,search_field).execute();
            }
        });
        view_button.setOnClickListener(view ->{
            if(typeView == ConstAppVisuals.VistaLista){
                new PreferencesAdapter(this).setviewTypeInt(ConstAppVisuals.VistaCompacta);
            }else if(typeView == ConstAppVisuals.VistaCompacta){
                new PreferencesAdapter(this).setviewTypeInt(ConstAppVisuals.VistaLista);
            }
            if(selectedRoute.matches(ConstNoterRoutes.RouteTrash)){
                new PreferencesAdapter(this).setviewTypeInt(ConstAppVisuals.VistaLista);
            }
            new PreferencesAdapter(this).setprimaryRoute(ConstNoterRoutes.RouteMain);
            new PreferencesAdapter(this).setTitleSearch("");
            startActivity(new Intent(this,ActivityNotesList.class));
            finish();
        });
        settings_button.setOnClickListener(view -> {
            startActivity(new Intent(this, ActivityNotesAbout.class));
            finish();
        });
        trash_button.setOnClickListener(view -> {

            new PreferencesAdapter(this).setprimaryRoute(ConstNoterRoutes.RouteTrash);
            startActivity(new Intent(this,ActivityNotesList.class));
            finish();
        });
        delete_button.setOnClickListener(view -> {
            new DialogDeleteList.show(this,notesListArray);
        });

        LinearLayout boxB = new AddEmptyBoxContainerRelative().BoxContainer(this);
        recyclerView = new AddEmptyRecyclerContainer().Recicler(this,boxB);

        button_new.setOnClickListener(view -> goCreateNote());
        setAdapters();

    }
    private void primaryRoute(){
        if(selectedRoute.matches(ConstNoterRoutes.RouteMain)){
            if(lastTitle.isEmpty()){
                title_text = "SNoter";
            }else{
                title_text = lastTitle;
            }
            trash_button.setVisibility(View.VISIBLE);
        }else if(selectedRoute.matches(ConstNoterRoutes.RouteTrash)){
            title_text = "Papelera";
            delete_button.setVisibility(View.VISIBLE);
            trash_button.setVisibility(View.GONE);
            button_new.setVisibility(View.GONE);
        }
    }
    public void setAdapters(){
        if(typeView == ConstAppVisuals.VistaLista){
            recyclerView.setLayoutManager(listaManager);
            if(selectedRoute.matches(ConstNoterRoutes.RouteMain) && lastTitle.isEmpty()){
                trash_button.setVisibility(View.VISIBLE);
                notesListArray = new NotasHelper(this).getNotesArray(this,ConstNoterRoutes.RouteMain);
            }else if(selectedRoute.matches(ConstNoterRoutes.RouteMain) && !lastTitle.isEmpty()){
                trash_button.setVisibility(View.VISIBLE);
                notesListArray = new NotasHelper(this).getNotesArraybyTitle(this,ConstNoterRoutes.RouteMain,lastTitle);
            }else if(selectedRoute.matches(ConstNoterRoutes.RouteTrash)){
                trash_button.setVisibility(View.GONE);
                new PreferencesAdapter(this).setTitleSearch("");
                notesListArray = new NotasHelper(this).getNotesArray(this,ConstNoterRoutes.RouteTrash);
            }
            recyclerMultipleAdapterList = new RecyclerMultipleAdapterList(
                    this,
                    notesListArray,
                    this
            );
        }else if(typeView == ConstAppVisuals.VistaCompacta){
            trash_button.setVisibility(View.GONE);
            recyclerView.setLayoutManager(titlesManager);
            notesTitleArray = new NotasHelper(this).getTitullis(this);
            recyclerMultipleAdapterCompact = new RecyclerMultipleAdapterCompact(
                    this,
                    notesTitleArray,
                    this
            );
        }
        loadRecicler();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void loadRecicler(){
        if(typeView == ConstAppVisuals.VistaLista){
            recyclerView.setAdapter(recyclerMultipleAdapterList);
            recyclerMultipleAdapterList.notifyDataSetChanged();
        }else if(typeView == ConstAppVisuals.VistaCompacta){
            recyclerView.setAdapter(recyclerMultipleAdapterCompact);
            recyclerMultipleAdapterCompact.notifyDataSetChanged();
        }

    }



    public void goCreateNote(){
        startActivity(new Intent(ActivityNotesList.this,ActivityNotesCreate.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        new DialogExit.show(this);
    }

    //LISTENERS PRE-CONSTRUCTED
    @Override
    public void onTitleClicked(String string, int position) {
        new PreferencesAdapter(this).setviewTypeInt(ConstAppVisuals.VistaLista);
        new PreferencesAdapter(this).setprimaryRoute(ConstNoterRoutes.RouteMain);
        new PreferencesAdapter(this).setTitleSearch(string);

        startActivity(new Intent(this,ActivityNotesList.class));
        finish();

    }
    @Override
    public void onTextClicked(Objeto note, int position) {
        final Intent noteclicked = new Intent(this, ActivityNotesCreate.class);
        noteclicked.putExtra("update", true);
        noteclicked.putExtra("note",note);
        startActivity(noteclicked);
        finish();
    }
    @Override
    public void onTextLongClicked(Objeto note, int position) {

    }
}
