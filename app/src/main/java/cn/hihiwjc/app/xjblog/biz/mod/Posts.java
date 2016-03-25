package cn.hihiwjc.app.xjblog.biz.mod;

/**
 * *******************************************************************************
 * <br/>
 * 程序描述：
 * <br/>
 * *******************************************************************************
 * <br/>
 * 修改历史：<br/>
 * Date：************** by：*****************Reason：<br/>
 * 2016/3/22 0022********** hihiwjc************Initial Version
 * <br/>
 * *******************************************************************************
 */
public class Posts {

    /**
     * id : 1
     * date : 2015-08-10T15:16:02
     * date_gmt : 2015-08-10T07:16:02
     * guid : {"rendered":"http://182.254.223.120/?p=1"}
     * modified : 2016-03-10T16:21:54
     * modified_gmt : 2016-03-10T08:21:54
     * slug : gradle-multi-product-flavors
     * type : post
     * link : http://hihiwjc.cn/articles/1
     * title : {"rendered":"Gradle多渠道打包"}
     * content : {"rendered":"<ol>\n<li>渠道：只是为了后期统计时（用户量、安装数量、点击量等）使用，默认不会修改安装包的功能<\/li>\n<li>Gradle2.4 自动多渠道打包配置<!-- more -->channels.properties文件\n<pre><code>#默认渠道\r\nchannel.default=qq\r\n#全部渠道列表\r\nchannel.list=baidu,360,hiapk\r\nGradle2.4 自动多渠道打包配置\r\nbuild.gradle文件\r\napply plugin: 'com.android.application'\r\n// 加载多渠道配置文件\r\nProperties properties = new Properties()\r\n//若此处报channels.properties文件未找到的错误，尝试修改文件路径\r\nproperties.load(project.rootProject.file('app/channels.properties').newDataInputStream())\r\nString channelDefault = properties.getProperty('channel.default')\r\nString channleList = properties.getProperty('channel.list')\r\nString[] channelArray = channleList.split(',')\r\nandroid {\r\n compileSdkVersion 23\r\n buildToolsVersion \"23.0.0\"\r\n defaultConfig {\r\n     applicationId \"com.hihiwjc.learn.popularmovies\"\r\n     minSdkVersion 18\r\n     targetSdkVersion 23\r\n     versionCode 1\r\n     versionName \"1.0\"\r\n     manifestPlaceholders = [CHANNEL_VALUE: channelDefault]\r\n\r\n }\r\n buildTypes {\r\n     release {\r\n         minifyEnabled false\r\n         // 开启多dex（解除65535的限制）\r\n         multiDexEnabled true\r\n        proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'\r\n     }\r\n }\r\n//这里是为了防止产生证书文件重复的问题\r\n packagingOptions {\r\n     exclude 'META-INF/DEPENDENCIES.txt'\r\n     exclude 'META-INF/LICENSE.txt'\r\n     exclude 'META-INF/NOTICE.txt'\r\n     exclude 'META-INF/NOTICE'\r\n     exclude 'META-INF/LICENSE'\r\n     exclude 'META-INF/DEPENDENCIES'\r\n     exclude 'META-INF/notice.txt'\r\n     exclude 'META-INF/license.txt'\r\n     exclude 'META-INF/dependencies.txt'\r\n     exclude 'META-INF/LGPL2.1'\r\n }\r\n productFlavors {\r\n     //产品渠道，遍历配置\r\n     for (int i = 0; i &lt; channelArray.size(); i++) {\r\n         def channel = channelArray[i]\r\n         \"${channel}\" {\r\n             manifestPlaceholders = [CHANNEL_VALUE: channel]\r\n         }\r\n     }\r\n }\r\n}\r\n//依赖库\r\ndependencies {\r\n compile fileTree(include: ['*.jar'], dir: 'libs')\r\n}\r\n<\/code><\/pre>\n<\/li>\n<\/ol>\n"}
     * excerpt : {"rendered":"<p>渠道：只是为了后期统计时（用户量、安装数量、点击量等）使用，默认不会修改安装包的功能 Gradle2.4 自动多渠道打包配置channels.properties文件 #默认渠道 <\/p>\n"}
     * author : 1
     * featured_media : 0
     * comment_status : open
     * ping_status : open
     * sticky : false
     * format : standard
     * categories : [3]
     * tags : [4,5]
     * _links : {"self":[{"href":"http://hihiwjc.cn/wp-json/wp/v2/posts/1"}],"collection":[{"href":"http://hihiwjc.cn/wp-json/wp/v2/posts"}],"about":[{"href":"http://hihiwjc.cn/wp-json/wp/v2/types/post"}],"author":[{"embeddable":true,"href":"http://hihiwjc.cn/wp-json/wp/v2/users/1"}],"replies":[{"embeddable":true,"href":"http://hihiwjc.cn/wp-json/wp/v2/comments?post=1"}],"version-history":[{"href":"http://hihiwjc.cn/wp-json/wp/v2/posts/1/revisions"}],"https://api.w.org/attachment":[{"href":"http://hihiwjc.cn/wp-json/wp/v2/media?parent=1"}],"https://api.w.org/term":[{"taxonomy":"category","embeddable":true,"href":"http://hihiwjc.cn/wp-json/wp/v2/categories?post=1"},{"taxonomy":"post_tag","embeddable":true,"href":"http://hihiwjc.cn/wp-json/wp/v2/tags?post=1"}],"https://api.w.org/meta":[{"embeddable":true,"href":"http://hihiwjc.cn/wp-json/wp/v2/posts/1/meta"}]}
     */

    private int id;
    private String date;
    private String date_gmt;
    /**
     * rendered : http://182.254.223.120/?p=1
     */

    private GuidBean guid;
    private String modified;
    private String modified_gmt;
    private String slug;
    private String type;
    private String link;
    /**
     * rendered : Gradle多渠道打包
     */

    private TitleBean title;
    /**
     * rendered : <ol>
     * <li>渠道：只是为了后期统计时（用户量、安装数量、点击量等）使用，默认不会修改安装包的功能</li>
     * <li>Gradle2.4 自动多渠道打包配置<!-- more -->channels.properties文件
     * <pre><code>#默认渠道
     * channel.default=qq
     * #全部渠道列表
     * channel.list=baidu,360,hiapk
     * Gradle2.4 自动多渠道打包配置
     * build.gradle文件
     * apply plugin: 'com.android.application'
     * // 加载多渠道配置文件
     * Properties properties = new Properties()
     * //若此处报channels.properties文件未找到的错误，尝试修改文件路径
     * properties.load(project.rootProject.file('app/channels.properties').newDataInputStream())
     * String channelDefault = properties.getProperty('channel.default')
     * String channleList = properties.getProperty('channel.list')
     * String[] channelArray = channleList.split(',')
     * android {
     * compileSdkVersion 23
     * buildToolsVersion "23.0.0"
     * defaultConfig {
     * applicationId "com.hihiwjc.learn.popularmovies"
     * minSdkVersion 18
     * targetSdkVersion 23
     * versionCode 1
     * versionName "1.0"
     * manifestPlaceholders = [CHANNEL_VALUE: channelDefault]
     * <p/>
     * }
     * buildTypes {
     * release {
     * minifyEnabled false
     * // 开启多dex（解除65535的限制）
     * multiDexEnabled true
     * proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
     * }
     * }
     * //这里是为了防止产生证书文件重复的问题
     * packagingOptions {
     * exclude 'META-INF/DEPENDENCIES.txt'
     * exclude 'META-INF/LICENSE.txt'
     * exclude 'META-INF/NOTICE.txt'
     * exclude 'META-INF/NOTICE'
     * exclude 'META-INF/LICENSE'
     * exclude 'META-INF/DEPENDENCIES'
     * exclude 'META-INF/notice.txt'
     * exclude 'META-INF/license.txt'
     * exclude 'META-INF/dependencies.txt'
     * exclude 'META-INF/LGPL2.1'
     * }
     * productFlavors {
     * //产品渠道，遍历配置
     * for (int i = 0; i &lt; channelArray.size(); i++) {
     * def channel = channelArray[i]
     * "${channel}" {
     * manifestPlaceholders = [CHANNEL_VALUE: channel]
     * }
     * }
     * }
     * }
     * //依赖库
     * dependencies {
     * compile fileTree(include: ['*.jar'], dir: 'libs')
     * }
     * </code></pre>
     * </li>
     * </ol>
     */

    private ContentBean content;
    /**
     * rendered : <p>渠道：只是为了后期统计时（用户量、安装数量、点击量等）使用，默认不会修改安装包的功能 Gradle2.4 自动多渠道打包配置channels.properties文件 #默认渠道 </p>
     */

    private ExcerptBean excerpt;
    private int author;
    private int featured_media;
    private String comment_status;
    private String ping_status;
    private boolean sticky;
    private String format;

    public static class GuidBean {
        private String rendered;

        public String getRendered() {
            return rendered;
        }

        public void setRendered(String rendered) {
            this.rendered = rendered;
        }
    }

    public static class TitleBean {
        private String rendered;

        public String getRendered() {
            return rendered;
        }

        public void setRendered(String rendered) {
            this.rendered = rendered;
        }
    }

    public static class ContentBean {
        private String rendered;

        public String getRendered() {
            return rendered;
        }

        public void setRendered(String rendered) {
            this.rendered = rendered;
        }
    }

    public static class ExcerptBean {
        private String rendered;

        public String getRendered() {
            return rendered;
        }

        public void setRendered(String rendered) {
            this.rendered = rendered;
        }
    }

    @Override
    public String toString() {
        return this.content.rendered;
    }
}
