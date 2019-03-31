package com.example.kotlingooglesceneform

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.ar.core.HitResult
import com.google.ar.core.Plane
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.ViewRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.BaseArFragment
import com.google.ar.sceneform.ux.TransformableNode
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var arFragment: ArFragment

    lateinit var arrayView: Array<View>

    lateinit var bearRenderable: ModelRenderable
    lateinit var catRenderable: ModelRenderable
    lateinit var cowRenderable: ModelRenderable
    lateinit var dogRenderable: ModelRenderable
    lateinit var elephantRenderable: ModelRenderable
    lateinit var ferretRenderable: ModelRenderable
    lateinit var hippopotamusRenderable: ModelRenderable
    lateinit var horseRenderable: ModelRenderable
    lateinit var koalaRenderable: ModelRenderable
    lateinit var lionRenderable: ModelRenderable
    lateinit var reindeerRenderable: ModelRenderable
    lateinit var wolverineRenderable: ModelRenderable

    internal var selected = 1 //Default Bear is Choose

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setArrayView()
        setupClickListener()

        setupModel()

        arFragment = supportFragmentManager.findFragmentById(R.id.sceneform_ux_fragment) as ArFragment

        arFragment.setOnTapArPlaneListener { hitResult, plane, motionEvent ->
            //When user tap on plane , we will add model

            val anchor = hitResult.createAnchor()
            val anchorNode = AnchorNode(anchor)
            anchorNode.setParent(arFragment.arSceneView.scene)

            createModel(anchorNode, selected)
        }
    }

    private fun createModel(anchorNode: AnchorNode, selected: Int) {
        if (selected == 1) {
            val bear = TransformableNode(arFragment.transformationSystem)
            bear.setParent(anchorNode)
            bear.renderable = bearRenderable
            bear.select()

            addName(anchorNode, bear, "Bear")
        }

        else if (selected == 2) {
            val cat = TransformableNode(arFragment.transformationSystem)
            cat.setParent(anchorNode)
            cat.renderable = catRenderable
            cat.select()

            addName(anchorNode, cat, "Cat")
        }

        else if (selected == 3) {
            val cow = TransformableNode(arFragment.transformationSystem)
            cow.setParent(anchorNode)
            cow.renderable = cowRenderable
            cow.select()

            addName(anchorNode, cow, "Cow")
        }

        else if (selected == 4) {
            val dog = TransformableNode(arFragment.transformationSystem)
            dog.setParent(anchorNode)
            dog.renderable = dogRenderable
            dog.select()

            addName(anchorNode, dog, "Dog")
        }

        else if (selected == 5) {
            val elephant = TransformableNode(arFragment.transformationSystem)
            elephant.setParent(anchorNode)
            elephant.renderable = elephantRenderable
            elephant.select()

            addName(anchorNode, elephant, "Elephant")
        }

        else if (selected == 6) {
            val ferret = TransformableNode(arFragment.transformationSystem)
            ferret.setParent(anchorNode)
            ferret.renderable = ferretRenderable
            ferret.select()

            addName(anchorNode, ferret, "Ferret")
        }

        else if (selected == 7) {
            val hippopotamus = TransformableNode(arFragment.transformationSystem)
            hippopotamus.setParent(anchorNode)
            hippopotamus.renderable = hippopotamusRenderable
            hippopotamus.select()

            addName(anchorNode, hippopotamus, "Hippopotamus")
        }

        else if (selected == 8) {
            val horse = TransformableNode(arFragment.transformationSystem)
            horse.setParent(anchorNode)
            horse.renderable = horseRenderable
            horse.select()

            addName(anchorNode, horse, "Horse")
        }

        else if (selected == 9) {
            val koala = TransformableNode(arFragment.transformationSystem)
            koala.setParent(anchorNode)
            koala.renderable = koalaRenderable
            koala.select()

            addName(anchorNode, koala, "Koala Bear")
        }

        else if (selected == 10) {
            val lion = TransformableNode(arFragment.transformationSystem)
            lion.setParent(anchorNode)
            lion.renderable = lionRenderable
            lion.select()

            addName(anchorNode, lion, "Lion")
        }

        else if (selected == 11) {
            val reindeer = TransformableNode(arFragment.transformationSystem)
            reindeer.setParent(anchorNode)
            reindeer.renderable = reindeerRenderable
            reindeer.select()

            addName(anchorNode, reindeer, "Reindeer")
        }

        else if (selected == 12) {
            val wolverine = TransformableNode(arFragment.transformationSystem)
            wolverine.setParent(anchorNode)
            wolverine.renderable = wolverineRenderable
            wolverine.select()

            addName(anchorNode, wolverine, "Wolverine")
        }
    }

    private fun addName(anchorNode: AnchorNode, node: TransformableNode, name: String) {
        ViewRenderable.builder().setView(this, R.layout.name_animal).build().thenAccept { viewRenderable ->
            val nameView = TransformableNode(arFragment.transformationSystem)
            nameView.localPosition = Vector3(0f, node.localPosition.y + 0.5f, 0f)
            nameView.setParent(anchorNode)
            nameView.renderable = viewRenderable
            nameView.select()

            //Set Text
            val txt_name = viewRenderable.view as TextView
            txt_name.text = name

            //Click to Text view to remove animal
            txt_name.setOnClickListener { anchorNode.setParent(null) }
        }
    }

    private fun setupModel() {
        ModelRenderable.builder().setSource(this, R.raw.bear)
            .build().thenAccept { renderable -> bearRenderable = renderable }
            .exceptionally { throwable ->
                val toast = Toast.makeText(this, "Unable to load bear model", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
                null
            }

        ModelRenderable.builder().setSource(this, R.raw.cat)
            .build().thenAccept { renderable -> catRenderable = renderable }
            .exceptionally { throwable ->
                val toast = Toast.makeText(this, "Unable to load cat model", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
                null
            }

        ModelRenderable.builder().setSource(this, R.raw.cow)
            .build().thenAccept { renderable -> cowRenderable = renderable }
            .exceptionally { throwable ->
                Toast.makeText(this, "Unable to load cow model", Toast.LENGTH_SHORT).show()
                null
            }

        ModelRenderable.builder().setSource(this, R.raw.dog)
            .build().thenAccept { renderable -> dogRenderable = renderable }
            .exceptionally { throwable ->
                Toast.makeText(this, "Unable to load dog model", Toast.LENGTH_SHORT).show()
                null
            }

        ModelRenderable.builder().setSource(this, R.raw.elephant)
            .build().thenAccept { renderable -> elephantRenderable = renderable }
            .exceptionally { throwable ->
                Toast.makeText(this, "Unable to load elephant model", Toast.LENGTH_SHORT).show()
                null
            }

        ModelRenderable.builder().setSource(this, R.raw.ferret)
            .build().thenAccept { renderable -> ferretRenderable = renderable }
            .exceptionally { throwable ->
                Toast.makeText(this, "Unable to load ferret model", Toast.LENGTH_SHORT).show()
                null
            }

        ModelRenderable.builder().setSource(this, R.raw.hippopotamus)
            .build().thenAccept { renderable -> hippopotamusRenderable = renderable }
            .exceptionally { throwable ->
                Toast.makeText(this, "Unable to load hippopotamus model", Toast.LENGTH_SHORT).show()
                null
            }

        ModelRenderable.builder().setSource(this, R.raw.horse)
            .build().thenAccept { renderable -> horseRenderable = renderable }
            .exceptionally { throwable ->
                Toast.makeText(this, "Unable to load horse model", Toast.LENGTH_SHORT).show()
                null
            }

        ModelRenderable.builder().setSource(this, R.raw.koala_bear)
            .build().thenAccept { renderable -> koalaRenderable = renderable }
            .exceptionally { throwable ->
                Toast.makeText(this, "Unable to load koala model", Toast.LENGTH_SHORT).show()
                null
            }

        ModelRenderable.builder().setSource(this, R.raw.lion)
            .build().thenAccept { renderable -> lionRenderable = renderable }
            .exceptionally { throwable ->
                Toast.makeText(this, "Unable to load lion model", Toast.LENGTH_SHORT).show()
                null
            }

        ModelRenderable.builder().setSource(this, R.raw.reindeer)
            .build().thenAccept { renderable -> reindeerRenderable = renderable }
            .exceptionally { throwable ->
                Toast.makeText(this, "Unable to load reindeer model", Toast.LENGTH_SHORT).show()
                null
            }

        ModelRenderable.builder().setSource(this, R.raw.wolverine)
            .build().thenAccept { renderable -> wolverineRenderable = renderable }
            .exceptionally { throwable ->
                Toast.makeText(this, "Unable to load wolverine model", Toast.LENGTH_SHORT).show()
                null
            }
    }

    override fun onClick(v: View?) {
        if (v!!.id == R.id.bear) {
            selected = 1
            mySetBackground(v!!.id)
        } else if (v!!.id == R.id.cat) {
            selected = 2
            mySetBackground(v!!.id)
        } else if (v!!.id == R.id.cow) {
            selected = 3
            mySetBackground(v!!.id)
        } else if (v!!.id == R.id.dog) {
            selected = 4
            mySetBackground(v!!.id)
        } else if (v!!.id == R.id.elephant) {
            selected = 5
            mySetBackground(v!!.id)
        } else if (v!!.id == R.id.ferret) {
            selected = 6
            mySetBackground(v!!.id)
        } else if (v!!.id == R.id.hippopotamus) {
            selected = 7
            mySetBackground(v!!.id)
        } else if (v!!.id == R.id.horse) {
            selected = 8
            mySetBackground(v!!.id)
        } else if (v!!.id == R.id.koala_bear) {
            selected = 9
            mySetBackground(v!!.id)
        } else if (v!!.id == R.id.lion) {
            selected = 10
            mySetBackground(v!!.id)
        } else if (v!!.id == R.id.reindeer) {
            selected = 11
            mySetBackground(v!!.id)
        } else if (v!!.id == R.id.wolverine) {
            selected = 12
            mySetBackground(v!!.id)
        }
    }

    private fun mySetBackground(id: Int) {
        for (i in arrayView.indices) {
            if (arrayView[i].id == id) {
                arrayView[i].setBackgroundColor(Color.parseColor("#80333639")) //Set Background color for selected animal
            } else {
                arrayView[i].setBackgroundColor(Color.TRANSPARENT)
            }
        }
    }

    private fun setupClickListener() {
        for (i in arrayView.indices) {
            arrayView[i].setOnClickListener(this)
        }
    }

    private fun setArrayView() {
        arrayView = arrayOf<View>(bear, cat, cow, dog, elephant, ferret, hippopotamus, horse, koala_bear, lion, reindeer, wolverine)
    }
}
