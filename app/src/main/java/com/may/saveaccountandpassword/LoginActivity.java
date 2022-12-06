package com.may.saveaccountandpassword;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * DES:
 * <p>
 * Date: 2022/12/6  21:44
 *
 * @author Jason
 */
public class LoginActivity extends AppCompatActivity {

    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText etAccount = findViewById(R.id.et_account);
        EditText etPassword = findViewById(R.id.et_password);
        CheckBox cbRemember = findViewById(R.id.cb_remember);
        Button btnLogin = findViewById(R.id.btn_login);
        pref = PreferenceManager.getDefaultSharedPreferences(this);

        boolean isRemember = pref.getBoolean("remember_password", false);
        if (isRemember){
            String account = pref.getString("account", "");
            String password = pref.getString("password", "");
            etAccount.setText(account);
            etPassword.setText(password);
            cbRemember.setChecked(true);
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CommitPrefEdits")
            @Override
            public void onClick(View view) {
                String account = etAccount.getText().toString();
                String password = etPassword.getText().toString();
                if (account.equals("admin")&&password.equals("123456")){
                    SharedPreferences.Editor editor = pref.edit();
                    if (cbRemember.isChecked()){
                        editor.putString("account",account);
                        editor.putString("password",password);
                        editor.putBoolean("remember_password",true);
                    }else{
                        editor.clear();
                    }
                    editor.apply();
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    finish();

                }else{
                    Toast.makeText(LoginActivity.this,"你输入的账号或密码不正确",Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}
