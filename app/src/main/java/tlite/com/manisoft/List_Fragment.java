package tlite.com.manisoft;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class List_Fragment extends Fragment {

    ListView number_list;
    TextView value_txt;
    ArrayAdapter<Integer> arrayAdapter;
    List<Integer> values;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

           View view =  inflater.inflate(R.layout.list_fragment,container,false);

        number_list = (ListView) view.findViewById(R.id.number_list);
        value_txt = (TextView) view.findViewById(R.id.value_txt);

        values = new ArrayList<>();
        for(int i = -50; i<51 ; i++){
            values.add(i);
        }

        arrayAdapter = new ArrayAdapter<Integer>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        number_list.setAdapter(arrayAdapter);

        number_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String result = "";

                if((values.get(i) % 3 == 0) && (values.get(i) % 5 == 0)) result = "Fizz Buzz";
                else if(values.get(i) % 3 == 0) result = "Fizz";
                else if(values.get(i) % 5 == 0) result = "Buzz";
                else result = "Select some other number";

                value_txt.setText(result);
            }
        });

        return view;

    }
}
