package id.co.halallocal.connection;

/**
 * Created by Milzam on 9/11/2016.
 */
public interface ServerCallback {
    void onError(String errorMessage);
    void onResult(String result, int requestNo);
    void onProgress(int progress);
}
