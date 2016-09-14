package id.co.halallocal.connection;

import android.os.AsyncTask;

/**
 * Created by Milzam on 9/11/2016.
 */
public class ServerTask extends AsyncTask<String, Integer, String> {

    private ServerCallback mCallback;

    public ServerTask(ServerCallback pCallback) {
        mCallback = pCallback;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected String doInBackground(String... params) {
        String result = "";
        return result;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String pS) {
        super.onPostExecute(pS);
    }
}
