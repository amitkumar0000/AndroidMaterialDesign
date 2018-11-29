package com.android.materialdesign.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.android.materialdesign.R
import android.support.design.widget.BottomSheetBehavior
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import android.support.design.widget.BottomSheetDialog
import com.android.materialdesign.ui.frag.BottomSheetFragment




class BottomSheetActivity : AppCompatActivity() {

    @BindView(R.id.bottom_sheet)
    lateinit var layoutBottomSheet: LinearLayout

    @BindView(R.id.btn_bottom_sheet)
    lateinit var btnBottomSheet: Button

    var sheetBehavior: BottomSheetBehavior<View>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_sheel)
        ButterKnife.bind(this);

        layoutBottomSheet = findViewById(R.id.bottom_sheet)
        btnBottomSheet = findViewById(R.id.btn_bottom_sheet)


        sheetBehavior = BottomSheetBehavior.from(layoutBottomSheet)

        /**
         * bottom sheet state change listener
         * we are changing button text when sheet changed state
         * */
        sheetBehavior?.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> {
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        btnBottomSheet?.setText("Close Sheet")
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        btnBottomSheet?.setText("Expand Sheet")
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }
        })
    }

    fun onClick(view:View){
        when(view.id){
            R.id.btn_bottom_sheet -> {
                toggleBottomSheet()
            }
            R.id.btn_bottom_sheet_dialog -> {
                showBottomSheetDialog()
            }
            R.id.btn_bottom_sheet_dialog_fragment -> {
                val bottomSheetFragment = BottomSheetFragment()
                bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)

            }
        }
    }

    @OnClick(R.id.btn_bottom_sheet)
    fun toggleBottomSheet() {
        if (sheetBehavior?.getState() !== BottomSheetBehavior.STATE_EXPANDED) {
            sheetBehavior?.setState(BottomSheetBehavior.STATE_EXPANDED)
            btnBottomSheet?.setText("Close sheet")
        } else {
            sheetBehavior?.setState(BottomSheetBehavior.STATE_COLLAPSED)
            btnBottomSheet?.setText("Expand sheet")
        }
    }

    fun showBottomSheetDialog() {
        val view = layoutInflater.inflate(R.layout.fragment_bottom_sheet_dialog, null)

        val dialog = BottomSheetDialog(this)
        dialog.setContentView(view)
        dialog.show()
    }
}
