public class AccessibleVoteFragment extends Fragment {

    RadioGroup radioGroup;
    Button btnSubmit;
    TextToSpeech textToSpeech;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_accessible_vote, container, false);

        radioGroup = view.findViewById(R.id.radioGroup);
        btnSubmit = view.findViewById(R.id.btnSubmit);

        // Text to Speech Initialization
        textToSpeech = new TextToSpeech(getContext(), status -> {
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech.setLanguage(Locale.ENGLISH);
                textToSpeech.speak(
                        "Welcome to accessible voting page. Please select a candidate.",
                        TextToSpeech.QUEUE_FLUSH,
                        null,
                        null
                );
            }
        });

        btnSubmit.setOnClickListener(v -> {

            int selectedId = radioGroup.getCheckedRadioButtonId();

            if (selectedId == -1) {
                speak("No candidate selected. Please select one candidate.");
            } else {
                speak("Your vote has been submitted successfully. Thank you for voting.");
                radioGroup.clearCheck();
            }
        });

        return view;
    }

    private void speak(String message) {
        textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (textToSpeech != null) {
            textToSpeech.shutdown();
        }
    }
}
