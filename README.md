-# SimpleWidget
\ No newline at end of file
#SimpleWidget
SimpleWidget library for Android.

## Key Feature
Easy to customize the widget color

## What's in SimpleWidget
* SimpleProgressBar

## installation
* Maven
```
<dependency>
  <groupId>com.mintminter</groupId>
  <artifactId>simplewidget</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```


* Gradle
```
compile 'com.mintminter:simplewidget:1.0.0'
```

## SimpleProgressBar:
Colorful horizontal progress bar which you can customize the foreground color and background color and the gap size.

### Usage
Define 'app' namespace on root view in your layout
```
xmlns:app="http://schemas.android.com/apk/res-auto"
```
Using the SimpleProgressBar in your layout (with or without gap)
```
<com.mintminter.simplewidget.SimpleProgressBar
    android:id="@id/simpleprobressbar"
    android:layout_width="match_parent"
    android:layout_height="20dp"
    android:layout_marginTop="5dp"
    app:spb_scale="20"
    app:spb_progress="0.7"
    app:spb_gap="0dp"
    app:spb_foreground_color="@color/colorAccent"
    app:spb_background_color="@color/colorPrimary"/>
    
<com.mintminter.simplewidget.SimpleProgressBar
    android:id="@id/simpleprobressbar_with_gap"
    android:layout_width="match_parent"
    android:layout_height="20dp"
    android:layout_marginTop="5dp"
    app:spb_scale="20"
    app:spb_progress="0.7"
    app:spb_gap="2dp"
    app:spb_foreground_color="@color/colorAccent"
    app:spb_background_color="@color/colorPrimary"/>
```
Set progress in your Activity
```
SimpleProgressBar progressBar = (SimpleProgressBar) findViewById(R.id.simpleprobressbar);
progressBar.setProgress(0.6f);

SimpleProgressBar progressBarWithGap = (SimpleProgressBar) findViewById(R.id.simpleprobressbar_with_gap);
progressBarWithGap.setProgress(0.4f);
```
They will be looked like
<img src="https://github.com/IreneXY/SimpleWidget/raw/master/screenshot/simple_progress_bar.png" width="300">





