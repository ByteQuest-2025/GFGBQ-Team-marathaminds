public class OtpActivity extends AppCompatActivity {

    EditText etOtp;
    Button btnVerify;
    TextView txtResend;
    TextToSpeech tts;

    // Demo OTP
    String demoOtp = "123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        etOtp = findViewById(R.id.etOtp);
        btnVerify = findViewById(R.id.btnVerify);
        txtResend = findViewById(R.id.txtResend);

        // Text To Speech for accessibility
        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(Locale.ENGLISH);
                speak("Please enter the OTP sent to your mobile number.");
            }
        });

        btnVerify.setOnClickListener(v -> {
            String enteredOtp = etOtp.getText().toString().trim();

            if (enteredOtp.isEmpty()) {
                speak("OTP field is empty");
                Toast.makeText(this, "Enter OTP", Toast.LENGTH_SHORT).show();
            } else if (enteredOtp.equals(demoOtp)) {
                speak("OTP verified successfully");
                Toast.makeText(this, "OTP Verified", Toast.LENGTH_SHORT).show();

                // Go to next page
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                speak("Invalid OTP. Please try again.");
                Toast.makeText(this, "Invalid OTP", Toast.LENGTH_SHORT).show();
            }
        });

        txtResend.setOnClickListener(v -> {
            speak("OTP resent successfully");
            Toast.makeText(this, "OTP Resent", Toast.LENGTH_SHORT).show();
        });
    }

    private void speak(String msg) {
        tts.speak(msg, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tts != null) {
            tts.shutdown();
        }
    }
}
