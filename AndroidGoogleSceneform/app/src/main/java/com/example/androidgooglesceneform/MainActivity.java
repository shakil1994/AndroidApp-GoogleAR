package com.example.androidgooglesceneform;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArFragment arFragment;
    private ModelRenderable bearRenderable,
            catRenderable,
            cowRenderable,
            dogRenderable,
            elephantRenderable,
            ferretRenderable,
            hippopotamusRenderable,
            horseRenderable,
            koalaRenderable,
            lionRenderable,
            reindeerRenderable,
            wolverineRenderable;
    ImageView bear, cat, cow, dog, elephant, ferret, hippo, horse, koala, lion, reindeer, wolverine;

    View arrayView[];
    ViewRenderable name_animal;
    int selected = 1; //Default Bear is Choose

    ViewRenderable animal_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.sceneform_ux_fragment);

        //View
        bear = findViewById(R.id.bear);
        cat = findViewById(R.id.cat);
        cow = findViewById(R.id.cow);
        dog = findViewById(R.id.dog);
        elephant = findViewById(R.id.elephant);
        ferret = findViewById(R.id.ferret);
        hippo = findViewById(R.id.hippopotamus);
        horse = findViewById(R.id.horse);
        koala = findViewById(R.id.koala_bear);
        lion = findViewById(R.id.lion);
        reindeer = findViewById(R.id.reindeer);
        wolverine = findViewById(R.id.wolverine);

        setArrayView();
        setClickListener();

        setupModel();

        arFragment.setOnTapArPlaneListener(new BaseArFragment.OnTapArPlaneListener() {
            @Override
            public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {
                //When user tap on plane , we will add model

                Anchor anchor = hitResult.createAnchor();
                AnchorNode anchorNode = new AnchorNode(anchor);
                anchorNode.setParent(arFragment.getArSceneView().getScene());

                createModel(anchorNode, selected);

            }
        });
    }

    private void setupModel() {

        /*ViewRenderable.builder().setView(this, R.layout.name_animal).build().thenAccept(renderable -> name_animal = renderable);*/

        ModelRenderable.builder().setSource(this, R.raw.bear)
                .build().thenAccept(renderable -> bearRenderable = renderable)
                .exceptionally(throwable -> {
                    Toast toast = Toast.makeText(this, "Unable to load bear model", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return null;
                });

        ModelRenderable.builder().setSource(this, R.raw.cat)
                .build().thenAccept(renderable -> catRenderable = renderable)
                .exceptionally(throwable -> {
                    Toast toast = Toast.makeText(this, "Unable to load cat model", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return null;
                });

        ModelRenderable.builder().setSource(this, R.raw.cow)
                .build().thenAccept(renderable -> cowRenderable = renderable)
                .exceptionally(throwable -> {
                    Toast.makeText(this, "Unable to load cow model", Toast.LENGTH_SHORT).show();
                    return null;
                });

        ModelRenderable.builder().setSource(this, R.raw.dog)
                .build().thenAccept(renderable -> dogRenderable = renderable)
                .exceptionally(throwable -> {
                    Toast.makeText(this, "Unable to load dog model", Toast.LENGTH_SHORT).show();
                    return null;
                });

        ModelRenderable.builder().setSource(this, R.raw.elephant)
                .build().thenAccept(renderable -> elephantRenderable = renderable)
                .exceptionally(throwable -> {
                    Toast.makeText(this, "Unable to load elephant model", Toast.LENGTH_SHORT).show();
                    return null;
                });

        ModelRenderable.builder().setSource(this, R.raw.ferret)
                .build().thenAccept(renderable -> ferretRenderable = renderable)
                .exceptionally(throwable -> {
                    Toast.makeText(this, "Unable to load ferret model", Toast.LENGTH_SHORT).show();
                    return null;
                });

        ModelRenderable.builder().setSource(this, R.raw.hippopotamus)
                .build().thenAccept(renderable -> hippopotamusRenderable = renderable)
                .exceptionally(throwable -> {
                    Toast.makeText(this, "Unable to load hippopotamus model", Toast.LENGTH_SHORT).show();
                    return null;
                });

        ModelRenderable.builder().setSource(this, R.raw.horse)
                .build().thenAccept(renderable -> horseRenderable = renderable)
                .exceptionally(throwable -> {
                    Toast.makeText(this, "Unable to load horse model", Toast.LENGTH_SHORT).show();
                    return null;
                });

        ModelRenderable.builder().setSource(this, R.raw.koala_bear)
                .build().thenAccept(renderable -> koalaRenderable = renderable)
                .exceptionally(throwable -> {
                    Toast.makeText(this, "Unable to load koala model", Toast.LENGTH_SHORT).show();
                    return null;
                });

        ModelRenderable.builder().setSource(this, R.raw.lion)
                .build().thenAccept(renderable -> lionRenderable = renderable)
                .exceptionally(throwable -> {
                    Toast.makeText(this, "Unable to load lion model", Toast.LENGTH_SHORT).show();
                    return null;
                });

        ModelRenderable.builder().setSource(this, R.raw.reindeer)
                .build().thenAccept(renderable -> reindeerRenderable = renderable)
                .exceptionally(throwable -> {
                    Toast.makeText(this, "Unable to load reindeer model", Toast.LENGTH_SHORT).show();
                    return null;
                });

        ModelRenderable.builder().setSource(this, R.raw.wolverine)
                .build().thenAccept(renderable -> wolverineRenderable = renderable)
                .exceptionally(throwable -> {
                    Toast.makeText(this, "Unable to load wolverine model", Toast.LENGTH_SHORT).show();
                    return null;
                });
    }

    private void createModel(AnchorNode anchorNode, int selected) {
        if (selected == 1) {
            TransformableNode bear = new TransformableNode(arFragment.getTransformationSystem());
            bear.setParent(anchorNode);
            bear.setRenderable(bearRenderable);
            bear.select();

            addName(anchorNode, bear, "Bear");
        }

        if (selected == 2) {
            TransformableNode cat = new TransformableNode(arFragment.getTransformationSystem());
            cat.setParent(anchorNode);
            cat.setRenderable(catRenderable);
            cat.select();

            addName(anchorNode, cat, "Cat");
        }

        if (selected == 3) {
            TransformableNode cow = new TransformableNode(arFragment.getTransformationSystem());
            cow.setParent(anchorNode);
            cow.setRenderable(cowRenderable);
            cow.select();

            addName(anchorNode, cow, "Cow");
        }

        if (selected == 4) {
            TransformableNode dog = new TransformableNode(arFragment.getTransformationSystem());
            dog.setParent(anchorNode);
            dog.setRenderable(dogRenderable);
            dog.select();

            addName(anchorNode, dog, "Dog");
        }

        if (selected == 5) {
            TransformableNode elephant = new TransformableNode(arFragment.getTransformationSystem());
            elephant.setParent(anchorNode);
            elephant.setRenderable(elephantRenderable);
            elephant.select();

            addName(anchorNode, elephant, "Elephant");
        }

        if (selected == 6) {
            TransformableNode ferret = new TransformableNode(arFragment.getTransformationSystem());
            ferret.setParent(anchorNode);
            ferret.setRenderable(ferretRenderable);
            ferret.select();

            addName(anchorNode, ferret, "Ferret");
        }

        if (selected == 7) {
            TransformableNode hippopotamus = new TransformableNode(arFragment.getTransformationSystem());
            hippopotamus.setParent(anchorNode);
            hippopotamus.setRenderable(hippopotamusRenderable);
            hippopotamus.select();

            addName(anchorNode, hippopotamus, "Hippopotamus");
        }

        if (selected == 8) {
            TransformableNode horse = new TransformableNode(arFragment.getTransformationSystem());
            horse.setParent(anchorNode);
            horse.setRenderable(horseRenderable);
            horse.select();

            addName(anchorNode, horse, "Horse");
        }

        if (selected == 9) {
            TransformableNode koala = new TransformableNode(arFragment.getTransformationSystem());
            koala.setParent(anchorNode);
            koala.setRenderable(koalaRenderable);
            koala.select();

            addName(anchorNode, koala, "Koala Bear");
        }

        if (selected == 10) {
            TransformableNode lion = new TransformableNode(arFragment.getTransformationSystem());
            lion.setParent(anchorNode);
            lion.setRenderable(lionRenderable);
            lion.select();

            addName(anchorNode, lion, "Lion");
        }

        if (selected == 11) {
            TransformableNode reindeer = new TransformableNode(arFragment.getTransformationSystem());
            reindeer.setParent(anchorNode);
            reindeer.setRenderable(reindeerRenderable);
            reindeer.select();

            addName(anchorNode, reindeer, "Reindeer");
        }

        if (selected == 12) {
            TransformableNode wolverine = new TransformableNode(arFragment.getTransformationSystem());
            wolverine.setParent(anchorNode);
            wolverine.setRenderable(wolverineRenderable);
            wolverine.select();

            addName(anchorNode, wolverine, "Wolverine");
        }
    }

    private void addName(AnchorNode anchorNode, TransformableNode model, String name) {

        ViewRenderable.builder().setView(this, R.layout.name_animal).build().thenAccept(renderable -> {
            TransformableNode nameView = new TransformableNode(arFragment.getTransformationSystem());
            nameView.setLocalPosition(new Vector3(0f, model.getLocalPosition().y + 0.5f, 0));
            nameView.setParent(anchorNode);
            nameView.setRenderable(name_animal);
            nameView.select();

            //Set Text
            TextView txt_name = (TextView) name_animal.getView();
            txt_name.setText(name);

            //Click to Text view to remove animal
            txt_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    anchorNode.setParent(null);
                }
            });
        });
    }


    private void setClickListener() {
        for (int i = 0; i < arrayView.length; i++) {
            arrayView[i].setOnClickListener(this);
        }
    }

    private void setArrayView() {
        arrayView = new View[]{
                bear, cat, cow, dog, elephant, ferret, hippo, horse, koala, lion, reindeer, wolverine
        };
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bear){
            selected = 1;
            setBackground(v.getId());
        }
        else if (v.getId() == R.id.cat){
            selected = 2;
            setBackground(v.getId());
        }
        else if (v.getId() == R.id.cow){
            selected = 3;
            setBackground(v.getId());
        }
        else if (v.getId() == R.id.dog){
            selected = 4;
            setBackground(v.getId());
        }
        else if (v.getId() == R.id.elephant){
            selected = 5;
            setBackground(v.getId());
        }
        else if (v.getId() == R.id.ferret){
            selected = 6;
            setBackground(v.getId());
        }
        else if (v.getId() == R.id.hippopotamus){
            selected = 7;
            setBackground(v.getId());
        }
        else if (v.getId() == R.id.horse){
            selected = 8;
            setBackground(v.getId());
        }
        else if (v.getId() == R.id.koala_bear){
            selected = 9;
            setBackground(v.getId());
        }
        else if (v.getId() == R.id.lion){
            selected = 10;
            setBackground(v.getId());
        }
        else if (v.getId() == R.id.reindeer){
            selected = 11;
            setBackground(v.getId());
        }
        else if (v.getId() == R.id.wolverine){
            selected = 12;
            setBackground(v.getId());
        }

    }

    private void setBackground(int id) {
        for (int i = 0; i < arrayView.length; i++){
            if (arrayView[i].getId() == id){
                arrayView[i].setBackgroundColor(Color.parseColor("#80333639")); //Set Background color for selected animal
            }
            else {
                arrayView[i].setBackgroundColor(Color.TRANSPARENT);
            }
        }
    }
}
