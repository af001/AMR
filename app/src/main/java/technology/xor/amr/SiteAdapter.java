package technology.xor.amr;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import technology.xor.amr.DetailViews.DetailsActivity;

/**
 * SiteAdapter - Load the sites into the recyclerview
 */
public class SiteAdapter extends RecyclerView.Adapter<SiteAdapter.SiteViewHolder>{

    private List<Site> sites;
    private Activity activity;
    private ViewGroup viewGroup;
    private SharedPreferences sharedPreferences;

    SiteAdapter(List<Site> sites, Activity activity, ViewGroup viewGroup){
        this.sites = sites;
        this.activity = activity;
        this.viewGroup = viewGroup;
    }

    @Override
    public SiteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.site_view, parent, false);
        SiteViewHolder svh = new SiteViewHolder(v);

        return svh;
    }

    @Override
    public void onBindViewHolder(final SiteViewHolder holder, int position) {

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        final SharedPreferences.Editor prefEditor = sharedPreferences.edit();
        final String teamName = sharedPreferences.getString("team_name", "INTEGRITY");

        holder.siteName.setText(sites.get(position).name);
        holder.siteDescription.setText(sites.get(position).description);
        holder.sitePhoto.setImageResource(sites.get(position).photoId);

        final String name = holder.siteName.getText().toString();
        final String arriveMsg = "arrived";
        final String departMsg = "departed";

        CheckStatus(name, holder.btnArrive, holder.btnDepart);

        holder.btnArrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AsyncTaskRunner runner = new AsyncTaskRunner();
                runner.execute(teamName, arriveMsg, name);
                prefEditor.putInt(name, 1);
                prefEditor.apply();
                holder.btnArrive.setEnabled(false);
                holder.btnDepart.setEnabled(true);
            }
        });

        holder.btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailActivity = new Intent(activity, DetailsActivity.class);
                detailActivity.putExtra("site", name);
                activity.startActivity(detailActivity);
            }
        });

        holder.btnDepart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncTaskRunner runner = new AsyncTaskRunner();
                runner.execute(teamName, departMsg, name);
                prefEditor.putInt(name, 2);
                prefEditor.apply();
                holder.btnArrive.setEnabled(false);
                holder.btnDepart.setEnabled(false);
            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return sites.size();
    }

    public static class SiteViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView siteName;
        TextView siteDescription;
        ImageView sitePhoto;
        Button btnArrive;
        Button btnDetails;
        Button btnDepart;

        SiteViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            siteName = (TextView)itemView.findViewById(R.id.title);
            siteDescription = (TextView)itemView.findViewById(R.id.description);
            sitePhoto = (ImageView)itemView.findViewById(R.id.thumbnail);
            btnArrive = (Button) itemView.findViewById(R.id.btn_arrive);
            btnDepart = (Button) itemView.findViewById(R.id.btn_depart);
            btnDetails = (Button) itemView.findViewById(R.id.btn_details);
        }
    }

    private void CheckStatus(String name, Button arrive, Button depart) {
        final int activityLevel = sharedPreferences.getInt(name, 0);

        switch (activityLevel) {
            case 0:
                depart.setEnabled(false);
                arrive.setEnabled(true);
                break;
            case 1:
                depart.setEnabled(true);
                arrive.setEnabled(false);
                break;
            case 2:
                depart.setEnabled(false);
                arrive.setEnabled(false);
                break;
        }
    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;

        @Override
        protected String doInBackground(final String... params) {
            publishProgress("Initializing Bot..."); // Calls onProgressUpdate()
            String chatId = sharedPreferences.getString("chatId", null);

            long date = System.currentTimeMillis();

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm z");
            String dateString = sdf.format(date);

            TelegramBot bot = TelegramBotAdapter.build(activity.getString(R.string.bot_token));
            String msg = params[0] + " " + params[1] + " " + params[2] + " at " + dateString;

            SendMessage request = new SendMessage(chatId, msg)
                    .parseMode(ParseMode.HTML)
                    .disableWebPagePreview(true)
                    .disableNotification(false);

            // Async send message
            bot.execute(request, new Callback<SendMessage, SendResponse>() {
                @Override
                public void onResponse(SendMessage request, SendResponse response) {
                    Snackbar.make(viewGroup, "Status update sent!", Snackbar.LENGTH_LONG)
                            .setAction(params[0], new MyUndoListener()).show();
                }

                @Override
                public void onFailure(SendMessage request, IOException e) {
                    Log.d("Adapter", "Message Status: " + e.getMessage());
                }
            });

            return resp;
        }

        class MyUndoListener implements View.OnClickListener{

            @Override
            public void onClick(View v) {
                Log.d("Adapter", "Clicked");
            }
        }

        /*
         * (non-Javadoc)
         *
         * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
         */
        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            // finalResult.setText(result);
        }

        /*
         * (non-Javadoc)
         *
         * @see android.os.AsyncTask#onPreExecute()
         */
        @Override
        protected void onPreExecute() {
            // Things to be done before execution of long running operation. For
            // example showing ProgessDialog
        }

        /*
         * (non-Javadoc)
         *
         * @see android.os.AsyncTask#onProgressUpdate(Progress[])
         */
        @Override
        protected void onProgressUpdate(String... text) {
            // finalResult.setText(text[0]);
            // Things to be done while execution of long running operation is in
            // progress. For example updating ProgessDialog
        }
    }
}