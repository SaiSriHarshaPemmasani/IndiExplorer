package com.UNHAndroidDevelopmnt.indiexplorer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.UNHAndroidDevelopmnt.indiexplorer.Models.User
import com.UNHAndroidDevelopmnt.indiexplorer.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {

    val binding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    lateinit var user: User
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(binding.root)
            user = User()

            binding.SignUpBtn.setOnClickListener{
                if ( binding.UserName.editText?.text.toString().equals("") or
                    binding.UserEmail.editText?.text.toString().equals("") or
                    binding.UserPassword.editText?.text.toString().equals("") )
                {
                    Toast.makeText(this@SignUpActivity,"Please fill all the required fields",Toast.LENGTH_LONG).show()
                }else{

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                        binding.UserEmail.editText?.text.toString(),
                        binding.UserPassword.editText?.text.toString()
                    ).addOnCompleteListener {
                        result->

                        if (result.isSuccessful){
                            user.name=binding.UserName.editText?.text.toString()
                            user.email=binding.UserEmail.editText?.text.toString()
                            user.password=binding.UserPassword.editText?.text.toString()
                            Firebase.firestore.collection("User")
                                .document(Firebase.auth.currentUser!!.uid).set(user)
                                .addOnSuccessListener {
                                    Toast.makeText(this@SignUpActivity,"Login Successful ",Toast.LENGTH_LONG).show()
                                }

                        }else{
                            Toast.makeText(this@SignUpActivity,result.exception?.localizedMessage,Toast.LENGTH_LONG).show()
                        }
                    }

                }
            }
        }
}
