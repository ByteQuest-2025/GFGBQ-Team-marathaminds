public class VoteFragment extends Fragment {

    RadioGroup radioGroup;
    Button btnVote;

    // static vote count (demo purpose)
    public static int voteA = 0, voteB = 0, voteC = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_vote, container, false);

        radioGroup = view.findViewById(R.id.radioGroup);
        btnVote = view.findViewById(R.id.btnVote);

        btnVote.setOnClickListener(v -> {

            int selectedId = radioGroup.getCheckedRadioButtonId();

            if (selectedId == -1) {
                Toast.makeText(getContext(), "Please select a candidate", Toast.LENGTH_SHORT).show();
            } else {

                if (selectedId == R.id.radio1) voteA++;
                else if (selectedId == R.id.radio2) voteB++;
                else if (selectedId == R.id.radio3) voteC++;

                Toast.makeText(getContext(), "Vote Submitted Successfully", Toast.LENGTH_SHORT).show();
                radioGroup.clearCheck();
            }
        });

        return view;
    }
}
