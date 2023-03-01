import android.os.AsyncTask;

import com.example.loginapp.R;

import java.io.IOException;

private class RequestInstagramAPI extends AsyncTask<Void,String,String>
{

    @Override
    protected String doInBackground(Void... voids) {

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(getResources().getString(R.string.get_user_info_url) + token);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();
            return EntityUtils.toString(httpEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
    @Override
    protected void onPostExecute(String response)
    {
        super.onPostExecute(response);
        if(response != null)
        {
            try
            {
                JSONObject jsonObject = new JSONObject(response);
                JSONObject jsonData = jsonObject.getJSONObject("data");
                if (jsonData.has("id")) {
                    appPreferences.putString(AppPreferences.USER_ID, jsonData.getString("id"));
                    appPreferences.putString(AppPreferences.USER_NAME, jsonData.getString("username"));
                    appPreferences.putString(AppPreferences.PROFILE_PIC, jsonData.getString("profile_picture"));
                    login();
            }
            catch(JSONException ex)
            {
                e.printStackTrace();
            }
        }
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(),"Login error!",Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
