package br.com.vsgdev.socialsoftware.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.vsgdev.socialsoftware.R;
import br.com.vsgdev.socialsoftware.models.Institution;
import br.com.vsgdev.socialsoftware.utils.InstituitionListAdapter;

public class InstitutionsFragment extends Fragment implements ListView.OnItemClickListener {
    private ListView lvInstitutions;
    private ArrayList<Institution> institutions = new ArrayList<>();

    public InstitutionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_institutions, container, false);
        lvInstitutions = (ListView) view.findViewById(R.id.lv_institutions_fragment_institutions);
        getData();
        InstituitionListAdapter instituitionListAdapter = new InstituitionListAdapter(view.getContext(), institutions);
        lvInstitutions.setAdapter(instituitionListAdapter);
        lvInstitutions.setOnItemClickListener(this);
        return view;

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Fragment fragment = new InstitutionDetailsFragment();
        Bundle institution = new Bundle();
        institution.putSerializable("INSTITUTION", institutions.get(position));
        fragment.setArguments(institution);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.mainContent, fragment, "INSTITUTION_DETAIL").addToBackStack("INSTITUTION_DETAIL")
                .commit();

    }

    private void getData() {
        Institution i1 = new Institution(1, "Lar de maria", getString(R.string.grande_600), null, null);
        Institution i2 = new Institution(2, "APIPA", getString(R.string.grande_600), null, null);
        Institution i3 = new Institution(3, "Lar da criança", getString(R.string.grande_600), null, null);
        Institution i4 = new Institution(4, "teste2", getString(R.string.grande_600), null, null);
        Institution i5 = new Institution(5, "teste3", getString(R.string.grande_600), null, null);
        Institution i6 = new Institution(6, "test54", getString(R.string.grande_600), null, null);
        Institution i7 = new Institution(7, "La", getString(R.string.grande_600), null, null);
        Institution i8 = new Institution(8, "teste56", getString(R.string.grande_600), null, null);
        Institution i9 = new Institution(9, "teste7", getString(R.string.grande_600), null, null);
        institutions.add(i1);
        institutions.add(i2);
        institutions.add(i3);
        institutions.add(i4);
        institutions.add(i5);
        institutions.add(i6);
        institutions.add(i7);
        institutions.add(i8);
        institutions.add(i9);
    }
}
