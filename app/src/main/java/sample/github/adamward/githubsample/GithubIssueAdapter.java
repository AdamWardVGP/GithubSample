package sample.github.adamward.githubsample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import sample.github.adamward.githubsample.Models.GithubIssue;

/**
 * Created by adamward on 11/12/15.
 */
public class GithubIssueAdapter extends ArrayAdapter<GithubIssue> {
    /**
     * Constructor
     *
     * @param context  The current context.
     * @param objects  The objects to represent in the ListView.
     */
    public GithubIssueAdapter(Context context, ArrayList<GithubIssue> objects) {
        super(context, 0, objects);
    }

    static class ViewHolder {
        @Bind(R.id.title) TextView title;
        @Bind(R.id.body) TextView body;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        GithubIssue rowItem = getItem(position);
        ViewHolder viewHolder;
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.github_issue_row, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Populate the data into the template view using the data object
        viewHolder.title.setText(rowItem.title);
        viewHolder.body.setText(rowItem.body);

        // Return the completed view to render on screen
        return convertView;
    }
}
