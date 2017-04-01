# SimpleWidget

[![Maven Central](https://img.shields.io/badge/maven%20central-1.2.1-brightgreen.svg)](http://search.maven.org/#artifactdetails%7Ccom.mintminter%7Csimplewidget%7C1.0.0%7Caar) [![jcenter](https://img.shields.io/badge/jcenter-1.2.1-brightgreen.svg)](https://bintray.com/irenexy/SimpleWidget/simplewidget) [![Hex.pm](https://img.shields.io/hexpm/l/plug.svg?maxAge=2592000)](https://github.com/IreneXY/SimpleWidget/blob/master/LICENSE)

SimpleWidget library for Android.

## Key Feature
Easy to customize the widget style

## What's in SimpleWidget
* SimpleProgressBar
* SimpleButton

## Installation
* Maven
```
<dependency>
  <groupId>com.mintminter</groupId>
  <artifactId>simplewidget</artifactId>
  <version>1.2.1</version>
  <type>pom</type>
</dependency>
```

* Gradle
```
compile 'com.mintminter:simplewidget:1.2.1'
```

## 1. SimpleProgressBar:
Colorful horizontal progress bar which you can customize the foreground color, the background color, the shadow color and the corner size.

### Usage
Define 'app' namespace on the root view in your layout
```
xmlns:app="http://schemas.android.com/apk/res-auto"
```
Using the SimpleProgressBar in your layout 
```
<com.mintminter.simplewidget.SimpleProgressBar
        android:layout_marginTop="20dp"
        android:id="@+id/simpleprogressbar1"
        android:layout_width="match_parent"
        android:layout_height="32dp"
        app:spb_foreground_color="@color/purple500"
        app:spb_background_color="@color/purple100"
        app:spb_progress="0.3"
        app:spb_corner="7dp"
        app:spb_shadow_border_width="8dp"
        app:spb_shadow_corner="16dp"
        app:spb_shadow_color="@color/grey300"/>
        
```
Set progress and get progress in your Activity
```
SimpleProgressBar progressBar = (SimpleProgressBar) findViewById(R.id.simpleprobressbar);
progressBar.setProgress(0.6f);

float progress = progressBar.getProgress();

```
They will be looked like

<img src="https://github.com/IreneXY/SimpleWidget/raw/master/screenshot/SimpleProgressBar-1.1.3.gif">

## 2. SimpleButton:
A button widget which is easy to set button style and icon.

### Usage
Define 'app' namespace on the root view in your layout
```
xmlns:app="http://schemas.android.com/apk/res-auto"
```
Using the SimpleButton in your layout 
```
<!-- only icon on the button -->
<com.mintminter.simplewidget.SimpleButton
  android:layout_width="0dp"
  android:layout_weight="1"
  android:layout_height="50dp"
  android:layout_marginRight="10dp"
  android:background="@drawable/button_green"
  app:sb_icon="@mipmap/ic_button"
  app:sb_icon_size="36dp"
  app:sb_icon_tint="@color/white"/>
  
<!-- icon and text on the button -->
<com.mintminter.simplewidget.SimpleButton
  android:layout_width="0dp"
  android:layout_weight="2"
  android:layout_height="50dp"
  android:layout_marginRight="10dp"
  android:background="@color/orange600"
  app:sb_icon="@mipmap/ic_button"
  app:sb_icon_tint="@color/white"
  app:sb_text="@string/button"
  app:sb_icon_size="36dp"
  app:sb_text_appearance="@style/simplebuttonText1"/>

<!-- only text on the button -->
<com.mintminter.simplewidget.SimpleButton
  android:layout_width="0dp"
  android:layout_weight="3"
  android:layout_height="50dp"
  android:background="@drawable/button_green"
  app:sb_text="Button Without Icon"
  app:sb_text_appearance="@style/simplebuttonText1"/>
        
```
Set/Change button's text in your Activity
```
SimpleButton simpleButton = (SimpleButton) findViewById(R.id.simplebutton);
simpleButton.setText("Set Text Programmably");
```
They will be looked like

<img src="https://github.com/IreneXY/SimpleWidget/raw/master/screenshot/SimpleButton-1.2.1.png">

## Demo
https://raw.githubusercontent.com/IreneXY/SimpleWidget/master/apk/demo-1.2.1.apk

## Licence

Copyright 2016 Irene Yu

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this work except in compliance with the License. You may obtain a copy of the License in the LICENSE file, or at:

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.





