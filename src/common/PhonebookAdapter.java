package common;

import java.util.List;

import com.example.myapp.R;
import com.example.myapp.R.id;
import com.example.myapp.R.layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class PhonebookAdapter extends BaseAdapter {
    private Context context;

    private List<Phonebook> listPhonebook;

    public PhonebookAdapter(Context context, List<Phonebook> listPhonebook) {
        this.context = context;
        this.listPhonebook = listPhonebook;
    }

    public int getCount() {
        return listPhonebook.size();
    }

    public Object getItem(int position) {
        return listPhonebook.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Phonebook entry = listPhonebook.get(position);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list, null);
        }

        TextView tvPhone = (TextView) convertView.findViewById(R.id.name);
        tvPhone.setText(entry.getName());

        TextView tvMail = (TextView) convertView.findViewById(R.id.content2);
        tvMail.setText(entry.getContent());

        // Set the onClick Listener on this button

        // Set the entry, so that you can capture which item was clicked and
        // then remove it
        // As an alternative, you can use the id/position of the item to capture
        // the item
        // that was clicked.


        // btnRemove.setId(position);
        

        return convertView;
    }

    private void showDialog(Phonebook entry) {
        // Create and show your dialog
        // Depending on the Dialogs button clicks delete it or do nothing
    }

}
