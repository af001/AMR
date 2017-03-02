package technology.xor.amr.trek;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.MultiSelectListPreference;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import technology.xor.amr.R;

public class TrekAdapter extends RecyclerView.Adapter<TrekAdapter.SiteViewHolder>{

    private List<Trek> candidates;
    private Activity activity;
    private ViewGroup viewGroup;
    private SharedPreferences sharedPreferences;

    public TrekAdapter(List<Trek> candidates, Activity activity, ViewGroup viewGroup){
        this.candidates = candidates;
        this.activity = activity;
        this.viewGroup = viewGroup;
    }

    @Override
    public SiteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.trek_view, parent, false);
        TrekAdapter.SiteViewHolder tvh = new TrekAdapter.SiteViewHolder(v);

        return tvh;
    }

    @Override
    public void onBindViewHolder(final SiteViewHolder holder, final int position) {

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);

        final SharedPreferences.Editor prefEditor = sharedPreferences.edit();
        final String siteName = sharedPreferences.getString("site_name", "RV1");

        holder.candidateNumber.setText(candidates.get(position).candidate);

        final String name = holder.candidateNumber.getText().toString();
        final String arriveMsg = "arrived";
        final String departMsg = "departed";

        CheckStatus(name, holder.btnArrive, holder.btnDepart);

        holder.btnArrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncTaskRunner runner = new AsyncTaskRunner();
                runner.execute(name, arriveMsg, siteName);
                prefEditor.putInt(name, 1);
                prefEditor.apply();
                holder.btnArrive.setEnabled(false);
                holder.btnDepart.setEnabled(true);
            }
        });

        holder.btnDepart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncTaskRunner runner = new AsyncTaskRunner();
                runner.execute(name, departMsg, siteName);
                prefEditor.putInt(name, 2);
                prefEditor.apply();
                holder.btnArrive.setEnabled(false);
                holder.btnDepart.setEnabled(false);
            }
        });

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

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return candidates.size();
    }

    public static class SiteViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView candidateNumber;
        Button btnArrive;
        Button btnDepart;

        SiteViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv2);
            candidateNumber = (TextView)itemView.findViewById(R.id.candidate);
            btnArrive = (Button) itemView.findViewById(R.id.btn_arrive2);
            btnDepart = (Button) itemView.findViewById(R.id.btn_depart2);
        }
    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;

        @Override
        protected String doInBackground(final String... params) {
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
                            .setAction(params[0], new AsyncTaskRunner.MyUndoListener()).show();
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
