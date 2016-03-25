package cn.hihiwjc.app.xjblog.biz.upload;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.hihiwjc.app.xjblog.R;
import cn.hihiwjc.app.xjblog.biz.mod.Media;
import cn.hihiwjc.app.xjblog.biz.mod.Meta;
import cn.hihiwjc.app.xjblog.biz.mod.Post;
import cn.hihiwjc.app.xjblog.biz.rest.ClientRetrofit;
import cn.hihiwjc.app.xjblog.biz.setting.SettingBiz;
import cn.hihiwjc.app.xjblog.biz.util.ContentUtil;
import cn.hihiwjc.app.xjblog.biz.util.MediaUtil;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/01/29.
 */
public class UploadPostIntentService extends IntentService {

    public static final String ACTION_UPLOAD_DONE = "cn.hihiwjc.app.xjblog.action.upload.done";

    private NotificationCompat.Builder notificationBuilder;
    private NotificationManager notificationManager;
    private int notifyId = 1;

    private Call<Media> activeCall;

    private String link;
    private boolean finished;
    private boolean cancelled;

    public UploadPostIntentService() {
        super("文章上传服务");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.mipmap.ic_upload)
                .setProgress(0, 0, true)
                .setContentTitle("上传文章");

        Intent stopIntent = new Intent(this, CancelPostUploadReceiver.class);
        PendingIntent pendingIntentStop = PendingIntent.getBroadcast(this, 0, stopIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.addAction(R.mipmap.ic_cancel, "取消", pendingIntentStop);

        notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        String baseUrl = SettingBiz.getSelf().loadServerIP();
        String username = "xml-rpc";
        String pass = "@#df$%S";

        ClientRetrofit clientRetrofit = new ClientRetrofit(baseUrl, username, pass, true);

        Bundle extras = intent.getExtras();

        String headline = extras.getString("headline");
        String content = extras.getString("content");
        long[] catArray = extras.getLongArray("catIds");
        ArrayList<WpMediaItem> mediaItems = extras.getParcelableArrayList("mediaStrings");
        ArrayList<Meta> postMetas = extras.getParcelableArrayList("metas");

        List<Long> catIds = new ArrayList<>();
        for (long aCatArray : catArray) {
            catIds.add(aCatArray);
        }

        // Upload images

        List<Long> mediaIds = new ArrayList<>();
        for (int i = 0; i < mediaItems.size(); i++) {
            log("上传图片" + (i + 1) + " of " + mediaItems.size());
            File f = new File(MediaUtil.getRealPathFromURI(getApplicationContext(), mediaItems.get(i).mediaPath));

            Media media = new Media();
            media.withTitle(f.getName())
                    .withCaption(mediaItems.get(i).caption);

            try {
                activeCall = clientRetrofit.createMedia(media, f);
                Response<Media> mediaResponse = activeCall.execute();
                if (mediaResponse.isSuccessful()) {
                    mediaIds.add(mediaResponse.body().getId());
                } else {
                    error(mediaResponse.code(), mediaResponse.errorBody() != null ? mediaResponse.errorBody().string() : mediaResponse.message());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (cancelled) {
            return;
        }

        // Create post object

        log("正在创建文章数据...");
        StringBuilder builder = new StringBuilder();
        if (mediaIds.size() > 0) {
            //[gallery ids="75,73,67"]
            builder.append("[gallery ids=\"");

            for (int i = 0; i < mediaIds.size(); i++) {
                builder.append(mediaIds.get(i));
                if (i < mediaIds.size() - 1) {
                    builder.append(",");
                }
            }

            builder.append("\"]")
                    .append("<br/>")
                    .append("<br/>");
        }

        builder.append(content);

        if (postMetas != null && postMetas.size() > 0) {
            String address = postMetas.get(0).getValue();
            String shortCode = ContentUtil.getContentLocationShortcode(getApplicationContext(), address);

            builder.append("<br/>")
                    .append("<br/>")
                    .append(shortCode);
        }

        Post post = new Post();
        post.withTitle(headline)
                .withContent(builder.toString())
                .withStatus("publish")
                .withCategories(catIds);

        if (mediaIds.size() == 0) {
            post.setFormat("standard");
        } else {
            int firstImage = mediaIds.get(0).intValue();
            post.withFormat("gallery")
                    .withFeaturedImage(firstImage);
        }

        long postId = -1;
        try {
            Response<Post> postResponse = clientRetrofit.createPost(post).execute();
            if (postResponse.isSuccessful()) {
                postId = postResponse.body().getId();
                link = postResponse.body().getLink();
            } else {
                error(postResponse.code(), postResponse.errorBody() != null ? postResponse.errorBody().string() : postResponse.message());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set post meta

        if (postId != -1 && postMetas != null && postMetas.size() > 0) {
            for (Meta meta : postMetas) {
                try {
                    Response<Meta> metaResponse = clientRetrofit.createPostMeta(postId, meta).execute();
                    if (metaResponse.isSuccessful()) {
                        //log("uploaded post meta data");
                    } else {
                        error(metaResponse.code(), metaResponse.errorBody() != null ? metaResponse.errorBody().string() : metaResponse.message());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        finished = true;
    }

    @Override
    public void onDestroy() {
        if (activeCall != null) {
            if (activeCall.isExecuted() && !activeCall.isCanceled()) {
                activeCall.cancel();
            }
        }
        if (!finished) {
            cancelled = true;

            notificationBuilder.setProgress(0, 0, false)
                    .setContentTitle("上传取消")
                    .setContentText("")
                    .setAutoCancel(true)
                    .mActions.clear();
            notificationManager.notify(notifyId, notificationBuilder.build());
        } else {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 123, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            notificationBuilder.setProgress(0, 0, false)
                    .setContentTitle("上传文章")
                    .setContentText(link)
                    .setAutoCancel(true);

            notificationBuilder.mActions.clear();
            notificationBuilder.addAction(R.mipmap.ic_info, "预览", pendingIntent);
            notificationManager.notify(notifyId, notificationBuilder.build());
        }

        Intent intent = new Intent(ACTION_UPLOAD_DONE);
        intent.putExtra("success", finished);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);

        super.onDestroy();
    }

    private void log(String msg) {
        System.out.println("========== " + msg + " =========");

        notificationBuilder.setContentText(msg);
        notificationManager.notify(notifyId, notificationBuilder.build());
    }

    private void error(int code, String message) {
        System.out.println("========== Error : (" + code + ") " + message);
    }
}
