package com.mukeri.pearassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.transition.Transition;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.transition.MaterialContainerTransform;

public class MainActivity2 extends AppCompatActivity {

    CoordinatorLayout coordinator;
    MaterialCardView card;
    FloatingActionButton floating_action_button;
    AppCompatButton menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        menu=findViewById(R.id.menu);
        coordinator=findViewById(R.id.coordinator);
        card=findViewById(R.id.card);
        floating_action_button=findViewById(R.id.floating_action_button);



        floating_action_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialContainerTransform materialContainerTransform = new MaterialContainerTransform();
                materialContainerTransform.setDuration(300);
                materialContainerTransform.setScrimColor(getResources().getColor(R.color.theme));
                materialContainerTransform.setFadeMode(MaterialContainerTransform.FADE_MODE_CROSS);
                materialContainerTransform.setStartView(floating_action_button);
                materialContainerTransform.setEndView(card);
                //TransitionManager.beginDelayedTransition(coordinator,materialContainerTransform);
                materialContainerTransform.addTarget(menu);
            }
        });
    }

    private void buildcontainer()
    {
        MaterialContainerTransform materialContainerTransform = new MaterialContainerTransform();
        materialContainerTransform.setDuration(300);
        materialContainerTransform.setScrimColor(getResources().getColor(R.color.theme));
        materialContainerTransform.setFadeMode(MaterialContainerTransform.FADE_MODE_IN);
        materialContainerTransform.addTarget(menu);
    }

}