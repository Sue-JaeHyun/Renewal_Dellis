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
    EditText etNewMessage;
    Button btUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMessage = (TextView) findViewById(R.id.tv_message);
        etNewMessage = (EditText) findViewById(R.id.et_newData);
        btUpdate = (Button) findViewById(R.id.bt_update);
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