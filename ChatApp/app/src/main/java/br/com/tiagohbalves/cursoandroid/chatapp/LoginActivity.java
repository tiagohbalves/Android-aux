package br.com.tiagohbalves.cursoandroid.chatapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

import br.com.tiagohbalves.cursoandroid.chatapp.utils.Mask;

public class LoginActivity extends AppCompatActivity {

    EditText name;
    EditText phoneNumber;
    EditText phoneDD;
    EditText phoneDDI;
    Button cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phoneNumber =  (EditText) findViewById(R.id.tel_number);
        phoneDD = (EditText)findViewById(R.id.ddcode);
        phoneDDI = (EditText)findViewById(R.id.dicode);
        name = (EditText)findViewById(R.id.name);
        cadastrar = (Button) findViewById(R.id.button);
        phoneNumber.addTextChangedListener(Mask.insert(phoneNumber));

        phoneDDI.addTextChangedListener(new TextWatcher() {
            String old;
            boolean isUpdating;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String str = Mask.unmask(s.toString());
                String mask;
                String defaultMask = "+###";
                switch (str.length()) {
                    case 0:
                        mask = "";
                        break;
                    default:
                        mask = defaultMask;
                        break;
                }



                String mascara = "";
                if (isUpdating) {
                    old = str;
                    isUpdating = false;

                    return;
                }
                int i = 0;
                for (char m : mask.toCharArray()) {
                    if (m != '#') {
                        mascara += m;
                        continue;
                    }
                    try {
                        mascara += str.charAt(i);
                    } catch (Exception e) {
                        break;
                    }
                    i++;
                }
                isUpdating = true;
                phoneDDI.setText(mascara);
                phoneDDI.setSelection(mascara.length());


            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = name.getText().toString();
                String telefoneCompleto = phoneDDI.getText().toString() +
                        phoneDD.getText().toString() +
                        phoneNumber.getText().toString();
                String telefoneSemFormatacao = telefoneCompleto.replace("-","");
                telefoneSemFormatacao = telefoneCompleto.replace("+","");

                // Gerar token (Deveria ser via server
                Random random = new Random();
                int randNum = random.nextInt(8999)+1000;
                String tokek = String.valueOf(randNum);

            }
        });



    }
}
