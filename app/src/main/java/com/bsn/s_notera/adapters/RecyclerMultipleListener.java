package com.bsn.s_notera.adapters;

import com.bsn.s_notera.pages.database.Objeto;

public interface RecyclerMultipleListener {
    void onTitleClicked(String string, int position);
    void onTextClicked(Objeto note, int position);
    void onTextLongClicked(Objeto note, int position);
}
