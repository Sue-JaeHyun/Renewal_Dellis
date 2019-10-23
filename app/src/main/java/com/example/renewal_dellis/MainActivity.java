package com.example.renewal_dellis;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private FirebaseFirestore db;
    TextView tvMessage;
    TextView setTextView;
    TextView nameTextView;
    TextView backTextView;
    TextView h_TextView;
    TextView ls_TextView;
    TextView anthena_long_TextView;
    TextView anthena_long2_TextView;
    TextView anthena_short_TextView;
    TextView anthena_short2_TextView;
    EditText etNewMessage;
    Button btUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMessage = (TextView) findViewById(R.id.tv_message);
        etNewMessage = (EditText) findViewById(R.id.et_newData);
        btUpdate = (Button) findViewById(R.id.bt_update);
        setTextView = (TextView) findViewById(R.id.set_textview);
        nameTextView = (TextView) findViewById(R.id.name_textview);
        backTextView = (TextView) findViewById(R.id.back_textview);
        h_TextView = (TextView) findViewById(R.id.h_textview);
        ls_TextView = (TextView) findViewById(R.id.ls_textview);
        anthena_long_TextView = (TextView) findViewById(R.id.anthena_long_textview);
        anthena_long2_TextView = (TextView) findViewById(R.id.anthena_long2_textview);
        anthena_short_TextView = (TextView) findViewById(R.id.anthena_short_textview);
        anthena_short2_TextView = (TextView) findViewById(R.id.anthena_short2_textview);

        db = FirebaseFirestore.getInstance();

        //버튼 이벤트
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String newMessage = etNewMessage.getText().toString().trim();
                db.collection("sig_item").document(newMessage).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if( documentSnapshot.exists())
                        {
                            Map<String,Object>result = documentSnapshot.getData();
//                                Log.d("ABCDE",documentSnapshot.getData().get("name").toString());
                            tvMessage.setText(((Map<String,Object>)result.get("components")).get("anthena_short").toString());
                            setTextView.setText(((Map<String,Object>)result.get("components")).get("set").toString());
                            nameTextView.setText(documentSnapshot.getData().get("name").toString());
                            backTextView.setText(((Map<String,Object>)result.get("components")).get("back").toString());
                            h_TextView.setText(((Map<String,Object>)result.get("components")).get("h-920").toString());
                            ls_TextView.setText(((Map<String,Object>)result.get("components")).get("ls-354").toString());
                            anthena_long_TextView.setText(((Map<String,Object>)result.get("components")).get("anthena_long").toString());
                            anthena_long2_TextView.setText(((Map<String,Object>)result.get("components")).get("anthena_long2").toString());
                            anthena_short_TextView.setText(((Map<String,Object>)result.get("components")).get("anthena_short").toString());
                            anthena_short2_TextView.setText(((Map<String,Object>)result.get("components")).get("anthena_short2").toString());

                        }
                    }
                });
            }


        });


    }
}
/*
1. firebase에서 데이터 형식 먼저 만든다.(문서-일련번호 추가, 필드-부수기재추가)
2. 레이아웃 만들기(과거 아웃사이더 매니지먼트 참고)
3. 레이아웃에 나눈 형식대로 값이 출력되어야함.'
4. 이해완료
 */