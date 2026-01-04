public class AccessibleHelpFragment extends Fragment {

    Button btnHowToVote, btnAudioGuide, btnEmergency;
    TextToSpeech tts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_accessible_help, container, false);

        btnHowToVote = view.findViewById(R.id.btnHowToVote);
        btnAudioGuide = view.findViewById(R.id.btnAudioGuide);
        btnEmergency = view.findViewById(R.id.btnEmergency);

        tts = new TextToSpeech(getContext(), status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(Locale.ENGLISH);
                speak("Welcome to voter help and support page.");
            }
        });

        btnHowToVote.setOnClickListener(v ->
                speak("To vote, select one candidate and press submit button.")
        );

        btnAudioGuide.setOnClickListener(v ->
                speak("This is an audio guide to help abled citizens vote easily.")
        );

        btnEmergency.setOnClickListener(v ->
                speak("Emergency assistance activated. Please contact the booth officer.")
        );

        return view;
    }

    private void speak(String message) {
        tts.speak(message, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (tts != null) {
            tts.shutdown();
        }
    }
}
