package com.example.sarango.exampleespresso.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sarango.exampleespresso.R;
import com.example.sarango.exampleespresso.model.Person;
import com.example.sarango.exampleespresso.view.interface_view.DataBaseView;

import java.util.List;

/**
 * Created by sarango on 24/08/2016.
 */
public class RVAdapterPerson extends RecyclerView.Adapter<RVAdapterPerson.ViewHolder> {

    private List<Person> personList;
    private DataBaseView dataBaseView;

    public RVAdapterPerson(List<Person> personList, DataBaseView dataBaseView) {
        this.personList = personList;
        this.dataBaseView = dataBaseView;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvIdPerson;
        TextView tvNamePerson;
        TextView tvAgePerson;
        ImageView imgDelete;


        public ViewHolder(View view) {
            super(view);

            tvIdPerson = (TextView) view.findViewById(R.id.tv_id_person);
            tvNamePerson = (TextView) view.findViewById(R.id.tv_name_person);
            tvAgePerson = (TextView) view.findViewById(R.id.tv_age_person);
            imgDelete = (ImageView) view.findViewById(R.id.img_delete);


            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Person person = personList.get(getAdapterPosition());
                    removeAt(person, getAdapterPosition());
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout_person, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Person person = personList.get(position);

        holder.tvIdPerson.setText("Id: " + person.getUserID());
        holder.tvNamePerson.setText("Nombre:\n" + person.getUserName() + " " + person.getUserLastName());
        holder.tvAgePerson.setText("Edad: " + person.getUserAge());
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }


    public void removeAt(Person person, int position) {
        //personList.remove(person);
        dataBaseView.deleteForId(personList.get(position).getUserID());
        notifyItemRemoved(position);
        //   notifyItemRangeChanged(position, personList.size());

    }


}
