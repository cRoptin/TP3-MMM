package fr.istic.tp3_mmm;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity implements FragmentList.OnURLSelectedListener {

    private boolean detailed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            FragmentList listFragment = (FragmentList) new FragmentList();
            ft.replace(R.id.fragList, listFragment);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.commit();
        }

        if (findViewById(R.id.fragweb) != null) {
            detailed = true;
            getFragmentManager().popBackStack();

            FragmentMap mapFrag = (FragmentMap) getFragmentManager().findFragmentById(R.id.fragweb);
            if (mapFrag == null) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                mapFrag = new FragmentMap();
                ft.replace(R.id.webView, mapFrag, "Detail_Fragment1");
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        }
    }

    @Override
    public void onURLSelected(String URL) {
        if(detailed){
            FragmentList fragList = (FragmentList) getFragmentManager().findFragmentById(R.id.fragList);
            fragList.updateURLContent(URL);
        }
        else{
            FragmentList fragList = new FragmentList();
            fragList.setURLContent(URL);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragList, fragList, "Detail_Fragment2");
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

}
