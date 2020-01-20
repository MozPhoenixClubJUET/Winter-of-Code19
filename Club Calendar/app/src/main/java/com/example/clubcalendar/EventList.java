package com.example.clubcalendar;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.clubcalendar.model.Event;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class EventList extends ArrayAdapter<Event> {

    private Activity context;
    List<Event> events;

    public EventList(Activity context, List<Event> events) {
        super(context, R.layout.event_item, events);
        this.context = context;
        this.events = events;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.event_item, null, true);

        TextView textEventName = (TextView) listViewItem.findViewById(R.id.item_event_name);
        TextView textEventVenue = (TextView) listViewItem.findViewById(R.id.item_venue);
        TextView textEventClub = (TextView) listViewItem.findViewById(R.id.item_club_name);
        TextView textEventDate = (TextView) listViewItem.findViewById(R.id.item_date);
        TextView textEventDesc = (TextView) listViewItem.findViewById(R.id.description_event_item);

        Event event = events.get(position);

        textEventName.setText(event.getEventname());
        textEventVenue.setText(event.getVenue());
        textEventDate.setText(event.getDate());
        textEventDesc.setText(event.getDescription());
        textEventClub.setText(event.getClubName());



        return listViewItem;
    }

}
