package com.example.medicinealarm;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatDialogFragment;

public class CustomDialog extends AppCompatDialogFragment {
    private Context context;
    EditText editText;
    private CustomDialogListener listener;
    RadioGroup radioGroup;
    RadioButton emoji_verygood;
    RadioButton emoji_good;
    RadioButton emoji_normal;
    RadioButton emoji_bad;
    RadioButton emoji_verybad;
    int selectedId;

    //팝업창 크기
//    public void onResume(){
//        int width = getResources().getDimensionPixelSize(R.dimen.dialog_width);
//        int height = getResources().getDimensionPixelSize(R.dimen.dialog_height);
//        getDialog().getWindow().setLayout(width,height);
//        super.onResume();
//    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_dialog,null);

        builder.setView(view)
                .setTitle("MEMO")
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("저장", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int id = radioGroup.getCheckedRadioButtonId();

                        if(emoji_verygood.getId()==id){
                            selectedId = id;
                        }else if(emoji_good.getId() == id){
                            selectedId = id;
                        }else if(emoji_normal.getId() == id){
                            selectedId = id;
                        }else if(emoji_bad.getId()==id){
                            selectedId = id;
                        }else if(emoji_verybad.getId()==id){
                            selectedId = id;
                        }
                        listener.applyEmoji(id);
                    }
                });

        editText = view.findViewById(R.id.editText_memo);
        radioGroup = view.findViewById(R.id.radio_group);
        emoji_verygood = view.findViewById(R.id.emoji_verygood);
        emoji_good = view.findViewById(R.id.emoji_good);
        emoji_bad = view.findViewById(R.id.emoji_bad);
        emoji_verybad = view.findViewById(R.id.emoji_verybad);
        emoji_normal = view.findViewById(R.id.emoji_normal);

        return builder.create();
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try{
            listener = (CustomDialogListener) context;
        }catch(ClassCastException e){
            e.printStackTrace();
        }

    }
    public interface CustomDialogListener{
        //선택된 이모티콘 정보 넘기기
         void applyEmoji(int id);
    }
}
