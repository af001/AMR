package technology.xor.amr.DetailViews;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import technology.xor.amr.R;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder> {


    List<Details> details;

    DetailsAdapter(List<Details> details) {
        this.details = details;
    }

    @Override
    public DetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.details_view, parent, false);
        DetailsViewHolder dvh = new DetailsViewHolder(v);

        return dvh;
    }

    @Override
    public void onBindViewHolder(DetailsViewHolder holder, int position) {
        holder.question.setText(details.get(position).question);
        holder.answer.setText(details.get(position).answer);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    public static class DetailsViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView question;
        TextView answer;

        DetailsViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            question = (TextView) itemView.findViewById(R.id.question);
            answer = (TextView) itemView.findViewById(R.id.answer);

        }
    }
}


